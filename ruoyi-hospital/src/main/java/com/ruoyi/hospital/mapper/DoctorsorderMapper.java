package com.ruoyi.hospital.mapper;

import java.util.List;

import com.ruoyi.hospital.DTO.DoctorsorderDTO;
import com.ruoyi.hospital.Vo.DoctorsorderVo;
import com.ruoyi.hospital.domain.Doctorsorder;

/**
 * 医嘱记录Mapper接口
 * 
 * @author ruoyi
 * @date 2024-06-13
 */
public interface DoctorsorderMapper 
{
    /**
     * 查询医嘱记录
     * 
     * @param ordersId 医嘱记录主键
     * @return 医嘱记录
     */
    public Doctorsorder selectDoctorsorderByOrdersId(Long ordersId);

    /**
     * 查询医嘱记录列表
     * 
     * @param doctorsorder 医嘱记录
     * @return 医嘱记录集合
     */
    public List<DoctorsorderVo> selectDoctorsorderList(DoctorsorderDTO doctorsorder);

    /**
     * 新增医嘱记录
     * 
     * @param doctorsorder 医嘱记录
     * @return 结果
     */
    public int insertDoctorsorder(Doctorsorder doctorsorder);

    /**
     * 修改医嘱记录
     * 
     * @param doctorsorder 医嘱记录
     * @return 结果
     */
    public int updateDoctorsorder(Doctorsorder doctorsorder);

    /**
     * 删除医嘱记录
     * 
     * @param ordersId 医嘱记录主键
     * @return 结果
     */
    public int deleteDoctorsorderByOrdersId(Long ordersId);

    /**
     * 批量删除医嘱记录
     * 
     * @param ordersIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteDoctorsorderByOrdersIds(Long[] ordersIds);
}
