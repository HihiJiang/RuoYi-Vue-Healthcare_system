package com.ruoyi.medical.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 医生看病流程对象 t_medical_outpatientdoctor_action
 * 
 * @author ruoyi
 * @date 2024-06-13
 */
public class DoctorAction extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 看病流程ID */
    private Long actionId;

    /** 消费事项，如：检查CT、检验血常规 */
    @Excel(name = "消费事项，如：检查CT、检验血常规")
    private String actionPartname;

    /** 消费数量 */
    @Excel(name = "消费数量")
    private Long actionPartnumber;

    /** 患者类型，1门诊患者2住院患者 */
    @Excel(name = "患者类型，1门诊患者2住院患者")
    private String actionPatienttype;

    /** 单价 */
    @Excel(name = "单价")
    private BigDecimal actionPartmoney;

    /** 类型，1检查2检验3处置 */
    @Excel(name = "类型，1检查2检验3处置")
    private String actionType;

    /** 接诊记录ID */
    @Excel(name = "接诊记录ID")
    private String recordsId;

    public void setActionId(Long actionId) 
    {
        this.actionId = actionId;
    }

    public Long getActionId() 
    {
        return actionId;
    }
    public void setActionPartname(String actionPartname) 
    {
        this.actionPartname = actionPartname;
    }

    public String getActionPartname() 
    {
        return actionPartname;
    }
    public void setActionPartnumber(Long actionPartnumber) 
    {
        this.actionPartnumber = actionPartnumber;
    }

    public Long getActionPartnumber() 
    {
        return actionPartnumber;
    }
    public void setActionPatienttype(String actionPatienttype) 
    {
        this.actionPatienttype = actionPatienttype;
    }

    public String getActionPatienttype() 
    {
        return actionPatienttype;
    }
    public void setActionPartmoney(BigDecimal actionPartmoney) 
    {
        this.actionPartmoney = actionPartmoney;
    }

    public BigDecimal getActionPartmoney() 
    {
        return actionPartmoney;
    }
    public void setActionType(String actionType) 
    {
        this.actionType = actionType;
    }

    public String getActionType() 
    {
        return actionType;
    }
    public void setRecordsId(String recordsId) 
    {
        this.recordsId = recordsId;
    }

    public String getRecordsId() 
    {
        return recordsId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("actionId", getActionId())
            .append("actionPartname", getActionPartname())
            .append("actionPartnumber", getActionPartnumber())
            .append("actionPatienttype", getActionPatienttype())
            .append("actionPartmoney", getActionPartmoney())
            .append("actionType", getActionType())
            .append("recordsId", getRecordsId())
            .toString();
    }
}
