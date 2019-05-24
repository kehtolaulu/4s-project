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

    @Query(
            nativeQuery = true,
            value = "select * " +
                    "FROM account p " +
                    " INNER JOIN (select profile_id AS id " +
                    " from profile_skills " +
                    " WHERE skill_id IN :skills " +
                    " group by profile_id " +
                    " HAVING (COUNT(profile_id) >= :match)) AS s USING (id);"
    )
    List<Profile> getPeopleBySkills(@Param("skills") List<Long> skills, @Param("match") int match);
}
