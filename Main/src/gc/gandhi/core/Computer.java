package gc.gandhi.core;

import gc.gandhi.copying.CPObject;
import gc.gandhi.marksweep.MSObject;
import gc.gandhi.marksweep.MarkSweepStrategy;

public class Computer {

    protected Memory memory;

    public Computer(Strategy strategy) throws WrongStrategyException {
        if (strategy == null)
            throw new WrongStrategyException();
        else
            this.memory = new Memory(strategy);
    }

    public Computer() {
        this.memory = new Memory(new MarkSweepStrategy());
    }


    public Pointer createPointer(MyObject object) throws NoMemoryException {
        return new Pointer(object, this);
    }
    public Field createField(MyObject object) throws NoMemoryException {
        return new Field(object, this);
    }
    public void gc(){
        this.memory.gc();
    }

    public MyObject createObject(Class<? extends MyObject> cls, Field[] fields) throws MaxMemoryException, NoMemoryException {
        switch (cls.getName()) {
            case "gc.gandhi.marksweep.MSObject":
                return new MSObject(fields, this);
            case "gc.gandhi.copying.CPObject":
                return new CPObject(fields, this);
            default:
                break;

        }
        return null;
    }

    public void reportMemory() {
        System.out.println("Heap Pointer : "+(memory.getHeapPointer() - 1));
        System.out.println("Garbage Collection Successful!");
    }

}
