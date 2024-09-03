package raf.diplomski.mmgcritic.filters.principal;

import lombok.Getter;

import java.security.Principal;

@Getter
public class CustomUserPrincipal implements Principal {
    private Long userId;
    private String email;

    public CustomUserPrincipal(Long userId, String email) {
        this.userId = userId;
        this.email = email;
    }

    @Override
    public String getName() {
        return email;
    }

}
