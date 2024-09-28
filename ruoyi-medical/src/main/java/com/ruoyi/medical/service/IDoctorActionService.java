package com.ruoyi.medical.service;

import java.util.List;
import com.ruoyi.medical.domain.DoctorAction;

/**
 * 医生看病流程Service接口
 * 
 * @author ruoyi
 * @date 2024-06-13
 */
public interface IDoctorActionService 
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
     * 批量删除医生看病流程
     * 
     * @param actionIds 需要删除的医生看病流程主键集合
     * @return 结果
     */
    public int deleteDoctorActionByActionIds(Long[] actionIds);

    /**
     * 删除医生看病流程信息
     * 
     * @param actionId 医生看病流程主键
     * @return 结果
     */
    public int deleteDoctorActionByActionId(Long actionId);
}
