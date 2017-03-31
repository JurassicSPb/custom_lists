package com.epam.java.se;

import java.util.*;

/**
 * Created by Мария on 29.03.2017.
 */
public class CustomLinkedList<T> implements List<T> {

    private Node<T> head = new Node<>(null);
    private int size = 0;

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return !head.hasNext();
    }

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

    @Override
    public void clear() {
        head = new Node<>(null);
        size = 0;
    }

    @Override
    public T remove(int index) {
        Node<T> current = getNodeByIndex(index - 1);
        size--;
        T value = current.next.value;
        current.next = current.next.next;
        return value;
    }

    @Override
    public T get(int index) {
        return getNodeByIndex(index).value;
    }

    @Override
    public T set(int index, T element) {
        Node<T> node = getNodeByIndex(index);
        T oldValue = node.value;
        node.value = element;

        return oldValue;
    }

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
     */
    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        if (fromIndex > toIndex) {
            throw new IndexOutOfBoundsException();
        }
        int count = fromIndex;
        Node<T> startNode = getNodeByIndex(fromIndex);
        List<T> newList = new LinkedList<>();
        while (startNode != null) {
            if (count <= toIndex) {
                newList.add(startNode.value);
            }
            startNode = startNode.next;
            count++;
        }
        return newList;
    }

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
        boolean wasRetained = false;
        LinkedList<Object> buffer = new LinkedList<>();
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
        };
    }

    @Override
    public ListIterator<T> listIterator() {
        return listIterator(0);
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return new ListIterator<T>() {
            int cursor = index;

            @Override
            public boolean hasNext() {
                return cursor < size;
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