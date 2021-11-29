package ClassForDataStructures;

public class SinglyNode<E> {

    public E data;
    public SinglyNode<E> next;

    public SinglyNode(E data){
        this.data = data;
        this.next = null;
    }

}
