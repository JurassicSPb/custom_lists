package com.epam.java.se;

import java.util.*;

/**
 * Class represents an implementation of Custom LinkedList with
 * dynamic capacity. Values are sorted in this List in the way they
 * were added.
 *
 * Created by Мария on 29.03.2017.
 */
public class CustomLinkedList<T> implements List<T> {

    private Node<T> head = new Node<>(null);
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
        return !head.hasNext();
    }

    /**
     * @param o element whose presence in this list is to be tested
     * @return true if this list contains the specified element.
     */
    @Override
    public boolean contains(Object o) {
        Node<T> node = head;
        while (node.hasNext()) {
            node = node.next;
            if (node.value == null) {
                if (o == null) {
                    return true;
                }
            } else if (node.value.equals(o)) {
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
        Node<T> iterator = head;
        while (iterator.hasNext()) {
            iterator = iterator.next;
        }
        iterator.next = new Node<>(t);
        size++;
        return true;
    }

    /**
     * Removes the first occurrence of the specified element from this list
     * @param o element to be removed from this list, if present
     * @return true if element was removes, false otherwise
     */
    @Override
    public boolean remove(Object o) {
        Node<T> current = head.next;
        Node<T> prev = head;
        while (current != null) {
            if (o.equals(current.value)) {
                prev.next = current.next;
                size--;
                return true;
            }
            prev = current;
            current = current.next;
        }
        return false;
    }

    /**
     * clears the entire list
     */
    @Override
    public void clear() {
        head = new Node<>(null);
        size = 0;
    }

    /**
     * Removes the element at the specified position in this list
     * @param index the index of the element to be removed
     * @return the element previously at the specified position
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    @Override
    public T remove(int index) {
        Node<T> current = getNodeByIndex(index - 1);
        size--;
        T value = current.next.value;
        current.next = current.next.next;
        return value;
    }

    /**
     * @param index index of the element to return
     * @return the element at the specified position in this list
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    @Override
    public T get(int index) {
        return getNodeByIndex(index).value;
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
        Node<T> node = getNodeByIndex(index);
        T oldValue = node.value;
        node.value = element;

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
        Node<T> prev = getNodeByIndex(index - 1);
        Node<T> current = prev.next;
        prev.next = new Node<>(element);
        prev.next.next = current;
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
        if (fromIndex > toIndex) {
            throw new IndexOutOfBoundsException();
        }
        int count = fromIndex;
        Node<T> startNode = getNodeByIndex(fromIndex);
        List<T> newList = new CustomLinkedList<>();
        while (startNode != null) {
            if (count <= toIndex) {
                newList.add(startNode.value);
            }
            startNode = startNode.next;
            count++;
        }
        return newList;
    }

    /**
     * @param o element to search for
     * @return the index of the first occurrence of the specified element
     * in this list, or -1 if this list does not contain the element.
     */
    @Override
    public int indexOf(Object o) {
        Node<T> node = head;
        int index = 0;
        while (node.hasNext()) {
            node = node.next;
            if (node.value == null) {
                if (o == null) {
                    return index;
                }
            } else if (node.value.equals(o)) {
                return index;
            }
            index++;
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
        for (int i = size; i >= 1; i--) {
            int index = i - 1;
            Node<T> last = getNodeByIndex(index);
            if (last.value == null) {
                if (o == null) {
                    return index;
                }
            } else if (last.value.equals(o)) {
                return index;
            }
        }
        return -1;
    }

    /**
     * @return Ran array containing all of the elements in this list in proper
     * sequence.
     */
    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];
        Node<T> node = head.next;
        for (int i = 0; i < size; i++) {
            array[i] = node.value;
            node = node.next;
        }
        return array;
    }

    /**
     * @param a the array into which the elements of this list are to be stored
     * @return an array containing all of the elements in this list in proper
     * sequence.
     */
    @Override
    public <T1> T1[] toArray(T1[] a) {
        Object[] array = new Object[size];
        Node<T> node = head.next;
        for (int i = 0; i < size; i++) {
            array[i] = node.value;
            node = node.next;
        }
        return (T1[]) array;
    }

    /**
     * @param c collection to be checked for containment in this list
     * @return if this list contains all of the elements of the
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
        List<Object> buffer = new CustomLinkedList<>();
        int tempSize = 0;

        for (Object element : c) {
            if (contains(element)) {
                buffer.add(element);
                tempSize++;
                wasRetained = true;
            }
        }
        clear();
        for (Object element : buffer) {
            add((T) element);
        }
        size = tempSize;
        return wasRetained;
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
            public boolean hasNext() {
                return cursor != size;
            }

            @Override
            public T next() {
                return getNodeByIndex(cursor++).value;
            }

            @Override
            public void remove() {
                CustomLinkedList.this.remove(cursor--);
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
                return getNodeByIndex(cursor++).value;
            }

            @Override
            public boolean hasPrevious() {
                return cursor != 0;
            }

            @Override
            public T previous() {
                return getNodeByIndex(cursor--).value;
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
                CustomLinkedList.this.remove(cursor--);
            }

            @Override
            public void set(T t) {
                getNodeByIndex(cursor).value = t;
            }

            @Override
            public void add(T t) {
                CustomLinkedList.this.add(cursor++, t);
            }
        };
    }

    private Node<T> getNodeByIndex(int index) {

        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        Node<T> current = head.next;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current;
    }

    private class Node<T> {

        private Node<T> next;
        private T value;

        public Node(T value) {
            this.value = value;
        }

        public boolean hasNext() {
            return next != null;
        }

    }
}