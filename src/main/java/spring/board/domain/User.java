package spring.board.domain;

import lombok.*;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Getter
@Entity
@NoArgsConstructor
@Table
public class User extends BaseTimeEntity {
    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String email;

    @Column
    private String picture;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    private String provider;    // oauth2를 이용할 경우 어떤 플랫폼을 이용하는지
    private String providerId;  // oauth2를 이용할 경우 아이디값
    private String password;

    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
    private List<Post> posts = new ArrayList<>();

    public User update(String username, String picture){
        this.username = username;
        this.picture = picture;

        return this;
    }
    @Builder(builderClassName = "OAuth2Register", builderMethodName = "oauth2Register")
    public User(String username, String password, String email, Role role, String provider, String providerId) {
        this.username = username;
        this.email = email;
        this.role = role;
        this.password = password;
        this.provider = provider;
        this.providerId = providerId;
    }
}
