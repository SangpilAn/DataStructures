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

    @SuppressWarnings("unchecked")
    private void resize() {

        int newCapacity = table.length * 2;

        final Node<E>[] newTable = (Node<E>[]) new Node[newCapacity];

        for (int i = 0; i < table.length; i++) {

            Node<E> value = table[i];

            if(value == null){
                continue;
            }

            table[i] = null;

            Node<E> nextNode;

            do {

                int idx = value.hash % newCapacity;

                if(newTable[idx] != null){
                    Node<E> tail = newTable[idx];

                    while (tail.next != null){
                        tail = tail.next;
                    }

                    nextNode = value.next;
                    value.next = null;
                    tail.next = value;
                }else{
                    nextNode = value.next;
                    value.next = null;
                    newTable[idx] = value;
                }

                value = nextNode;
            }while (value != null);
        }
        table = null;
        table = newTable;
    }

    private void unlinkNode(Node<E> o){
        Node<E> prevNode = o.prevLink;
        Node<E> nextNode = o.nextLink;

        if(prevNode == null){
            head = nextNode;
        }else {
            prevNode.nextLink = nextNode;
            o.prevLink = null;
        }

        if(nextNode == null){
            tail = prevNode;
        }else {
            nextNode.prevLink = prevNode;
            o.nextLink = null;
        }
    }

    @Override
    public boolean remove(Object o) {
        return remove(hash(o), o) != null;
    }

    private Object remove(int hash, Object key) {

        int idx = hash % table.length;

        Node<E> node = table[idx];
        Node<E> removedNode = null;
        Node<E> prev = null;

        if(node == null){
            return null;
        }

        do {

            if(node.hash == hash && (node.key == key || node.key.equals(key))){

                removedNode = node;

                if(prev == null){
                    table[idx] = node.next;
                }else {
                    prev.next = node.next;
                }

                unlinkNode(node);
                node = null;

                size--;
                break;
            }
            prev = node;
            node = node.next;
        }while (node != null);

        return removedNode;
    }

    @Override
    public boolean contains(Object o) {
        int idx = hash(o) % table.length;
        Node<E> temp = table[idx];

        while (temp != null){
            if(o == temp.key || (o != null && temp.key.equals(o))){
                return true;
            }
            temp = temp.next;
        }

        return false;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        if(table != null && size > 0){
            for (int i = 0; i < table.length; i++) {
                table[i] = null;
            }
            size = 0;
        }
        head = tail = null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean equals(Object o) {

        if(o == this){
            return true;
        }

        if(!(o instanceof LinkedHashSet)){
            return false;
        }

        LinkedHashSet<E> oSet;

        try{

            oSet = (LinkedHashSet<E>) o;

            if(oSet.size() != size){
                return false;
            }

            for (int i = 0; i < oSet.table.length; i++) {
                Node<E> oNode = oSet.table[i];

                while (oNode != null){
                    if(!contains(oNode)){
                        return false;
                    }
                    oNode = oNode.next;
                }
            }

        }catch (ClassCastException e){
            return false;
        }

        return true;
    }
}
