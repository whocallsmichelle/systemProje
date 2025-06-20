package Model.User;

public class User {
    private int userId;
    private String Username;
    private String Password;
    private int userType;

    public User() {
        this.userId = -1;
        this.Username = "";
        this.Password = "";
        this.userType = 0;
    }

    public User(String username, String password, int userType) {
        this.Username = username;
        this.Password = password;
        this.userType = userType;
    }
    public User(int userId, String username, String password, int userType) {
        this.userId = userId;
        this.Username = username;
        this.Password = password;
        this.userType = userType;
    }
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public String getUsername() {
        return Username;
    }
    public void setUsername(String username) {
        this.Username = username;
    }
    public String getPassword() {
        return Password;
    }
    public void setPassword(String password) {
        password.hashCode();
        this.Password = password;
    }
    public int getUserType() {
        return userType;
    }
    public void setUserType(int userType) {
        this.userType = userType;
    }

}
