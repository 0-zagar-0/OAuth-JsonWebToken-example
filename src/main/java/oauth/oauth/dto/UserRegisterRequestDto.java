package oauth.oauth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import oauth.oauth.validation.FieldMatcher;
import oauth.oauth.validation.Password;

@Getter
@Setter
@FieldMatcher(field = "password", fieldMatch = "repeatPassword")
public class UserRegisterRequestDto {
    @Email
    @Size(min = 8, max = 30)
    @NotBlank
    private String email;
    @Size(min = 8, max = 30)
    @NotBlank
    @Password
    private String password;
    @Size(min = 8, max = 30)
    @NotBlank
    @Password
    private String repeatPassword;
    @Size(min = 3, max = 30)
    @NotBlank
    private String firstName;
    @Size(min = 3, max = 30)
    @NotBlank
    private String lastName;
}
