package com.ruoyi.hospital.DTO;

import com.ruoyi.hospital.domain.User;
import lombok.Data;

@Data
public class UserDTO extends User {
    //部门名称
    private String dpName;
}
