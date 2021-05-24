package org.example.service;

import org.example.pojo.Typelist;
import com.baomidou.mybatisplus.extension.service.IService;
import org.example.pojo.parameter.AddType;
import org.example.pojo.parameter.FindType;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ChenQ
 * @since 2021-05-22
 */
public interface TypelistService extends IService<Typelist> {

    //添加类型
    boolean addType(AddType addType);



}
