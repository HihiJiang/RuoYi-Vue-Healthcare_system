package com.ruoyi.medical.mapper;

import java.util.List;
import java.util.Map;

import com.ruoyi.medical.domain.CostOutpatientcard;
import com.ruoyi.medical.dto.CostOutpatientcardDTO;
import com.ruoyi.medical.vo.CostOutpatientcardVo;

/**
 * 门诊卡信息Mapper接口
 * 
 * @author ruoyi
 * @date 2024-06-03
 */
public interface CostOutpatientcardMapper 
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
     * 删除门诊卡信息
     * 
     * @param outpatientId 门诊卡信息主键
     * @return 结果
     */
    public int deleteCostOutpatientcardByOutpatientId(String outpatientId);

    /**
     * 批量删除门诊卡信息
     * 
     * @param outpatientIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCostOutpatientcardByOutpatientIds(String[] outpatientIds);

    /**
     * 门诊卡充值取现
     * @param costOutpatientcardDTO
     * @return
     */
    public int updataOutpatientBalanceById(CostOutpatientcardDTO costOutpatientcardDTO);

    /**
     * 根据部门id获取挂号医生信息
     * @param deptId
     * @return
     */
    List<Map<String,Object>> queryDoctorsByDetptId(Long deptId);
}
