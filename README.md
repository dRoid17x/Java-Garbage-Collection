# MarkSweepGC
Java Garbage Collecton using Mark and Sweep Strategy and Copying Strategy

# Overview
This is a simulator to simulate garbage collection mechanism by using Java. I wrote this in order to learn GC. It contains mark and sweep, copying, reference counting GC strategies. Here is the background knowledge:

# Mark and Sweep Strategy

Mark phase :
When an object is created, its mark bit is set to 0(false). In the Mark phase, we set the marked bit for all the reachable objects (or the objects which a user can refer to) to 1(true). Now to perform this operation we simply need to do a graph traversal, a depth first search approach would work for us. Here we can consider every object as a node and then all the nodes (objects) that are reachable from this node (object) are visited and it goes on till we have visited all the reachable nodes.

Sweep phase :
As the name suggests it “sweeps” the unreachable objects i.e. it clears the heap memory for all the unreachable objects. All those objects whose marked value is set to false are cleared from the heap memory, for all other objects (reachable objects) the marked bit is set to true.

Advantages of Mark and Sweep Algorithm :

  --It handles the case with cyclic references, even in case of a cycle, this algorithm never ends up in an infinite loop.
  --There are no additional overheads incurred during the execution of the algorithm.

Disadvantages of Mark and Sweep Algorithm :

  --The main disadvantage of the mark-and-sweep approach is the fact that that normal program execution is suspended while the  
    garbage collection algorithm runs.
  --Other disadvantage is that, after the Mark and Sweep Algorithm is run several times on a program, reachable objects end up
    being separated by many, small unused memory regions.

# Copying Strategy

In contrast, a copying collector copies reachable objects to another region of memory as they are being traversed. Provided the traversal is done in breadth first order, there is a well-known and simple algorithm for performing this traversal without auxiliary storage or recursion. After such a traversal all surviving objects reside in a contiguous region of memory, and all pointers have been updated to point to the new object locations. The previously used region of memory can then be reused in its entirety. Allocation becomes trivial, since all free space is always contiguous.

Advantages of Copying Strategy :

  --The fact that copying collectors offer much faster allocation than the allocation+sweep time of a mark-sweep collector means
    that they are still attractive for the youngest generation(s) in generational collectors, where the copy time can be kept 
    very low. But the crucial factor here is the small constant factor in the allocation time, and the fact that young objects 
    can be segregated, thus making it easier to identify old unmodified objects. This does not reflect any difference in 
    asymptotic complexity. 
  --Copying collectors can also offer better worst-case space bounds than nonmoving collectors, particularly if real-time
    constraints are present. Thus they may be appropriate for certain embedded real-time applications.

Disadvantages of Copying Strategy :

  --There is growing evidence that copying collectors are a poor choice for old objects in a typical desktop application. 
  --They often result in unnecessarily large memory footprints, and paging where none is really necessary. The net effect can be
    disastrous performance.

# Sample output for Mark and Sweep Strategy
```
run:
Garbage collection start
[gc.gandhi.marksweep.MSObject@6d06d69c, gc.gandhi.marksweep.MSObject@7852e922, gc.gandhi.marksweep.MSObject@4e25154f, gc.gandhi.marksweep.MSObject@70dea4e, gc.gandhi.marksweep.MSObject@5c647e05]
After GC Processing : 
[gc.gandhi.marksweep.MSObject@6d06d69c, null, gc.gandhi.marksweep.MSObject@4e25154f, null, gc.gandhi.marksweep.MSObject@5c647e05]
After Compaction : 
[gc.gandhi.marksweep.MSObject@6d06d69c, gc.gandhi.marksweep.MSObject@4e25154f, gc.gandhi.marksweep.MSObject@5c647e05, null, null]
Heap Pointer : 3
Garbage Collection Successful!
BUILD SUCCESSFUL (total time: 0 seconds)

```
After GC Processing objects gc.gandhi.marksweep.MSObject@7852e922 and gc.gandhi.marksweep.MSObject@70dea4e are swept by the GC.

After Compaction the memory space is made easily available for user to use.

(Proper explanation is demonstrated below)

# Sample output explanation

![GC_1](https://user-images.githubusercontent.com/48925853/55738361-9c6a5100-5a44-11e9-9404-5fcd76ed85ce.jpg)



Thank You!
