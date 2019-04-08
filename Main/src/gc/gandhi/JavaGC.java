package gc.gandhi;

import gc.gandhi.copying.CPObject;
import gc.gandhi.core.*;
import gc.gandhi.marksweep.MSObject;

public class JavaGC {

    public static void main(String[] args) throws MaxMemoryException, NoMemoryException {
        //MARK AND SWEEP STRATEGY
        Computer computer = new Computer();
        Pointer p1 = computer.createPointer(null);
        MSObject obj1 = (MSObject) computer.createObject(MSObject.class, null);
        p1.pointTo(obj1);
        MSObject obj2 = (MSObject) computer.createObject(MSObject.class, null);
        Pointer p3 = computer.createPointer(null);
        MSObject obj3 = (MSObject) computer.createObject(MSObject.class, null);
        p3.pointTo(obj3);
        MSObject obj4 = (MSObject) computer.createObject(MSObject.class, null);
        MSObject obj5 = (MSObject) computer.createObject(MSObject.class, null);
        Field[] fields =new Field[1];
        Field name = computer.createField(obj5);
        fields[0] = name;
        obj1.setFields(fields);
        
        
        
        computer.gc();
        computer.reportMemory();

        //COPYING STRATEGY
        /*MAKE CHANGES IN Computer.java in 
        public Computer() {
                this.memory = new Memory(new CopyingStrategy());
        }*/
        //and then run the following code after commenting the MS Strategy
        /*Computer computer = new Computer();
        Pointer p1 = computer.createPointer(null);
        CPObject obj1 = (CPObject) computer.createObject(CPObject.class, null);
        p1.pointTo(obj1);
        CPObject obj2 = (CPObject) computer.createObject(CPObject.class, null);
        Pointer p3 = computer.createPointer(null);
        CPObject obj3 = (CPObject) computer.createObject(CPObject.class, null);
        p3.pointTo(obj3);
        CPObject obj4 = (CPObject) computer.createObject(CPObject.class, null);
        CPObject obj5 = (CPObject) computer.createObject(CPObject.class, null);
        Field[] fields =new Field[1];
        Field name = computer.createField(obj5);
        fields[0] = name;
        obj1.setFields(fields);

        computer.gc();
        computer.reportMemory();*/
    }
}
