package com.ruoyi.hospital.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 住院治疗处方总对象 t_medical_cpoe_prescriptiontotal
 * 
 * @author ruoyi
 * @date 2024-06-13
 */
public class Prescriptiontotal extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 汇总ID */
    private Long prestId;

    /** 状态，1草稿2发送3撤回4退回5完成认领 */
    @Excel(name = "状态，1草稿2发送3撤回4退回5完成认领")
    private Long prestStatus;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date prestCreatetime;

    /** 备注 */
    @Excel(name = "备注")
    private String prestRemark;

    public void setPrestId(Long prestId) 
    {
        this.prestId = prestId;
    }

    public Long getPrestId() 
    {
        return prestId;
    }
    public void setPrestStatus(Long prestStatus) 
    {
        this.prestStatus = prestStatus;
    }

    public Long getPrestStatus() 
    {
        return prestStatus;
    }
    public void setPrestCreatetime(Date prestCreatetime) 
    {
        this.prestCreatetime = prestCreatetime;
    }

    public Date getPrestCreatetime() 
    {
        return prestCreatetime;
    }
    public void setPrestRemark(String prestRemark) 
    {
        this.prestRemark = prestRemark;
    }

    public String getPrestRemark() 
    {
        return prestRemark;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("prestId", getPrestId())
            .append("prestStatus", getPrestStatus())
            .append("prestCreatetime", getPrestCreatetime())
            .append("prestRemark", getPrestRemark())
            .toString();
    }
}
