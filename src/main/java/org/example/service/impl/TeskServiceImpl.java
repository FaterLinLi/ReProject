package org.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.models.auth.In;
import org.apache.poi.ss.formula.functions.T;
import org.example.common.exception.BankAppException;
import org.example.common.result.MessageCode;
import org.example.common.result.ResultCode;
import org.example.mapper.UserMapper;
import org.example.pojo.Tesk;
import org.example.mapper.TeskMapper;
import org.example.pojo.User;
import org.example.pojo.parameter.TeskUploadPar;
import org.example.service.TeskService;
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
public class TeskServiceImpl extends ServiceImpl<TeskMapper, Tesk> implements TeskService {

    @Resource
    private TeskMapper teskMapper;
    @Resource
    private UserMapper userMapper;


    @Override
    public boolean teskUpload(TeskUploadPar teskUpdatePar) {

        //判断用户积分是否够用
        //获取上传用户
        QueryWrapper<User> lock_user = new QueryWrapper<>();
        lock_user.eq("user_id",teskUpdatePar.getUserId());
        //获取上传用户积分
        Integer user_points = userMapper.selectOne(lock_user).getUserPoints();
        //若积分不足
        if (user_points <= teskUpdatePar.getPayment()){
            throw new BankAppException(ResultCode.ERROR, MessageCode.Tesk.UPLOAD.PAYMENT_NEED);
        }

        //判断标签数量
        switch (teskUpdatePar.getTagNum()){
            case 3:
                if (teskUpdatePar.getTag03() == null)
                    throw new BankAppException(ResultCode.ERROR,MessageCode.Tesk.UPLOAD.TAG_NULL);
                break;
            case 4:
                if (teskUpdatePar.getTag04() == null)
                    throw new BankAppException(ResultCode.ERROR,MessageCode.Tesk.UPLOAD.TAG_NULL);
        }

        //生成唯一任务ID
        GetOneId getOneId = new GetOneId("tesk",10);
        String onlyId = getOneId.GetOnlyId();
        QueryWrapper<Tesk> find_tesk_id = new QueryWrapper<>();
        find_tesk_id.eq("tesk_id",onlyId);
        Tesk tesk_id = teskMapper.selectOne(find_tesk_id);
        if (tesk_id != null){
            onlyId = getOneId.GetOnlyId();
            tesk_id = teskMapper.selectOne(find_tesk_id);
        }
        return false;

        //格式无误，插入数据

    }
}
