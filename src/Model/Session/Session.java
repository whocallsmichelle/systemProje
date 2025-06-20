package Model.Session;

public class Session {
    private int id;
    private int userId;
    private int userType;
    private boolean active;

    public Session(int id, int userId, int userType, boolean active) {
        this.id = id;
        this.userId = userId;
        this.userType = userType;
        this.active = active;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public int getUserType() {
        return userType;
    }
    public void setUserType(int userType) {
        this.userType = userType;
    }
    public boolean isActive() {
        return active;
    }
    public void setActive(boolean active) {
        this.active = active;
    }

}
