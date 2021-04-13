package learn.leecode.testcode;

import java.util.ArrayList;
import java.util.List;

/**
 * testCode1
 *
 * @author zgq
 */
public class TestCode1 {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        System.out.println(list.subList(1, 3));
    }
}
