package spring.board.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import spring.board.domain.Post;
import spring.board.domain.Role;
import spring.board.domain.User;
import spring.board.oauth.PrincipalDetails;
import spring.board.service.UserService;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/users/new")
    public String createForm(Model model){
        model.addAttribute("UserForm",new UserForm());
        return "users/createUserForm";
    }

    @PostMapping("/users/new")
    public String create(@Valid UserForm form, BindingResult result){
        if(result.hasErrors()){
            return "users/createUserForm";
        }
        User user =User.oauth2Register()
                .username(form.getUsername()).password(form.getPassword()).email(form.getEmail()).role(Role.ROLE_GUEST)
                .build();
        userService.join(user);
        return "redirect:/users";
    }
    @GetMapping("/users")
    public String list(Model model){
        List<User> users = userService.findAll();
        model.addAttribute("users",users);
        return "users/userList";
    }
    @GetMapping("/users/{id}")
    public String list(@PathVariable Long id, Model model){
        List<Post> posts = userService.findPosts(id);
        model.addAttribute("posts",posts);
        return "users/postList";
    }


}
