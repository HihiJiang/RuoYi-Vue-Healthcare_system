package com.ruoyi.medical.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * pacs影像信息对象 t_medical_pacs_info
 * 
 * @author ruoyi
 * @date 2024-06-13
 */
public class PacsInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** pacs检查ID */
    private Long pacsId;

    /** pacs检查项目 */
    @Excel(name = "pacs检查项目")
    private String pacsPartname;

    /** 患者ID */
    @Excel(name = "患者ID")
    private String patientId;

    /** 医生ID */
    @Excel(name = "医生ID")
    private Long doctorId;

    /** 执行状态 */
    @Excel(name = "执行状态")
    private String pacsStatus;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date pacsCreatedate;

    /** 接诊记录ID */
    @Excel(name = "接诊记录ID")
    private String recordsId;

    /** 病情名称 */
    @Excel(name = "病情名称")
    private String pacsSickname;

    /** 检查结果 */
    @Excel(name = "检查结果")
    private String pacsResult;

    public void setPacsId(Long pacsId) 
    {
        this.pacsId = pacsId;
    }

    public Long getPacsId() 
    {
        return pacsId;
    }
    public void setPacsPartname(String pacsPartname) 
    {
        this.pacsPartname = pacsPartname;
    }

    public String getPacsPartname() 
    {
        return pacsPartname;
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
    public void setPacsStatus(String pacsStatus) 
    {
        this.pacsStatus = pacsStatus;
    }

    public String getPacsStatus() 
    {
        return pacsStatus;
    }
    public void setPacsCreatedate(Date pacsCreatedate) 
    {
        this.pacsCreatedate = pacsCreatedate;
    }

    public Date getPacsCreatedate() 
    {
        return pacsCreatedate;
    }
    public void setRecordsId(String recordsId) 
    {
        this.recordsId = recordsId;
    }

    public String getRecordsId() 
    {
        return recordsId;
    }
    public void setPacsSickname(String pacsSickname) 
    {
        this.pacsSickname = pacsSickname;
    }

    public String getPacsSickname() 
    {
        return pacsSickname;
    }
    public void setPacsResult(String pacsResult) 
    {
        this.pacsResult = pacsResult;
    }

    public String getPacsResult() 
    {
        return pacsResult;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("pacsId", getPacsId())
            .append("pacsPartname", getPacsPartname())
            .append("patientId", getPatientId())
            .append("doctorId", getDoctorId())
            .append("pacsStatus", getPacsStatus())
            .append("pacsCreatedate", getPacsCreatedate())
            .append("recordsId", getRecordsId())
            .append("pacsSickname", getPacsSickname())
            .append("pacsResult", getPacsResult())
            .toString();
    }
}
