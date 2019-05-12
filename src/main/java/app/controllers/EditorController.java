package app.controllers;

import app.entities.Post;
import app.services.AccountDetails;
import app.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(path = "/editor")
public class EditorController {
    private final PostService postService;

    @Autowired
    public EditorController(PostService postService) {
        this.postService = postService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getEditorPage() {
        return "editor";
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String addPost(Post post, @AuthenticationPrincipal AccountDetails account) {
        postService.create(post, account.getUser());
        return "redirect:/home";
    }
}
