package com.ruoyi.hospital.DTO;

import com.ruoyi.hospital.domain.CpoeSickroom;
import lombok.Data;

@Data
public class CpoeSickroomDTO extends CpoeSickroom {
    //病房已住数量
    private String countNum;

    //病房空床位数量
    private String nullNum;

    //病房床位查询条件 1 已住 0 空床
    private String bedStatus;
}
