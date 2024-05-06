package oauth.oauth.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import oauth.oauth.dto.UserRegisterRequestDto;
import oauth.oauth.dto.UserResponseDto;
import oauth.oauth.entity.User;
import oauth.oauth.exception.RegistrationException;
import oauth.oauth.mapper.UserMapper;
import oauth.oauth.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(
            UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserResponseDto register(UserRegisterRequestDto request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RegistrationException("Can't continue registration!");
        }

        User user = userMapper.toEntity(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        return userMapper.toDto(userRepository.save(user));
    }
}
