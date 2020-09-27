public class LinkedListDeque<Type> {
    private class IntNode {
        /** notice the type of item in nested class*/
        /** private?? and the constructor method has no decoration in the front*/
        /** you can not use "static", since you must use <Type> in the upper class*/
        private IntNode prev;
        private Type item;
        private IntNode next;

        IntNode(Type i, IntNode p, IntNode n) {
            prev = p;
            next = n;
            item = i;
        }
    }

    private IntNode sentinel;
    private int size;

    public LinkedListDeque() {
        size = 0;
        /** item initiate ? to prevent the error of item type*/
        sentinel = new IntNode(null, null, null);
        sentinel.prev = sentinel; /** not sure*/
        sentinel.next = sentinel;
    }

    public void addFirst(Type item) {
        size += 1;
        IntNode first = new IntNode(item, sentinel, sentinel.next);
        sentinel.next = first;
        first.next.prev = first;
    }

    public void addLast(Type item) {
        size += 1;
        IntNode last = new IntNode(item, sentinel.prev, sentinel);
        sentinel.prev = last;
        last.prev.next = last;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        IntNode p = sentinel;
        while (p.next != sentinel) {
            System.out.print(p.next.item + " ");
            p = p.next;
        }
        System.out.println();
    }

    public Type removeFirst() {
        if (this.isEmpty()) {
            return null;
        }
        size -= 1;
        IntNode temp = sentinel.next;
        sentinel.next = temp.next;
        temp.next.prev = sentinel;
        return temp.item;
    }

    public Type removeLast() {
        if (this.isEmpty()) {
            return null;
        }
        size -= 1;
        IntNode temp = sentinel.prev;
        sentinel.prev = temp.prev;
        temp.prev.next = sentinel;
        return temp.item; /** don't forget the ".item"*/
    }

    public Type get(int index) {
        if (index > size - 1 || index < 0) {
            return null;
        }
        IntNode temp = sentinel.next;
        while (index == 0) {
            temp = temp.next;
            index -= 1;
        }
        return temp.item;
    }

    private Type getRecursiveHelper(int index, IntNode temp) { /** remain to be checked*/
        if (index == 0) {
            return temp.item;
        }
        return getRecursiveHelper(index - 1, temp.next);
    }

    public Type getRecursive(int index) {
        if (index > size - 1 || index < 0) {
            return null;
        }
        return getRecursiveHelper(index, sentinel.next);
    }
}
