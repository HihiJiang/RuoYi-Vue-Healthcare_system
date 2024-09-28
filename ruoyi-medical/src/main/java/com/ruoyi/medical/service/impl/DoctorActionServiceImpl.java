package com.ruoyi.medical.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.medical.mapper.DoctorActionMapper;
import com.ruoyi.medical.domain.DoctorAction;
import com.ruoyi.medical.service.IDoctorActionService;

/**
 * 医生看病流程Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-06-13
 */
@Service
public class DoctorActionServiceImpl implements IDoctorActionService 
{
    @Autowired
    private DoctorActionMapper doctorActionMapper;

    /**
     * 查询医生看病流程
     * 
     * @param actionId 医生看病流程主键
     * @return 医生看病流程
     */
    @Override
    public DoctorAction selectDoctorActionByActionId(Long actionId)
    {
        return doctorActionMapper.selectDoctorActionByActionId(actionId);
    }

    /**
     * 查询医生看病流程列表
     * 
     * @param doctorAction 医生看病流程
     * @return 医生看病流程
     */
    @Override
    public List<DoctorAction> selectDoctorActionList(DoctorAction doctorAction)
    {
        return doctorActionMapper.selectDoctorActionList(doctorAction);
    }

    /**
     * 新增医生看病流程
     * 
     * @param doctorAction 医生看病流程
     * @return 结果
     */
    @Override
    public int insertDoctorAction(DoctorAction doctorAction)
    {
        return doctorActionMapper.insertDoctorAction(doctorAction);
    }

    /**
     * 修改医生看病流程
     * 
     * @param doctorAction 医生看病流程
     * @return 结果
     */
    @Override
    public int updateDoctorAction(DoctorAction doctorAction)
    {
        return doctorActionMapper.updateDoctorAction(doctorAction);
    }

    /**
     * 批量删除医生看病流程
     * 
     * @param actionIds 需要删除的医生看病流程主键
     * @return 结果
     */
    @Override
    public int deleteDoctorActionByActionIds(Long[] actionIds)
    {
        return doctorActionMapper.deleteDoctorActionByActionIds(actionIds);
    }

    /**
     * 删除医生看病流程信息
     * 
     * @param actionId 医生看病流程主键
     * @return 结果
     */
    @Override
    public int deleteDoctorActionByActionId(Long actionId)
    {
        return doctorActionMapper.deleteDoctorActionByActionId(actionId);
    }
}
