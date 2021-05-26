package org.example;

import static org.junit.Assert.assertTrue;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.minidev.json.JSONUtil;
import org.example.mapper.PiclistMapper;
import org.example.mapper.UserMapper;
import org.example.pojo.Piclist;
import org.example.pojo.Teskpack;
import org.example.pojo.User;
import org.example.util.GetOneId;
import org.example.util.SnowFlakeUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Unit test for simple App.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AppTest
{
   @Autowired
   private UserMapper userMapper;
   @Autowired
   private PiclistMapper piclistMapper;

//   @Test
//   public void contextLoads(){
//       List<User> list = userMapper.selectList(null);
//       list.forEach(System.out::println);
//   }
//    @Test
//    public void main(){
//        SnowFlakeUtil snowFlakeUtil = new SnowFlakeUtil(2,3);
//        Long id = snowFlakeUtil.nextId();
//        String id_done = id.toString();
//        String result = "user"+id_done.substring(id_done.length()-9,id_done.length());
//        System.out.println(result);
//    }

//    @Test
//    public void test(){
//        QueryWrapper<User> wrapper = new QueryWrapper<>();
//        wrapper.like("user_name","d");
//        User user = userMapper.selectOne(wrapper);
//        System.out.println(user);
//    }

//    @Test
//    public void test(){
//        GetOneId getOneId = new GetOneId("user","user_name",8);
//        String onlyId = getOneId.GetOnlyId();
//        QueryWrapper<User> wrapper_id = new QueryWrapper<>();
//        wrapper_id.eq("user_name",onlyId);
//        User user_id = userMapper.selectOne(wrapper_id);
//        if (user_id != null){
//            onlyId = getOneId.GetOnlyId();
//            user_id = userMapper.selectOne(wrapper_id);
//        }
//        System.out.println(onlyId);
//    }

//    @Test
//    public void testPage(){
//        // 参数一：当前页
//        // 参数二：页面大小
//        // 使用了分页插件之后，所有的分页操作也变得简单的！
//        Page<User> page = new Page<>(1,10);
//        userMapper.selectPage(page,null);
//        page.getRecords().forEach(System.out::println);
//        System.out.println(page.getTotal());
//        System.out.println(page.getPages());
//        //转成json输出，需要引入hutool工具类依赖
////        String jsonString = JSONUtil.parse(userDOIPage).toJSONString(1);
////        log.info("查询的结果 = {}",jsonString);
//    }

    @Test
    public void te(){
        QueryWrapper<Piclist> picinpack = new QueryWrapper<>();
        picinpack.eq("tesk_id","tesk9281845248")
                .eq("inteskid",1);
        Piclist piclist  = piclistMapper.selectOne(picinpack);
        System.out.println(piclist);

    }

}
