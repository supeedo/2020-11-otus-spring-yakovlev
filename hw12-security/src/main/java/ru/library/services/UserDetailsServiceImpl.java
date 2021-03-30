package ru.library.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.library.models.User;
import ru.library.repositories.UserRepositories;

import java.util.Optional;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    Logger log = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    private final UserRepositories repositories;

    public UserDetailsServiceImpl(UserRepositories repositories) {
        this.repositories = repositories;
    }

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        log.info(String.format("Name = {0}", name));
        Optional<User> user = repositories.findUserByName(name);
        return user.map(userFromDB ->
                new org.springframework.security.core.userdetails.User(userFromDB.getName(), userFromDB.getPassword(),
                        Set.of(new SimpleGrantedAuthority(userFromDB.getRoles().getRoleName()))))
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User with name: %s, not found", name)));
    }


}
