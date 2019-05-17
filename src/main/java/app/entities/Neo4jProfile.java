package app.entities;

import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.LinkedList;
import java.util.List;

@NodeEntity(label = "Profile")
public class Neo4jProfile {
    @Id
    private Long profileId;

    @Relationship(type = "KNOWS")
    List<Neo4jProfile> friends = new LinkedList<>();
}
