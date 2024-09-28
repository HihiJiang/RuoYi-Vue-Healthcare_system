package com.ruoyi.hospital.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 接诊记录对象 t_medical_outpatientdoctor_receiverecord
 * 
 * @author ruoyi
 * @date 2024-06-11
 */
public class Receiverecord extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 接诊记录ID */
    private String recordsId;

    /** 医生ID */
    @Excel(name = "医生ID")
    private Long recordsDoctorid;

    /** 诊断状态，1门诊2住院 */
    @Excel(name = "诊断状态，1门诊2住院")
    private String recordsDiagnosticStatus;

    /** 接诊状态，0未到1候诊2接诊3顺延4结束 */
    @Excel(name = "接诊状态，0未到1候诊2接诊3顺延4结束")
    private String recordsStatus;

    /** 接诊时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "接诊时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date recordsCreatedate;

    /** 上午/下午 */
    @Excel(name = "上午/下午")
    private String recordsAmpm;

    /** 科室 */
    @Excel(name = "科室")
    private Integer recordsDeptid;

    /** 挂号顺序号 */
    @Excel(name = "挂号顺序号")
    private Integer recordsNo;

    /** 门诊卡ID */
    @Excel(name = "门诊卡ID")
    private String outpatientId;

    /** 操作人ID */
    @Excel(name = "操作人ID")
    private Long operatorId;

    /** 录入时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "录入时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date enterdate;

    public void setRecordsId(String recordsId) 
    {
        this.recordsId = recordsId;
    }

    public String getRecordsId() 
    {
        return recordsId;
    }
    public void setRecordsDoctorid(Long recordsDoctorid) 
    {
        this.recordsDoctorid = recordsDoctorid;
    }

    public Long getRecordsDoctorid() 
    {
        return recordsDoctorid;
    }
    public void setRecordsDiagnosticStatus(String recordsDiagnosticStatus) 
    {
        this.recordsDiagnosticStatus = recordsDiagnosticStatus;
    }

    public String getRecordsDiagnosticStatus() 
    {
        return recordsDiagnosticStatus;
    }
    public void setRecordsStatus(String recordsStatus) 
    {
        this.recordsStatus = recordsStatus;
    }

    public String getRecordsStatus() 
    {
        return recordsStatus;
    }
    public void setRecordsCreatedate(Date recordsCreatedate) 
    {
        this.recordsCreatedate = recordsCreatedate;
    }

    public Date getRecordsCreatedate() 
    {
        return recordsCreatedate;
    }
    public void setRecordsAmpm(String recordsAmpm) 
    {
        this.recordsAmpm = recordsAmpm;
    }

    public String getRecordsAmpm() 
    {
        return recordsAmpm;
    }
    public void setRecordsDeptid(Integer recordsDeptid) 
    {
        this.recordsDeptid = recordsDeptid;
    }

    public Integer getRecordsDeptid() 
    {
        return recordsDeptid;
    }
    public void setRecordsNo(Integer recordsNo) 
    {
        this.recordsNo = recordsNo;
    }

    public Integer getRecordsNo() 
    {
        return recordsNo;
    }
    public void setOutpatientId(String outpatientId) 
    {
        this.outpatientId = outpatientId;
    }

    public String getOutpatientId() 
    {
        return outpatientId;
    }
    public void setOperatorId(Long operatorId) 
    {
        this.operatorId = operatorId;
    }

    public Long getOperatorId() 
    {
        return operatorId;
    }
    public void setEnterdate(Date enterdate) 
    {
        this.enterdate = enterdate;
    }

    public Date getEnterdate() 
    {
        return enterdate;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("recordsId", getRecordsId())
            .append("recordsDoctorid", getRecordsDoctorid())
            .append("recordsDiagnosticStatus", getRecordsDiagnosticStatus())
            .append("recordsStatus", getRecordsStatus())
            .append("recordsCreatedate", getRecordsCreatedate())
            .append("recordsAmpm", getRecordsAmpm())
            .append("recordsDeptid", getRecordsDeptid())
            .append("recordsNo", getRecordsNo())
            .append("outpatientId", getOutpatientId())
            .append("operatorId", getOperatorId())
            .append("enterdate", getEnterdate())
            .toString();
    }
}
