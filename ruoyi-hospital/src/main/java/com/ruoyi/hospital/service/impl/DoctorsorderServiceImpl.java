package com.ruoyi.hospital.service.impl;

import java.util.List;

import com.ruoyi.hospital.DTO.DoctorsorderDTO;
import com.ruoyi.hospital.Vo.DoctorsorderVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.hospital.mapper.DoctorsorderMapper;
import com.ruoyi.hospital.domain.Doctorsorder;
import com.ruoyi.hospital.service.IDoctorsorderService;

/**
 * 医嘱记录Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-06-13
 */
@Service
public class DoctorsorderServiceImpl implements IDoctorsorderService 
{
    @Autowired
    private DoctorsorderMapper doctorsorderMapper;

    /**
     * 查询医嘱记录
     * 
     * @param ordersId 医嘱记录主键
     * @return 医嘱记录
     */
    @Override
    public Doctorsorder selectDoctorsorderByOrdersId(Long ordersId)
    {
        return doctorsorderMapper.selectDoctorsorderByOrdersId(ordersId);
    }

    /**
     * 查询医嘱记录列表
     * 
     * @param doctorsorder 医嘱记录
     * @return 医嘱记录
     */
    @Override
    public List<DoctorsorderVo> selectDoctorsorderList(DoctorsorderDTO doctorsorder)
    {
        return doctorsorderMapper.selectDoctorsorderList(doctorsorder);
    }

    /**
     * 新增医嘱记录
     * 
     * @param doctorsorder 医嘱记录
     * @return 结果
     */
    @Override
    public int insertDoctorsorder(Doctorsorder doctorsorder)
    {
        return doctorsorderMapper.insertDoctorsorder(doctorsorder);
    }

    /**
     * 修改医嘱记录
     * 
     * @param doctorsorder 医嘱记录
     * @return 结果
     */
    @Override
    public int updateDoctorsorder(Doctorsorder doctorsorder)
    {
        return doctorsorderMapper.updateDoctorsorder(doctorsorder);
    }

    /**
     * 批量删除医嘱记录
     * 
     * @param ordersIds 需要删除的医嘱记录主键
     * @return 结果
     */
    @Override
    public int deleteDoctorsorderByOrdersIds(Long[] ordersIds)
    {
        return doctorsorderMapper.deleteDoctorsorderByOrdersIds(ordersIds);
    }

    /**
     * 删除医嘱记录信息
     * 
     * @param ordersId 医嘱记录主键
     * @return 结果
     */
    @Override
    public int deleteDoctorsorderByOrdersId(Long ordersId)
    {
        return doctorsorderMapper.deleteDoctorsorderByOrdersId(ordersId);
    }
}
