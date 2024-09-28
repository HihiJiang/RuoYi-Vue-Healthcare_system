package com.ruoyi.medical.service;

import java.util.List;
import com.ruoyi.medical.domain.DoctorResulthandle;

/**
 * 医生看病治疗方案Service接口
 * 
 * @author ruoyi
 * @date 2024-06-15
 */
public interface IDoctorResulthandleService 
{
    /**
     * 查询医生看病治疗方案
     * 
     * @param treatId 医生看病治疗方案主键
     * @return 医生看病治疗方案
     */
    public DoctorResulthandle selectDoctorResulthandleByTreatId(Long treatId);

    /**
     * 查询医生看病治疗方案列表
     * 
     * @param doctorResulthandle 医生看病治疗方案
     * @return 医生看病治疗方案集合
     */
    public List<DoctorResulthandle> selectDoctorResulthandleList(DoctorResulthandle doctorResulthandle);

    /**
     * 新增医生看病治疗方案
     * 
     * @param doctorResulthandle 医生看病治疗方案
     * @return 结果
     */
    public int insertDoctorResulthandle(DoctorResulthandle doctorResulthandle);

    /**
     * 修改医生看病治疗方案
     * 
     * @param doctorResulthandle 医生看病治疗方案
     * @return 结果
     */
    public int updateDoctorResulthandle(DoctorResulthandle doctorResulthandle);

    /**
     * 批量删除医生看病治疗方案
     * 
     * @param treatIds 需要删除的医生看病治疗方案主键集合
     * @return 结果
     */
    public int deleteDoctorResulthandleByTreatIds(Long[] treatIds);

    /**
     * 删除医生看病治疗方案信息
     * 
     * @param treatId 医生看病治疗方案主键
     * @return 结果
     */
    public int deleteDoctorResulthandleByTreatId(Long treatId);
}
