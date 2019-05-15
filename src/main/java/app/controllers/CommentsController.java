package app.controllers;

import app.entities.Comment;
import app.services.AccountDetails;
import app.exceptions.PostNotFoundException;
import app.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.TreeMap;

@Controller
@RequestMapping(path = "/posts/{id}")
public class CommentsController {
    private final PostService postService;

    @Autowired
    public CommentsController(PostService postService) {
        this.postService = postService;
    }

    @ResponseBody
    @PostMapping(path = "/comments", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map createComment(@RequestParam("text") String content,
                             @AuthenticationPrincipal AccountDetails user,
                             @PathVariable("id") Long postId) throws PostNotFoundException {

        Comment comment = postService.createComment(content, user.getUser(), postId);

        return new TreeMap<String, Object>() {{
            put("id", comment.getId());
            put("content", comment.getContent());
            put("publishedAt", comment.getPublishedAt());
            put("author", new TreeMap<String, String>() {{
                put("name", comment.getAuthor().getName());
            }});
        }};
    }
}
