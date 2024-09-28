package com.ruoyi.hospital.DTO;

import com.ruoyi.hospital.domain.CpoeSplitbed;
import lombok.Data;

@Data
public class CpoeSplitbedDTO  extends CpoeSplitbed {
    //房间名称(mapper查询语句给了别名)
    private String rmName;

    //床位名称(同上)
    private String bedName;

    //用户id (职工ID)(同上)
    private String uId;

    //职工姓名(同上)(医生)
    private String doctorName;

    //患者姓名
    private String phName;

    //患者身份证号
    private String patientIdcard;

}
