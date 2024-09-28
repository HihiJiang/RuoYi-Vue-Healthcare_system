package com.ruoyi.medical.dto;


import com.ruoyi.medical.domain.CostOutpatientcard;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CostOutpatientcardDTO extends CostOutpatientcard {

    //患者姓名
    private String patientName;

    //患者性别
    private String patientSex;

    //联系电话
    private String patientPhone;

    //患者身份证号
    private String patientIdcard;

    //充值取现金额
    private BigDecimal outpatientMoney;
    //充值取现类型
    private String rechargeType;



}
