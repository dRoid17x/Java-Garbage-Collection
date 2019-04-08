package gc.gandhi.copying;

import gc.gandhi.core.*;

public class CopyingStrategy implements Strategy {

    private int point ;

    @Override
    public int execute(Memory memory) {
        point = 0;
        for (Pointer pointer : memory.getStaticArea()) {
            forward(pointer, memory);
        }
        memory.setSwapped(!memory.isSwapped());
        sweep(memory);
        return point;
    }

    public void forward(Pointer p, Memory memory) {
        CPObject object = (CPObject) p.getObject();
        if (object == null)
            return;
        if (object.hasForwardAddress())
            p.pointTo(object.getForwardAddress());
        else {
            int newAddress;
            if (memory.isSwapped())
                newAddress = copy(p, memory.getFrom());
            else
                newAddress = copy(p, memory.getTo());
            ((CPObject) p.getObject()).setForwardAddress(newAddress);
            for (Field field : p.getObject().getFields()) {
                forward(field, memory);
            }
            p.pointTo(newAddress);
        }
    }

    public int copy(Pointer p, MyObject[] TO) {
        TO[point] = p.getObject();
        return ++point;
    }

    public void sweep(Memory memory) {
        MyObject[] myObjects;
        if (memory.isSwapped()) {
            myObjects = memory.getTo();
        } else
            myObjects = memory.getFrom();
        for (int i = 0; i < memory.getSize(); i++) {
            myObjects[i] = null;
        }
    }
}
