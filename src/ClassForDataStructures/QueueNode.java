package ClassForDataStructures;

public class QueueNode<E> {

    public E data;
    public QueueNode<E> next;

    public QueueNode(E data){
        this.data = data;
        this.next = null;
    }
}
