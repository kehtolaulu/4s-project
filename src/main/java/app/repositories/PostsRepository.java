package app.repositories;

import app.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostsRepository extends JpaRepository<Post, Long> {
    @Query("SELECT a FROM Post a ORDER BY publishedAt DESC")
    List<Post> findAllOrderByPublishedAtDesc();
}
