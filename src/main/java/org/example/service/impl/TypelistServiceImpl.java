package org.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.poi.ss.formula.functions.T;
import org.example.common.exception.BankAppException;
import org.example.common.result.MessageCode;
import org.example.common.result.ResultCode;
import org.example.pojo.Typelist;
import org.example.mapper.TypelistMapper;
import org.example.pojo.parameter.AddType;
import org.example.pojo.parameter.ChangeTypePar;
import org.example.pojo.parameter.FindType;
import org.example.service.TypelistService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ChenQ
 * @since 2021-05-22
 */
@Service
public class TypelistServiceImpl extends ServiceImpl<TypelistMapper, Typelist> implements TypelistService {

    @Resource
    private TypelistMapper typelistMapper;

    @Override
    public boolean addType(AddType addType) {

        //类型名是否存在
        QueryWrapper<Typelist> queryWrapper_typename = new QueryWrapper<>();
        queryWrapper_typename.eq("type_name", addType.getTypeName());
        Typelist typelist_name = typelistMapper.selectOne(queryWrapper_typename);
        if (typelist_name != null){
            throw new BankAppException(ResultCode.ERROR, MessageCode.Typelist.ADDTYPE.TYPENAME_EXIST);
        }

        //插入数据
        Typelist typelist = new Typelist();
        typelist.setTypeName(addType.getTypeName());
        int result = typelistMapper.insert(typelist);
        if (result<1){
            return false;
        }else{
            return true;
        }

    }

    @Override
    public boolean changeType(ChangeTypePar changeTypePar) {

        QueryWrapper<Typelist> wrapper_changename = new QueryWrapper<>();
        wrapper_changename.eq("type_name",changeTypePar.getTypeName());
        Typelist typelist_name = typelistMapper.selectOne(wrapper_changename);
        if (typelist_name != null){
            throw new BankAppException(ResultCode.ERROR,MessageCode.Typelist.ADDTYPE.TYPENAME_EXIST);
        }
        Typelist typelist = new Typelist();
        typelist.setTypeId(changeTypePar.getTypeId());
        typelist.setTypeName(changeTypePar.getTypeName());
        typelistMapper.updateById(typelist);
        return true;
    }


}
