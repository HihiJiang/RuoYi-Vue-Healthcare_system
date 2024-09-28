package com.ruoyi.medical.mapper;

import java.util.List;
import com.ruoyi.medical.domain.ServiceProject;

/**
 * 医疗服务项目Mapper接口
 * 
 * @author ruoyi
 * @date 2024-06-12
 */
public interface ServiceProjectMapper 
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
     * 删除医疗服务项目
     * 
     * @param serviceprojectId 医疗服务项目主键
     * @return 结果
     */
    public int deleteServiceProjectByServiceprojectId(Long serviceprojectId);

    /**
     * 批量删除医疗服务项目
     * 
     * @param serviceprojectIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteServiceProjectByServiceprojectIds(Long[] serviceprojectIds);
}
