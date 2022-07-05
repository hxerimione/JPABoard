package spring.board.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter @Setter
@Entity
public class Post extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    @Column(length = 100, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    //영속성 전이를 해줘야 쿼리문에서 검색이 된다.
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private User user;



    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

}
