package DataStructureClass;

import ClassForLinkedHash.Node;
import Interface_from.Set;

public class LinkedHashSet<E> implements Set<E> {

    private static final int DEFAULT_CAPACITY = 1 << 4;
    private static final float LOAD_FACTOR = 0.75f;

    Node<E>[] table;
    private int size;

    private Node<E> head;
    private Node<E> tail;

    @SuppressWarnings("unchecked")
    public LinkedHashSet(){
        table = (Node<E>[]) new Node[DEFAULT_CAPACITY];
        size = 0;
        head = null;
        tail = null;
    }

    private static int hash(Object key){
        int hash;
        if(key == null){
            return 0;
        }else {
            return Math.abs(hash = key.hashCode()) ^ (hash >>> 16);
        }
    }

    private void linkLastNode(Node<E> o){

        Node<E> last = tail;

        tail = o;

        if(last == null){
            head = o;
        }else {
            o.prevLink = last;
            last.nextLink = o;
        }

    }

    @Override
    public boolean add(E e) {
        return add(hash(e), e) == null;
    }

    private E add(int hash, E key) {

        int idx = hash % table.length;

        Node<E> newNode = new Node<>(hash, key, null);

        if(table[idx] == null){
            table[idx] = newNode;
        }else{

            Node<E> temp = table[idx];
            Node<E> prev;


            do {

                if((temp.hash == hash) && (temp.key == key || temp.key.equals(key))){
                    return key;
                }
                prev = temp;
                temp = temp.next;

            } while (temp != null);

            prev.next = newNode;
        }
        size++;
        
        linkLastNode(newNode);
        
        if(size >= LOAD_FACTOR * table.length){
            resize();
        }
        
        return null;
    }

    private void resize() {
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public void clear() {

    }
}
