package app.services;

import app.entities.Account;
import app.entities.Comment;
import app.entities.Post;
import app.exceptions.PostNotFoundException;
import app.repositories.CommentsRepository;
import app.repositories.PostsRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    private final PostsRepository postsRepository;
    private final CommentsRepository commentsRepository;

    public PostService(PostsRepository postsRepository, CommentsRepository commentsRepository) {
        this.postsRepository = postsRepository;
        this.commentsRepository = commentsRepository;
    }

    public Optional<Post> findPost(Long id) {
        return postsRepository.findById(id);
    }

    public Comment createComment(String content, Account author, Long postId) throws PostNotFoundException {
        Comment comment = new Comment();
        comment.setAuthor(author);
        comment.setContent(content);
        comment.setPublishedAt(LocalDateTime.now());

        Optional<Post> post = postsRepository.findById(postId);
        comment.setPost(post.orElseThrow(PostNotFoundException::new));

        commentsRepository.save(comment);

        post.get().getComments().add(comment);
        postsRepository.save(post.get());

        return comment;
    }

    public void create(Post post, Account author) {
        post.setAuthor(author);
        post.setPublishedAt(LocalDateTime.now());
        postsRepository.save(post);
    }

    public List<Post> all() {
        return postsRepository.findAllOrderByPublishedAtDesc();
    }
}
