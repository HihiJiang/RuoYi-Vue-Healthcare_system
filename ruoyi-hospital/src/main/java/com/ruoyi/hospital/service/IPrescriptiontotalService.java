package com.ruoyi.hospital.service;

import java.util.List;
import com.ruoyi.hospital.domain.Prescriptiontotal;

/**
 * 住院治疗处方总Service接口
 * 
 * @author ruoyi
 * @date 2024-06-13
 */
public interface IPrescriptiontotalService 
{
    /**
     * 查询住院治疗处方总
     * 
     * @param prestId 住院治疗处方总主键
     * @return 住院治疗处方总
     */
    public Prescriptiontotal selectPrescriptiontotalByPrestId(Long prestId);

    /**
     * 查询住院治疗处方总列表
     * 
     * @param prescriptiontotal 住院治疗处方总
     * @return 住院治疗处方总集合
     */
    public List<Prescriptiontotal> selectPrescriptiontotalList(Prescriptiontotal prescriptiontotal);

    /**
     * 新增住院治疗处方总
     * 
     * @param prescriptiontotal 住院治疗处方总
     * @return 结果
     */
    public int insertPrescriptiontotal(Prescriptiontotal prescriptiontotal);

    /**
     * 修改住院治疗处方总
     * 
     * @param prescriptiontotal 住院治疗处方总
     * @return 结果
     */
    public int updatePrescriptiontotal(Prescriptiontotal prescriptiontotal);

    /**
     * 批量删除住院治疗处方总
     * 
     * @param prestIds 需要删除的住院治疗处方总主键集合
     * @return 结果
     */
    public int deletePrescriptiontotalByPrestIds(Long[] prestIds);

    /**
     * 删除住院治疗处方总信息
     * 
     * @param prestId 住院治疗处方总主键
     * @return 结果
     */
    public int deletePrescriptiontotalByPrestId(Long prestId);
}
