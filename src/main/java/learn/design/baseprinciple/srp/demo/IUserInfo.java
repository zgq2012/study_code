package learn.design.baseprinciple.srp.demo;

/**
 * 用户接口
 *
 * @author zgq
 * @date 2022/8/14
 */
public interface IUserInfo {

    /**
     * 设置姓名
     *
     * @param name 姓名
     */
    void setName(String name);

    /**
     * 删除用户
     *
     * @param userId 用户ID
     */
    void delete(Long userId);

    /**
     * 修改用户信息
     *
     * @param pw 修改的用户信息
     */
    public void updatePassword(String pw);
}
