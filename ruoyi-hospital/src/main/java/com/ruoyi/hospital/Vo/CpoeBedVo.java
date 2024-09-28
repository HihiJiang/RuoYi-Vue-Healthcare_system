package com.ruoyi.hospital.Vo;

import com.ruoyi.hospital.domain.CpoeBed;
import lombok.Data;

@Data
public class CpoeBedVo extends CpoeBed {
    //病房名称
    private String roomName;
    //病房地址
    private String roomAddr;
    //所属科室
    private String roomDept;
}
