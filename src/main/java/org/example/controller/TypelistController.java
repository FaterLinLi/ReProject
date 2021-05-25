package org.example.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.example.common.exception.BankAppException;
import org.example.common.result.MessageCode;
import org.example.common.result.ResultCode;
import org.example.mapper.TypelistMapper;
import org.example.pojo.Typelist;
import org.example.pojo.parameter.AddType;
import org.example.pojo.parameter.ChangeTypePar;
import org.example.service.TypelistService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
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

    //修改类型
    @RequestMapping("/change")
    public String typeChange(@Valid @RequestBody ChangeTypePar changeTypePar){
        if (typelistService.changeType(changeTypePar)){
            return "Change type success!";
        }else {
            return "Change type false!";
        }
    }

    //列表分页查询
    @GetMapping("/find/page/{pageNum}")
    public List<Typelist> queryTypeList(@PathVariable long pageNum){
        Page<Typelist> page = new Page<>(pageNum, 20);
        typelistMapper.selectPage(page,null);
        return page.getRecords();
    };

    //删除类型
    @PostMapping("/delete/{typeId}")
    public String deleteTypeById(@PathVariable String typeId){
        typelistMapper.deleteById(typeId);
        return "Delete Success!";
    }



}

