package pl.clinic.app_user.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AccountDetails implements UserDetails {

    private Account user;

    public AccountDetails(Account user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Stream
                .of((GrantedAuthority) () -> user.getRole().toString())
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return user.getHash();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
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

    public Role getRole() {
        return user.getRole();
    }

    public Long getId(){
        return user.getEmployeeId();
    }
}
