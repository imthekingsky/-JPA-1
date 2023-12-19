package jpabook.jpashop.exception;

public class NotEnouhStockException extends RuntimeException{

    // 메소드 오버라이드
    public NotEnouhStockException() {
        super();
    }

    public NotEnouhStockException(String message) {
        super(message);
    }

    public NotEnouhStockException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotEnouhStockException(Throwable cause) {
        super(cause);
    }

    protected NotEnouhStockException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
