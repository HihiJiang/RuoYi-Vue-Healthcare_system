package com.ruoyi.hospital.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 病房记录对象 t_medical_cpoe_sickroom
 * 
 * @author ruoyi
 * @date 2024-06-04
 */
public class CpoeSickroom extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 病房ID */
    private Long roomId;

    /** 病房名称 */
    @Excel(name = "病房名称")
    private String roomName;

    /** 病房规格，例如：单人间，双人间等等 */
    @Excel(name = "病房规格，例如：单人间，双人间等等")
    private String roomSize;

    /** 床位数 */
    @Excel(name = "床位数")
    private Long roomNum;

    /** 所属科室，例如：儿童科、耳鼻科等等 */
    @Excel(name = "所属科室，例如：儿童科、耳鼻科等等")
    private String roomDept;

    /** 病房地址 */
    @Excel(name = "病房地址")
    private String roomAddr;

    /** 备注 */
    @Excel(name = "备注")
    private String roomRemark;

    public void setRoomId(Long roomId) 
    {
        this.roomId = roomId;
    }

    public Long getRoomId() 
    {
        return roomId;
    }
    public void setRoomName(String roomName) 
    {
        this.roomName = roomName;
    }

    public String getRoomName() 
    {
        return roomName;
    }
    public void setRoomSize(String roomSize) 
    {
        this.roomSize = roomSize;
    }

    public String getRoomSize() 
    {
        return roomSize;
    }
    public void setRoomNum(Long roomNum) 
    {
        this.roomNum = roomNum;
    }

    public Long getRoomNum() 
    {
        return roomNum;
    }
    public void setRoomDept(String roomDept) 
    {
        this.roomDept = roomDept;
    }

    public String getRoomDept() 
    {
        return roomDept;
    }
    public void setRoomAddr(String roomAddr) 
    {
        this.roomAddr = roomAddr;
    }

    public String getRoomAddr() 
    {
        return roomAddr;
    }
    public void setRoomRemark(String roomRemark) 
    {
        this.roomRemark = roomRemark;
    }

    public String getRoomRemark() 
    {
        return roomRemark;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("roomId", getRoomId())
            .append("roomName", getRoomName())
            .append("roomSize", getRoomSize())
            .append("roomNum", getRoomNum())
            .append("roomDept", getRoomDept())
            .append("roomAddr", getRoomAddr())
            .append("roomRemark", getRoomRemark())
            .toString();
    }
}
