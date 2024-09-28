package com.ruoyi.hospital.mapper;

import java.util.List;

import com.ruoyi.hospital.DTO.PharmacyDTO;
import com.ruoyi.hospital.Vo.PharmacyVo;
import com.ruoyi.hospital.domain.Pharmacy;

/**
 * 药房库存Mapper接口
 * 
 * @author ruoyi
 * @date 2024-06-13
 */
public interface PharmacyMapper 
{
    /**
     * 查询药房库存
     * 
     * @param pharmacyId 药房库存主键
     * @return 药房库存
     */
    public Pharmacy selectPharmacyByPharmacyId(Long pharmacyId);

    /**
     * 查询药房库存列表
     * 
     * @param pharmacy 药房库存
     * @return 药房库存集合
     */
    public List<PharmacyVo> selectPharmacyList(PharmacyDTO pharmacy);

    /**
     * 新增药房库存
     * 
     * @param pharmacy 药房库存
     * @return 结果
     */
    public int insertPharmacy(Pharmacy pharmacy);

    /**
     * 修改药房库存
     * 
     * @param pharmacy 药房库存
     * @return 结果
     */
    public int updatePharmacy(Pharmacy pharmacy);

    /**
     * 删除药房库存
     * 
     * @param pharmacyId 药房库存主键
     * @return 结果
     */
    public int deletePharmacyByPharmacyId(Long pharmacyId);

    /**
     * 批量删除药房库存
     * 
     * @param pharmacyIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePharmacyByPharmacyIds(Long[] pharmacyIds);
}
