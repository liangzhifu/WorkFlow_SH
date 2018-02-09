package com.takata.dpcoi.rr.service;

import com.takata.dpcoi.file.domain.FileUpload;
import com.takata.dpcoi.rr.domain.RRProblem;
import com.takata.dpcoi.rr.query.RRProblemQuery;
import com.takata.system.user.domain.SystemUser;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * Created by liangzhifu
 * DATE:2017/6/28.
 */
public interface RRProblemService {

    /**
     * 添加
     * @param rrProblem 参数
     * @return 返回结果
     * @throws Exception 异常
     */
    public Integer addRRProblem(RRProblem rrProblem) throws Exception;

    /**
     * 更新
     * @param rrProblem 参数
     * @return 返回结果
     * @throws Exception 异常
     */
    public Integer updateRRProblem(RRProblem rrProblem) throws Exception;

    /**
     * 查询分页列表
     * @param rrProblemQuery 查询条件
     * @return 返回结果
     * @throws Exception 异常
     */
    public List<Map<String, Object>> queryRRProblemPageList(RRProblemQuery rrProblemQuery) throws Exception;

    /**
     * 查询总数
     * @param rrProblemQuery 查询条件
     * @return 返回结果
     * @throws Exception 异常
     */
    public Integer queryRRProblemCount(RRProblemQuery rrProblemQuery) throws Exception;

    /**
     * 获取发生日期的范围
     * @return 返回结果
     * @throws Exception 异常
     */
    public Map<String, Object> getHappenDateRandom() throws Exception;

    /**
     * 查询责任人
     * @return 返回结果
     * @throws Exception 异常
     */
    public List<Map<String, Object>> queryPersionLiableList() throws Exception;

    /**
     * 查询RR问题点
     * @param rrProblem RR问题点
     * @return 返回结果
     * @throws Exception 异常
     */
    public RRProblem queryRRProblem(RRProblem rrProblem) throws Exception;

    /**
     * 查询部长权限
     * @return 返回结果
     * @throws Exception 异常
     */
    public Integer queryMinisterJurisdiction(SystemUser systemUser) throws Exception;

    /**
     * 查询RR问题（正在处理）列表
     * @return 返回结果
     * @throws Exception 异常
     */
    public List<RRProblem> queryJobRRProblemList() throws Exception;

    /**
     * 更新RR问题进度
     * @param rrProblem RR问题
     * @throws Exception 异常
     */
    public void updateSpeedOfProgress(RRProblem rrProblem) throws Exception;

    /**
     * 更新RR问题追踪等级
     * @param rrProblem RR问题
     * @throws Exception 异常
     */
    public void updateTrackingLevel(RRProblem rrProblem) throws Exception;

    /**
     * 获取RR问题点延期的邮件
     * @param rrProblem RR问题点
     * @return 返回结果
     * @throws Exception 异常
     */
    public String queryDelayEmails(RRProblem rrProblem) throws Exception;

    /**
     * 查询屏幕显示列表
     * @return 返回结果
     * @throws Exception 异常
     */
    public List<Map<String, Object>> queryRRProblemScreenShowList() throws Exception;

    /**
     * 上传文件
     * @param rrProblemId RR问题点ID
     * @param fileAttr 文件所属
     * @param file 文件
     * @param path 文件路径
     * @param systemUser 用户
     * @return 返回结果
     * @throws Exception 异常
     */
    public FileUpload addUploadFile(Integer rrProblemId, String fileAttr, MultipartFile file, String path, SystemUser systemUser) throws Exception;

    /**
     * 申请延期，发送部长邮件
     * @param rrProblem RR问题点
     * @throws Exception 异常
     */
    public void addSendMinisterEmail(RRProblem rrProblem) throws Exception;

    /**
     * 查询1/5的RR问题点列表
     * @return 返回结果
     * @throws Exception 异常
     */
    public List<RRProblem> queryJobRRProblemTrackingLevelList() throws Exception;

    /**
     * 计算4次日期
     * @param happenDate 发生日期
     * @return 返回结果
     * @throws Exception 异常
     */
    public Map<String, Object> getFourDate(String happenDate) throws Exception;

    /**
     * 当问题进展为5/5时，发送通知邮件
     * @param rrProblem RR问题点
     * @throws Exception 异常
     */
    void addEmailByProblemProgress(RRProblem rrProblem) throws Exception;

    /**
     * 更新RR问题点
     * @param rrProblem RR问题点实体
     * @throws Exception 异常
     */
    void updateByPrimaryKey(RRProblem rrProblem) throws Exception;

}
