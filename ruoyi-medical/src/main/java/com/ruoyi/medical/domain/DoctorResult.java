package com.ruoyi.medical.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 医生看病结果对象 t_medical_outpatientdoctor_result
 * 
 * @author ruoyi
 * @date 2024-06-14
 */
public class DoctorResult extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 看病结果ID */
    private Long resultId;

    /** 病名称 */
    @Excel(name = "病名称")
    private String resultName;

    /** 症状描述 */
    @Excel(name = "症状描述")
    private String resultDescription;

    /** 医嘱 */
    @Excel(name = "医嘱")
    private String resultRemark;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date resultCreatedate;

    /** 接诊记录ID */
    @Excel(name = "接诊记录ID")
    private String recordsId;

    public void setResultId(Long resultId) 
    {
        this.resultId = resultId;
    }

    public Long getResultId() 
    {
        return resultId;
    }
    public void setResultName(String resultName) 
    {
        this.resultName = resultName;
    }

    public String getResultName() 
    {
        return resultName;
    }
    public void setResultDescription(String resultDescription) 
    {
        this.resultDescription = resultDescription;
    }

    public String getResultDescription() 
    {
        return resultDescription;
    }
    public void setResultRemark(String resultRemark) 
    {
        this.resultRemark = resultRemark;
    }

    public String getResultRemark() 
    {
        return resultRemark;
    }
    public void setResultCreatedate(Date resultCreatedate) 
    {
        this.resultCreatedate = resultCreatedate;
    }

    public Date getResultCreatedate() 
    {
        return resultCreatedate;
    }
    public void setRecordsId(String recordsId) 
    {
        this.recordsId = recordsId;
    }

    public String getRecordsId() 
    {
        return recordsId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("resultId", getResultId())
            .append("resultName", getResultName())
            .append("resultDescription", getResultDescription())
            .append("resultRemark", getResultRemark())
            .append("resultCreatedate", getResultCreatedate())
            .append("recordsId", getRecordsId())
            .toString();
    }
}
