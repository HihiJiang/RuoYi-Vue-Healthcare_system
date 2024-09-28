package com.ruoyi.hospital.Vo;

import com.ruoyi.hospital.domain.Doctorsorder;
import lombok.Data;

@Data
public class DoctorsorderVo extends Doctorsorder {
    //医生姓名
    private String nickName;
    //患者姓名
    private String patientName;
}
