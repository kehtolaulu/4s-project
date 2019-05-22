package app.services;

import app.entities.Neo4jProfile;
import app.entities.Profile;
import app.repositories.ProfileRepository;
import app.repositories.neo4j.Neo4jProfileRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ContactService {
    private final Neo4jProfileRepository neo4jProfileRepository;
    private final ProfileRepository profileRepository;

    public ContactService(Neo4jProfileRepository neo4jProfileRepository,
                          ProfileRepository profileRepository) {
        this.neo4jProfileRepository = neo4jProfileRepository;
        this.profileRepository = profileRepository;
    }

    public Consumer<Profile> addContact(Profile profile1) {
        return profile2 -> {
            // ensure that profiles exist in graph
            for (Profile p : Arrays.asList(profile1, profile2))
                if (!neo4jProfileRepository.existsByProfileId(p.getId())) {
                    neo4jProfileRepository.save(new Neo4jProfile(p.getId()));
                }
            // create relation p1 -- :KNOWS -> p2
            neo4jProfileRepository.createLink(profile1.getId(), profile2.getId());
        };
    }

    public Function<Integer, List<Profile>> circleOfProfile(long profileId) {
        return depth -> {
            final Stream<Neo4jProfile> circle;
            if (depth == 1) {
                circle = neo4jProfileRepository.findCircleWithDepth1(profileId);
            } else if (depth == 2) {
                circle = neo4jProfileRepository.findCircleWithDepth2(profileId);
            } else {
                circle = neo4jProfileRepository.findCircleWithDepth3(profileId);
            }
            final Collection<Long> ids = circle.map(Neo4jProfile::getProfileId).collect(Collectors.toList());
            return profileRepository.findAllById(ids);
        };
    }
}
