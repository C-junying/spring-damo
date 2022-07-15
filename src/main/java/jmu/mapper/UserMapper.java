package jmu.mapper;

import jmu.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {

    List<User> selectAll();     //返回所有用户
    int deleteUser(String userID);      //删除用户

    int insertUser(User user);          //插入用户

    User selectUser(String userID);     //根据ID返回用户

    int updateUser(User user);          //更新用户
}
