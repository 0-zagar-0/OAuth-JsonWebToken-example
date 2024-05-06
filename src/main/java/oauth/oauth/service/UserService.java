package oauth.oauth.service;

import oauth.oauth.dto.UserRegisterRequestDto;
import oauth.oauth.dto.UserResponseDto;

public interface UserService {
    UserResponseDto register(UserRegisterRequestDto request);
}
