package org.messenger.model;
import org.springframework.stereotype.Component;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Component

public class RegistrationDTO {
    @NotEmpty
    private String name;
    @NotEmpty
    private String password;
    @NotEmpty
    private String password2;

   @NotEmpty
    private String email;
   @NotEmpty
    private String dateOfBirth;
   @NotEmpty
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }
}
