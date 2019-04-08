package gc.gandhi.core;

public class MyObject {

    private Field[] fields;

    private int address = -1;


    public MyObject(Field[] fields, Computer computer) throws MaxMemoryException, NoMemoryException {
        if (computer == null)
            throw new NoMemoryException();
        this.address = computer.memory.allocate(this);
        this.fields = fields;


    }

    public int getAddress() {
        return address;
    }

    public void setAddress(int address) {
        this.address = address;
    }

    public Field[] getFields() {
        return fields;
    }

    public void setFields(Field[] fields) {
        this.fields = fields;
    }
}
