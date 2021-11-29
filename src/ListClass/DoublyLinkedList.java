package ListClass;

import ClassForList.DoublyNode;
import InterfaceForList.List;

import java.util.NoSuchElementException;

public class DoublyLinkedList<E> implements List<E> {

    private DoublyNode<E> head;
    private DoublyNode<E> tail;
    private int size;

    public DoublyLinkedList(){
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public DoublyNode<E> search(int index){

        if(index < 0 || index >= size){
            throw new IndexOutOfBoundsException();
        }

        // 뒤에서부터 검색
        if(index > size/2){
            DoublyNode<E> x = tail;
            for (int i = size - 1; i > index; i--) {
                x = x.prev;
            }
            return x;
        }
        // 앞에서부터 검색
        else{
            DoublyNode<E> x = head;
            for (int i = 0; i < index; i++) {
                x = x.next;
            }
            return x;
        }

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

        DoublyNode<E> newNode = new DoublyNode<>(value);
        DoublyNode<E> prevNode = search(index-1);
        DoublyNode<E> nextNode = prevNode.next;
        newNode.prev = prevNode;
        newNode.next = nextNode;
        prevNode.next = newNode;
        nextNode.prev = newNode;
        size++;
    }

    public void addFirst(E value){
        DoublyNode<E> newNode = new DoublyNode<>(value);
        newNode.next = head;

        if(head != null){
            head.prev = newNode;
        }

        head = newNode;
        size++;

        if(head.next == null){
            tail = head;
        }
    }

    public void addLast(E value){
        if(size == 0){
            addFirst(value);
            return;
        }

        DoublyNode<E> newNode = new DoublyNode<>(value);
        newNode.prev = tail;
        tail.next = newNode;
        tail = newNode;
        size++;
    }

    @Override
    public E remove(int index) {
        if(index < 0 || index >= size){
            throw new IndexOutOfBoundsException();
        }

        if(index == 0){
            return remove();
        }

        DoublyNode<E> preNode = search(index-1);
        DoublyNode<E> targetNode = preNode.next;
        DoublyNode<E> nextNode = targetNode.next;
        E element = targetNode.data;

        targetNode.data = null;
        targetNode.next = null;
        targetNode.prev = null;
        if(targetNode.equals(tail)){
            preNode.next = null;
            tail = preNode;
        }else{
            preNode.next = nextNode;
            nextNode.prev = preNode;
        }
        size--;

        return element;
    }

    @Override
    public boolean remove(E value) {
        DoublyNode<E> preNode = head;
        DoublyNode<E> x = head;

        for (; x != null; x = x.next){
            if(value.equals(x.data)){
                break;
            }
            preNode = x;
        }

        if(x == null){
            return false;
        }

        if(x.equals(head)){
            remove();
        }else{
            DoublyNode<E> nextNode = x.next;
            x.data = null;
            x.next = null;
            x.prev = null;

            if(nextNode != null){
                preNode.next = nextNode;
                nextNode.prev = preNode;
            }else{
                tail = preNode;
            }
        }

        size--;

        return true;
    }

    public E remove(){
        if(head == null){
            throw new NoSuchElementException();
        }

        DoublyNode<E> nextNode = head.next;
        E element = head.data;

        head.data = null;
        head.next = null;

        if(nextNode !=null){
            nextNode.prev = null;
        }

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
