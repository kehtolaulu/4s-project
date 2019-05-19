package app.controllers;

import app.entities.Comment;
import app.entities.Post;
import app.exceptions.PostNotFoundException;
import app.services.AccountDetails;
import app.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping(path = "/posts/{id}/comments")
public class CommentsController {
    private final PostService postService;

    @Autowired
    public CommentsController(PostService postService) {
        this.postService = postService;
    }

    @ResponseBody
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Map createComment(@RequestParam("text") String content,
                             @AuthenticationPrincipal AccountDetails user,
                             @PathVariable("id") Long postId) throws PostNotFoundException {

        Comment comment = postService.createComment(content, user.getUser(), postId);

        return jsonify(comment);
    }

    @ResponseBody
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Map> getComments(@PathVariable("id") Long postId) {
        List<Map> comments = postService.findPost(postId)
                .map(Post::getComments)
                .orElse(Collections.emptyList())
                .stream()
                .map(this::jsonify)
                .collect(Collectors.toList());
        return comments;
    }

    private Map jsonify(Comment comment) {
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
