package app.services;

import app.entities.Neo4jProfile;
import app.entities.Profile;
import app.repositories.ProfileRepository;
import app.repositories.neo4j.Neo4jProfileRepository;
import org.springframework.stereotype.Service;

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
            neo4jProfileRepository.createLink(profile1.getId(), profile2.getId());
        };
    }

    public Function<Integer, List<Profile>> circleOfProfile(long profileId) {
        return depth -> {
            final Stream<Neo4jProfile> circle = neo4jProfileRepository.findCircleWithDepth(profileId, depth);
            final Collection<Long> ids = circle.map(Neo4jProfile::getProfileId).collect(Collectors.toList());
            return profileRepository.findAllById(ids);
        };
    }
}
