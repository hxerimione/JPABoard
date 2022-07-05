package spring.board.controller;

import lombok.Getter;
import lombok.Setter;
import spring.board.domain.User;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class PostForm {
    @NotEmpty(message = "제목은 필수 입니다")
    private String title;
    private String content;
    private User user;
}
