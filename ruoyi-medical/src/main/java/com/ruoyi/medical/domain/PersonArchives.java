package com.ruoyi.medical.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 患者档案信息对象 t_medical_cost_person_archives
 * 
 * @author ruoyi
 * @date 2024-06-01
 */
public class PersonArchives extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 患者ID */
    private String patientId;

    /** 患者姓名 */
    @Excel(name = "患者姓名")
    private String patientName;

    /** 患者性别 */
    @Excel(name = "患者性别")
    private String patientSex;

    /** 患者生日 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "患者生日", width = 30, dateFormat = "yyyy-MM-dd")
    private Date patientBirthday;

    /** 患者身份证号 */
    @Excel(name = "患者身份证号")
    private String patientIdcard;

    /** 患者户籍 */
    @Excel(name = "患者户籍")
    private String patientHr;

    /** 家庭地址 */
    @Excel(name = "家庭地址")
    private String patientAddress;

    /** 患者民族 */
    @Excel(name = "患者民族")
    private String patientNation;

    /** 联系电话 */
    @Excel(name = "联系电话")
    private String patientPhone;

    /** 创建日期 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "创建日期", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date patientCreateDate;

    /** 逻辑删除标记 */
    @Excel(name = "逻辑删除标记")
    private Long deleted;

    public void setPatientId(String patientId) 
    {
        this.patientId = patientId;
    }

    public String getPatientId() 
    {
        return patientId;
    }
    public void setPatientName(String patientName) 
    {
        this.patientName = patientName;
    }

    public String getPatientName() 
    {
        return patientName;
    }
    public void setPatientSex(String patientSex) 
    {
        this.patientSex = patientSex;
    }

    public String getPatientSex() 
    {
        return patientSex;
    }
    public void setPatientBirthday(Date patientBirthday) 
    {
        this.patientBirthday = patientBirthday;
    }

    public Date getPatientBirthday() 
    {
        return patientBirthday;
    }
    public void setPatientIdcard(String patientIdcard) 
    {
        this.patientIdcard = patientIdcard;
    }

    public String getPatientIdcard() 
    {
        return patientIdcard;
    }
    public void setPatientHr(String patientHr) 
    {
        this.patientHr = patientHr;
    }

    public String getPatientHr() 
    {
        return patientHr;
    }
    public void setPatientAddress(String patientAddress) 
    {
        this.patientAddress = patientAddress;
    }

    public String getPatientAddress() 
    {
        return patientAddress;
    }
    public void setPatientNation(String patientNation) 
    {
        this.patientNation = patientNation;
    }

    public String getPatientNation() 
    {
        return patientNation;
    }
    public void setPatientPhone(String patientPhone) 
    {
        this.patientPhone = patientPhone;
    }

    public String getPatientPhone() 
    {
        return patientPhone;
    }
    public void setPatientCreateDate(Date patientCreateDate) 
    {
        this.patientCreateDate = patientCreateDate;
    }

    public Date getPatientCreateDate() 
    {
        return patientCreateDate;
    }
    public void setDeleted(Long deleted) 
    {
        this.deleted = deleted;
    }

    public Long getDeleted() 
    {
        return deleted;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("patientId", getPatientId())
            .append("patientName", getPatientName())
            .append("patientSex", getPatientSex())
            .append("patientBirthday", getPatientBirthday())
            .append("patientIdcard", getPatientIdcard())
            .append("patientHr", getPatientHr())
            .append("patientAddress", getPatientAddress())
            .append("patientNation", getPatientNation())
            .append("patientPhone", getPatientPhone())
            .append("patientCreateDate", getPatientCreateDate())
            .append("deleted", getDeleted())
            .toString();
    }
}
