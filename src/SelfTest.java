/**
 * Development IDE: IntelliJ IDEA
 * Author: irving
 * Project Name: cs245-assignment-08
 * Date: 2018-10-29
 */
public class SelfTest {
    public static void main(String[] args) throws Exception {
        String[] data = {"irving", "pipi", "yarly"};
        LinkedList linkedList = new LinkedList();
        for (int i = 0; i < data.length; i++) {
            linkedList.add(i, i);
        }
        for (int i = data.length - 1; i >= 0; i--) {
            System.out.println(linkedList.remove(i));
        }

    }
}
