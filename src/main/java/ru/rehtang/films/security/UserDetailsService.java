package ru.rehtang.films.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.rehtang.films.mapper.JwtUserMapper;
import ru.rehtang.films.service.UserService;

@Service
@RequiredArgsConstructor
public class UserDetailsService
    implements org.springframework.security.core.userdetails.UserDetailsService {

  private final UserService userService;
  private final JwtUserMapper jwtUserMapper;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return jwtUserMapper.toUserDetails(userService.findByUsername(username));
  }
}