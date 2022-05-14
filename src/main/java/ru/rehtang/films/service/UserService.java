package ru.rehtang.films.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.rehtang.films.dto.UserDto;
import ru.rehtang.films.mapper.UserMapper;
import ru.rehtang.films.persistence.entity.User;
import ru.rehtang.films.persistence.repository.UserRepository;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;
  private final UserMapper userMapper;

  public User findByUsername(String username) {
    return userRepository
        .findUserByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException("No user found by username: " + username));
  }

  public void register(UserDto userDto) {
    userRepository.save(userMapper.register(userDto));
  }

  public List<User> findAll() {
    return userRepository.findAll();
  }

  public User findById(UUID id) {
    return userRepository
        .findById(id)
        .orElseThrow(() -> new UsernameNotFoundException("No user found by id: " + id));
  }

  public UserDto getUserById(UUID uuid) {
    return userMapper.toDto(findById(uuid));
  }

  public void editUserData(UserDto dto) {
    User user = findById(dto.getUuid());
    userRepository.save(userMapper.edit(user, dto));
  }

  public void deleteById(UUID id) {
    var entity =
        userRepository
            .findById(id)
            .orElseThrow(() -> new UsernameNotFoundException("No user found by id: " + id));
    userRepository.delete(entity);
  }
}