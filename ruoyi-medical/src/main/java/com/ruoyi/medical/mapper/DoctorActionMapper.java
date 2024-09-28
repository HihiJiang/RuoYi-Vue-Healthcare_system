package com.ruoyi.medical.mapper;

import java.util.List;
import com.ruoyi.medical.domain.DoctorAction;
import org.apache.ibatis.annotations.Param;

/**
 * 医生看病流程Mapper接口
 * 
 * @author ruoyi
 * @date 2024-06-13
 */
public interface DoctorActionMapper 
{
    /**
     * 查询医生看病流程
     * 
     * @param actionId 医生看病流程主键
     * @return 医生看病流程
     */
    public DoctorAction selectDoctorActionByActionId(Long actionId);

    /**
     * 查询医生看病流程列表
     * 
     * @param doctorAction 医生看病流程
     * @return 医生看病流程集合
     */
    public List<DoctorAction> selectDoctorActionList(DoctorAction doctorAction);

    /**
     * 新增医生看病流程
     * 
     * @param doctorAction 医生看病流程
     * @return 结果
     */
    public int insertDoctorAction(DoctorAction doctorAction);

    /**
     * 修改医生看病流程
     * 
     * @param doctorAction 医生看病流程
     * @return 结果
     */
    public int updateDoctorAction(DoctorAction doctorAction);

    /**
     * 删除医生看病流程
     * 
     * @param actionId 医生看病流程主键
     * @return 结果
     */
    public int deleteDoctorActionByActionId(Long actionId);

    /**
     * 批量删除医生看病流程
     * 
     * @param actionIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteDoctorActionByActionIds(Long[] actionIds);

    /**
     * 批量新增医生看病流程记录
     * @param lst
     * @return
     */
    public int addDoctorActionBatch(@Param("lst") List<DoctorAction> lst);
}
