package learn.leecode.arr;

import cn.hutool.core.util.StrUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * test
 *
 * @author zgq
 * @version v4.2.0
 * @since 2021/12/1
 **/
public class Test222 {
    private String sex;

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public static void main(String[] args) {

        LinkVad<Object> linkVad = new LinkVad<>();
        linkVad.next(123, v -> Integer.parseInt(v.toString()) > 100, "参数必须大于100");
        linkVad.next("zxj123432352545", v -> v.toString().startsWith("zxj"), "姓名必须是zxj开头");
        linkVad.next(v -> StrUtil.isNotEmpty(v.toString()), "姓名不能为空");
        linkVad.next(v -> v.toString().length() <= 10, "姓名不能大于10个字节");

        String validate = linkVad.validate();

        System.out.println("validate = " + validate);

        if (true) {
            return;
        }

        String[] fileNames = {"你的组别1", "你的组别2", "你的组别6", "你的组别11",
                "你的组别7", "你的组别8", "你的组别9", "你的组别10",
                "我的组别7", "我的组别8", "我的组别9", "我的组别10",
                "你的组别3", "你的组别4", "你的组别5", "你的组别12"};

        char[][] list = getList(fileNames);
        Arrays.sort(list, new ChineseSortCp());
//
        List<String> res = new ArrayList<>();

        for (int i = 0; i < fileNames.length; i++) {
            res.add(String.valueOf(list[i]));
        }

        System.out.println(res);
    }

    private static char[][] getList(String[] fileNames) {
        char[][] chFileNames = new char[fileNames.length][];
        for (int i = 0; i < fileNames.length; i++) {
            chFileNames[i] = fileNames[i].toCharArray();
        }
        return chFileNames;
    }

    public static class InnerClassTest {
        private String name;
        private String realName;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getRealName() {
            return realName;
        }

        public void setRealName(String realName) {
            this.realName = realName;
        }
    }
}
