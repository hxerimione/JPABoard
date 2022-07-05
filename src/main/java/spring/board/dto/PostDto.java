package spring.board.dto;

import lombok.Getter;
import lombok.Setter;
import spring.board.domain.Post;

import java.time.LocalDateTime;

@Getter
public class PostDto {
    private Long id;
    private String title;
    private String content;
    private String username;
    private LocalDateTime createdTime;

    public PostDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.username = post.getUser().getUsername();
        this.createdTime = post.getCreatedDate();
    }
}
