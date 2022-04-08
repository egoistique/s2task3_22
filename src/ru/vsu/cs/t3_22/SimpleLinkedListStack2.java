package ru.vsu.cs.t3_22;

public class SimpleLinkedListStack2<T> implements SimpleStack<T> {

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
    private int count = 0;

    @Override
    public void push(T value) {
        head = new SimpleLinkedListNode(value, head);
        count++;
    }

    @Override
    public T pop() throws Exception {
        T result = this.peek();
        head = head.next;
        count--;
        return result;
    }

    @Override
    public T peek() throws Exception {
        if (count == 0) {
            throw new Exception("Stack is empty");
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
