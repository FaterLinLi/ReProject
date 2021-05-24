package org.example.util;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.example.mapper.UserMapper;
import org.example.pojo.User;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;


public class GetOneId {

    //ID头部
    private String head;
    //ID所在表
    private String block;
    //ID尾部长度
    private Integer tail_number;

    public GetOneId(String head, String block, int tail_number) {
        this.head = head;
        this.tail_number = tail_number;
        this.block = block;
    }

    public String GetOnlyId(){

        SnowFlakeUtil snowFlakeUtil = new SnowFlakeUtil(2,3);
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        Long number = snowFlakeUtil.nextId();
        String number_str = number.toString();
        String number_done = number_str.substring(number_str.length() - tail_number,number_str.length());
        wrapper.like(block,number_done);
        String id_done = head + number_done;
        return id_done;
    }


}
