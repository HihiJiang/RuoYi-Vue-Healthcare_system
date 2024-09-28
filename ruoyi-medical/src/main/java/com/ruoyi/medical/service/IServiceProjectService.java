package com.ruoyi.medical.service;

import java.util.List;
import com.ruoyi.medical.domain.ServiceProject;

/**
 * 医疗服务项目Service接口
 * 
 * @author ruoyi
 * @date 2024-06-12
 */
public interface IServiceProjectService 
{
    /**
     * 查询医疗服务项目
     * 
     * @param serviceprojectId 医疗服务项目主键
     * @return 医疗服务项目
     */
    public ServiceProject selectServiceProjectByServiceprojectId(Long serviceprojectId);

    /**
     * 查询医疗服务项目列表
     * 
     * @param serviceProject 医疗服务项目
     * @return 医疗服务项目集合
     */
    public List<ServiceProject> selectServiceProjectList(ServiceProject serviceProject);

    /**
     * 新增医疗服务项目
     * 
     * @param serviceProject 医疗服务项目
     * @return 结果
     */
    public int insertServiceProject(ServiceProject serviceProject);

    /**
     * 修改医疗服务项目
     * 
     * @param serviceProject 医疗服务项目
     * @return 结果
     */
    public int updateServiceProject(ServiceProject serviceProject);

    /**
     * 批量删除医疗服务项目
     * 
     * @param serviceprojectIds 需要删除的医疗服务项目主键集合
     * @return 结果
     */
    public int deleteServiceProjectByServiceprojectIds(Long[] serviceprojectIds);

    /**
     * 删除医疗服务项目信息
     * 
     * @param serviceprojectId 医疗服务项目主键
     * @return 结果
     */
    public int deleteServiceProjectByServiceprojectId(Long serviceprojectId);

    /**
     * 根据消费大类查询子类信息
     * @param serviceId
     * @return
     */
    public List<ServiceProject>  queryServiceProjectBySid(Long serviceId);

}
