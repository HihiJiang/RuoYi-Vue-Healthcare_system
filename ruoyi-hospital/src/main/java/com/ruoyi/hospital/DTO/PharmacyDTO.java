package com.ruoyi.hospital.DTO;

import com.ruoyi.hospital.domain.Pharmacy;
import lombok.Data;

import java.math.BigDecimal;
@Data
public class PharmacyDTO extends Pharmacy {
    //药品名称
    private String drugsName;

    //药品规格
    private String drugsSize;

    //药品售价
    private BigDecimal drugsOutpay;

}
