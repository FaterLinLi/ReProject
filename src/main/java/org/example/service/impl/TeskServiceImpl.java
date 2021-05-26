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
import org.example.mapper.PiclistMapper;
import org.example.mapper.TeskpackMapper;
import org.example.mapper.UserMapper;
import org.example.pojo.Piclist;
import org.example.pojo.Tesk;
import org.example.mapper.TeskMapper;
import org.example.pojo.Teskpack;
import org.example.pojo.User;
import org.example.pojo.parameter.GetTeskPar;
import org.example.pojo.parameter.PassTeskPar;
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
    @Resource
    private TeskpackMapper teskpackMapper;
    @Resource
    private PiclistMapper piclistMapper;


    @Override
    public boolean teskUpload(TeskUploadPar teskUpdatePar) {

        //根据图片数量计算任务所需积分以及人数
        //所需积分
        Integer point_cost = teskUpdatePar.getPicNum()*3*5;
        //所需人数
        //以后再说，先全算三个人
        Integer need_num = 3;
//        Integer need_num = null;
//        if (teskUpdatePar.getPicNum()<10){
//            need_num = 3;
//        }else {
//            double a = teskUpdatePar.getPicNum()/10;
//            need_num = (int)Math.rint(a)*3;
//        }

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

        //获取当前时间
        Date date = new Date();
//        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");



        //格式无误，插入数据
        Tesk tesk = new Tesk();
        tesk.setTeskId(onlyId);
        tesk.setUserId(teskUpdatePar.getUserId());
        tesk.setCreateTime(date);
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

    @Override
    public boolean passTesk(String teskId) {

        Tesk tesk =  new Tesk();
        tesk.setTeskId(teskId);
        tesk.setState("1");
        teskMapper.updateById(tesk);

        //需要的人数
//        int need_num = teskMapper.selectById(teskId).getNeedNum();

        GetOneId getOneId = new GetOneId("pack",10);
        Teskpack teskpack = new Teskpack();
        Piclist piclist = new Piclist();

        int need_num = 3;
        int count = 1;
        int pic_num = teskMapper.selectById(teskId).getPicNum();
        while (count <= need_num){
            int inteskid = 1;
            switch (count){
                case 1:
                    String OnlyId = getOneId.GetOnlyId();
                    teskpack.setTaskpackId(OnlyId);
                    teskpack.setTaskId(teskId);
                    teskpack.setTeskpackGet(pic_num);
                    teskpack.setTeskpackPay(pic_num);
                    int result = teskpackMapper.insert(teskpack);
                    if (result < 1){
                        return false;
                    }else {

                        while (inteskid <= pic_num){
                            QueryWrapper<Piclist> picinpack = new QueryWrapper<>();
                            picinpack.eq("tesk_id",teskId)
                                    .eq("inteskid",inteskid);
                            piclist  = piclistMapper.selectOne(picinpack);
                            Piclist changePack = new Piclist();
                            changePack.setPack01(OnlyId);
                            piclistMapper.update(changePack,picinpack);
                            inteskid = inteskid + 1;
                        }
                    }
                    break;
                case 2:

                    String OnlyId2 = getOneId.GetOnlyId();
                    teskpack.setTaskpackId(OnlyId2);
                    teskpack.setTaskId(teskId);
                    teskpack.setTeskpackGet(pic_num);
                    teskpack.setTeskpackPay(pic_num);
                    int result2 = teskpackMapper.insert(teskpack);
                    if (result2 < 1){
                        return false;
                    }else {

                        while (inteskid <= pic_num){
                            QueryWrapper<Piclist> picinpack = new QueryWrapper<>();
                            picinpack.eq("tesk_id",teskId)
                                    .eq("inteskid",inteskid);
                            piclist  = piclistMapper.selectOne(picinpack);
                            Piclist changePack = new Piclist();
                            changePack.setPack02(OnlyId2);
                            piclistMapper.update(changePack,picinpack);
                            inteskid = inteskid + 1;
                        }
                    }
                    break;
                case 3:

                    String OnlyId3 = getOneId.GetOnlyId();
                    teskpack.setTaskpackId(OnlyId3);
                    teskpack.setTaskId(teskId);
                    teskpack.setTeskpackGet(pic_num);
                    teskpack.setTeskpackPay(pic_num);
                    int result3 = teskpackMapper.insert(teskpack);
                    if (result3 < 1){
                        return false;
                    }else {

                        while (inteskid <= pic_num){
                            QueryWrapper<Piclist> picinpack = new QueryWrapper<>();
                            picinpack.eq("tesk_id",teskId)
                                    .eq("inteskid",inteskid);
                            piclist  = piclistMapper.selectOne(picinpack);
                            Piclist changePack = new Piclist();
                            changePack.setPack03(OnlyId3);
                            piclistMapper.update(changePack,picinpack);
                            inteskid = inteskid + 1;
                        }
                    }
                    break;

            }
            count = count + 1;

        }


        //按人数创建任务包
        //并对应到图片列表中

//        while (count <= need_num){
//            String OnlyId = getOneId.GetOnlyId();
//            teskpack.setTaskpackId(OnlyId);
//            teskpack.setTaskId(teskId);
//            int result = teskpackMapper.insert(teskpack);
//            if (result < 1){
//                return false;
//            }else {
//                count = count + 1;
//            }
//        }
        //任务包链接图片
//        System.out.println(need_num);

        return true;
    }

    @Override
    public boolean getTesk(String teskpackId, String userId) {

        //获取对应任务信息
        Teskpack teskpack = teskpackMapper.selectById(teskpackId);
        String tesk_id = teskpack.getTaskId();
        Tesk tesk = teskMapper.selectById(tesk_id);
        User user = userMapper.selectById(userId);
        //任务所需限制
        Integer tesk_level = tesk.getLimitLevel();
        Integer tesk_credit = tesk.getLimitCredit();
        //用户等级信用
        Integer user_level = user.getUserLevel();
        Integer user_credit = user.getUserCredit();

        //判断用户是否已承接该任务的其他任务包
        QueryWrapper<Teskpack> ifGet = new QueryWrapper<>();
        ifGet.eq("tesk_id",tesk_id)
                .eq("user_id",userId);
        if (ifGet != null){
            throw new BankAppException(ResultCode.ERROR,MessageCode.GetTesk.ALREADY_GET);
        }

        //判断等级、信誉限制
        if ( tesk_level > user_level){
            throw new BankAppException(ResultCode.ERROR,MessageCode.GetTesk.LEVEL_LIMIT);
        }
        if ( tesk_credit > user_credit){
            throw new BankAppException(ResultCode.ERROR,MessageCode.GetTesk.CREDIT_LIMIT);
        }

        //计算用户权重
        Double weight = null;
        switch (user_level){
            case 1:
                weight = 1.0;
                break;
            case 2:
                weight = 1.1;
                break;
            case 3:
                weight = 1.2;
                break;
            case 4:
                weight = 1.3;
                break;
            case 5:
                weight = 1.5;
                break;
            case 6:
                weight = 1.7;
                break;
        }

        weight = weight*user_credit/100;

        //获取当前时间
        Date date = new Date();
        //更新任务包信息
        teskpack.setTaskpackId(teskpackId);
        teskpack.setUserId(userId);
        teskpack.setGetTime(date);
        teskpack.setTeskpackWeight(weight);
        teskpackMapper.updateById(teskpack);

        return false;
    }

//    @Override
//    public boolean getTesk(String teskpackId, GetTeskPar getTeskPar) {
//
//        //获取对应任务信息
//        Teskpack teskpack = teskpackMapper.selectById(teskpackId);
//        String tesk_id = teskpack.getTaskId();
//        Tesk tesk = teskMapper.selectById(tesk_id);
//        //任务所需限制
//        Integer tesk_level = tesk.getLimitLevel();
//        Integer tesk_credit = tesk.getLimitCredit();
//
//        //判断等级、信誉限制
//        if ( tesk_level > getTeskPar.getUserLevel()){
//            throw new BankAppException(ResultCode.ERROR,MessageCode.GetTesk.LEVEL_LIMIT);
//        }
//        if ( tesk_credit > getTeskPar.getUserCredit()){
//            throw new BankAppException(ResultCode.ERROR,MessageCode.GetTesk.CREDIT_LIMIT);
//        }
//
//        //计算用户权重
//        Double weight = null;
//        switch (getTeskPar.getUserLevel()){
//            case 1:
//                weight = 1.0;
//                break;
//            case 2:
//                weight = 1.1;
//                break;
//            case 3:
//                weight = 1.2;
//                break;
//            case 4:
//                weight = 1.3;
//                break;
//            case 5:
//                weight = 1.5;
//                break;
//            case 6:
//                weight = 1.7;
//                break;
//        }
//
//        weight = weight*getTeskPar.getUserCredit()/100;
//
//        //获取当前时间
//        Date date = new Date();
//        //更新任务包信息
//        teskpack.setTaskpackId(teskpackId);
//        teskpack.setUserId(getTeskPar.getUserId());
//        teskpack.setGetTime(date);
//        teskpack.setTeskpackWeight(weight);
//        teskpackMapper.updateById(teskpack);
//
//        return true;
//    }

}
