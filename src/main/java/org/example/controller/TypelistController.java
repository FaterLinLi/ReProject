package org.example.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.example.common.exception.BankAppException;
import org.example.common.result.MessageCode;
import org.example.common.result.ResultCode;
import org.example.mapper.TypelistMapper;
import org.example.pojo.Typelist;
import org.example.pojo.parameter.AddType;
import org.example.pojo.parameter.FindType;
import org.example.service.TypelistService;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/admin/typelist")
public class TypelistController {

    @Resource
    private TypelistService typelistService;

    @Resource
    private TypelistMapper typelistMapper;

    //添加类型
    @PostMapping("/add")
    public String addType(@Valid @RequestBody AddType addType){
        if (typelistService.addType(addType)){
            throw new BankAppException(ResultCode.SUCCESS, MessageCode.Typelist.ADDTYPE.SUCESS);
        }else {
            throw new BankAppException(ResultCode.ERROR,MessageCode.Typelist.ADDTYPE.ERROR);
        }
    }

    //查找类型
    @GetMapping("/find/{typename}")
    public Typelist findType(@Valid @PathVariable String typename){
        QueryWrapper<Typelist> wrapper = new QueryWrapper<>();
        wrapper.eq("type_name", typename);
        Typelist typelist = typelistMapper.selectOne(wrapper);
        if (typelist == null){
            throw new BankAppException(ResultCode.ERROR,MessageCode.Typelist.FINDTYPE.TYPE_NULL);
        }
        return typelist;
    }


}

