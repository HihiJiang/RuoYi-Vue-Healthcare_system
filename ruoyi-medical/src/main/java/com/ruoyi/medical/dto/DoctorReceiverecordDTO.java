package com.ruoyi.medical.dto;

import com.ruoyi.medical.domain.DoctorReceiverecord;
import com.ruoyi.medical.domain.ServiceProject;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class DoctorReceiverecordDTO extends DoctorReceiverecord {
    //挂号费
    private BigDecimal toll;
    //患者姓名
    private String patientName;

    //消费事项列表
    private List<ServiceProjectDTO> partLst;
    //患者id
    private String patientId;
    //检验检查类型
    private  String actionType;
    //患者类型
   private String  actionPatienttype;

}
