package com.ruoyi.medical.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.medical.mapper.ServiceTypeMapper;
import com.ruoyi.medical.domain.ServiceType;
import com.ruoyi.medical.service.IServiceTypeService;

/**
 * 医疗服务Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-06-12
 */
@Service
public class ServiceTypeServiceImpl implements IServiceTypeService 
{
    @Autowired
    private ServiceTypeMapper serviceTypeMapper;

    /**
     * 查询医疗服务
     * 
     * @param servicetypeId 医疗服务主键
     * @return 医疗服务
     */
    @Override
    public ServiceType selectServiceTypeByServicetypeId(String servicetypeId)
    {
        return serviceTypeMapper.selectServiceTypeByServicetypeId(servicetypeId);
    }

    /**
     * 查询医疗服务列表
     * 
     * @param serviceType 医疗服务
     * @return 医疗服务
     */
    @Override
    public List<ServiceType> selectServiceTypeList(ServiceType serviceType)
    {
        return serviceTypeMapper.selectServiceTypeList(serviceType);
    }

    /**
     * 新增医疗服务
     * 
     * @param serviceType 医疗服务
     * @return 结果
     */
    @Override
    public int insertServiceType(ServiceType serviceType)
    {
        return serviceTypeMapper.insertServiceType(serviceType);
    }

    /**
     * 修改医疗服务
     * 
     * @param serviceType 医疗服务
     * @return 结果
     */
    @Override
    public int updateServiceType(ServiceType serviceType)
    {
        return serviceTypeMapper.updateServiceType(serviceType);
    }

    /**
     * 批量删除医疗服务
     * 
     * @param servicetypeIds 需要删除的医疗服务主键
     * @return 结果
     */
    @Override
    public int deleteServiceTypeByServicetypeIds(String[] servicetypeIds)
    {
        return serviceTypeMapper.deleteServiceTypeByServicetypeIds(servicetypeIds);
    }

    /**
     * 删除医疗服务信息
     * 
     * @param servicetypeId 医疗服务主键
     * @return 结果
     */
    @Override
    public int deleteServiceTypeByServicetypeId(String servicetypeId)
    {
        return serviceTypeMapper.deleteServiceTypeByServicetypeId(servicetypeId);
    }

    /**
     * 根据serviceType=“pacs/lis”查询医疗服务大类信息
     * @param serviceType
     * @return
     */
    @Override
    public List<ServiceType> queryServiceTypeByDetails(String serviceType) {
        ServiceType sType= new ServiceType();
        sType.setServicetypeDetails(serviceType);
        return serviceTypeMapper.selectServiceTypeList(sType);
    }
}
