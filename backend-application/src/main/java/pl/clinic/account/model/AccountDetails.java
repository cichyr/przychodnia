package pl.clinic.account.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AccountDetails implements UserDetails {

    private final Account account;
    private final Role role;

    private AccountDetails(AccountDetails.Builder builder) {
        this.account = builder.account;
        this.role = builder.role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Stream
                .of((GrantedAuthority) () -> role.name)
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return account.getHash();
    }

    @Override
    public String getUsername() {
        return account.getUsername();
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

    public Long getId() {
        return account.getEmployeeId();
    }

    public Role getRole() {
        return role;
    }

    public AccountStatus getStatus() {
        return account.getStatus();
    }

    public static class Builder {
        private Account account;
        private Role role;

        public AccountDetails.Builder account(Account account) {
            this.account = account;
            return this;
        }

        public AccountDetails.Builder role(Role role) {
            this.role = role;
            return this;
        }

        public AccountDetails build() {
            return new AccountDetails(this);
        }
    }
}
