package com.ruoyi.hospital.DTO;

import com.ruoyi.hospital.domain.Doctorsorder;
import lombok.Data;

@Data
public class DoctorsorderDTO extends Doctorsorder {
    //医生姓名
    private String nickName;
    //患者姓名
    private String patientName;
}