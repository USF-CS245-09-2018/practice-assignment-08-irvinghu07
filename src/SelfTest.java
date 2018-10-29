/**
 * Development IDE: IntelliJ IDEA
 * Author: irving
 * Project Name: cs245-assignment-08
 * Date: 2018-10-29
 */
public class SelfTest {
    public static void main(String[] args) throws Exception {
        LinkedList linkedList = new LinkedList();
        for (int i = 0; i < 5; i++) {
            linkedList.add(i, i);
        }
        System.out.println(linkedList.size());
        linkedList.printLinkedList();
        System.out.println(linkedList.remove(0));
        linkedList.printLinkedList();
        System.out.println(linkedList.size());
    }
}
