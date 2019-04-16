package app.controllers;

import app.entities.Post;
import app.repositories.PostsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping(path = "/home")
public class HomeController {
    private final PostsRepository postsRepository;

    @Autowired
    public HomeController(PostsRepository postsRepository) {
        this.postsRepository = postsRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getHomePage(ModelMap modelMap) {
        final List<Post> posts = postsRepository.findAll();
        modelMap.put("posts", posts);
        return "home";
    }
}
