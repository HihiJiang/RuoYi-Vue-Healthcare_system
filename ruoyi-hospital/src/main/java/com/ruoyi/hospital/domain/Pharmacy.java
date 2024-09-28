package com.ruoyi.hospital.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 药房库存对象 t_medical_drugs_pharmacy
 * 
 * @author ruoyi
 * @date 2024-06-13
 */
public class Pharmacy extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 唯一标识 */
    private Long pharmacyId;

    /** 药房类型，1中医药房2西医药房3儿童药房 */
    @Excel(name = "药房类型，1中医药房2西医药房3儿童药房")
    private String pharmacyType;

    /** 药品编号 */
    @Excel(name = "药品编号")
    private Long drugsId;

    /** 药品数量 */
    @Excel(name = "药品数量")
    private Long pharmacyNo;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date pharmacyUpdatetime;

    /** 操作人ID */
    @Excel(name = "操作人ID")
    private Long operatorId;

    public void setPharmacyId(Long pharmacyId) 
    {
        this.pharmacyId = pharmacyId;
    }

    public Long getPharmacyId() 
    {
        return pharmacyId;
    }
    public void setPharmacyType(String pharmacyType) 
    {
        this.pharmacyType = pharmacyType;
    }

    public String getPharmacyType() 
    {
        return pharmacyType;
    }
    public void setDrugsId(Long drugsId) 
    {
        this.drugsId = drugsId;
    }

    public Long getDrugsId() 
    {
        return drugsId;
    }
    public void setPharmacyNo(Long pharmacyNo) 
    {
        this.pharmacyNo = pharmacyNo;
    }

    public Long getPharmacyNo() 
    {
        return pharmacyNo;
    }
    public void setPharmacyUpdatetime(Date pharmacyUpdatetime) 
    {
        this.pharmacyUpdatetime = pharmacyUpdatetime;
    }

    public Date getPharmacyUpdatetime() 
    {
        return pharmacyUpdatetime;
    }
    public void setOperatorId(Long operatorId) 
    {
        this.operatorId = operatorId;
    }

    public Long getOperatorId() 
    {
        return operatorId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("pharmacyId", getPharmacyId())
            .append("pharmacyType", getPharmacyType())
            .append("drugsId", getDrugsId())
            .append("pharmacyNo", getPharmacyNo())
            .append("pharmacyUpdatetime", getPharmacyUpdatetime())
            .append("operatorId", getOperatorId())
            .toString();
    }
}
