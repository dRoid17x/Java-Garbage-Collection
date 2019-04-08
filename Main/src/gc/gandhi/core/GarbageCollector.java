package gc.gandhi.core;

public class GarbageCollector {

    public int gc(Memory memory, Strategy strategy) {
        return strategy.execute(memory);
    }
}
