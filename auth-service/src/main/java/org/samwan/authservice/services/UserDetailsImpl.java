package org.samwan.authservice.services;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.samwan.authservice.models.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;
import java.util.stream.Collectors;

public class UserDetailsImpl implements UserDetails {

    private Long id;
    private String email;
    @JsonIgnore
    private String password;
    private Collection<? extends  GrantedAuthority> grantedAuthorities;

    public UserDetailsImpl(Long id, String email, String password, Collection<? extends GrantedAuthority> grantedAuthorities) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.grantedAuthorities = grantedAuthorities;
    }

    public static UserDetailsImpl build(User user){
        Set<GrantedAuthority> grantedAuthorities = user.getRoleSet().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toSet());

        return new UserDetailsImpl(
                user.getId(),
                user.getEmail(),
                user.getPassword(),
                grantedAuthorities
        );
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;
        if(obj == null || getClass() != obj.getClass())
            return false;

        UserDetailsImpl userDetails = (UserDetailsImpl)obj;
        return Objects.equals(id,userDetails.id);
    }
}
