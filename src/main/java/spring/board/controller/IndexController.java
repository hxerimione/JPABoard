package spring.board.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import spring.board.domain.Post;
import spring.board.service.PostService;

import java.util.List;

@RequiredArgsConstructor
@Controller

public class IndexController {
    private final PostService postService;
    @GetMapping("/")
    public String index(Model model) {
        List<Post> posts = postService.findAll();
        model.addAttribute("posts",posts);
        return "posts/postList";
    }
}
