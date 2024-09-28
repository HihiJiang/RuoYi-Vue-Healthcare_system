package com.ruoyi.medical.vo;


import com.ruoyi.medical.domain.CostOutpatientcard;
import lombok.Data;

@Data
public class CostOutpatientcardVo extends CostOutpatientcard {

    //患者姓名
    private String patientName;

    //患者性别
    private String patientSex;

    //联系电话
    private String patientPhone;

    //患者身份证号
    private String patientIdcard;


}
