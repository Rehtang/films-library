package ru.rehtang.films.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.util.CollectionUtils;
import ru.rehtang.films.persistence.entity.RoleEntity;
import ru.rehtang.films.persistence.entity.RoleEntity.ERole;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Mapper(
    componentModel = "spring",
    imports = {UUID.class, StandardCharsets.class})
public abstract class RoleMapper {

  public static final RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);

  @Mapping(
      target = "id",
      expression = "java(UUID.nameUUIDFromBytes(name.name().getBytes(StandardCharsets.UTF_8)))")
  @Mapping(target = "name", source = "name")
  protected abstract RoleEntity toEntity(ERole name);

  public List<String> toList(List<RoleEntity> roleEntities) {
    if (CollectionUtils.isEmpty(roleEntities)) {
      return Collections.emptyList();
    }
    return roleEntities.stream().map(o -> o.getName().name()).collect(Collectors.toList());
  }
}