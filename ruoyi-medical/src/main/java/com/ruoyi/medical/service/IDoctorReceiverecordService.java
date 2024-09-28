package com.ruoyi.medical.service;

import java.util.List;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.medical.domain.DoctorReceiverecord;
import com.ruoyi.medical.dto.DoctorReceiverecordDTO;
import com.ruoyi.medical.vo.DoctorReceiverecordVo;

/**
 * 接诊记录Service接口
 * 
 * @author ruoyi
 * @date 2024-06-11
 */
public interface IDoctorReceiverecordService 
{
    /**
     * 查询接诊记录
     * 
     * @param recordsId 接诊记录主键
     * @return 接诊记录
     */
    public DoctorReceiverecord selectDoctorReceiverecordByRecordsId(String recordsId);

    /**
     * 查询接诊记录列表
     * 
     * @param doctorReceiverecord 接诊记录
     * @return 接诊记录集合
     */
    public List<DoctorReceiverecordVo> selectDoctorReceiverecordList(DoctorReceiverecordDTO doctorReceiverecord);

    /**
     * 新增接诊记录
     * 
     * @param doctorReceiverecord 接诊记录
     * @return 结果
     */
    public int insertDoctorReceiverecord(DoctorReceiverecord doctorReceiverecord);

    /**
     * 修改接诊记录
     * 
     * @param doctorReceiverecord 接诊记录
     * @return 结果
     */
    public int updateDoctorReceiverecord(DoctorReceiverecord doctorReceiverecord);

    /**
     * 批量删除接诊记录
     * 
     * @param recordsIds 需要删除的接诊记录主键集合
     * @return 结果
     */
    public int deleteDoctorReceiverecordByRecordsIds(String[] recordsIds);

    /**
     * 删除接诊记录信息
     * 
     * @param recordsId 接诊记录主键
     * @return 结果
     */
    public int deleteDoctorReceiverecordByRecordsId(String recordsId);

    /**
     *添加 检验、检查记录
     * @param receiverecordDTO
     * @return
     */
    public AjaxResult addLisAndPacs(DoctorReceiverecordDTO receiverecordDTO);

    /**
     *添加处置药方记录
     * @param receiverecordDTO
     * @return
     */
    public AjaxResult addDrugs(DoctorReceiverecordDTO receiverecordDTO);
}
