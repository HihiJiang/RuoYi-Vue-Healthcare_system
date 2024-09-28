package com.ruoyi.hospital.Vo;

import com.ruoyi.hospital.domain.User;
import lombok.Data;

@Data
public class UserVo extends User {
    //部门名称
    private String dpName;
}
