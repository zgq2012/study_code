package learn.leecode.str;

import java.util.ArrayList;
import java.util.List;

/**
 * 上N休M的序列
 *
 * @author zgq
 * @version v4.3.0
 * @since 2021/9/27
 **/
public class WorkAndRest {

    public static void main(String[] args) {
        for (int n = 1; n <= 5; n++) {
            for (int m = 1; m <= 5; m++) {
                if (n + m >= 7) {
                    continue;
                }
                System.out.println("上" + n + "休" + m + ",结果" + getList(n, m));
            }
        }
    }

    private static List<String> getList(int n, int m) {
        int week = 7;
        StringBuilder oldStr = new StringBuilder();
        for (int i = 0; i < n; i++) {
            oldStr.append('1');
        }
        for (int i = 0; i < m; i++) {
            oldStr.append('0');
        }
        while (oldStr.length() <= week * 2) {
            oldStr.append(oldStr);
        }

        List<String> res = new ArrayList<>();
        int index = 0;
        while (true) {
            String sub = oldStr.substring(index, week + index);
            if (res.contains(sub)) {
                break;
            }
            res.add(sub);
            // 使用余数来减少字符串拼接
            index = index % week + week % (n + m);
        }
        return res;
    }
}
