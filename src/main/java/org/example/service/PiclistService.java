package org.example.service;

import org.example.pojo.Piclist;
import com.baomidou.mybatisplus.extension.service.IService;
import org.example.pojo.parameter.AddPic;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ChenQ
 * @since 2021-05-22
 */
public interface PiclistService extends IService<Piclist> {

    boolean add(AddPic addPic);
}
