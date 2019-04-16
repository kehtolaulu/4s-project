package app.controllers;

import app.entities.Account;
import app.entities.Post;
import app.repositories.PostsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(path = "/editor")
public class EditorController {

    private final PostsRepository postsRepository;

    @Autowired
    public EditorController(PostsRepository postsRepository) {
        this.postsRepository = postsRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getEditorPage() {
        return "editor";
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String addPost(Post post) {
        postsRepository.save(post);
        return "redirect:/home";
    }
}
