package itxj.ymb.exception;

/**
 * 需要被抛出的业务异常
 */
public class AppException extends Exception {
    public AppException(String message) {
        super(message);
    }
}
