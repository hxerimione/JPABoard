package spring.board.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import spring.board.domain.Post;
import spring.board.domain.User;
import spring.board.dto.PostDto;
import spring.board.service.PostService;
import spring.board.service.UserService;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    private final UserService userService;
    @GetMapping("/posts/new")
    public String createPost(Model model, Principal principal){
        User user = userService.findByUsername("kakao_"+principal.getName());
        System.out.println("세션:"+user.getUsername());
        model.addAttribute(user);

        model.addAttribute("post",new Post());
        return "posts/createPostForm";
    }
    @PostMapping("/posts/new")
    public String create(@Valid PostForm form,
                         @RequestParam("userId") Long id,
                         BindingResult result){
        if(result.hasErrors()){
            System.out.println("sad");
            return "posts/createPostForm";
        }
        System.out.println("formname"+id);
        Post post = new Post();
        post.setTitle(form.getTitle());
        post.setContent(form.getContent());

        post.setUser(userService.findById(id).get());
        //PostDto postDto = new PostDto(post);
        postService.savePost(post);

        return "redirect:/posts";
    }

    @GetMapping("/posts")
    public String list(Model model){
        List<Post> posts = postService.findAll();
        model.addAttribute("posts",posts);
        return "posts/postList";
    }

    @GetMapping("/posts/{id}")
    public String read(@PathVariable Long id, Model model){
        Post post = postService.findById(id);
        String username = post.getUser().getUsername();
        System.out.println("이름"+post.getUser().getUsername());
        model.addAttribute("username",username);
        model.addAttribute(post);
        return "posts/postView";
    }
}
