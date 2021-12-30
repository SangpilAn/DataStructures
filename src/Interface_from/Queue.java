package Interface_from;

public interface Queue<E> {

    boolean offer(E e);

    E poll();

    E peek();
}
