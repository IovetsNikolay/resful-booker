package booker.models.auth;

public class Auth {
    private String username;

    private String password;

    public Auth(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Auth() {}

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

}
