package comchaowangcanada.httpsgithub.calculator;

import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;

/**
 * Created by Chao on 27/03/2016.
 */

public class BoundedArrayList<E> extends AbstractList<E>{

    private final int capacity; // maxi allowed array Length

    int size;
    transient Object[] array;


    public BoundedArrayList(int capacity){
        if (capacity < 0) {
            throw new IllegalArgumentException("capacity < 0: " + capacity);
        }
        array = (capacity == 0 ? new Object[0] : new Object[capacity]);
        this.capacity = capacity ;
    }


    public BoundedArrayList(Collection<? extends E> collection, int capacity) {
        if (collection == null) {
            throw new NullPointerException("collection == null");
        }

        Object[] a = collection.toArray();
        if (a.getClass() != Object[].class) {
            Object[] newArray = new Object[a.length];
            System.arraycopy(a, 0, newArray, 0, a.length);
            a = newArray;
        }
        array = a;
        size = a.length;
        this.capacity= capacity;
    }


    public int capacity(){
        return capacity;
    }


    @Override
    public void add(int index, E e) {
        Object[] a = array;
        int s = size;
        if (index < 0 || index > s) {
            throw new IndexOutOfBoundsException();
        }
        if (s == capacity()){
            System.arraycopy(a, index, a, index + 1, s-index-1);
        }
        else {
            System.arraycopy(a, index, a, index + 1, s-index);
            size = s + 1;
        }
        a[index] = e;
        modCount++;

    }

    @Override
    public boolean add(E object) {
        Object[] a = array;
        int s = size;
        if (capacity == s) {
            System.arraycopy(a, 0, a, 1, s-1);
            array = a ;
        }
        else  {
            System.arraycopy(a, 0, a, 1, s);
            array = a ;
            size = s + 1;
        }
        a[0] = object;
        modCount++;
        return true;
    }

    @Override public void clear() {
        if (size != 0) {
            Arrays.fill(array, 0, size, null);
            size = 0;
            modCount++;
        }
    }

    @SuppressWarnings("unchecked") @Override
    public E get(int index) {
        if (index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return (E) array[index];
    }


    @Override public E remove(int index) {
        Object[] a = array;
        int s = size;
        if (index >= s) {
            throw new IndexOutOfBoundsException();
        }
        @SuppressWarnings("unchecked") E result = (E) a[index];
        System.arraycopy(a, index + 1, a, index, --s - index);
        a[s] = null;  // Prevent memory leak
        size = s;
        modCount++;
        return result;
    }

    @Override public boolean remove(Object object) {
        Object[] a = array;
        int s = size;
        if (object != null) {
            for (int i = 0; i < s; i++) {
                if (object.equals(a[i])) {
                    System.arraycopy(a, i + 1, a, i, --s - i);
                    a[s] = null;  // Prevent memory leak
                    size = s;
                    modCount++;
                    return true;
                }
            }
        } else {
            for (int i = 0; i < s; i++) {
                if (a[i] == null) {
                    System.arraycopy(a, i + 1, a, i, --s - i);
                    a[s] = null;  // Prevent memory leak
                    size = s;
                    modCount++;
                    return true;
                }
            }
        }
        return false;
    }



    @Override
    public int size() {
        return size;
    }

    @Override public boolean isEmpty() {
        return size == 0;
    }

    @Override public boolean contains(Object object) {
        Object[] a = array;
        int s = size;
        if (object != null) {
            for (int i = 0; i < s; i++) {
                if (object.equals(a[i])) {
                    return true;
                }
            }
        } else {
            for (int i = 0; i < s; i++) {
                if (a[i] == null) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override public int indexOf(Object object) {
        Object[] a = array;
        int s = size;
        if (object != null) {
            for (int i = 0; i < s; i++) {
                if (object.equals(a[i])) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < s; i++) {
                if (a[i] == null) {
                    return i;
                }
            }
        }
        return -1;
    }

    @Override public int lastIndexOf(Object object) {
        Object[] a = array;
        if (object != null) {
            for (int i = size - 1; i >= 0; i--) {
                if (object.equals(a[i])) {
                    return i;
                }
            }
        } else {
            for (int i = size - 1; i >= 0; i--) {
                if (a[i] == null) {
                    return i;
                }
            }
        }
        return -1;
    }

    @Override public Object[] toArray() {
        int s = size;
        Object[] result = new Object[s];
        System.arraycopy(array, 0, result, 0, s);
        return result;
    }

    @Override public E set(int index, E object) {
        Object[] a = array;
        if (index >= size) {
            throw new IndexOutOfBoundsException();
        }
        @SuppressWarnings("unchecked") E result = (E) a[index];
        a[index] = object;
        return result;
    }
}
