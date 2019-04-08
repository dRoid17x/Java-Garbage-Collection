package gc.gandhi.core;

public class Pointer {

    protected int address = -1;
    protected Computer computer;

    public Pointer(MyObject object, Computer computer) throws NoMemoryException {
        this.computer = computer;
        if (computer == null) {
            throw new NoMemoryException();
        }
        if (object != null) {
            this.address = object.getAddress();
        }
        computer.memory.addPointer(this);
    }

    public Pointer() {

    }

    public int getAddress() {
        return address;
    }

    public MyObject getObject() {
        MyObject myObject;
        try {
            myObject = computer.memory.getHeap()[address];
        } catch (Exception e) {
            return null;
        }
        return myObject;
    }

    public void pointTo(int address) {
        this.address = address;
    }


    public void pointTo(MyObject newObj) {
        if (newObj != null) {
            this.address = newObj.getAddress();
        } else {
            this.address = -1;
        }
    }
}

