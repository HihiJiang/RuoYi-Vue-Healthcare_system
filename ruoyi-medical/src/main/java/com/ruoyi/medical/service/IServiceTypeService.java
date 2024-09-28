package com.ruoyi.medical.service;

import java.util.List;
import com.ruoyi.medical.domain.ServiceType;

/**
 * 医疗服务Service接口
 * 
 * @author ruoyi
 * @date 2024-06-12
 */
public interface IServiceTypeService 
{
    /**
     * 查询医疗服务
     * 
     * @param servicetypeId 医疗服务主键
     * @return 医疗服务
     */
    public ServiceType selectServiceTypeByServicetypeId(String servicetypeId);

    /**
     * 查询医疗服务列表
     * 
     * @param serviceType 医疗服务
     * @return 医疗服务集合
     */
    public List<ServiceType> selectServiceTypeList(ServiceType serviceType);

    /**
     * 新增医疗服务
     * 
     * @param serviceType 医疗服务
     * @return 结果
     */
    public int insertServiceType(ServiceType serviceType);

    /**
     * 修改医疗服务
     * 
     * @param serviceType 医疗服务
     * @return 结果
     */
    public int updateServiceType(ServiceType serviceType);

    /**
     * 批量删除医疗服务
     * 
     * @param servicetypeIds 需要删除的医疗服务主键集合
     * @return 结果
     */
    public int deleteServiceTypeByServicetypeIds(String[] servicetypeIds);

    /**
     * 删除医疗服务信息
     * 
     * @param servicetypeId 医疗服务主键
     * @return 结果
     */
    public int deleteServiceTypeByServicetypeId(String servicetypeId);

    /**
     * 根据serviceType=“pacs/lis”查询医疗服务大类信息
     * @param serviceType
     * @return
     */
    public  List<ServiceType> queryServiceTypeByDetails(String serviceType);
}