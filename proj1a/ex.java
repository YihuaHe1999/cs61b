public class ex {
    public static void main(String[] args) {
        ArrayDeque<Integer> a = new ArrayDeque<>();
        for (int index = 0; index < 40; index++) {
            a.addLast(index );
        }
        a.printDeque();
        for (int index = 0; index < 10; index++) {
            a.removeFirst();
        }
        for (int index = 0; index < 23; index++) {
            a.removeLast();
        }
        a.printDeque();
        System.out.println(a.get(1));
    }
}
