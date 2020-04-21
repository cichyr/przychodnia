package pl.clinic.common_services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.clinic.app_user.model.AppUser;
import pl.clinic.app_user.model.AppUserDetails;
import pl.clinic.app_user.model.AppUserRepository;

import java.util.logging.Logger;

@Service
public class AppUserDetailsService implements UserDetailsService {

    private static final Logger log = Logger.getLogger(UserDetailsService.class.getName());

    @Autowired
    private AppUserRepository userRepository;


    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username) {
       AppUser user = userRepository.findAppUserByUsername(username);

        if(user == null)
            throw new UsernameNotFoundException(username);

        log.info("Found user: " + username);

        return new AppUserDetails(user);
    }
}
