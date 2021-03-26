package learn.leecode.other;

import java.util.HashMap;
import java.util.Map;

/**
 * 299
 *
 * @author zgq
 */
public class LeCode299 {

    public static void main(String[] args) {
        String secret = "1245";
        String guess = "4218";
        String hint = getHint(secret, guess);
        System.out.println(hint);
    }

    /**
     * (299)猜数字游戏
     * 你在和朋友一起玩 猜数字（Bulls and Cows）游戏，该游戏规则如下：
     * 你写出一个秘密数字，并请朋友猜这个数字是多少。
     * 朋友每猜测一次，你就会给他一个提示，告诉他的猜测数字中有多少位属于数字和确切位置都猜对了（称为“Bulls”, 公牛），
     * 有多少位属于数字猜对了但是位置不对（称为“Cows”, 奶牛）。
     * 朋友根据提示继续猜，直到猜出秘密数字。
     * 请写出一个根据秘密数字和朋友的猜测数返回提示的函数，
     * 返回字符串的格式为 xAyB ，x 和 y 都是数字，A 表示公牛，用 B 表示奶牛。
     * xA 表示有 x 位数字出现在秘密数字中，且位置都与秘密数字一致。
     * yB 表示有 y 位数字出现在秘密数字中，但位置与秘密数字不一致。
     * 请注意秘密数字和朋友的猜测数都可能含有重复数字，每位数字只能统计一次。
     * <p>
     * 示例 1:
     * 输入: secret = "1807", guess = "7810"      输出: "1A3B"
     * 解释: 1 公牛和 3 奶牛。公牛是 8，奶牛是 0, 1 和 7。
     * <p>
     * 示例 2:
     * 输入: secret = "1123", guess = "0111"      输出: "1A1B"
     * 解释: 朋友猜测数中的第一个 1 是公牛，第二个或第三个 1 可被视为奶牛。
     * <p>
     * 说明: 你可以假设秘密数字和朋友的猜测数都只包含数字，并且它们的长度永远相等。
     */
    public static String getHint(String secret, String guess) {
        int x = 0;
        int y = 0;
        // 使用map计数，数字0-9，或者a-z都可以使用arr[]计数更方便，直接使用下标代替char
        Map<Character, Integer> cache = new HashMap<>(secret.length());
        // 使用一次循环
        for (int i = 0; i < secret.length(); i++) {
            char si = secret.charAt(i);
            char gi = guess.charAt(i);
            if (si == gi) {
                // 当前位置值相等时，
                x++;
                continue;
            }
            // si为null时，初始化0
            cache.putIfAbsent(si, 0);

            // 最开始为0，若gi已经被si填过值，则y++,并且回复当前位置数量+1
            if (cache.get(si) < 0) {
                // 表示改该数占一个位置
                y++;
            }
            cache.put(si, cache.get(si) + 1);

            // gi为null时，初始化0
            cache.putIfAbsent(gi, 0);

            // 最开始为0，若gi已经被si填过值，则y++,并且回复当前位置数量-1
            if (cache.get(gi) > 0) {
                y++;
            }
            cache.put(gi, cache.get(gi) - 1);
        }

        return x + "A" + y + "B";
    }
}
