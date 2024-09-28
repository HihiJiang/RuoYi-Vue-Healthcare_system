package com.ruoyi.medical.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 医疗服务项目对象 t_medical_service_project
 * 
 * @author ruoyi
 * @date 2024-06-12
 */
public class ServiceProject extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /**  服务项目ID */
    private Long serviceprojectId;

    /** 服务项目名称 */
    @Excel(name = "服务项目名称")
    private String serviceprojectName;

    /** 服务项目价格 */
    @Excel(name = "服务项目价格")
    private BigDecimal serviceprojectPrice;

    /** 服务项目说明 */
    @Excel(name = "服务项目说明")
    private String serviceprojectDetails;

    /** 服务项目计价单位 */
    @Excel(name = "服务项目计价单位")
    private String serviceprojectUnit;

    /** 服务类型ID */
    @Excel(name = "服务类型ID")
    private Long serviceId;

    public void setServiceprojectId(Long serviceprojectId) 
    {
        this.serviceprojectId = serviceprojectId;
    }

    public Long getServiceprojectId() 
    {
        return serviceprojectId;
    }
    public void setServiceprojectName(String serviceprojectName) 
    {
        this.serviceprojectName = serviceprojectName;
    }

    public String getServiceprojectName() 
    {
        return serviceprojectName;
    }
    public void setServiceprojectPrice(BigDecimal serviceprojectPrice) 
    {
        this.serviceprojectPrice = serviceprojectPrice;
    }

    public BigDecimal getServiceprojectPrice() 
    {
        return serviceprojectPrice;
    }
    public void setServiceprojectDetails(String serviceprojectDetails) 
    {
        this.serviceprojectDetails = serviceprojectDetails;
    }

    public String getServiceprojectDetails() 
    {
        return serviceprojectDetails;
    }
    public void setServiceprojectUnit(String serviceprojectUnit) 
    {
        this.serviceprojectUnit = serviceprojectUnit;
    }

    public String getServiceprojectUnit() 
    {
        return serviceprojectUnit;
    }
    public void setServiceId(Long serviceId) 
    {
        this.serviceId = serviceId;
    }

    public Long getServiceId() 
    {
        return serviceId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("serviceprojectId", getServiceprojectId())
            .append("serviceprojectName", getServiceprojectName())
            .append("serviceprojectPrice", getServiceprojectPrice())
            .append("serviceprojectDetails", getServiceprojectDetails())
            .append("serviceprojectUnit", getServiceprojectUnit())
            .append("serviceId", getServiceId())
            .toString();
    }
}
