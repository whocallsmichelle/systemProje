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
    Object data = null;

    public void setData(Object data) {
        this.data = data;
    }
    public Object getData() {
        return data;
    }
    @Override
    public String toString() {
        return "Result{" +
                "status='" + status + '\'' +
                ", message='" + message + '\'' +
                ", sessionId=" + sessionId +
                '}';
    }
}
