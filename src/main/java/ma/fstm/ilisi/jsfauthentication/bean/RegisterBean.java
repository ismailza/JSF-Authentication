package ma.fstm.ilisi.jsfauthentication.bean;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.ValidatorException;
import jakarta.inject.Named;
import ma.fstm.ilisi.jsfauthentication.model.User;
import ma.fstm.ilisi.jsfauthentication.service.UserService;

import java.io.Serializable;

@Named("registerBean")
@SessionScoped
public class RegisterBean implements Serializable {
    private String firstname;
    private String lastname;
    private String email;
    private String username;
    private String password;
    private String confirmPassword;

    private UserService userService;

    public RegisterBean() {
        userService = new UserService();
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    /**
     * Register a new user
     * @return the login page after registration is successful
     */
    public String register() {
        User user = new User(firstname, lastname, email, username, password);
        if (this.userService.register(user)) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Registration successful!", "You can now login"));
            return "login";
        }
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Registration failed!", "An error occurred"));
        return "register";
    }

    /**
     * Validate the email field
     * @param context the FacesContext
     * @param toValidate the UIComponent to validate
     * @param value the value of the field
     */
    public void validateEmail(FacesContext context, UIComponent toValidate, Object value) {
        String email = (String) value;
        if (!email.contains("@")) {
            FacesMessage message = new FacesMessage("Invalid email format");
            throw new ValidatorException(message);
        }

        if (userService.checkEmail(email)) {
            FacesMessage message = new FacesMessage("Email already exists");
            throw new ValidatorException(message);
        }
    }

    /**
     * Validate the username field
     * @param context the FacesContext
     * @param toValidate the UIComponent to validate
     * @param value the value of the field
     */
    public void validateUsername(FacesContext context, UIComponent toValidate, Object value) {
        String username = (String) value;
        if (username.length() < 5) {
            FacesMessage message = new FacesMessage("Username must be at least 5 characters long.");
            throw new ValidatorException(message);
        }

        if (userService.checkUsername(username)) {
            FacesMessage message = new FacesMessage("Username already exists");
            throw new ValidatorException(message);
        }
    }

    /**
     * Validate the password field
     * @param context the FacesContext
     * @param toValidate the UIComponent to validate
     * @param value the value of the field
     */
    public void validatePassword(FacesContext context, UIComponent toValidate, Object value) {
        String password = (String) value;
        if (password.length() < 8) {
            FacesMessage message = new FacesMessage("Password must be at least 8 characters long.");
            throw new ValidatorException(message);
        }
    }

    /**
     * Validate the confirmation password field
     * @param context the FacesContext
     * @param toValidate the UIComponent to validate
     * @param value the value of the field
     */
    public void validateConfirmPassword(FacesContext context, UIComponent toValidate, Object value) {
        String confirmPassword = (String) value;
        if (!confirmPassword.equals(password)) {
            FacesMessage message = new FacesMessage("Passwords do not match.");
            throw new ValidatorException(message);
        }
    }
}
