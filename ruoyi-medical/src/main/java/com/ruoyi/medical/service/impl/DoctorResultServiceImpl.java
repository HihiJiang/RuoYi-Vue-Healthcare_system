package com.ruoyi.medical.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.medical.mapper.DoctorResultMapper;
import com.ruoyi.medical.domain.DoctorResult;
import com.ruoyi.medical.service.IDoctorResultService;

/**
 * 医生看病结果Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-06-14
 */
@Service
public class DoctorResultServiceImpl implements IDoctorResultService 
{
    @Autowired
    private DoctorResultMapper doctorResultMapper;

    /**
     * 查询医生看病结果
     * 
     * @param resultId 医生看病结果主键
     * @return 医生看病结果
     */
    @Override
    public DoctorResult selectDoctorResultByResultId(Long resultId)
    {
        return doctorResultMapper.selectDoctorResultByResultId(resultId);
    }

    /**
     * 查询医生看病结果列表
     * 
     * @param doctorResult 医生看病结果
     * @return 医生看病结果
     */
    @Override
    public List<DoctorResult> selectDoctorResultList(DoctorResult doctorResult)
    {
        return doctorResultMapper.selectDoctorResultList(doctorResult);
    }

    /**
     * 新增医生看病结果
     * 
     * @param doctorResult 医生看病结果
     * @return 结果
     */
    @Override
    public int insertDoctorResult(DoctorResult doctorResult)
    {
        return doctorResultMapper.insertDoctorResult(doctorResult);
    }

    /**
     * 修改医生看病结果
     * 
     * @param doctorResult 医生看病结果
     * @return 结果
     */
    @Override
    public int updateDoctorResult(DoctorResult doctorResult)
    {
        return doctorResultMapper.updateDoctorResult(doctorResult);
    }

    /**
     * 批量删除医生看病结果
     * 
     * @param resultIds 需要删除的医生看病结果主键
     * @return 结果
     */
    @Override
    public int deleteDoctorResultByResultIds(Long[] resultIds)
    {
        return doctorResultMapper.deleteDoctorResultByResultIds(resultIds);
    }

    /**
     * 删除医生看病结果信息
     * 
     * @param resultId 医生看病结果主键
     * @return 结果
     */
    @Override
    public int deleteDoctorResultByResultId(Long resultId)
    {
        return doctorResultMapper.deleteDoctorResultByResultId(resultId);
    }
}
