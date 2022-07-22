package tn.esprit.asi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.asi.entities.User;
import tn.esprit.asi.reposetories.UserRepo;
import tn.esprit.asi.security.UserPrincipal;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepo userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {

        User user = userRepository.FindUserByLogin(login);
        if (user == null) throw new UsernameNotFoundException("User not found with login : " + login);
        return UserPrincipal.create(user);
    }

    @Transactional
    public UserDetails loadUserById(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) throw new UsernameNotFoundException("User not found with id : " + id);
        return UserPrincipal.create(user);
    }
}
