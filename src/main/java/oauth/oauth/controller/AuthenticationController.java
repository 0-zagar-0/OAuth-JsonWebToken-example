package oauth.oauth.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import oauth.oauth.dto.UserLoginRequestDto;
import oauth.oauth.dto.UserRegisterRequestDto;
import oauth.oauth.dto.UserResponseDto;
import oauth.oauth.security.AuthenticationService;
import oauth.oauth.service.UserService;

@Controller
@RequestMapping(value = "/auth")
public class AuthenticationController {
    private final UserService userService;
    private final AuthenticationService authenticationService;
    private final OtherController otherController;

    public AuthenticationController(
            UserService userService, AuthenticationService authenticationService, OtherController helloController) {
        this.userService = userService;
        this.authenticationService = authenticationService;
        this.otherController = helloController;
    }

    @PostMapping(value = "/register")
    @ResponseBody
    public UserResponseDto register(@Valid @RequestBody UserRegisterRequestDto request) {
        return userService.register(request);
    }

    @PostMapping(value = "/login")
    @ResponseBody
    public String login(@RequestBody UserLoginRequestDto requestDto) {
        return authenticationService.authenticate(requestDto);
    }

    @GetMapping(value = "/registration")
    public String registration(Model model) {
        model.addAttribute("user", new UserRegisterRequestDto());
        return "registration";
    }

    @PostMapping(value = "/registration")
    public String registration(@ModelAttribute("user") @Valid UserRegisterRequestDto userDto) {
        userService.register(userDto);
        return "login";
    }

    @GetMapping(value = "/loginIn")
    public String login(Model model) {
        model.addAttribute("user", new UserLoginRequestDto());
        return "login";
    }

    @PostMapping(value = "/loginIn")
    public String loginUser(
            @ModelAttribute("user") UserLoginRequestDto userDto, HttpServletResponse response
    ) {
        String token = authenticationService.authenticate(userDto);
        Cookie cookie = new Cookie("abracadabra", token);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        response.addCookie(cookie);
        return otherController.start();
    }
}
