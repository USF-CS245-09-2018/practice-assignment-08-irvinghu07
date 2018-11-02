/**
 * Development IDE: IntelliJ IDEA
 * Author: irving
 * Project Name: cs245-assignment-08
 * Date: 2018-10-29
 */
public class SelfTest {
    public static void main(String[] args) throws Exception {
        Practice8Test practice8Test = new Practice8Test();
        for (int i = 0; i < 10; i++) {
            System.out.println(String.format("running time:%d time:%d\n", i, practice8Test.timingTest()));
        }
    }
}
