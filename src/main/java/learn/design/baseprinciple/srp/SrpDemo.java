package learn.design.baseprinciple.srp;

import learn.design.baseprinciple.srp.demo.IUserBo;
import learn.design.baseprinciple.srp.demo.IUserBusiness;
import learn.design.baseprinciple.srp.demo.IUserInfo;

/**
 * 单一职责原则 SingleResponsibilityPrinciple
 * *应且仅有一个原因引起类的变更*
 *
 * @author zgq
 * @date 2022/8/12
 */
public class SrpDemo {
    public static void main(String[] args) {
        // 统一处理
        IUserInfo userInfo = new IUserInfo() {
            @Override
            public void setName(String name) {
                // 属性操作
                System.out.println("执行name属性操作!");
            }

            @Override
            public void delete(Long userId) {
                // 业务操作
                System.out.println("执行业务操作!");
            }

            @Override
            public void updatePassword(String pw) {
                // 业务操作
            }
        };
        // 属性操作，
        userInfo.setName("zgq");
        // 用户业务逻辑操作,
        userInfo.delete(124L);


        // 遵循SRP原则
        // 若需要对用户属性操作使用IUserBo
        IUserBo iUserBo = new IUserBo() {
            @Override
            public void setName(String name) {
                // 属性操作
                System.out.println("执行name属性操作!");
            }

            @Override
            public void setOrg(String org) {

            }
        };
        iUserBo.setName("zgq");
        // 新增属性操作，只需要在属性接口上变更，
        iUserBo.setOrg("d");

        // 若需要用户业务操作使用IUserBusiness
        IUserBusiness userBusiness = new IUserBusiness() {
            @Override
            public void delete(Long userId) {

            }

            @Override
            public void updatePassword(String pw) {
                // 业务操作
                System.out.println("执行业务操作!");
            }
        };
        userBusiness.updatePassword("pw");

        // 优点：
        // 1、类的复杂性降低，可读性提高
        // 2、可维护性增加
        // 3、变更风险降低，有利于扩展
    }
}
