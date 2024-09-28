package com.ruoyi.medical.vo;

import com.ruoyi.medical.domain.CostBill;
import lombok.Data;

@Data
public class CostBillVo extends CostBill {

    //患者姓名
    private String patientName;

    //患者身份证ID
    private String patientIdcard;

}
