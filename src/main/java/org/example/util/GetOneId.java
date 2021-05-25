package org.example.util;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.poi.ss.formula.functions.T;


public class GetOneId {

    //ID头部
    private String head;
    //ID尾部长度
    private Integer tail_number;

    public GetOneId(String head, int tail_number) {
        this.head = head;
        this.tail_number = tail_number;
    }

    public String GetOnlyId(){

        SnowFlakeUtil snowFlakeUtil = new SnowFlakeUtil(2,3);
        Long number = snowFlakeUtil.nextId();
        String number_str = number.toString();
        String number_done = number_str.substring(number_str.length() - tail_number,number_str.length());
        String id_done = head + number_done;
        return id_done;
    }


}
