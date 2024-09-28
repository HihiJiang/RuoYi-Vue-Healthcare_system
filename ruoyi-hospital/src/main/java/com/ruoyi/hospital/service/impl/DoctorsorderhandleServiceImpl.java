package com.ruoyi.hospital.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.hospital.mapper.DoctorsorderhandleMapper;
import com.ruoyi.hospital.domain.Doctorsorderhandle;
import com.ruoyi.hospital.service.IDoctorsorderhandleService;

/**
 * 医嘱执行记录Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-06-13
 */
@Service
public class DoctorsorderhandleServiceImpl implements IDoctorsorderhandleService 
{
    @Autowired
    private DoctorsorderhandleMapper doctorsorderhandleMapper;

    /**
     * 查询医嘱执行记录
     * 
     * @param ordershId 医嘱执行记录主键
     * @return 医嘱执行记录
     */
    @Override
    public Doctorsorderhandle selectDoctorsorderhandleByOrdershId(Long ordershId)
    {
        return doctorsorderhandleMapper.selectDoctorsorderhandleByOrdershId(ordershId);
    }

    /**
     * 查询医嘱执行记录列表
     * 
     * @param doctorsorderhandle 医嘱执行记录
     * @return 医嘱执行记录
     */
    @Override
    public List<Doctorsorderhandle> selectDoctorsorderhandleList(Doctorsorderhandle doctorsorderhandle)
    {
        return doctorsorderhandleMapper.selectDoctorsorderhandleList(doctorsorderhandle);
    }

    /**
     * 新增医嘱执行记录
     * 
     * @param doctorsorderhandle 医嘱执行记录
     * @return 结果
     */
    @Override
    public int insertDoctorsorderhandle(Doctorsorderhandle doctorsorderhandle)
    {
        return doctorsorderhandleMapper.insertDoctorsorderhandle(doctorsorderhandle);
    }

    /**
     * 修改医嘱执行记录
     * 
     * @param doctorsorderhandle 医嘱执行记录
     * @return 结果
     */
    @Override
    public int updateDoctorsorderhandle(Doctorsorderhandle doctorsorderhandle)
    {
        return doctorsorderhandleMapper.updateDoctorsorderhandle(doctorsorderhandle);
    }

    /**
     * 批量删除医嘱执行记录
     * 
     * @param ordershIds 需要删除的医嘱执行记录主键
     * @return 结果
     */
    @Override
    public int deleteDoctorsorderhandleByOrdershIds(Long[] ordershIds)
    {
        return doctorsorderhandleMapper.deleteDoctorsorderhandleByOrdershIds(ordershIds);
    }

    /**
     * 删除医嘱执行记录信息
     * 
     * @param ordershId 医嘱执行记录主键
     * @return 结果
     */
    @Override
    public int deleteDoctorsorderhandleByOrdershId(Long ordershId)
    {
        return doctorsorderhandleMapper.deleteDoctorsorderhandleByOrdershId(ordershId);
    }
}
