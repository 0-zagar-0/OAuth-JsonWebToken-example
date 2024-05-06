package oauth.oauth.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import oauth.oauth.dto.UserLoginRequestDto;

@Service
public class AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public AuthenticationService(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    public String authenticate(UserLoginRequestDto requestDto) {
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        requestDto.getEmail(), requestDto.getPassword()
                )
        );

        return jwtUtil.generateToken(authenticate.getName());
    }
}
