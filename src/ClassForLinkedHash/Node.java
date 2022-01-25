package ClassForLinkedHash;

public class Node<E> {

    public final int hash;
    public final E key;

    public Node<E> next;

    public Node<E> nextLink;
    public Node<E> prevLink;

    public Node(int hash, E key, Node<E> next){
        this.hash = hash;
        this.key = key;
        this.next = next;

        this.nextLink = null;
        this.prevLink = null;
    }
}
