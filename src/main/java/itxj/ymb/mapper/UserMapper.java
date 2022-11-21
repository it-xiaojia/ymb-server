package itxj.ymb.mapper;

import itxj.ymb.pojo.User;

/**
 * 用户数据接口
 */
public interface UserMapper {
    /**
     * 通过用户ID查询用户
     *
     * @param id 用户ID
     * @return 返回用户对象
     */
    User findUserById(Integer id);

    /**
     * 通过用户对象查询用户
     *
     * @param user 用户对象
     * @return 返回用户对象
     */
    User findUserByObject(User user);

    /**
     * 更新用户数据（id和账号除外）
     *
     * @param user 用户对象
     */
    void updateUser(User user);
}
