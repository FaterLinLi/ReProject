package org.example.service.impl;

import org.example.pojo.Piclist;
import org.example.mapper.PiclistMapper;
import org.example.pojo.parameter.AddPic;
import org.example.service.PiclistService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.util.GetOneId;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ChenQ
 * @since 2021-05-22
 */
@Service
public class PiclistServiceImpl extends ServiceImpl<PiclistMapper, Piclist> implements PiclistService {

    @Resource
    private PiclistMapper piclistMapper;

    @Override
    public boolean add(AddPic addPic) {

        GetOneId getOneId = new GetOneId("pic",11);
        String onlyId = getOneId.GetOnlyId();

        Piclist piclist = new Piclist();
        piclist.setInteskid(1);
        piclist.setPicId(onlyId);
        piclist.setTeskId(addPic.getTeskId());
        piclist.setPicUrl("WDNMD");
        piclistMapper.insert(piclist);

        return false;
    }
}
