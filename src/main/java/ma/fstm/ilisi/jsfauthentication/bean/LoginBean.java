package ma.fstm.ilisi.jsfauthentication.bean;

import jakarta.faces.application.FacesMessage;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.component.UIComponent;
import jakarta.faces.component.UIInput;
import jakarta.faces.context.FacesContext;
import ma.fstm.ilisi.jsfauthentication.service.UserService;

import java.io.Serializable;

@Named
@SessionScoped
public class LoginBean implements Serializable {
    private String login;
    private String password;

    private final UserService userService;

    public LoginBean() {
        this.userService = new UserService();
    }

    public LoginBean(String login, String password) {
        this.login = login;
        this.password = password;
        this.userService = new UserService();
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Authenticate the user
     * @return the page to redirect to after authentication
     */
    public String authenticate() {
        if (this.userService.authenticate(login, password) != null) {
            return "home?faces-redirect=true";
        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login failed!", "Invalid credentials"));
            return "login";
        }
    }

    /**
     * Validate login field
     * @param context the FacesContext
     * @param toValidate the UIComponent to validate
     * @param value the value of the field
     */
    public void validateLogin(FacesContext context, UIComponent toValidate, Object value) {
        String login = (String) value;
        if (login.length() < 5) {
            FacesMessage message = new FacesMessage("Login must be at least 5 characters long.");
            context.addMessage(toValidate.getClientId(context), message);
            ((UIInput) toValidate).setValid(false);
        }
    }

    /**
     * Validate password field
     * @param context the FacesContext
     * @param toValidate the UIComponent to validate
     * @param value the value of the field
     */
    public void validatePassword(FacesContext context, UIComponent toValidate, Object value) {
        String password = (String) value;
        if (password.length() < 8) {
            FacesMessage message = new FacesMessage("Password must be at least 8 characters long.");
            context.addMessage(toValidate.getClientId(context), message);
            ((UIInput) toValidate).setValid(false);
        }
    }

}
