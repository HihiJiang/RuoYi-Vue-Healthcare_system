package com.ruoyi.medical.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.medical.mapper.ServiceProjectMapper;
import com.ruoyi.medical.domain.ServiceProject;
import com.ruoyi.medical.service.IServiceProjectService;

/**
 * 医疗服务项目Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-06-12
 */
@Service
public class ServiceProjectServiceImpl implements IServiceProjectService 
{
    @Autowired
    private ServiceProjectMapper serviceProjectMapper;

    /**
     * 查询医疗服务项目
     * 
     * @param serviceprojectId 医疗服务项目主键
     * @return 医疗服务项目
     */
    @Override
    public ServiceProject selectServiceProjectByServiceprojectId(Long serviceprojectId)
    {
        return serviceProjectMapper.selectServiceProjectByServiceprojectId(serviceprojectId);
    }

    /**
     * 查询医疗服务项目列表
     * 
     * @param serviceProject 医疗服务项目
     * @return 医疗服务项目
     */
    @Override
    public List<ServiceProject> selectServiceProjectList(ServiceProject serviceProject)
    {
        return serviceProjectMapper.selectServiceProjectList(serviceProject);
    }

    /**
     * 新增医疗服务项目
     * 
     * @param serviceProject 医疗服务项目
     * @return 结果
     */
    @Override
    public int insertServiceProject(ServiceProject serviceProject)
    {
        return serviceProjectMapper.insertServiceProject(serviceProject);
    }

    /**
     * 修改医疗服务项目
     * 
     * @param serviceProject 医疗服务项目
     * @return 结果
     */
    @Override
    public int updateServiceProject(ServiceProject serviceProject)
    {
        return serviceProjectMapper.updateServiceProject(serviceProject);
    }

    /**
     * 批量删除医疗服务项目
     * 
     * @param serviceprojectIds 需要删除的医疗服务项目主键
     * @return 结果
     */
    @Override
    public int deleteServiceProjectByServiceprojectIds(Long[] serviceprojectIds)
    {
        return serviceProjectMapper.deleteServiceProjectByServiceprojectIds(serviceprojectIds);
    }

    /**
     * 删除医疗服务项目信息
     * 
     * @param serviceprojectId 医疗服务项目主键
     * @return 结果
     */
    @Override
    public int deleteServiceProjectByServiceprojectId(Long serviceprojectId)
    {
        return serviceProjectMapper.deleteServiceProjectByServiceprojectId(serviceprojectId);
    }

    /**
     * 根据消费大类查询子类信息
     * @param serviceId
     * @return
     */
    @Override
    public List<ServiceProject> queryServiceProjectBySid(Long serviceId) {
        ServiceProject sProject = new ServiceProject();
        sProject.setServiceId(serviceId);
        return serviceProjectMapper.selectServiceProjectList(sProject);
    }
}
