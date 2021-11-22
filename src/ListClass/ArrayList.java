package ListClass;

import InterfaceForList.List;

import java.util.Arrays;

public class ArrayList<E> implements List<E> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final Object[] EMPTY_ARRAY = {};

    private int size;
    Object[] array;

    public ArrayList(){
        this.array = EMPTY_ARRAY;
        this.size = 0;
    }

    public ArrayList(int capacity){
        this.array = new Object[capacity];
        this.size = 0;
    }

    public void resize(){
        int array_capacity = array.length;

        // 용적량이 0인 경우
        if(Arrays.equals(array, EMPTY_ARRAY)){
            array = new Object[DEFAULT_CAPACITY];
            return;
        }

        // 데이터 개수와 용적량이 같은 경우
        if(size == array_capacity){
            int new_capacity = array_capacity * 2;
            array = Arrays.copyOf(array, new_capacity);
        }
        // 데이터 개수가 용적량의 반 미만인 경우
        if(size < (array_capacity / 2)){
            int new_capacity = array_capacity / 2;
            array = Arrays.copyOf(array, Math.max(new_capacity, DEFAULT_CAPACITY));
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

        if(index == size){
            addLast(value);
        }else{
            
            if(size == array.length){
                resize();
            }

            for (int i = size; i > index; i--) {
                array[i] = array[i-1];
            }

            array[index] = value;
            size++;
        }
    }

    public void addLast(E value){
        if(size == array.length){
            resize();
        }
        array[size] = value;
        size++;
    }

    public void addFirst(E value){
        add(0, value);
    }

    @SuppressWarnings("unchecked")
    @Override
    public E remove(int index) {
        if(index >= size || index < 0){
            throw new IndexOutOfBoundsException();
        }

        E element = (E) array[index];
        array[index] = null;

        for (int i = index; i < size; i++) {
            array[i] = array[i+1];
            array[i+1] = null;
        }
        size--;
        resize();

        return element;
    }

    @Override
    public boolean remove(E value) {
        int index = indexOf(value);

        if(index == -1){
            return false;
        }

        remove(index);
        return true;
    }

    @SuppressWarnings("unchecked")
    @Override
    public E get(int index) {
        if(index >= size || index < 0){
            throw new IndexOutOfBoundsException();
        }

        return (E) array[index];
    }

    @Override
    public void set(int index, E value) {
        if(index >= size || index < 0){
            throw new IndexOutOfBoundsException();
        }else {
            array[index] = value;
        }

    }

    @Override
    public boolean contains(E value) {
        return indexOf(value) >= 0;
    }

    @Override
    public int indexOf(E value) {
        for (int i = 0; i < size; i++) {
            if(array[i].equals(value)){
                return i;
            }
        }

        return -1;
    }

    public int LastIndexOf(Object value){
        for (int i = size - 1; i >= 0; i--) {
            if(array[i].equals(value)){
                return i;
            }
        }

        return -1;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            array[i] = null;
        }
        size = 0;
        resize();
    }
}
