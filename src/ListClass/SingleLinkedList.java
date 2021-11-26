package ListClass;

import ClassForList.Node;
import InterfaceForList.List;

import java.util.NoSuchElementException;

public class SingleLinkedList<E> implements List<E> {

    private Node<E> head;
    private Node<E> tail;
    private int size;

    SingleLinkedList(){
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    private Node<E> search(int index){

        if(index < 0 || index >= size){
            throw new IndexOutOfBoundsException();
        }

        Node<E> x = head;

        for (int i = 0; i < index; i++) {
            x = x.next;
        }
        return x;
    }

    @Override
    public boolean add(E value) {
        addLast(value);
        return true;
    }

    @Override
    public void add(int index, E value) {
        if(index < 0 || index > size){
            throw new IndexOutOfBoundsException();
        }

        if(index == 0){
            addFirst(value);
            return;
        }

        if(index == size){
            addLast(value);
            return;
        }

        Node<E> newNode = new Node<>(value);
        Node<E> preNode = search(index-1);
        newNode.next = preNode.next;
        preNode.next = newNode;
        size++;
    }

    public void addFirst(E value){
        Node<E> newNode = new Node<>(value);

        newNode.next = head;
        head = newNode;
        size++;

        if(head.next == null){
            tail = head;
        }

    }

    public void addLast(E value){
        Node<E> newNode = new Node<>(value);

        if(size == 0){
            addFirst(value);
            return;
        }

        tail.next = newNode;
        tail = newNode;
        size++;
    }

    @Override
    public E remove(int index) {
        return null;
    }

    @Override
    public boolean remove(E value) {
        return false;
    }

    public E remove(){
        Node<E> headNode = head;

        if(headNode == null){
            throw new NoSuchElementException();
        }

        E element = headNode.data;
        Node<E> nextNode = head.next;

        headNode.next = null;
        headNode.data = null;

        head = nextNode;
        size--;

        if(size == 0){
            tail = null;
        }

        return element;
    }

    @Override
    public E get(int index) {
        return null;
    }

    @Override
    public void set(int index, E value) {

    }

    @Override
    public boolean contains(E value) {
        return false;
    }

    @Override
    public int indexOf(E value) {
        return 0;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void clear() {

    }
}
