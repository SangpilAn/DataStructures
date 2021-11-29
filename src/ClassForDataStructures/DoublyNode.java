package ClassForDataStructures;

public class DoublyNode<E> {

    public E data;
    public DoublyNode<E> next;
    public DoublyNode<E> prev;

    public DoublyNode(E data){
        this.data = data;
        this.next = null;
        this.prev = null;
    }

}
