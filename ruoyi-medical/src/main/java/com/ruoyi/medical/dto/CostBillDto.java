package com.ruoyi.medical.dto;

import com.ruoyi.medical.domain.CostBill;
import lombok.Data;

import java.util.Date;

@Data
public class CostBillDto extends CostBill {
    //患者姓名
    private String patientName;
    //患者身份证ID
    private String patientIdcard;
}
