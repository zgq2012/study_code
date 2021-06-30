package learn.leecode.arr;

/**
 * 14
 *
 * @author zgq
 */
public class LeCode14 {

    public static void main(String[] args) {
        getPre();
    }

    /**
     * 最长公共前缀(14)
     * 编写一个函数来查找字符串数组中的最长公共前缀。如果不存在公共前缀，返回空字符串 ""
     */
    private static void getPre() {
        String[] strs = {"flower", "flow", "flight"};
//        String[] strs = {"dog", "racecar", "car"};
//        String[] strs = {"aa", "ab"};
        long time1 = System.currentTimeMillis();
        doPreStr(strs);
        long time2 = System.currentTimeMillis();
        twoSelectPre(strs);
        long time3 = System.currentTimeMillis();
        // 最优解
        hxStr(strs);
        long time4 = System.currentTimeMillis();
        System.out.println("doPreStr-time:" + (time2 - time1));
        System.out.println("twoSelectPre-time:" + (time3 - time2));
        System.out.println("hxStr-time:" + (time4 - time3));
    }

    private static void hxStr(String[] strs) {
        checkStr(strs);
        // 横向扫描，1和2比较，然后公共和3比较，得到公共，再依次比较到最后...
        String pre = strs[0];
        for (int i = 1; i < strs.length; i++) {
            pre = compare(pre, strs[i]);
            if ("".equals(pre)) {
                return;
            }
        }
        System.out.println("hxStr:" + pre);
    }

    private static String compare(String pre, String str) {
        // 比较时，以最短长度的字符串为基准，不然会出现索引越界，且可能出现["aa","a"] 返回"aa"的情况;
        int min = Math.min(pre.length(), str.length());
        for (int i = 0; i < min; i++) {
            if (pre.charAt(i) != str.charAt(i)) {
                if (i == 0) {
                    return "";
                }
                return pre.substring(0, i);
            }
        }
        // 返回最短长度
        return pre.substring(0, min);
    }

    private static void twoSelectPre(String[] strs) {
        if (checkStr(strs)) {
            return;
        }
        // 找到数组中长度最短的字符串
        int minStrLen = Integer.MAX_VALUE;
        for (String str : strs) {
            minStrLen = Math.min(minStrLen, str.length());
        }
        int low = 0;
        int high = minStrLen;
        // 计算中间值
        // 以第一个字符串为基准
        while (low < high) {
            int mid = (high - low + 1) / 2 + low;
            // 是否为高的一半
            if (isAfter(strs, mid)) {
                // 如果在后一半，下次则以后半开始比较
                low = mid;
            } else {
                // 如果在前一半，下次则以前半比较
                high = mid - 1;
            }
        }
        System.out.println("twoSelectPre:" + strs[0].substring(0, low));
    }

    private static boolean isAfter(String[] strs, int mid) {
        String pre = strs[0];
        String subPre = pre.substring(0, mid);
        for (int i = 1; i < strs.length; i++) {
            if (!strs[i].startsWith(subPre)) {
                // 如果前一半不是前缀，直接返回
                return false;
            }
        }
        return true;
    }

    private static boolean checkStr(String[] strs) {
        if (strs == null || strs.length < 1) {
            System.out.println("pre :" + "");
            return true;
        }
        if (strs.length == 1) {
            System.out.println("pre :" + strs[0]);
            return true;
        }
        return false;
    }

    private static void doPreStr(String[] strs) {
        if (checkStr(strs)) {
            return;
        }
        // 默认第一个数据为公共标准
        String preStr = strs[0];

        int length = preStr.length();
        boolean flag = true;
        for (int j = 0; j < strs[0].length(); j++) {
            for (int i = 1; i < strs.length; i++) {
                if (!strs[i].startsWith(preStr)) {
                    // 不为前缀
                    flag = false;
                    preStr = preStr.substring(0, length - 1);
                    length--;
                    if (j == strs[0].length() - 1) {
                        System.out.println("pre :" + "");
                        return;
                    }
                }
            }
            if (flag) {
                break;
            }
        }
        System.out.println("doPreStr:" + preStr);
    }
}
