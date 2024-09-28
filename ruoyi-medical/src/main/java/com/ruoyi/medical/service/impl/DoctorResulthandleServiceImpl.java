package com.ruoyi.medical.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.medical.mapper.DoctorResulthandleMapper;
import com.ruoyi.medical.domain.DoctorResulthandle;
import com.ruoyi.medical.service.IDoctorResulthandleService;

/**
 * 医生看病治疗方案Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-06-15
 */
@Service
public class DoctorResulthandleServiceImpl implements IDoctorResulthandleService 
{
    @Autowired
    private DoctorResulthandleMapper doctorResulthandleMapper;

    /**
     * 查询医生看病治疗方案
     * 
     * @param treatId 医生看病治疗方案主键
     * @return 医生看病治疗方案
     */
    @Override
    public DoctorResulthandle selectDoctorResulthandleByTreatId(Long treatId)
    {
        return doctorResulthandleMapper.selectDoctorResulthandleByTreatId(treatId);
    }

    /**
     * 查询医生看病治疗方案列表
     * 
     * @param doctorResulthandle 医生看病治疗方案
     * @return 医生看病治疗方案
     */
    @Override
    public List<DoctorResulthandle> selectDoctorResulthandleList(DoctorResulthandle doctorResulthandle)
    {
        return doctorResulthandleMapper.selectDoctorResulthandleList(doctorResulthandle);
    }

    /**
     * 新增医生看病治疗方案
     * 
     * @param doctorResulthandle 医生看病治疗方案
     * @return 结果
     */
    @Override
    public int insertDoctorResulthandle(DoctorResulthandle doctorResulthandle)
    {
        return doctorResulthandleMapper.insertDoctorResulthandle(doctorResulthandle);
    }

    /**
     * 修改医生看病治疗方案
     * 
     * @param doctorResulthandle 医生看病治疗方案
     * @return 结果
     */
    @Override
    public int updateDoctorResulthandle(DoctorResulthandle doctorResulthandle)
    {
        return doctorResulthandleMapper.updateDoctorResulthandle(doctorResulthandle);
    }

    /**
     * 批量删除医生看病治疗方案
     * 
     * @param treatIds 需要删除的医生看病治疗方案主键
     * @return 结果
     */
    @Override
    public int deleteDoctorResulthandleByTreatIds(Long[] treatIds)
    {
        return doctorResulthandleMapper.deleteDoctorResulthandleByTreatIds(treatIds);
    }

    /**
     * 删除医生看病治疗方案信息
     * 
     * @param treatId 医生看病治疗方案主键
     * @return 结果
     */
    @Override
    public int deleteDoctorResulthandleByTreatId(Long treatId)
    {
        return doctorResulthandleMapper.deleteDoctorResulthandleByTreatId(treatId);
    }
}
