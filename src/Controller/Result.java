package Controller;

public class Result {

    Result(String status,String message, int sessionId) {
        this.status = status;
        this.message = message;
        this.sessionId = sessionId;
    }
    String status;
    String message;
    int sessionId;
}
