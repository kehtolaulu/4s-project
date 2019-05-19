package app.repositories;

import app.entities.Application;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationsRepository extends JpaRepository<Application, Long> {
}
