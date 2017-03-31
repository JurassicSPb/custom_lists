package com.epam.java.se;

import java.util.*;

/**
 ** Class represents an implementation of Custom ArrayList with
 * dynamic capacity. Values are sorted in this List in the way they
 * were added.
 *
 * Created by Мария on 29.03.2017.
 */
public class CustomArrayList<T> implements List<T> {

    public static final int CAPACITY = 10;

    private Object[] data = new Object[CAPACITY];
    private int size = 0;

    /**
     * @return size of this list.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * @return true is this list is empty
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * @param o element whose presence in this list is to be tested
     * @return true if this list contains the specified element
     */
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

    /**
     * Adds the specified element to the end of this list
     * @param t element to be added to this list
     * @return true
     */
    @Override
    public boolean add(T t) {
        if (size == data.length) {
            int newLength = (data.length * 3) / 2 + 1;
            data = Arrays.copyOf(data, newLength);
        }
        data[size++] = t;
        return true;
    }

    /**
     * Removes the first occurrence of the specified element from this list
     * @param o element to be removed from this list, if present
     * @return true if element was removes, false otherwise
     */
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

    /**
     * clears the entire list
     */
    @Override
    public void clear() {
        data = new Object[CAPACITY];
        size = 0;
    }

    /**
     * @param index index of the element to return
     * @return the element at the specified position in this list
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    @Override
    public T get(int index) {
        boundsCheck(index);
        return (T) data[index];
    }

    /**
     * Removes the element at the specified position in this list
     * @param index the index of the element to be removed
     * @return the element previously at the specified position
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    @Override
    public T remove(int index) {
        boundsCheck(index);
        int length = data.length - index;
        T value = (T) data[index];
        System.arraycopy(data, index + 1, data, index, length - 1);
        size--;
        return value;
    }

    /**
     * Replaces the element at the specified position in this list
     * with the new element
     * @param index index of the element to replace
     * @param element new element to be stored
     * @return the element previously at the specified position
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    @Override
    public T set(int index, T element) {
        boundsCheck(index);
        T oldValue = (T) data[index];
        data[index] = element;

        return oldValue;
    }

    /**
     * Adds the specified element at the specified position in this list
     * Shifts the element currently at that position to the right
     * @param index index of the element to be added
     * @param element element to be added
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    @Override
    public void add(int index, T element) {
        boundsCheck(index);
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
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        if (fromIndex < 0 || fromIndex >= size || fromIndex > toIndex) {
            boundsCheck(toIndex);
        }
        int length = toIndex - fromIndex + 1;
        T[] subListData = (T[]) new Object[length];
        System.arraycopy(data, fromIndex, subListData, 0, length);
        return Arrays.asList(subListData);
    }

    /**
     * @param o element to search for
     * @return the lowest index of the first occurrence of the specified element
     * in this list, or -1 if this list does not contain the element.
     */
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

    /**
     * @param o element to search for
     * @return the highest index of the first occurrence of the specified element
     * in this list, or -1 if this list does not contain the element.
     */
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

    /**
     * @return an array containing all of the elements in this list in proper
     * sequence.
     */
    @Override
    public Object[] toArray() {
        return Arrays.copyOf(data, size);
    }

    /**
     * @param a the array into which the elements of this list are to be stored
     * @return an array containing all of the elements in this list in proper
     * sequence.
     */
    @Override
    public <T1> T1[] toArray(T1[] a) {
        return (T1[]) Arrays.copyOf(data, size);
    }

    /**
     * @param c collection to be checked for containment in this list
     * @return true if this list contains all of the elements of the
     * specified collection
     */
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

    /**
     * Appends all of the elements in the specified collection to the end of
     * this list
     * @param c collection containing elements to be added to this list
     * @return true if this list was changed
     */
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

    /**
     * Appends all of the elements in the specified collection to the end of
     * this list at the specified position
     * @param index index at which to ADD the first element
     * @param c collection containing elements to be added to this list
     * @return true if this list was changed
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        boundsCheck(index);
        boolean wasAdded = false;
        for (Object element : c) {
            add(index, (T) element);
            wasAdded = true;
        }
        return wasAdded;
    }

    /**
     * Removes from this list all of its elements that are contained in the
     * specified collection
     * @param c collection containing elements to be removed from this list
     * @return true if this list was changed
     */
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

    /**
     * Retains only the elements in this list that are contained in the
     * specified collection
     * @param c collection containing elements to be retained in this list
     * @return true if this list was changed
     */
    @Override
    public boolean retainAll(Collection<?> c) {
        boolean wasRetained = false;
        Object[] temp = new Object[CAPACITY];
        int tempSize = 0;
        int tempIndex = 0;

        for (int i = 0; i < size; i++) {
            if (c.contains(data[i])) {
                temp[tempIndex++] = data[i];
                tempSize++;
                wasRetained = true;
            }
        }
        data = temp;
        size = tempSize;
        return wasRetained;
    }

    private void boundsCheck(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * @return an iterator over the elements in this list in proper sequence.
     * Iterator can also remove elements, changes are reflected in this list
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int cursor = 0;

            @Override
            public void remove() {
                CustomArrayList.this.remove(cursor--);
            }

            @Override
            public boolean hasNext() {
                return cursor != size;
            }

            @Override
            public T next() {
                return (T) data[cursor++];
            }
        };
    }

    /**
     * @return an list iterator over the elements in this list in proper sequence.
     * Iterator can also remove, set and add elements, changes are reflected in this list
     */
    @Override
    public ListIterator<T> listIterator() {
        return listIterator(0);
    }

    /**
     * @param index index of the first element to be returned
     * @return an list iterator over the elements in this list in proper sequence
     * starting at the specified position in the list.
     */
    @Override
    public ListIterator<T> listIterator(int index) {
        return new ListIterator<T>() {
            int cursor = index;

            @Override
            public boolean hasNext() {
                return cursor != size;
            }

            @Override
            public T next() {
                return (T) data[cursor++];
            }

            @Override
            public boolean hasPrevious() {
                return cursor != 0;
            }

            @Override
            public T previous() {
                return (T) data[cursor--];
            }

            @Override
            public int nextIndex() {
                if (cursor == 0) {
                    return -1;
                }
                return cursor + 1;
            }

            @Override
            public int previousIndex() {
                if (cursor == 0) {
                    return -1;
                }
                return cursor - 1;
            }

            @Override
            public void remove() {
                CustomArrayList.this.remove(cursor--);
            }

            @Override
            public void set(T t) {
                CustomArrayList.this.set(cursor, t);
            }

            @Override
            public void add(T t) {
                CustomArrayList.this.add(cursor++, t);
            }
        };
    }
}
