package ru.vsu.cs.t3_22;

public class SimpleLinkedListQueue2<T> implements SimpleQueue<T> {

//    protected T get(int i) {
//        checkEmptyError();
//        return getNode(index).value;
//    }


    private class SimpleLinkedListNode {
        public T value;
        public SimpleLinkedListNode next;

        public SimpleLinkedListNode(T value, SimpleLinkedListNode next) {
            this.value = value;
            this.next = next;
        }

        public SimpleLinkedListNode(T value) {
            this(value, null);
        }
    }

    private SimpleLinkedListNode head = null;  // first, top
    private SimpleLinkedListNode tail = null;  // last
    private int count = 0;



    protected SimpleLinkedListQueue2.SimpleLinkedListNode getNode(int index) {
        SimpleLinkedListQueue2.SimpleLinkedListNode curr = head;
        for (int i = 0; i < index; i++) {
            curr = curr.next;
        }
        return curr;
    }

    public T get(int index) throws SimpleLinkedList.SimpleLinkedListException {
        checkEmptyError();
        return (T) getNode(index).value;
    }

    private void checkEmptyError() throws SimpleLinkedList.SimpleLinkedListException {
        if (count == 0) {
            throw new SimpleLinkedList.SimpleLinkedListException("Empty list");
        }
    }

    @Override
    public void add(T value) {
        if (count == 0) {
            head = tail = new SimpleLinkedListNode(value);
        } else {
            tail.next = new SimpleLinkedListNode(value);
            tail = tail.next;
        }
        count++;
    }

    @Override
    public T remove() throws Exception {
        T result = element();
        head = head.next;
        if (count == 1) {
            tail = null;
        }
        count--;
        return result;
    }

    @Override
    public T element() throws Exception {
        if (count() == 0) {
            throw new Exception("Queue is empty");
        }
        return head.value;
    }

    @Override
    public int count() {
        return count;
    }

    @Override
    public boolean empty() {
        return count() == 0;
    }
}
