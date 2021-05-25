package org.example.controller;


import org.example.pojo.parameter.TeskUploadPar;
import org.example.service.TeskService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ChenQ
 * @since 2021-05-22
 */
@RestController
@RequestMapping("/tesk")
public class TeskController {

    @Resource
    private TeskService teskService;

    //任务发布
    @RequestMapping("update")
    public String teskUpload(@Valid @RequestBody TeskUploadPar teskUpdatePar){
        if (teskService.teskUpload(teskUpdatePar)){
            return "Tesk Upload Success!";
        }else {
            return "TeskUpload Fail!";
        }
    }

}

