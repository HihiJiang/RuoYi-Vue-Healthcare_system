package com.ruoyi.medical.vo;

import com.ruoyi.medical.domain.PacsInfo;
import lombok.Data;

@Data
public class PacsInfoVo extends PacsInfo {

    //患者姓名
    private String patientName;

    //医生姓名
    private String  nickName;

}
