package app.controllers;

import app.entities.Post;
import app.services.AccountDetails;
import app.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping(path = "/home")
public class HomeController {
    private final PostService postService;

    @Autowired
    public HomeController(PostService postService) {
        this.postService = postService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getHomePage(ModelMap modelMap, @AuthenticationPrincipal AccountDetails account) {
        final List<Post> posts = postService.all(account.getUser());
        modelMap.put("posts", posts);
        return "home";
    }
}
