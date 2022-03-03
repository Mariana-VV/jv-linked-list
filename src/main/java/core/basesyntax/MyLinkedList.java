package core.basesyntax;

import java.util.List;
import org.w3c.dom.Node;

public class MyLinkedList<T> implements MyLinkedListInterface<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size;

    static class Node<T> {
        private Node<T> prev;
        private T value;
        private Node<T> next;

        public Node(Node<T> prev, T value, Node<T> next) {
            this.prev = prev;
            this.value = value;
            this.next = next;
        }
    }

    public Node findByIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node<T> currentNode;
        if (index < size / 2) {
            currentNode = head;
            for (int i = 0; i < index; i++) {
                currentNode = currentNode.next;
            }
        } else {
            currentNode = tail;
            for (int i = size - 1; i > index; i--) {
                currentNode = currentNode.prev;
            }
        }
        return currentNode;
    }

    @Override
    public void add(T value) {
        if (value == null) {
            return;
        }
        Node<T> newNode = new Node<T>(null, value, null);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.prev = tail;
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    @Override
     public void add(T value, int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node<T> currentNode = findByIndex(index);
        Node<T> newNode = new Node<T>(currentNode.prev,value,currentNode);
        newNode.next = currentNode.next;
        currentNode.next.prev = newNode;
        newNode.prev = currentNode;
        currentNode.next = newNode;
        size++;
    }

    @Override
     public void addAll(List<T> list) {
    }

    @Override
     public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        int counter = 0;
        Node<T> currentNode = head;

        while (currentNode != null) {
            if (counter == index) {
                return currentNode.value;
            }
            counter++;
            currentNode = currentNode.next;
        }
        return null;
    }

    @Override
     public T set(T value, int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        if (value == null) {
            return null;
        }
        int counter = 0;
        Node<T> currentNode = head;
        while (currentNode != null) {
            if (counter == index) {
                currentNode.value = value;
                break;
            }
            counter++;
            currentNode = currentNode.next;
        }
        return value;
    }

    public void unlink(Node currentNode) {
        currentNode.prev = currentNode.next;
        currentNode.next.prev = currentNode.prev;
        currentNode.prev.next = currentNode.next;
    }

    @Override
     public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node<T> currentNode = findByIndex(index);

        if (currentNode.prev == null) {
            head = currentNode.next;
            size--;
        } else if (currentNode.next == null) {
            tail = currentNode.prev;
            size--;
        } else {
            unlink(currentNode);
            size--;
        }
        return currentNode.value;
    }

    @Override
     public boolean remove(T object) {
        boolean result = false;
        Node<T> currentNode = head;

        while (currentNode != null) {
            if ((currentNode.value == object) || (currentNode.value != null
                    && (currentNode.value).equals(object))) {
                unlink(currentNode);
                result = true;
                size--;
                break;
            }
            currentNode = currentNode.next;
        }
        return result;
    }

    @Override
     public int size() {
        return size;
    }

    @Override
     public boolean isEmpty() {
        return size == 0;
    }
}
