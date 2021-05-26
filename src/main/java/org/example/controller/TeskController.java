package org.example.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.formula.functions.T;
import org.example.common.exception.BankAppException;
import org.example.common.result.MessageCode;
import org.example.common.result.R;
import org.example.common.result.ResultCode;
import org.example.mapper.TeskMapper;
import org.example.pojo.DataJson;
import org.example.pojo.Tesk;
import org.example.pojo.User;
import org.example.pojo.parameter.GetTeskPar;
import org.example.pojo.parameter.PassTeskPar;
import org.example.pojo.parameter.TeskCoursePar;
import org.example.pojo.parameter.TeskUploadPar;
import org.example.service.TeskService;
import org.example.util.UploadUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;

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
    @Resource
    private TeskMapper teskMapper;

    //任务发布
    @RequestMapping("update")
    public String teskUpload(@Valid @RequestBody TeskUploadPar teskUpdatePar){
        if (teskService.teskUpload(teskUpdatePar)){
            return "Tesk Upload Success!";
        }else {
            return "TeskUpload Fail!";
        }
    }

    //任务列表查询
    @GetMapping("/page/{pageNum}")
    public List<Tesk> queryTeskList(@PathVariable long pageNum){
        Page<Tesk> page = new Page<>(pageNum, 10);
        teskMapper.selectPage(page,null);
        return page.getRecords();
    };

    //任务列表查询
    @ApiOperation(value ="根据任务名模糊查找任务")
    @RequestMapping("find/{pageNum}")
    public R queryTeskByLimit(
            @PathVariable Long pageNum,     //当前页
            @RequestBody TeskCoursePar teskCoursePar){

        //创建一个Page对象，传入当前页和要查询记录数
        Page<Tesk> pageCourse = new Page<>(pageNum,10);

        //调用服务层的查询方法，传入Page对象和查询条件对象
        teskService.pageCourseQuery(pageCourse,teskCoursePar);

        //查询结束之后，Page对象就存在数据了，此时可以
        //通过该Page对象获取对应的行记录和总记录数
        List<Tesk> rows = pageCourse.getRecords();
        long total = pageCourse.getTotal();
        //统一返回结果
        return R.ok().data("total",total).data("rows",rows);
    }

    //逻辑删除
    @PutMapping("/delete/{teskId}")
    public String deleteTeskById(@PathVariable String teskId){
        teskMapper.deleteById(teskId);
        return "Delete Success!";
    }

    //任务审核通过
    @PutMapping("/pass/{teskId}")
    public String passTeskById(@PathVariable String teskId){

//        Tesk tesk = new Tesk();
//        tesk.setTeskId(teskId);
//        tesk.setState("1");
//        teskMapper.updateById(tesk);
//        return  "Tesk Pass Success!";

        if (teskService.passTesk(teskId)){
            return "Tesk Pass Success!";
        }else {
            return "Tesk Pass Fail!";
        }
    }

    @PutMapping("/get/{teskpackId}/{userId}")
    public String getTeskById(@PathVariable String teskpackId, @PathVariable String userId){
        if (teskService.getTesk(teskpackId,userId)){
            return "承接成功";
        }else {
            return "承接失败";
        }
    }

    @RequestMapping("/image")
    @ResponseBody
    public DataJson image(MultipartFile file){
        //调用工具类完成文件上传
        String imagePath = UploadUtils.upload(file);
        System.out.println(imagePath);
        DataJson dataJson = new DataJson();
        if (imagePath != null){
            //创建一个HashMap用来存放图片路径
            HashMap hashMap = new HashMap();
            hashMap.put("src",imagePath);
            dataJson.setCode(0);
            dataJson.setMsg("上传成功");
            dataJson.setData(hashMap);
        }else{
            dataJson.setCode(0);
            dataJson.setMsg("上传失败");
        }
        return dataJson;
    }


}

