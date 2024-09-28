package com.ruoyi.hospital.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 住院治疗处方对象 t_medical_cpoe_prescription
 * 
 * @author ruoyi
 * @date 2024-06-13
 */
public class Prescription extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 治疗处方ID */
    private Long presId;

    /** 医嘱记录ID */
    @Excel(name = "医嘱记录ID")
    private Long ordersId;

    /** 消费事项 */
    @Excel(name = "消费事项")
    private String presPartname;

    /** 消费数量 */
    @Excel(name = "消费数量")
    private Long presPartnumber;

    /** 消费类别 0药品1检验2检查 */
    @Excel(name = "消费类别 0药品1检验2检查")
    private Long presType;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date presCreatetime;

    /** 备注 */
    @Excel(name = "备注")
    private String presRemark;

    /** 操作人ID */
    @Excel(name = "操作人ID")
    private Long operatorId;

    public void setPresId(Long presId) 
    {
        this.presId = presId;
    }

    public Long getPresId() 
    {
        return presId;
    }
    public void setOrdersId(Long ordersId) 
    {
        this.ordersId = ordersId;
    }

    public Long getOrdersId() 
    {
        return ordersId;
    }
    public void setPresPartname(String presPartname) 
    {
        this.presPartname = presPartname;
    }

    public String getPresPartname() 
    {
        return presPartname;
    }
    public void setPresPartnumber(Long presPartnumber) 
    {
        this.presPartnumber = presPartnumber;
    }

    public Long getPresPartnumber() 
    {
        return presPartnumber;
    }
    public void setPresType(Long presType) 
    {
        this.presType = presType;
    }

    public Long getPresType() 
    {
        return presType;
    }
    public void setPresCreatetime(Date presCreatetime) 
    {
        this.presCreatetime = presCreatetime;
    }

    public Date getPresCreatetime() 
    {
        return presCreatetime;
    }
    public void setPresRemark(String presRemark) 
    {
        this.presRemark = presRemark;
    }

    public String getPresRemark() 
    {
        return presRemark;
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
            .append("presId", getPresId())
            .append("ordersId", getOrdersId())
            .append("presPartname", getPresPartname())
            .append("presPartnumber", getPresPartnumber())
            .append("presType", getPresType())
            .append("presCreatetime", getPresCreatetime())
            .append("presRemark", getPresRemark())
            .append("operatorId", getOperatorId())
            .toString();
    }
}
