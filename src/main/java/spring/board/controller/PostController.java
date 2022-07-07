package spring.board.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import spring.board.domain.Post;
import spring.board.domain.User;
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
                         Principal principal,
                         BindingResult result){
        if(result.hasErrors()){
            return "posts/createPostForm";
        }
        Post post = new Post();
        post.setTitle(form.getTitle());
        post.setContent(form.getContent());

        post.setUser(userService.findByUsername("kakao_"+principal.getName()));
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
        //System.out.println("이름"+post.getUser().getUsername());
        model.addAttribute("username",username);
        model.addAttribute(post);
        return "posts/postView";
    }
    @GetMapping("/update/{id}")
    public String update(@PathVariable Long id, Model model){
        Post post = postService.findById(id);
        String username = post.getUser().getUsername();
        model.addAttribute("username",username);
        model.addAttribute(post);
        return "posts/postUpdate";

    }
    @PostMapping("/update/{id}")
    public String update(@PathVariable Long id,@Valid PostForm form,
                Principal principal,
                BindingResult result){
            if(result.hasErrors()){
                return "/update/{id}";
            }
            Post post =postService.findById(id);
            post.setTitle(form.getTitle());
            post.setContent(form.getContent());

            //PostDto postDto = new PostDto(post);
            postService.update(post);

            return "redirect:/posts";
    }
    @DeleteMapping("/posts/{id}")
    public String delete(@PathVariable Long id){
        System.out.println("뭐가문제야");
        postService.deleteById(id);

        return "redirect:/";
    }

}

