package app.controllers;

import app.entities.Comment;
import app.entities.Post;
import app.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(path = "/posts")
public class PostsController {
    private final PostService postService;

    @Autowired
    public PostsController(PostService postService) {
        this.postService = postService;
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public String getPostPage(@PathVariable Long id, ModelMap modelMap) {
        final Optional<Post> post = postService.findPost(id);
        modelMap.put("post", post.get());
        final List<Comment> comments = post.get().getComments();
        modelMap.put("comments", comments);
        return "post";
    }
}
