package com.ruoyi.medical.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 患者费用明细对象 t_medical_cost_bill_detail
 * 
 * @author ruoyi
 * @date 2024-05-31
 */
public class BillDetail extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 费用明细ID */
    private Long billdetailId;

    /** 费用总账ID */
    @Excel(name = "费用总账ID")
    private String billId;

    /** 消费事项 */
    @Excel(name = "消费事项")
    private String billdetailPartname;

    /** 消费数量 */
    @Excel(name = "消费数量")
    private Long billdetaillPartnumber;

    /** 消费单价 */
    @Excel(name = "消费单价")
    private BigDecimal billdetailPrice;

    public void setBilldetailId(Long billdetailId) 
    {
        this.billdetailId = billdetailId;
    }

    public Long getBilldetailId() 
    {
        return billdetailId;
    }
    public void setBillId(String billId) 
    {
        this.billId = billId;
    }

    public String getBillId() 
    {
        return billId;
    }
    public void setBilldetailPartname(String billdetailPartname) 
    {
        this.billdetailPartname = billdetailPartname;
    }

    public String getBilldetailPartname() 
    {
        return billdetailPartname;
    }
    public void setBilldetaillPartnumber(Long billdetaillPartnumber) 
    {
        this.billdetaillPartnumber = billdetaillPartnumber;
    }

    public Long getBilldetaillPartnumber() 
    {
        return billdetaillPartnumber;
    }
    public void setBilldetailPrice(BigDecimal billdetailPrice) 
    {
        this.billdetailPrice = billdetailPrice;
    }

    public BigDecimal getBilldetailPrice() 
    {
        return billdetailPrice;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("billdetailId", getBilldetailId())
            .append("billId", getBillId())
            .append("billdetailPartname", getBilldetailPartname())
            .append("billdetaillPartnumber", getBilldetaillPartnumber())
            .append("billdetailPrice", getBilldetailPrice())
            .toString();
    }
}
