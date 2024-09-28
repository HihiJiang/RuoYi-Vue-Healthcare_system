package com.ruoyi.medical.service;

import java.util.List;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.medical.domain.CostOutpatientcard;
import com.ruoyi.medical.dto.CostOutpatientcardDTO;
import com.ruoyi.medical.dto.DoctorReceiverecordDTO;
import com.ruoyi.medical.vo.CostOutpatientcardVo;

/**
 * 门诊卡信息Service接口
 * 
 * @author ruoyi
 * @date 2024-06-03
 */
public interface ICostOutpatientcardService 
{
    /**
     * 查询门诊卡信息
     * 
     * @param outpatientId 门诊卡信息主键
     * @return 门诊卡信息
     */
    public CostOutpatientcard selectCostOutpatientcardByOutpatientId(String outpatientId);

    /**
     * 查询门诊卡信息列表
     * 
     * @param costOutpatientcard 门诊卡信息
     * @return 门诊卡信息集合
     */
    public List<CostOutpatientcardVo> selectCostOutpatientcardList(CostOutpatientcardDTO costOutpatientcard);

    /**
     * 新增门诊卡信息
     * 
     * @param costOutpatientcard 门诊卡信息
     * @return 结果
     */
    public int insertCostOutpatientcard(CostOutpatientcard costOutpatientcard);

    /**
     * 修改门诊卡信息
     * 
     * @param costOutpatientcard 门诊卡信息
     * @return 结果
     */
    public int updateCostOutpatientcard(CostOutpatientcard costOutpatientcard);

    /**
     * 批量删除门诊卡信息
     * 
     * @param outpatientIds 需要删除的门诊卡信息主键集合
     * @return 结果
     */
    public int deleteCostOutpatientcardByOutpatientIds(String[] outpatientIds);

    /**
     * 删除门诊卡信息信息
     * 
     * @param outpatientId 门诊卡信息主键
     * @return 结果
     */
    public int deleteCostOutpatientcardByOutpatientId(String outpatientId);

    /**
     * 建档办卡
     *
     * @param costOutpatientcard 门诊信息
     * @return 结果
     */
    public AjaxResult addJiandang(CostOutpatientcardDTO costOutpatientcard);

    /**
     * 充值取现记录
     * @param costOutpatientcard
     * @return
     */
    public AjaxResult addOutpatientBalance(CostOutpatientcardDTO costOutpatientcard);

    AjaxResult queryDoctorsByDetptId(Long deptId);

    AjaxResult doctorRegistered(DoctorReceiverecordDTO deceivercordDTO);
}
