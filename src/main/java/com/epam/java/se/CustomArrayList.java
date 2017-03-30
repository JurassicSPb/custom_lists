package com.epam.java.se;

import java.util.*;

/**
 * Created by Мария on 29.03.2017.
 */
public class CustomArrayList<T> implements List<T> {

    public static final int CAPACITY = 10;

    private Object[] data = new Object[CAPACITY];
    private int size = 0;

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        for (int i = 0; i < size; i++) {
            if (data[i] == null) {
                if (o == null) {
                    return true;
                }
            } else if (data[i].equals(o)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean add(T t) {
        if (size == data.length) {
            int newLength = (data.length * 3) / 2 + 1;
            data = Arrays.copyOf(data, newLength);
        }
        data[size++] = t;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        for (int i = 0; i < size; i++) {
            if (o.equals(data[i])) {
                remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public void clear() {
        data = new Object[CAPACITY];
        size = 0;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return (T) data[index];
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        int length = data.length - index;
        T value = (T) data[index];
        System.arraycopy(data, index + 1, data, index, length - 1);
        size--;
        return value;
    }

    @Override
    public T set(int index, T element) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        T oldValue = (T) data[index];
        data[index] = element;

        return oldValue;
    }

    @Override
    public void add(int index, T element) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        int length = data.length - index;
        System.arraycopy(data, index, data, index + 1, length - 1);
        data[index] = element;
        size++;
    }

    /**
     * Returns a copy of original data in chosen diapason
     *
     * @param fromIndex start index (inclusive) of the subList
     * @param toIndex   end index (exclusive) of the subList
     * @return a view of the specified range within this list
     */
    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        if (fromIndex < 0 || fromIndex >= size || fromIndex > toIndex) {
            if (toIndex < 0 || toIndex >= size) {
                throw new IndexOutOfBoundsException();
            }
        }
        int length = toIndex - fromIndex + 1;
        T[] subListData = (T[]) new Object[length];
        System.arraycopy(data, fromIndex, subListData, 0, length);
        return Arrays.asList(subListData);
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if (data[i] == null) {
                if (o == null) {
                    return i;
                }
            } else if (data[i].equals(o)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = size - 1; i >= 0; i--) {
            if (data[i] == null) {
                if (o == null) {
                    return i;
                }
            } else if (data[i].equals(o)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(data, size);
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return (T1[]) Arrays.copyOf(data, size);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        boolean isContaining = true;
        for (Object element : c) {
            if (!contains(element)) {
                isContaining = false;
                break;
            }
        }
        return isContaining;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        boolean wasAdded = false;
        for (Object element : c) {
            if (add((T) element)) {
                wasAdded = true;
            }
        }
        return wasAdded;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        boolean wasAdded = false;
        for (Object element : c) {
            add(index, (T) element);
            wasAdded = true;
        }
        return wasAdded;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean wasRemoved = false;
        for (Object element : c) {
            if (remove((T) element)) {
                wasRemoved = true;
            }
        }
        return wasRemoved;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    @Override
    public ListIterator<T> listIterator() {
        return null;
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return null;
    }

}
