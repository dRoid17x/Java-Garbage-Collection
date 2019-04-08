package gc.gandhi.marksweep;

import gc.gandhi.core.*;

public class MSObject extends MyObject {

    private boolean mark;

    public MSObject(Field[] fields, Computer computer) throws MaxMemoryException, NoMemoryException {
        super(fields, computer);
    }


    public boolean isMark() {
        return mark;
    }

    public void setMark(boolean mark) {
        this.mark = mark;
    }


}
