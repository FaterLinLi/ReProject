package org.example.service;

import org.example.pojo.Tesk;
import com.baomidou.mybatisplus.extension.service.IService;
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
}
