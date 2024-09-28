package com.ruoyi.medical.service;

import java.util.List;
import com.ruoyi.medical.domain.DoctorResult;

/**
 * 医生看病结果Service接口
 * 
 * @author ruoyi
 * @date 2024-06-14
 */
public interface IDoctorResultService 
{
    /**
     * 查询医生看病结果
     * 
     * @param resultId 医生看病结果主键
     * @return 医生看病结果
     */
    public DoctorResult selectDoctorResultByResultId(Long resultId);

    /**
     * 查询医生看病结果列表
     * 
     * @param doctorResult 医生看病结果
     * @return 医生看病结果集合
     */
    public List<DoctorResult> selectDoctorResultList(DoctorResult doctorResult);

    /**
     * 新增医生看病结果
     * 
     * @param doctorResult 医生看病结果
     * @return 结果
     */
    public int insertDoctorResult(DoctorResult doctorResult);

    /**
     * 修改医生看病结果
     * 
     * @param doctorResult 医生看病结果
     * @return 结果
     */
    public int updateDoctorResult(DoctorResult doctorResult);

    /**
     * 批量删除医生看病结果
     * 
     * @param resultIds 需要删除的医生看病结果主键集合
     * @return 结果
     */
    public int deleteDoctorResultByResultIds(Long[] resultIds);

    /**
     * 删除医生看病结果信息
     * 
     * @param resultId 医生看病结果主键
     * @return 结果
     */
    public int deleteDoctorResultByResultId(Long resultId);
}
