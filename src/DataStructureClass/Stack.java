package DataStructureClass;

import Interface_from.StackInterface;

public class Stack<E> implements StackInterface<E> {

    private static final int DEFAULT_CAPACITY = 10;
    private static final Object[] EMPTY_ARRAY = {};

    private Object[] array;
    private int size;

    public Stack(){
        this.array = EMPTY_ARRAY;
        this.size = 0;
    }

    public Stack(int capacity){
        this.array = new Object[capacity];
        this.size = 0;
    }

    private void resize(){

    }

    @Override
    public E push(E item) {
        return null;
    }

    @Override
    public E pop() {
        return null;
    }

    @Override
    public E peek() {
        return null;
    }

    @Override
    public int search(Object value) {
        return 0;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public void clear() {

    }

    @Override
    public boolean empty() {
        return false;
    }
}
