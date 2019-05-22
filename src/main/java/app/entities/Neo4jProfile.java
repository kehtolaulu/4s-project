package app.entities;

import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity(label = "Profile")
public class Neo4jProfile {
    @Id
    private Long profileId;

    public Neo4jProfile() {
    }

    public Neo4jProfile(Long profileId) {
        this.profileId = profileId;
    }

    public Long getProfileId() {
        return profileId;
    }

    public void setProfileId(Long profileId) {
        this.profileId = profileId;
    }
}
