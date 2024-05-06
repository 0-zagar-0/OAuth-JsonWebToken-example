package oauth.oauth.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import oauth.oauth.config.MapperConfig;
import oauth.oauth.dto.UserRegisterRequestDto;
import oauth.oauth.dto.UserResponseDto;
import oauth.oauth.entity.User;

@Mapper(config = MapperConfig.class)
public interface UserMapper {
    @Mapping(target = "id", ignore = true)
    User toEntity(UserRegisterRequestDto requestDto);

    UserResponseDto toDto(User user);
}
