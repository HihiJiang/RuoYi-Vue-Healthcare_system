package com.ruoyi.hospital.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 医嘱记录对象 t_medical_emr_doctorsorder
 * 
 * @author ruoyi
 * @date 2024-06-13
 */
public class Doctorsorder extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 医嘱ID */
    private Long ordersId;

    /** 患者ID */
    @Excel(name = "患者ID")
    private String patientId;

    /** 医生ID */
    @Excel(name = "医生ID")
    private Long doctorId;

    /** 医嘱类别，0长期1临时2出院 */
    @Excel(name = "医嘱类别，0长期1临时2出院")
    private Long ordersStatus;

    /** 执行状态，0停止1执行 */
    @Excel(name = "执行状态，0停止1执行")
    private Long ordersType;

    /** 医嘱 */
    @Excel(name = "医嘱")
    private String ordersDesc;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date ordersCreatedate;

    public void setOrdersId(Long ordersId) 
    {
        this.ordersId = ordersId;
    }

    public Long getOrdersId() 
    {
        return ordersId;
    }
    public void setPatientId(String patientId) 
    {
        this.patientId = patientId;
    }

    public String getPatientId() 
    {
        return patientId;
    }
    public void setDoctorId(Long doctorId) 
    {
        this.doctorId = doctorId;
    }

    public Long getDoctorId() 
    {
        return doctorId;
    }
    public void setOrdersStatus(Long ordersStatus) 
    {
        this.ordersStatus = ordersStatus;
    }

    public Long getOrdersStatus() 
    {
        return ordersStatus;
    }
    public void setOrdersType(Long ordersType) 
    {
        this.ordersType = ordersType;
    }

    public Long getOrdersType() 
    {
        return ordersType;
    }
    public void setOrdersDesc(String ordersDesc) 
    {
        this.ordersDesc = ordersDesc;
    }

    public String getOrdersDesc() 
    {
        return ordersDesc;
    }
    public void setOrdersCreatedate(Date ordersCreatedate) 
    {
        this.ordersCreatedate = ordersCreatedate;
    }

    public Date getOrdersCreatedate() 
    {
        return ordersCreatedate;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("ordersId", getOrdersId())
            .append("patientId", getPatientId())
            .append("doctorId", getDoctorId())
            .append("ordersStatus", getOrdersStatus())
            .append("ordersType", getOrdersType())
            .append("ordersDesc", getOrdersDesc())
            .append("ordersCreatedate", getOrdersCreatedate())
            .toString();
    }
}
