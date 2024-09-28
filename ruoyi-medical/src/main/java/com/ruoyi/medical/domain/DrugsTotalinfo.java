package com.ruoyi.medical.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 取药信息对象 t_medical_drugs_totalinfo
 * 
 * @author ruoyi
 * @date 2024-06-14
 */
public class DrugsTotalinfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 取药明细ID */
    private Long totalinfoId;

    /** 取药总表ID */
    @Excel(name = "取药总表ID")
    private String totalId;

    /** 药品ID */
    @Excel(name = "药品ID")
    private String drugsId;

    /** 取药数量 */
    @Excel(name = "取药数量")
    private Long totalNo;

    public void setTotalinfoId(Long totalinfoId) 
    {
        this.totalinfoId = totalinfoId;
    }

    public Long getTotalinfoId() 
    {
        return totalinfoId;
    }
    public void setTotalId(String totalId) 
    {
        this.totalId = totalId;
    }

    public String getTotalId() 
    {
        return totalId;
    }
    public void setDrugsId(String drugsId) 
    {
        this.drugsId = drugsId;
    }

    public String getDrugsId() 
    {
        return drugsId;
    }
    public void setTotalNo(Long totalNo) 
    {
        this.totalNo = totalNo;
    }

    public Long getTotalNo() 
    {
        return totalNo;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("totalinfoId", getTotalinfoId())
            .append("totalId", getTotalId())
            .append("drugsId", getDrugsId())
            .append("totalNo", getTotalNo())
            .toString();
    }
}
