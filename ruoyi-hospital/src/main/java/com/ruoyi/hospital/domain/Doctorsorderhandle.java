package com.ruoyi.hospital.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 医嘱执行记录对象 t_medical_cpoe_doctorsorderhandle
 * 
 * @author ruoyi
 * @date 2024-06-13
 */
public class Doctorsorderhandle extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 医嘱执行ID */
    private Long ordershId;

    /** 医嘱ID */
    @Excel(name = "医嘱ID")
    private Long ordersId;

    /** 医嘱执行结果，1正常2异常 */
    @Excel(name = "医嘱执行结果，1正常2异常")
    private Long ordershResult;

    /** 医嘱执行人ID（护士ID） */
    @Excel(name = "医嘱执行人ID", readConverterExp = "护=士ID")
    private Long ordershPersonid;

    /** 医嘱执行时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "医嘱执行时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date ordershCreatetime;

    /** 医嘱执行完成时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "医嘱执行完成时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date ordershCompletetime;

    /** 备注 */
    @Excel(name = "备注")
    private String ordershRemark;

    public void setOrdershId(Long ordershId) 
    {
        this.ordershId = ordershId;
    }

    public Long getOrdershId() 
    {
        return ordershId;
    }
    public void setOrdersId(Long ordersId) 
    {
        this.ordersId = ordersId;
    }

    public Long getOrdersId() 
    {
        return ordersId;
    }
    public void setOrdershResult(Long ordershResult) 
    {
        this.ordershResult = ordershResult;
    }

    public Long getOrdershResult() 
    {
        return ordershResult;
    }
    public void setOrdershPersonid(Long ordershPersonid) 
    {
        this.ordershPersonid = ordershPersonid;
    }

    public Long getOrdershPersonid() 
    {
        return ordershPersonid;
    }
    public void setOrdershCreatetime(Date ordershCreatetime) 
    {
        this.ordershCreatetime = ordershCreatetime;
    }

    public Date getOrdershCreatetime() 
    {
        return ordershCreatetime;
    }
    public void setOrdershCompletetime(Date ordershCompletetime) 
    {
        this.ordershCompletetime = ordershCompletetime;
    }

    public Date getOrdershCompletetime() 
    {
        return ordershCompletetime;
    }
    public void setOrdershRemark(String ordershRemark) 
    {
        this.ordershRemark = ordershRemark;
    }

    public String getOrdershRemark() 
    {
        return ordershRemark;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("ordershId", getOrdershId())
            .append("ordersId", getOrdersId())
            .append("ordershResult", getOrdershResult())
            .append("ordershPersonid", getOrdershPersonid())
            .append("ordershCreatetime", getOrdershCreatetime())
            .append("ordershCompletetime", getOrdershCompletetime())
            .append("ordershRemark", getOrdershRemark())
            .toString();
    }
}
