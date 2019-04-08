package gc.gandhi.marksweep;

import gc.gandhi.core.*;
import java.util.Arrays;

import java.util.Iterator;
import java.util.List;

public class MarkSweepStrategy implements Strategy {


    @Override
    public int execute(Memory memory) {
        Iterator iterator = memory.getStaticArea().iterator();
        while (iterator.hasNext()) {
            Pointer p = (Pointer) iterator.next();
            mark(p);
        }
        return sweep(memory);
    }

    private void mark(Pointer pointer) {
        if (pointer == null)
            return;
        MSObject msObject = (MSObject) pointer.getObject();
        if (msObject == null)
            return;
        if (!msObject.isMark()) {
            msObject.setMark(true);
            Field[] fields = msObject.getFields();
            if (fields != null) {
                for (Field field : fields) {
                    mark(field);
                }
            }
        }
    }

    private int sweep(Memory memory) {
        MyObject[] heap = memory.getHeap();
        for (int i = 0; i < memory.getHeapPointer(); i++) {
            MSObject msObject = (MSObject) heap[i];
            if (msObject.isMark())
                msObject.setMark(false);
            else
                heap[i] = null;
        }
        System.out.println(Arrays.toString(heap));
        return compact(memory, heap);
    }

    private int compact(Memory memory, MyObject[] heap) {
        // compact
        MyObject[] newHeap = new MyObject[memory.getSize()];
        int count = 0;
        for (int i = 0; i < memory.getHeapPointer(); i++) {
            MyObject myObject = heap[i];
            if (myObject != null) {
                for (Pointer pointer : memory.getStaticArea()) {
                    if (pointer.getAddress() == i)
                        pointer.pointTo(count);
                }
                for (Field field : memory.getFieldsArea()) {
                    if (field.getAddress() == i)
                        field.pointTo(count);
                }
                myObject.setAddress(count);
                newHeap[count++] = myObject;
            }
        }
        System.out.println(Arrays.toString(newHeap));
        memory.setHeap(newHeap);
        return count;
    }
}
