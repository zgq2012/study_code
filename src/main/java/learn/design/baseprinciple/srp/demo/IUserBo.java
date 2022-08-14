package learn.design.baseprinciple.srp.demo;

/**
 * 用户属性接口
 *
 * @author zgq
 * @date 2022/8/14
 */
public interface IUserBo {
    /**
     * 设置姓名
     *
     * @param name 姓名
     */
    void setName(String name);

    /**
     * 设置组织
     *
     * @param org 组织
     */
    void setOrg(String org);
}
