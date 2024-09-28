package com.ruoyi.medical.mapper;

import java.util.List;
import com.ruoyi.medical.domain.DoctorResulthandle;
import org.apache.ibatis.annotations.Param;

/**
 * 医生看病治疗方案Mapper接口
 * 
 * @author ruoyi
 * @date 2024-06-15
 */
public interface DoctorResulthandleMapper 
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
     * 删除医生看病治疗方案
     * 
     * @param treatId 医生看病治疗方案主键
     * @return 结果
     */
    public int deleteDoctorResulthandleByTreatId(Long treatId);

    /**
     * 批量删除医生看病治疗方案
     * 
     * @param treatIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteDoctorResulthandleByTreatIds(Long[] treatIds);

    /**
     * 批量保存治疗方案记录
     * @param doctorResulthandles
     */
    public int  addResulthandleBatch(@Param("lst") List<DoctorResulthandle> doctorResulthandles);
}
