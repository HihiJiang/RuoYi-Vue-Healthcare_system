package com.ruoyi.hospital.mapper;

import java.util.List;
import com.ruoyi.hospital.domain.Prescription;

/**
 * 住院治疗处方Mapper接口
 * 
 * @author ruoyi
 * @date 2024-06-13
 */
public interface PrescriptionMapper 
{
    /**
     * 查询住院治疗处方
     * 
     * @param presId 住院治疗处方主键
     * @return 住院治疗处方
     */
    public Prescription selectPrescriptionByPresId(Long presId);

    /**
     * 查询住院治疗处方列表
     * 
     * @param prescription 住院治疗处方
     * @return 住院治疗处方集合
     */
    public List<Prescription> selectPrescriptionList(Prescription prescription);

    /**
     * 新增住院治疗处方
     * 
     * @param prescription 住院治疗处方
     * @return 结果
     */
    public int insertPrescription(Prescription prescription);

    /**
     * 修改住院治疗处方
     * 
     * @param prescription 住院治疗处方
     * @return 结果
     */
    public int updatePrescription(Prescription prescription);

    /**
     * 删除住院治疗处方
     * 
     * @param presId 住院治疗处方主键
     * @return 结果
     */
    public int deletePrescriptionByPresId(Long presId);

    /**
     * 批量删除住院治疗处方
     * 
     * @param presIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePrescriptionByPresIds(Long[] presIds);
}
