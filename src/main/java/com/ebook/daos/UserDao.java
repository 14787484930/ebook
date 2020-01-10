package com.ebook.daos;

import com.ebook.beans.user.User;
import com.ebook.beans.user.UserQuery;

import java.util.List;

public interface UserDao {

    public List<User> getUsers(UserQuery query);
    public User getByWeiXin(UserQuery query);
    public User getByOpenId(UserQuery query);
    public void save(User user);
    public User getById(String id);
    public void update(User user);
    public void delete(UserQuery query);
    public void updateScore(User user);

    public List<User> getsuperMarkets(UserQuery query); //加载本栋楼的超市
    public void updateBuildingNum(UserQuery userQuery); //用户首次进入小卖铺选择楼栋

    public void updatebuildingNumber(UserQuery userQuery); //为用户开通小卖铺
}
