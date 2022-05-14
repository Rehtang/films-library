package ru.rehtang.films.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import ru.rehtang.films.persistence.entity.RoleEntity;
import ru.rehtang.films.persistence.entity.User;
import ru.rehtang.films.security.UserDetails;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(
    componentModel = "spring",
    imports = {User.Status.class})
public abstract class JwtUserMapper {

  @Mapping(target = "authorities", expression = "java(mapToGrantedAuthority(user.getRoles()))")
  @Mapping(target = "isEnabled", expression = "java(Status.ACTIVE.equals(user.getStatus()))")
  public abstract UserDetails toUserDetails(User user);

  protected static List<GrantedAuthority> mapToGrantedAuthority(Set<RoleEntity> userRoleEntities) {
    return userRoleEntities.stream()
        .map(role -> new SimpleGrantedAuthority(role.getName().name()))
        .collect(Collectors.toList());
  }
}