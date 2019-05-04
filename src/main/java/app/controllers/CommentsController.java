package app.controllers;

import app.entities.Comment;
import app.entities.Post;
import app.repositories.CommentsRepository;
import app.repositories.PostsRepository;
import app.services.AccountDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.TreeMap;

@Controller
@RequestMapping(path = "/posts/{id}")
public class CommentsController {
    private final PostsRepository postsRepository;
    private final CommentsRepository commentsRepository;

    @Autowired
    public CommentsController(PostsRepository postsRepository, CommentsRepository commentsRepository) {
        this.postsRepository = postsRepository;
        this.commentsRepository = commentsRepository;
    }

    @PostMapping(path = "/comments", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map getComment(@RequestParam("text") String content, @AuthenticationPrincipal AccountDetails user, @PathVariable Long id) {
        Comment comment = new Comment();
        comment.setAuthor(user.getUser());
        comment.setContent(content);
        comment.setPublishedAt(LocalDateTime.now());
        final Post post = postsRepository.findById(id).get();
        comment.setPost(post);
        commentsRepository.save(comment);
        post.getComments().add(comment);
        postsRepository.save(post);
        return new TreeMap() {{
            put("id", comment.getId());
            put("content", comment.getContent());
            put("publishedAt", comment.getPublishedAt());
            put("author", new TreeMap() {{ put("name", comment.getAuthor().getName()); }});
        }};
    }
}
