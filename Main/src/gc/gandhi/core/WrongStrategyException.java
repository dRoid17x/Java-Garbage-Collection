package gc.gandhi.core;

public class WrongStrategyException extends Exception {
    public WrongStrategyException() {
        super("Wrong garbage collection strategy!");
    }
}
