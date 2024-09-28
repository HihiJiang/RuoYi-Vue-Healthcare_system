package com.ruoyi.medical.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 药品信息对象 t_medical_drugs_info
 * 
 * @author ruoyi
 * @date 2024-06-14
 */
public class DrugsInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 药品ID */
    private Long drugsId;

    /** 药品名称 */
    @Excel(name = "药品名称")
    private String drugsName;

    /** 拼音名 */
    @Excel(name = "拼音名")
    private String drugsPinyin;

    /** 规格 */
    @Excel(name = "规格")
    private String drugsSize;

    /** 药品描述 */
    @Excel(name = "药品描述")
    private String drugsDesc;

    /** 生产厂家 */
    @Excel(name = "生产厂家")
    private String drugsFactory;

    /** 有效期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "有效期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date drugsExpire;

    /** 药品状态，1在用2作废 */
    @Excel(name = "药品状态，1在用2作废")
    private String drugsStatus;

    /** 进价 */
    @Excel(name = "进价")
    private BigDecimal drugsInpay;

    /** 售价 */
    @Excel(name = "售价")
    private BigDecimal drugsOutpay;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date drugsCreatetime;

    /** 操作人ID */
    @Excel(name = "操作人ID")
    private Long operatorId;

    public void setDrugsId(Long drugsId) 
    {
        this.drugsId = drugsId;
    }

    public Long getDrugsId() 
    {
        return drugsId;
    }
    public void setDrugsName(String drugsName) 
    {
        this.drugsName = drugsName;
    }

    public String getDrugsName() 
    {
        return drugsName;
    }
    public void setDrugsPinyin(String drugsPinyin) 
    {
        this.drugsPinyin = drugsPinyin;
    }

    public String getDrugsPinyin() 
    {
        return drugsPinyin;
    }
    public void setDrugsSize(String drugsSize) 
    {
        this.drugsSize = drugsSize;
    }

    public String getDrugsSize() 
    {
        return drugsSize;
    }
    public void setDrugsDesc(String drugsDesc) 
    {
        this.drugsDesc = drugsDesc;
    }

    public String getDrugsDesc() 
    {
        return drugsDesc;
    }
    public void setDrugsFactory(String drugsFactory) 
    {
        this.drugsFactory = drugsFactory;
    }

    public String getDrugsFactory() 
    {
        return drugsFactory;
    }
    public void setDrugsExpire(Date drugsExpire) 
    {
        this.drugsExpire = drugsExpire;
    }

    public Date getDrugsExpire() 
    {
        return drugsExpire;
    }
    public void setDrugsStatus(String drugsStatus) 
    {
        this.drugsStatus = drugsStatus;
    }

    public String getDrugsStatus() 
    {
        return drugsStatus;
    }
    public void setDrugsInpay(BigDecimal drugsInpay) 
    {
        this.drugsInpay = drugsInpay;
    }

    public BigDecimal getDrugsInpay() 
    {
        return drugsInpay;
    }
    public void setDrugsOutpay(BigDecimal drugsOutpay) 
    {
        this.drugsOutpay = drugsOutpay;
    }

    public BigDecimal getDrugsOutpay() 
    {
        return drugsOutpay;
    }
    public void setDrugsCreatetime(Date drugsCreatetime) 
    {
        this.drugsCreatetime = drugsCreatetime;
    }

    public Date getDrugsCreatetime() 
    {
        return drugsCreatetime;
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
            .append("drugsId", getDrugsId())
            .append("drugsName", getDrugsName())
            .append("drugsPinyin", getDrugsPinyin())
            .append("drugsSize", getDrugsSize())
            .append("drugsDesc", getDrugsDesc())
            .append("drugsFactory", getDrugsFactory())
            .append("drugsExpire", getDrugsExpire())
            .append("drugsStatus", getDrugsStatus())
            .append("drugsInpay", getDrugsInpay())
            .append("drugsOutpay", getDrugsOutpay())
            .append("drugsCreatetime", getDrugsCreatetime())
            .append("operatorId", getOperatorId())
            .toString();
    }
}
