package ClassForHash;

public class Node<E> {

    public final int hash;
    public final E key;
    public Node<E> next;

    public Node(int hash, E key, Node<E> next){
        this.hash = hash;
        this.key = key;
        this.next = next;
    }

}
