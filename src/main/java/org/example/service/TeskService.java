package org.example.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.example.pojo.Tesk;
import com.baomidou.mybatisplus.extension.service.IService;
import org.example.pojo.parameter.TeskCoursePar;
import org.example.pojo.parameter.TeskUploadPar;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ChenQ
 * @since 2021-05-22
 */
public interface TeskService extends IService<Tesk> {

    boolean teskUpload(TeskUploadPar teskUpdatePar);

    void pageCourseQuery(Page<Tesk> pageCourse, TeskCoursePar teskCoursePar);
}
