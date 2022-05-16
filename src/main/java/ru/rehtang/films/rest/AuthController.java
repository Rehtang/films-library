package ru.rehtang.films.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.rehtang.films.dto.AuthRequestDto;
import ru.rehtang.films.dto.JwtResponse;
import ru.rehtang.films.security.UserDetails;
import ru.rehtang.films.security.jwt.JwtUtils;

import java.util.List;
import java.util.stream.Collectors;

@Validated
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

  private final AuthenticationManager authenticationManager;
  private final JwtUtils jwtUtils;

  @PostMapping("/login")
  public ResponseEntity<?> authenticate(@RequestBody AuthRequestDto loginRequest) {
    Authentication authentication =
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(), loginRequest.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = jwtUtils.generateJwtToken(authentication);

    UserDetails userDetails = (UserDetails) authentication.getPrincipal();
    List<String> roles =
        userDetails.getAuthorities().stream()
            .map(GrantedAuthority::getAuthority)
            .collect(Collectors.toList());

    return ResponseEntity.ok(
        new JwtResponse()
            .setUuid(userDetails.getUuid())
            .setToken(jwt)
            .setUsername(userDetails.getUsername())
            .setRoles(roles));
  }
}