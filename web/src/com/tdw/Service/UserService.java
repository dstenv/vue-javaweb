package com.tdw.Service;

import com.tdw.pojo.User;

public interface UserService {
    /**
     * 注册用户
     * @param user
     */
    public void registUser(User user);

    /**
     * 查找用户id
     * @param username
     * @return
     */
    public User findUserid(String username);

    /**
     * 根据用户id 查询用户信息
     * @param userid
     * @return
     */
    public User findUserByuserid(Integer userid);

    /**
     * 登录
     * @param user
     * @return 如果返回null，说明登录失败，返回有值，是登录成功
     */
    public User login(User user);

    /**
     * 检查 用户名是否可用
     * @param username
     * @return 返回true表示用户名已存在，返回false表示用户名可用
     */
    public boolean existsUsername(String username);

    /**
     * 修改用户名
     * @param user
     * @return
     */
     public int updateUsername(User user);

    /**
     * 修改登录密码
     * @param user
     * @return
     */
    public int updateUserpassword(User user);

    /**
     * 根据用户名查找用户信息
     * @param username
     * @return
     */
     public User findUser(String username);
}

