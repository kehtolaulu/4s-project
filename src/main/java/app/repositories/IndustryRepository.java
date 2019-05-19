package app.repositories;

import app.entities.Industry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface IndustryRepository extends JpaRepository<Industry, Long> {
    @Query("SELECT a FROM Industry a WHERE a.name = :name")
    Optional<Industry> findByName(@Param("name") String name);
}
