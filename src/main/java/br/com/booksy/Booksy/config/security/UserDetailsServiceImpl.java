package br.com.booksy.Booksy.config.security;

import br.com.booksy.Booksy.exception.CommonException;
import br.com.booksy.Booksy.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws CommonException {
        return userRepository.findByEmail(email)
                .map(UserDetailsImpl::new)
                .orElseThrow(() -> new CommonException(HttpStatus.NOT_FOUND, "booksy.security.loadUserByUsername.notFound", "User not found"));
    }
}
