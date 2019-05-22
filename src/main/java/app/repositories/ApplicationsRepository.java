package app.repositories;

import app.entities.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ApplicationsRepository extends JpaRepository<Application, Long> {
    @Query("FROM Application a WHERE a.profile.id = :profileId AND a.job.id = :jobId ")
    Optional<Application> findByProfileIdAndJobId(@Param("profileId") Long profileId,
                                                  @Param("jobId") Long jobId);
}
