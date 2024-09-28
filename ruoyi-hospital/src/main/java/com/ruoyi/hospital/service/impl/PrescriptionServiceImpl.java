package com.ruoyi.hospital.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.hospital.mapper.PrescriptionMapper;
import com.ruoyi.hospital.domain.Prescription;
import com.ruoyi.hospital.service.IPrescriptionService;

/**
 * 住院治疗处方Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-06-13
 */
@Service
public class PrescriptionServiceImpl implements IPrescriptionService 
{
    @Autowired
    private PrescriptionMapper prescriptionMapper;

    /**
     * 查询住院治疗处方
     * 
     * @param presId 住院治疗处方主键
     * @return 住院治疗处方
     */
    @Override
    public Prescription selectPrescriptionByPresId(Long presId)
    {
        return prescriptionMapper.selectPrescriptionByPresId(presId);
    }

    /**
     * 查询住院治疗处方列表
     * 
     * @param prescription 住院治疗处方
     * @return 住院治疗处方
     */
    @Override
    public List<Prescription> selectPrescriptionList(Prescription prescription)
    {
        return prescriptionMapper.selectPrescriptionList(prescription);
    }

    /**
     * 新增住院治疗处方
     * 
     * @param prescription 住院治疗处方
     * @return 结果
     */
    @Override
    public int insertPrescription(Prescription prescription)
    {
        return prescriptionMapper.insertPrescription(prescription);
    }

    /**
     * 修改住院治疗处方
     * 
     * @param prescription 住院治疗处方
     * @return 结果
     */
    @Override
    public int updatePrescription(Prescription prescription)
    {
        return prescriptionMapper.updatePrescription(prescription);
    }

    /**
     * 批量删除住院治疗处方
     * 
     * @param presIds 需要删除的住院治疗处方主键
     * @return 结果
     */
    @Override
    public int deletePrescriptionByPresIds(Long[] presIds)
    {
        return prescriptionMapper.deletePrescriptionByPresIds(presIds);
    }

    /**
     * 删除住院治疗处方信息
     * 
     * @param presId 住院治疗处方主键
     * @return 结果
     */
    @Override
    public int deletePrescriptionByPresId(Long presId)
    {
        return prescriptionMapper.deletePrescriptionByPresId(presId);
    }
}
