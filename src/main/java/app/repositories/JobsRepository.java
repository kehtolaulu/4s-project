package app.repositories;

import app.entities.Industry;
import app.entities.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JobsRepository extends JpaRepository<Job, Long> {
    @Query("SELECT a FROM Job a ORDER BY publishedAt DESC")
    List<Job> findAllOrderByPublishedAtDesc();
    List<Job> findAllByPositionAndIndustryAndEmploymentType(String position, Industry industry, String employmentType);
}
