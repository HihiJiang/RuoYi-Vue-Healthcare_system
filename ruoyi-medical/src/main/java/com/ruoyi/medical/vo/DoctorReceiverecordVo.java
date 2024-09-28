package com.ruoyi.medical.vo;

import com.ruoyi.medical.domain.DoctorReceiverecord;
import lombok.Data;

@Data
public class DoctorReceiverecordVo extends DoctorReceiverecord {

    //患者id
    private  String patientId;
    //患者姓名
    private String patientName;

    //医生名称
    private String nickName;
    //科室名称
    private String deptName;

}
