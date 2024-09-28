package com.ruoyi.medical.dto;

import com.ruoyi.medical.domain.ServiceProject;
import lombok.Data;

@Data
public class ServiceProjectDTO extends ServiceProject {

    //消费事项数量
    private Long number;
}
