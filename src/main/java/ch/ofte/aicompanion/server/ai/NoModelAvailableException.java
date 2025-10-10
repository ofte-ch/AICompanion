package ch.ofte.aicompanion.server.ai;

public class NoModelAvailableException extends RuntimeException {
    public NoModelAvailableException() {
        super("There are no models registered!");
    }
}
