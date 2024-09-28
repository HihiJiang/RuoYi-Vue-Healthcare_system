package com.ruoyi.medical.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 门诊卡信息对象 t_medical_cost_outpatient_info
 * 
 * @author ruoyi
 * @date 2024-06-03
 */
public class CostOutpatientcard extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 门诊卡编号 */
    private String outpatientId;

    /** 卡上余额 */
    @Excel(name = "卡上余额")
    private BigDecimal outpatientBalance;

    /** 启用时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "启用时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date outpatientEnabletime;

    /** 挂失时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "挂失时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date outpatientLosstime;

    /** 作废时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "作废时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date outpatientDeadtime;

    /** 退卡时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "退卡时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date outpatientReturntime;

    /** 门诊卡状态，1启用2挂失3作废4退卡，默认1 */
    @Excel(name = "门诊卡状态，1启用2挂失3作废4退卡，默认1")
    private String outpatientStatus;

    /** 押金 */
    @Excel(name = "押金")
    private BigDecimal outpatientDeposit;

    /** 患者档案编号 */
    @Excel(name = "患者档案编号")
    private String patientId;

    /** 操作人ID */
    @Excel(name = "操作人ID")
    private Long operatorId;

    /** 逻辑删除标记 */
    @Excel(name = "逻辑删除标记")
    private Long deleted;

    public void setOutpatientId(String outpatientId) 
    {
        this.outpatientId = outpatientId;
    }

    public String getOutpatientId() 
    {
        return outpatientId;
    }
    public void setOutpatientBalance(BigDecimal outpatientBalance) 
    {
        this.outpatientBalance = outpatientBalance;
    }

    public BigDecimal getOutpatientBalance() 
    {
        return outpatientBalance;
    }
    public void setOutpatientEnabletime(Date outpatientEnabletime) 
    {
        this.outpatientEnabletime = outpatientEnabletime;
    }

    public Date getOutpatientEnabletime() 
    {
        return outpatientEnabletime;
    }
    public void setOutpatientLosstime(Date outpatientLosstime) 
    {
        this.outpatientLosstime = outpatientLosstime;
    }

    public Date getOutpatientLosstime() 
    {
        return outpatientLosstime;
    }
    public void setOutpatientDeadtime(Date outpatientDeadtime) 
    {
        this.outpatientDeadtime = outpatientDeadtime;
    }

    public Date getOutpatientDeadtime() 
    {
        return outpatientDeadtime;
    }
    public void setOutpatientReturntime(Date outpatientReturntime) 
    {
        this.outpatientReturntime = outpatientReturntime;
    }

    public Date getOutpatientReturntime() 
    {
        return outpatientReturntime;
    }
    public void setOutpatientStatus(String outpatientStatus) 
    {
        this.outpatientStatus = outpatientStatus;
    }

    public String getOutpatientStatus() 
    {
        return outpatientStatus;
    }
    public void setOutpatientDeposit(BigDecimal outpatientDeposit) 
    {
        this.outpatientDeposit = outpatientDeposit;
    }

    public BigDecimal getOutpatientDeposit() 
    {
        return outpatientDeposit;
    }
    public void setPatientId(String patientId) 
    {
        this.patientId = patientId;
    }

    public String getPatientId() 
    {
        return patientId;
    }
    public void setOperatorId(Long operatorId) 
    {
        this.operatorId = operatorId;
    }

    public Long getOperatorId() 
    {
        return operatorId;
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
            .append("outpatientId", getOutpatientId())
            .append("outpatientBalance", getOutpatientBalance())
            .append("outpatientEnabletime", getOutpatientEnabletime())
            .append("outpatientLosstime", getOutpatientLosstime())
            .append("outpatientDeadtime", getOutpatientDeadtime())
            .append("outpatientReturntime", getOutpatientReturntime())
            .append("outpatientStatus", getOutpatientStatus())
            .append("outpatientDeposit", getOutpatientDeposit())
            .append("patientId", getPatientId())
            .append("operatorId", getOperatorId())
            .append("deleted", getDeleted())
            .toString();
    }
}
