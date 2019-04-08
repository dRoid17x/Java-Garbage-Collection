package gc.gandhi.core;

public class MaxMemoryException extends Exception {

    public MaxMemoryException() {
        super("Memory overflow!");
    }
}
