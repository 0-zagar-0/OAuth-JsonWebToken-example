package oauth.oauth.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OtherController {
    @GetMapping(value = "/home")
    public String index() {
        return "home";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(value = "/admin")
    public String helloAdmin() {
        return "admin";
    }

    @PreAuthorize("hasRole('ROLE_USER') || hasAnyAuthority('OIDC_USER', 'OAUTH2_USER')")
    @GetMapping(value = "/user")
    public String helloUser() {
        return "user";
    }

    @GetMapping(value = "/start")
    public String start() {
        return "start";
    }
}
