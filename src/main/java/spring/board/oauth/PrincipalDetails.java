package spring.board.oauth;

import lombok.Getter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.transaction.annotation.Transactional;
import spring.board.domain.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
@Getter
public class PrincipalDetails implements OAuth2User {

    private User user;
    private Oauth2UserInfo oAuth2UserInfo;


    public PrincipalDetails(User user, Oauth2UserInfo oAuth2UserInfo) {
        this.user = user;
        this.oAuth2UserInfo = oAuth2UserInfo;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collect = new ArrayList<>();
        collect.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return user.getRole().toString();
            }
        });
        return collect;
    }


    @Override
    public Map<String, Object> getAttributes() {
        return oAuth2UserInfo.getAttributes();
    }


    @Override
    public String getName() {
        return oAuth2UserInfo.getProviderId();
    }
}
