package in.codelantern.cmrvideostreaming.models;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    @NotNull
    @Size(min = 5, max = 20 , message = "full name size should be in between 5 to 20 characters")
    private String fullName;

    @NotNull
    @NotBlank
    @Email(message = "Enter a valid email address")
    private String email;

    @Min(value = 18)
    @Max(value = 70)
    private int age;

    @Size(min = 8, max = 30, message = "password size should be 8 to 30 in length")
    @Pattern(regexp = "^(?=(.*[a-z]){1,})(?=(.*[A-Z]){1,})(?=(.*[0-9]){1,})(?=(.*[!@#$%^&*()\\-__+.]){1,}).{1,}$",
            message = "password should contain at least 1 smallCase, upperCase, special character and a number"
    )
    private String password;

    private boolean isVerified = false;

}
