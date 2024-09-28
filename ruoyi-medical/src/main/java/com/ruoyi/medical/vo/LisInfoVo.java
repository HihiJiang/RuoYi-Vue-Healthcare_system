package com.ruoyi.medical.vo;

import com.ruoyi.medical.domain.LisInfo;
import lombok.Data;

@Data
public class LisInfoVo extends LisInfo {
    //患者姓名
    private String patientName;

    //医生姓名
    private String  nickName;
}
