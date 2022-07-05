package spring.board.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {
    //시큐리 권한코드에는 항상 ROLE_이 앞에 있어야만 함
    ROLE_GUEST,
    ROLE_USER,
    ROLE_ADMIN
}
