package org.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mchange.lang.IntegerUtils;
import io.swagger.models.auth.In;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.formula.functions.T;
import org.example.common.exception.BankAppException;
import org.example.common.result.MessageCode;
import org.example.common.result.ResultCode;
import org.example.mapper.UserMapper;
import org.example.pojo.Tesk;
import org.example.mapper.TeskMapper;
import org.example.pojo.User;
import org.example.pojo.parameter.TeskCoursePar;
import org.example.pojo.parameter.TeskUploadPar;
import org.example.service.TeskService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.util.GetOneId;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;
import java.util.Date;

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

        //根据图片数量计算任务所需积分以及人数
        //所需积分
        Integer point_cost = teskUpdatePar.getPicNum()*3*5;
        //所需人数
        Integer need_num = null;
        if (teskUpdatePar.getPicNum()<10){
            need_num = 3;
        }else {
            double a = teskUpdatePar.getPicNum()/10;
            need_num = (int)Math.rint(a)*3;
        }

        //判断用户积分是否够用
        //获取上传用户
        QueryWrapper<User> lock_user = new QueryWrapper<>();
        lock_user.eq("user_id",teskUpdatePar.getUserId());
        //获取上传用户积分
        Integer user_points = userMapper.selectOne(lock_user).getUserPoints();
        //若积分不足
        if (user_points <= point_cost){
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


        //格式无误，插入数据
        Tesk tesk = new Tesk();
        tesk.setTeskId(onlyId);
        tesk.setUserId(teskUpdatePar.getUserId());
        tesk.setCreateTime(teskUpdatePar.getEndTime());
        tesk.setEndTime(teskUpdatePar.getEndTime());
        tesk.setTeskTitle(teskUpdatePar.getTeskTitle());
        tesk.setTeskDisc(teskUpdatePar.getTeskDisc());
        tesk.setTeskNeed(teskUpdatePar.getTeskNeed());
        tesk.setTeskType(teskUpdatePar.getTeskType());
        tesk.setLimitLevel(teskUpdatePar.getLimitLevel());
        tesk.setLimitCredit(teskUpdatePar.getLimitCredit());
        tesk.setPicNum(teskUpdatePar.getPicNum());
        tesk.setPayment(point_cost);
        tesk.setNeedNum(need_num);
        tesk.setTagNum(teskUpdatePar.getTagNum());
        tesk.setTag01(teskUpdatePar.getTag01());
        tesk.setTag02(teskUpdatePar.getTag02());
        tesk.setTag03(teskUpdatePar.getTag03());
        tesk.setTag04(teskUpdatePar.getTag04());
        int result = teskMapper.insert(tesk);
        if (result < 1){
            return false;
        }else{
            return true;
        }

    }

    @Override
    public void pageCourseQuery(Page<Tesk> pageCourse, TeskCoursePar teskCoursePar) {

        //创建查询queryWrapper对象
        QueryWrapper<Tesk> queryWrapper=new QueryWrapper<>();

        //根据创建时间排序
        //降序排序，后发的先显示
        String timeType = teskCoursePar.getTimeType();
        if (!StringUtils.isEmpty(timeType)){
            switch (timeType){
                case "create":
                    queryWrapper.orderByDesc("create_time");
                    break;
                case "end":
                    queryWrapper.orderByAsc("end_time");
                    break;
            }
        }else {
            queryWrapper.orderByDesc("create_time");
        }
        //判断此时的条件对象Vo是否等于空，若等于空，
        //直接进行selectPage查询、
        if(teskCoursePar==null){
            baseMapper.selectPage(pageCourse,queryWrapper);
        }

        //查询默认为正在进行中的任务
        queryWrapper.eq("state","1");
        //若Vo对象不为空，分别获取其中的字段，
        //并对其进行判断是否为空，这一步类似动态SQL的拼装
        String about = teskCoursePar.getTeskAbout();
        String type = teskCoursePar.getTeskType();

        if (!StringUtils.isEmpty(about)){
            queryWrapper.like("tesk_title",about);
        }
        if (!StringUtils.isEmpty(type)){
            queryWrapper.eq("tesk_type",type);
        }

        //最后调用selectPage方法，传入Page对象及queryWrapper对象
        baseMapper.selectPage(pageCourse,queryWrapper);
    }
}
