package com.ruoyi.hospital.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 床位记录对象 t_medical_cpoe_bed
 * 
 * @author ruoyi
 * @date 2024-06-04
 */
public class CpoeBed extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 床位ID */
    private Long bedId;

    /** 床位名称 */
    @Excel(name = "床位名称")
    private String bedName;

    /** 床位费 */
    @Excel(name = "床位费")
    private BigDecimal bedFee;

    /** 空调费 */
    @Excel(name = "空调费")
    private BigDecimal bedAirFee;

    /** 床位状态，0=空床1=已住 */
    @Excel(name = "床位状态，0=空床1=已住")
    private String bedStatus;

    /** 备注 */
    @Excel(name = "备注")
    private String bedRemark;

    /** 房间ID */
    @Excel(name = "房间ID")
    private Long roomId;

    public void setBedId(Long bedId) 
    {
        this.bedId = bedId;
    }

    public Long getBedId() 
    {
        return bedId;
    }
    public void setBedName(String bedName) 
    {
        this.bedName = bedName;
    }

    public String getBedName() 
    {
        return bedName;
    }
    public void setBedFee(BigDecimal bedFee) 
    {
        this.bedFee = bedFee;
    }

    public BigDecimal getBedFee() 
    {
        return bedFee;
    }
    public void setBedAirFee(BigDecimal bedAirFee) 
    {
        this.bedAirFee = bedAirFee;
    }

    public BigDecimal getBedAirFee() 
    {
        return bedAirFee;
    }
    public void setBedStatus(String bedStatus) 
    {
        this.bedStatus = bedStatus;
    }

    public String getBedStatus() 
    {
        return bedStatus;
    }
    public void setBedRemark(String bedRemark) 
    {
        this.bedRemark = bedRemark;
    }

    public String getBedRemark() 
    {
        return bedRemark;
    }
    public void setRoomId(Long roomId) 
    {
        this.roomId = roomId;
    }

    public Long getRoomId() 
    {
        return roomId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("bedId", getBedId())
            .append("bedName", getBedName())
            .append("bedFee", getBedFee())
            .append("bedAirFee", getBedAirFee())
            .append("bedStatus", getBedStatus())
            .append("bedRemark", getBedRemark())
            .append("roomId", getRoomId())
            .toString();
    }
}
