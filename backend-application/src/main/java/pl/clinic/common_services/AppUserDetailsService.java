package pl.clinic.common_services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.clinic.account.model.*;

import java.util.logging.Logger;

@Service
public class AppUserDetailsService implements UserDetailsService {

    private static final Logger log = Logger.getLogger(UserDetailsService.class.getName());

    @Autowired
    private AccountRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;


    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username) {
        Account account = userRepository.findAppUserByUsername(username);

        if (account == null)
            throw new UsernameNotFoundException(username);

        Role role = roleRepository.findById(account.getRoleId()).orElseThrow(() -> new UsernameNotFoundException(username));

        log.info("Found user: " + username);

        if (account.getStatus() == AccountStatus.DISABLED)
            throw new AccessDeniedException("Account is disabled");

        return new AccountDetails.Builder()
                .account(account)
                .role(role)
                .build();
    }
}
