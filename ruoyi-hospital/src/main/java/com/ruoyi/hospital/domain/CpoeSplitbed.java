package com.ruoyi.hospital.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 住院登记对象 t_medical_cpoe_splitbed
 * 
 * @author ruoyi
 * @date 2024-06-06
 */
public class CpoeSplitbed extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 分床ID */
    private Long splitbedId;

    /** 接诊记录ID */
    @Excel(name = "接诊记录ID")
    private String recordsId;

    /** 患者ID */
    @Excel(name = "患者ID")
    private String patientId;

    /** 医生ID */
    @Excel(name = "医生ID")
    private Long doctorId;

    /** 床位ID */
    @Excel(name = "床位ID")
    private Long bedId;

    /** 所属科室 */
    @Excel(name = "所属科室")
    private Long deptId;

    /** 分床状态 */
    @Excel(name = "分床状态")
    private String splitbedStatus;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date splitbedCreatedate;

    /** 入院时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "入院时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date splitbedIndate;

    /** 出院时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "出院时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date splitbedOutdate;

    /** 备注 */
    @Excel(name = "备注")
    private String splitbedRemark;

    public void setSplitbedId(Long splitbedId) 
    {
        this.splitbedId = splitbedId;
    }

    public Long getSplitbedId() 
    {
        return splitbedId;
    }
    public void setRecordsId(String recordsId) 
    {
        this.recordsId = recordsId;
    }

    public String getRecordsId() 
    {
        return recordsId;
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
    public void setBedId(Long bedId) 
    {
        this.bedId = bedId;
    }

    public Long getBedId() 
    {
        return bedId;
    }
    public void setDeptId(Long deptId) 
    {
        this.deptId = deptId;
    }

    public Long getDeptId() 
    {
        return deptId;
    }
    public void setSplitbedStatus(String splitbedStatus) 
    {
        this.splitbedStatus = splitbedStatus;
    }

    public String getSplitbedStatus() 
    {
        return splitbedStatus;
    }
    public void setSplitbedCreatedate(Date splitbedCreatedate) 
    {
        this.splitbedCreatedate = splitbedCreatedate;
    }

    public Date getSplitbedCreatedate() 
    {
        return splitbedCreatedate;
    }
    public void setSplitbedIndate(Date splitbedIndate) 
    {
        this.splitbedIndate = splitbedIndate;
    }

    public Date getSplitbedIndate() 
    {
        return splitbedIndate;
    }
    public void setSplitbedOutdate(Date splitbedOutdate) 
    {
        this.splitbedOutdate = splitbedOutdate;
    }

    public Date getSplitbedOutdate() 
    {
        return splitbedOutdate;
    }
    public void setSplitbedRemark(String splitbedRemark) 
    {
        this.splitbedRemark = splitbedRemark;
    }

    public String getSplitbedRemark() 
    {
        return splitbedRemark;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("splitbedId", getSplitbedId())
            .append("recordsId", getRecordsId())
            .append("patientId", getPatientId())
            .append("doctorId", getDoctorId())
            .append("bedId", getBedId())
            .append("deptId", getDeptId())
            .append("splitbedStatus", getSplitbedStatus())
            .append("splitbedCreatedate", getSplitbedCreatedate())
            .append("splitbedIndate", getSplitbedIndate())
            .append("splitbedOutdate", getSplitbedOutdate())
            .append("splitbedRemark", getSplitbedRemark())
            .toString();
    }
}
