package app.repositories.neo4j;

import app.entities.Neo4jProfile;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.stream.Stream;

public interface Neo4jProfileRepository extends Neo4jRepository<Neo4jProfile, Long> {
    @Query("MATCH (a:Profile {profileId: {id1}}) MATCH (b:Profile {profileId: {id2}}) CREATE (a)-[:KNOWS]->(b)")
    void createLink(@Param("id1") long profileId1, @Param("id2") long profileId2);

    @Query("MATCH (p:Profile {profileId: {id}})-[k:KNOWS]->(f:Profile) RETURN p, k, f;")
    Optional<Neo4jProfile> findByProfileId(@Param("id") Long profileId);

    @Query("CREATE (p:Profile {profileId: {id}}) RETURN p;")
    void createProfile(@Param("id") long profileId);

    @Query("MATCH (p:Profile {profileId: {id}})-[k:KNOWS]->(f) RETURN p, k, f;")
    Stream<Neo4jProfile> findCircleWithDepth1(@Param("id") long profileId);

    @Query("MATCH (p:Profile {profileId: {id}})-[k:KNOWS*1..2]->(f) RETURN p, k, f;")
    Stream<Neo4jProfile> findCircleWithDepth2(@Param("id") long profileId);

    @Query("MATCH (p:Profile {profileId: {id}})-[k:KNOWS*1..3]->(f) RETURN p, k, f;")
    Stream<Neo4jProfile> findCircleWithDepth3(@Param("id") long profileId);

    @Query("MATCH (p:Profile {profileId: {id}}) RETURN exists(p);")
    boolean existsByProfileId(@Param("id") Long profileId);
}
