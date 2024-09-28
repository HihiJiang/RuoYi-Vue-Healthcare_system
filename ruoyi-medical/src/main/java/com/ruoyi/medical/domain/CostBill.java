package com.ruoyi.medical.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 患者费用账单对象 t_medical_cost_bill
 * 
 * @author ruoyi
 * @date 2024-05-31
 */
public class CostBill extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 费用总账单ID */
    private String billId;

    /** 费用总额 */
    @Excel(name = "费用总额")
    private BigDecimal billAmount;

    /** 扣费时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "扣费时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date billCreatedate;

    /** 备注 */
    @Excel(name = "备注")
    private String billRemark;

    /** 门诊卡ID */
    @Excel(name = "门诊卡ID")
    private String outpatientId;

    public void setBillId(String billId) 
    {
        this.billId = billId;
    }

    public String getBillId() 
    {
        return billId;
    }
    public void setBillAmount(BigDecimal billAmount) 
    {
        this.billAmount = billAmount;
    }

    public BigDecimal getBillAmount() 
    {
        return billAmount;
    }
    public void setBillCreatedate(Date billCreatedate) 
    {
        this.billCreatedate = billCreatedate;
    }

    public Date getBillCreatedate() 
    {
        return billCreatedate;
    }
    public void setBillRemark(String billRemark) 
    {
        this.billRemark = billRemark;
    }

    public String getBillRemark() 
    {
        return billRemark;
    }
    public void setOutpatientId(String outpatientId) 
    {
        this.outpatientId = outpatientId;
    }

    public String getOutpatientId() 
    {
        return outpatientId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("billId", getBillId())
            .append("billAmount", getBillAmount())
            .append("billCreatedate", getBillCreatedate())
            .append("billRemark", getBillRemark())
            .append("outpatientId", getOutpatientId())
            .toString();
    }
}
