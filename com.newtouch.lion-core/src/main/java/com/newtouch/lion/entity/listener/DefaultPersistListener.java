package com.newtouch.lion.entity.listener;

import com.newtouch.lion.model.AuditEntity;
import com.newtouch.lion.model.VersionEntity;
import com.newtouch.lion.session.AppContext;

import java.util.Date;

/**
 *
 * Created by wanglijun on 16/4/15.
 */
public class DefaultPersistListener {

    /***
    * 持久化对象之前,设置保存创建时间,创建人ID
    * @param auditEntity
    */
    public void prePersist(AuditEntity  auditEntity) {
        //设置创建时间
        auditEntity.setCreatedDate(new Date());

            //设置创建人
            auditEntity.setCreatedById(AppContext.getUserInfo().getId());

    }

    /***
     * 更新对象之前,设置更新时间,更新人ID
     * @param auditEntity
     */
    public void preUpdate(AuditEntity  auditEntity) {
        auditEntity.setUpdatedDate(new Date());

            auditEntity.setUpdatedById(AppContext.getUserInfo().getId());

    }

    /****
     * 逻辑删除之前对,设置删除人(当主键为Long 类型才能有效设置更新人),删除的时间
     */
    public void  preUpdate(VersionEntity versionEntity){
        if(versionEntity.getIsDeleted()){
            versionEntity.setDeleteDate(new Date());
            versionEntity.setUpdatedDate(new Date());
            versionEntity.setUpdatedById(AppContext.getUserInfo().getId());
        }
    }
}
