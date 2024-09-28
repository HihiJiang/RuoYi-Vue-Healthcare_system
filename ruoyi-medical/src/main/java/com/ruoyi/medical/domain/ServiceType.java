package com.ruoyi.medical.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 医疗服务对象 t_medical_service_type
 * 
 * @author ruoyi
 * @date 2024-06-12
 */
public class ServiceType extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 服务类型ID */
    private String servicetypeId;

    /** 服务类型PID */
    @Excel(name = "服务类型PID")
    private Long servicetypePid;

    /** 服务类型名称 */
    @Excel(name = "服务类型名称")
    private String servicetypeName;

    /** 服务类型说明 */
    @Excel(name = "服务类型说明")
    private String servicetypeDetails;

    public void setServicetypeId(String servicetypeId) 
    {
        this.servicetypeId = servicetypeId;
    }

    public String getServicetypeId() 
    {
        return servicetypeId;
    }
    public void setServicetypePid(Long servicetypePid) 
    {
        this.servicetypePid = servicetypePid;
    }

    public Long getServicetypePid() 
    {
        return servicetypePid;
    }
    public void setServicetypeName(String servicetypeName) 
    {
        this.servicetypeName = servicetypeName;
    }

    public String getServicetypeName() 
    {
        return servicetypeName;
    }
    public void setServicetypeDetails(String servicetypeDetails) 
    {
        this.servicetypeDetails = servicetypeDetails;
    }

    public String getServicetypeDetails() 
    {
        return servicetypeDetails;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("servicetypeId", getServicetypeId())
            .append("servicetypePid", getServicetypePid())
            .append("servicetypeName", getServicetypeName())
            .append("servicetypeDetails", getServicetypeDetails())
            .toString();
    }
}
