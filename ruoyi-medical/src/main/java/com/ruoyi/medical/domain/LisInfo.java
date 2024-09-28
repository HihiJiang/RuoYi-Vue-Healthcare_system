package com.ruoyi.medical.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * Lis检验信息对象 t_medical_lis_info
 * 
 * @author ruoyi
 * @date 2024-06-13
 */
public class LisInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** List检验ID */
    private Long lisId;

    /** 检验项目 */
    @Excel(name = "检验项目")
    private String lisPartname;

    /** 患者ID */
    @Excel(name = "患者ID")
    private String patientId;

    /** 医生ID */
    @Excel(name = "医生ID")
    private Long doctorId;

    /** 执行状态 */
    @Excel(name = "执行状态")
    private String lisStatus;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date lisCreatedate;

    /** 接诊记录ID */
    @Excel(name = "接诊记录ID")
    private String recordsId;

    /** 病情名称 */
    @Excel(name = "病情名称")
    private String lisSickname;

    /** 检验结果 */
    @Excel(name = "检验结果")
    private String lisResult;

    public void setLisId(Long lisId) 
    {
        this.lisId = lisId;
    }

    public Long getLisId() 
    {
        return lisId;
    }
    public void setLisPartname(String lisPartname) 
    {
        this.lisPartname = lisPartname;
    }

    public String getLisPartname() 
    {
        return lisPartname;
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
    public void setLisStatus(String lisStatus) 
    {
        this.lisStatus = lisStatus;
    }

    public String getLisStatus() 
    {
        return lisStatus;
    }
    public void setLisCreatedate(Date lisCreatedate) 
    {
        this.lisCreatedate = lisCreatedate;
    }

    public Date getLisCreatedate() 
    {
        return lisCreatedate;
    }
    public void setRecordsId(String recordsId) 
    {
        this.recordsId = recordsId;
    }

    public String getRecordsId() 
    {
        return recordsId;
    }
    public void setLisSickname(String lisSickname) 
    {
        this.lisSickname = lisSickname;
    }

    public String getLisSickname() 
    {
        return lisSickname;
    }
    public void setLisResult(String lisResult) 
    {
        this.lisResult = lisResult;
    }

    public String getLisResult() 
    {
        return lisResult;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("lisId", getLisId())
            .append("lisPartname", getLisPartname())
            .append("patientId", getPatientId())
            .append("doctorId", getDoctorId())
            .append("lisStatus", getLisStatus())
            .append("lisCreatedate", getLisCreatedate())
            .append("recordsId", getRecordsId())
            .append("lisSickname", getLisSickname())
            .append("lisResult", getLisResult())
            .toString();
    }
}
