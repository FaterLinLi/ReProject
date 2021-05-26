package org.example.controller;


import org.example.mapper.PiclistMapper;
import org.example.pojo.parameter.AddPic;
import org.example.service.PiclistService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ChenQ
 * @since 2021-05-22
 */
@RestController
@RequestMapping("/piclist")
public class PiclistController {

    @Resource
    private PiclistService piclistService;

    @RequestMapping("/add")
    public String addPic(@RequestBody AddPic addPic){
        if (piclistService.add(addPic)){
            return "NMD";
        }else {
            return "WSM";
        }
    }

}

