package com.ruoyi.medical.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 医生看病治疗方案对象 t_medical_outpatientdoctor_resulthandle
 * 
 * @author ruoyi
 * @date 2024-06-15
 */
public class DoctorResulthandle extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 治疗方案ID */
    private Long treatId;

    /** 消费事项 */
    @Excel(name = "消费事项")
    private String treatPartname;

    /** 消费数量 */
    @Excel(name = "消费数量")
    private Long treatPartnumber;

    /** 单价 */
    @Excel(name = "单价")
    private BigDecimal treatPartmoney;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date treatCreatedate;

    /** 接诊记录ID */
    @Excel(name = "接诊记录ID")
    private String recordsId;

    public void setTreatId(Long treatId) 
    {
        this.treatId = treatId;
    }

    public Long getTreatId() 
    {
        return treatId;
    }
    public void setTreatPartname(String treatPartname) 
    {
        this.treatPartname = treatPartname;
    }

    public String getTreatPartname() 
    {
        return treatPartname;
    }
    public void setTreatPartnumber(Long treatPartnumber) 
    {
        this.treatPartnumber = treatPartnumber;
    }

    public Long getTreatPartnumber() 
    {
        return treatPartnumber;
    }
    public void setTreatPartmoney(BigDecimal treatPartmoney) 
    {
        this.treatPartmoney = treatPartmoney;
    }

    public BigDecimal getTreatPartmoney() 
    {
        return treatPartmoney;
    }
    public void setTreatCreatedate(Date treatCreatedate) 
    {
        this.treatCreatedate = treatCreatedate;
    }

    public Date getTreatCreatedate() 
    {
        return treatCreatedate;
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
            .append("treatId", getTreatId())
            .append("treatPartname", getTreatPartname())
            .append("treatPartnumber", getTreatPartnumber())
            .append("treatPartmoney", getTreatPartmoney())
            .append("treatCreatedate", getTreatCreatedate())
            .append("recordsId", getRecordsId())
            .toString();
    }
}
