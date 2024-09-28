package com.ruoyi.medical.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 取药记录对象 t_medical_drugs_total
 * 
 * @author ruoyi
 * @date 2024-06-14
 */
public class DrugsTotal extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 取药ID */
    private String totalId;

    /** 门诊卡ID */
    @Excel(name = "门诊卡ID")
    private String outpatientId;

    /** 患者ID */
    @Excel(name = "患者ID")
    private String patientId;

    /** 药房类型 */
    @Excel(name = "药房类型")
    private String pharmacyType;

    /** 取药状态，1已取2未取 */
    @Excel(name = "取药状态，1已取2未取")
    private Long totalStatus;

    /** 取药时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "取药时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date totalGettime;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date totalCreatetime;

    /** 操作人ID */
    @Excel(name = "操作人ID")
    private Long operatorId;

    public void setTotalId(String totalId) 
    {
        this.totalId = totalId;
    }

    public String getTotalId() 
    {
        return totalId;
    }
    public void setOutpatientId(String outpatientId) 
    {
        this.outpatientId = outpatientId;
    }

    public String getOutpatientId() 
    {
        return outpatientId;
    }
    public void setPatientId(String patientId) 
    {
        this.patientId = patientId;
    }

    public String getPatientId() 
    {
        return patientId;
    }
    public void setPharmacyType(String pharmacyType) 
    {
        this.pharmacyType = pharmacyType;
    }

    public String getPharmacyType() 
    {
        return pharmacyType;
    }
    public void setTotalStatus(Long totalStatus) 
    {
        this.totalStatus = totalStatus;
    }

    public Long getTotalStatus() 
    {
        return totalStatus;
    }
    public void setTotalGettime(Date totalGettime) 
    {
        this.totalGettime = totalGettime;
    }

    public Date getTotalGettime() 
    {
        return totalGettime;
    }
    public void setTotalCreatetime(Date totalCreatetime) 
    {
        this.totalCreatetime = totalCreatetime;
    }

    public Date getTotalCreatetime() 
    {
        return totalCreatetime;
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
            .append("totalId", getTotalId())
            .append("outpatientId", getOutpatientId())
            .append("patientId", getPatientId())
            .append("pharmacyType", getPharmacyType())
            .append("totalStatus", getTotalStatus())
            .append("totalGettime", getTotalGettime())
            .append("totalCreatetime", getTotalCreatetime())
            .append("operatorId", getOperatorId())
            .toString();
    }
}
