package dev.mayuna.nrdyloader;

public final class NrdyException extends RuntimeException {

    public NrdyException(String message, Throwable cause) {
        super(message, cause);
    }

    public NrdyException(String message) {
        super(message);
    }
}
