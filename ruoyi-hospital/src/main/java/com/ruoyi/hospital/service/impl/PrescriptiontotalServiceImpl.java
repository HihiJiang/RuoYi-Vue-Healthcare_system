package com.ruoyi.hospital.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.hospital.mapper.PrescriptiontotalMapper;
import com.ruoyi.hospital.domain.Prescriptiontotal;
import com.ruoyi.hospital.service.IPrescriptiontotalService;

/**
 * 住院治疗处方总Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-06-13
 */
@Service
public class PrescriptiontotalServiceImpl implements IPrescriptiontotalService 
{
    @Autowired
    private PrescriptiontotalMapper prescriptiontotalMapper;

    /**
     * 查询住院治疗处方总
     * 
     * @param prestId 住院治疗处方总主键
     * @return 住院治疗处方总
     */
    @Override
    public Prescriptiontotal selectPrescriptiontotalByPrestId(Long prestId)
    {
        return prescriptiontotalMapper.selectPrescriptiontotalByPrestId(prestId);
    }

    /**
     * 查询住院治疗处方总列表
     * 
     * @param prescriptiontotal 住院治疗处方总
     * @return 住院治疗处方总
     */
    @Override
    public List<Prescriptiontotal> selectPrescriptiontotalList(Prescriptiontotal prescriptiontotal)
    {
        return prescriptiontotalMapper.selectPrescriptiontotalList(prescriptiontotal);
    }

    /**
     * 新增住院治疗处方总
     * 
     * @param prescriptiontotal 住院治疗处方总
     * @return 结果
     */
    @Override
    public int insertPrescriptiontotal(Prescriptiontotal prescriptiontotal)
    {
        return prescriptiontotalMapper.insertPrescriptiontotal(prescriptiontotal);
    }

    /**
     * 修改住院治疗处方总
     * 
     * @param prescriptiontotal 住院治疗处方总
     * @return 结果
     */
    @Override
    public int updatePrescriptiontotal(Prescriptiontotal prescriptiontotal)
    {
        return prescriptiontotalMapper.updatePrescriptiontotal(prescriptiontotal);
    }

    /**
     * 批量删除住院治疗处方总
     * 
     * @param prestIds 需要删除的住院治疗处方总主键
     * @return 结果
     */
    @Override
    public int deletePrescriptiontotalByPrestIds(Long[] prestIds)
    {
        return prescriptiontotalMapper.deletePrescriptiontotalByPrestIds(prestIds);
    }

    /**
     * 删除住院治疗处方总信息
     * 
     * @param prestId 住院治疗处方总主键
     * @return 结果
     */
    @Override
    public int deletePrescriptiontotalByPrestId(Long prestId)
    {
        return prescriptiontotalMapper.deletePrescriptiontotalByPrestId(prestId);
    }
}
