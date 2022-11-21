package itxj.ymb.exception;

/**
 * 无需被抛出的业务异常
 */
public class AppRuntimeException extends RuntimeException {
    public AppRuntimeException(String message) {
        super(message);
    }
}
