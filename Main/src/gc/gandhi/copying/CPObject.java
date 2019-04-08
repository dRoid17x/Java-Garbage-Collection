package gc.gandhi.copying;

import gc.gandhi.core.*;

/**
 * Created by liangjiateng on 2017/10/30.
 */
public class CPObject extends MyObject {

    private int forwardAddress = -1;

    public CPObject(Field[] fields, Computer computer) throws MaxMemoryException, NoMemoryException {
        super(fields, computer);
    }

    public boolean hasForwardAddress() {
        return this.forwardAddress != -1;
    }

    public int getForwardAddress() {
        return forwardAddress;
    }

    public void setForwardAddress(int forwardAddress) {
        this.forwardAddress = forwardAddress;
    }
}
