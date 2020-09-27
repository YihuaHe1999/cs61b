public class ArrayDeque<T> {
    /**
     * Notice the status of Full(isFull()) and Empty(isEmpty())
     * Notice how to copy existing array to a new one
     * Important: integer/integer is integer !
     *  You need "(double)integer/integer" to get a double result!!
     */
    private int head;
    private int tail;
    private T[] list;

    private static final int START_SIZE = 8; //it is better.

    public ArrayDeque() {
        head = 0;
        tail = 0;
        list = (T[]) new Object[8]; /** notice the constructor of arrays*/
    }

    private void arrayCopy(int size) {
        /** create new array*/
        T[] temp = (T[]) new Object[size];
        int p = head;
        int index = 0;
        for (; p != tail; index++) {
            temp[index] = list[p];
            p = (p + 1) % list.length;
        }
        list = temp;
        head = 0;
        tail = index;
    }

    private boolean isFull() {
        return (tail + 1) % list.length == head;
    }

    public void addFirst(T item) {
        if (isFull()) {
            arrayCopy(list.length + 8);
        }
        head = (head - 1 + list.length) % list.length;
        list[head] = item;
    }

    public void addLast(T item) {
        if (isFull()) {
            arrayCopy(list.length + 8);
        }
        list[tail] = item;
        tail = (tail + 1) % list.length;
    }

    public boolean isEmpty() {
        return head == tail;
    }

    public int size() {
        return (tail - head + list.length) % list.length;
    }

    public void printDeque() {
        int p = head;
        while (p != tail) {
            System.out.print(list[p] + " ");
            p = (p + 1) % list.length;
        }
        System.out.println();
    }

    public T removeFirst() {
        if (this.isEmpty()) {
            return null;
        }
        T dele = list[head];
        head = (head + 1) % list.length;
        if ((double) this.size() / list.length < 0.25 && list.length >= 16) {
            arrayCopy((list.length / 2));
        }
        return dele;
    }

    public T removeLast() {
        if (this.isEmpty()) {
            return null;
        }
        tail = (tail - 1 + list.length) % list.length;
        T dele = list[tail];
        if ((double) this.size() / list.length < 0.25 && list.length >= 16) {
            arrayCopy(list.length / 2);
        }
        return dele;
    }

    public T get(int index) {
        if (this.size() <= index) {
            return null;
        }
        return list[(head + index) % list.length];
    }
}
