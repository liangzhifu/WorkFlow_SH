package com.takata.dpcoi.file.dao;

import com.takata.common.dao.SqlDao;
import com.takata.dpcoi.file.domain.FileUpload;
import org.springframework.stereotype.Repository;

/**
 * Created by 梁志福 on 2017/4/20.
 */
@Repository
public class FileUploadDao extends SqlDao {
    /**
     * 插入一个FileUpload文件
     * @param fileUpload 文件数据
     * @return 1成功，0不成功
     */
    public Integer insertFileUpload(FileUpload fileUpload){
        return this.writerSqlSession.insert("fileUpload.insertSelective", fileUpload);
    }

    /**
     * 根据文件Id查询文件实体
     * @param fileUpload 文件ID
     * @return 返回结果
     */
    public FileUpload selectBySelf(FileUpload fileUpload){
        return this.readSqlSession.selectOne("fileUpload.selectBySelf", fileUpload);
    }

}
