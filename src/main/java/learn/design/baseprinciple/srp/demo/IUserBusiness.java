package learn.design.baseprinciple.srp.demo;

/**
 * 用户信息业务操作接口
 *
 * @author zgq
 * @date 2022/8/14
 */
public interface IUserBusiness {
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
    void updatePassword(String pw);
}
