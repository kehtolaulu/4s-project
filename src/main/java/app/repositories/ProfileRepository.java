package app.repositories;

import app.entities.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {
    List<Profile> getAllByFirstNameAndLastName(String firstName, String lastName);
    @Query(nativeQuery = true, value = "WITH selected AS (SELECT DISTINCT profile_id FROM profile_skills WHERE skill_id IN (:skills)) SELECT * FROM account" +
            " INNER JOIN selected ON account.id = selected.profile_id;")
    List<Profile> getPeopleBySkills(@Param("skills") List<Long> skills);
}
