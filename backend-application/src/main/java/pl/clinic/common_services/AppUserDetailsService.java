package pl.clinic.common_services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.clinic.account.model.Account;
import pl.clinic.account.model.AccountDetails;
import pl.clinic.account.model.AccountRepository;

import java.util.logging.Logger;

@Service
public class AppUserDetailsService implements UserDetailsService {

    private static final Logger log = Logger.getLogger(UserDetailsService.class.getName());

    @Autowired
    private AccountRepository userRepository;


    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username) {
       Account user = userRepository.findAppUserByUsername(username);

        if(user == null)
            throw new UsernameNotFoundException(username);

        log.info("Found user: " + username);

        return new AccountDetails(user);
    }
}
