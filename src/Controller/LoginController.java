package Controller;

import Model.User.User;
import Controller.Result;
import Controller.SessionService;


public class LoginController {

    public Result login(String username, String password) {
        User result = Model.User.UserOps.login(username, password);
        Result loginResult;
        if (result.getUserId() != -1) {
             loginResult = new Result("success", "Login successful for user: " + username,SessionService.createSession(result).getId());
        } else {
             loginResult = new Result("failure", "Incorrect username or password",-1);
        }
        return loginResult;
    }

    public void logout(int sessionId) {
        SessionService.logOut(sessionId);

    }
    public Result createAcount(String username, String password, int userType) {
        User newUser = new User(username, password, userType);
        if (Model.User.UserOps.createUser(newUser)){
            return new Result("success", "Account created successfully for user: " + username,-1);
        } else {
            return new Result("failure", "Failed to create account for user: " + username,-1);

        }

    }
}
