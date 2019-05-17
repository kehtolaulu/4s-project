package app.repositories.neo4j;

import app.entities.Neo4jProfile;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

public interface Neo4jProfileRepository extends Neo4jRepository<Neo4jProfile, Long> {
    @Query("MATCH (a:Profile {profileId: {id1}}) MATCH (b:Profile {profileId: {id2}}) CREATE (a)-[:KNOWS]->(b)")
    void createLink(@Param("id1") long profileId1, @Param("id2") long profileId2);

    @Query("CREATE (p:Profile {profileId: {id}}) RETURN p;")
    void createProfile(@Param("id") long profileId);

    @Query("MATCH (p:Profile {profileId: {id}})-[r1]->(p1)-[r2]->(p2) RETURN p, r1, p1, r2, p2;")
    Neo4jProfile findWithCircle(@Param("id") long profileId);
}
