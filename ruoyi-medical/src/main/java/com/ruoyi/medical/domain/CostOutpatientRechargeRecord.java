package com.ruoyi.medical.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 门诊卡充值取现记录对象 t_medical_cost_outpatient_recharge_record
 * 
 * @author ruoyi
 * @date 2024-06-07
 */
public class CostOutpatientRechargeRecord extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 门诊卡充值记录ID */
    private Long rechargeId;

    /** 充值取现金额 */
    @Excel(name = "充值取现金额")
    private BigDecimal rechargeAmount;

    /** 类型，1充值2取现 */
    @Excel(name = "类型，1充值2取现")
    private String rechargeType;

    /** 充值取现日期 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "充值取现日期", width = 30, dateFormat = "yyyy-MM-dd  HH:mm:ss")
    private Date rechargeCreatedate;

    /** 门诊卡编号 */
    @Excel(name = "门诊卡编号")
    private String outpatientId;

    /** 操作人ID */
    @Excel(name = "操作人ID")
    private Long operatorId;

    public void setRechargeId(Long rechargeId) 
    {
        this.rechargeId = rechargeId;
    }

    public Long getRechargeId() 
    {
        return rechargeId;
    }
    public void setRechargeAmount(BigDecimal rechargeAmount) 
    {
        this.rechargeAmount = rechargeAmount;
    }

    public BigDecimal getRechargeAmount() 
    {
        return rechargeAmount;
    }
    public void setRechargeType(String rechargeType) 
    {
        this.rechargeType = rechargeType;
    }

    public String getRechargeType() 
    {
        return rechargeType;
    }
    public void setRechargeCreatedate(Date rechargeCreatedate) 
    {
        this.rechargeCreatedate = rechargeCreatedate;
    }

    public Date getRechargeCreatedate() 
    {
        return rechargeCreatedate;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("rechargeId", getRechargeId())
            .append("rechargeAmount", getRechargeAmount())
            .append("rechargeType", getRechargeType())
            .append("rechargeCreatedate", getRechargeCreatedate())
            .append("outpatientId", getOutpatientId())
            .append("operatorId", getOperatorId())
            .toString();
    }
}
