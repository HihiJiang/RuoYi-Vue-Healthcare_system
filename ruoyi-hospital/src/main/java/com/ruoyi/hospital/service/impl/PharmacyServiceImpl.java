package com.ruoyi.hospital.service.impl;

import java.util.List;

import com.ruoyi.hospital.DTO.PharmacyDTO;
import com.ruoyi.hospital.Vo.PharmacyVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.hospital.mapper.PharmacyMapper;
import com.ruoyi.hospital.domain.Pharmacy;
import com.ruoyi.hospital.service.IPharmacyService;

/**
 * 药房库存Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-06-13
 */
@Service
public class PharmacyServiceImpl implements IPharmacyService 
{
    @Autowired
    private PharmacyMapper pharmacyMapper;

    /**
     * 查询药房库存
     * 
     * @param pharmacyId 药房库存主键
     * @return 药房库存
     */
    @Override
    public Pharmacy selectPharmacyByPharmacyId(Long pharmacyId)
    {
        return pharmacyMapper.selectPharmacyByPharmacyId(pharmacyId);
    }

    /**
     * 查询药房库存列表
     * 
     * @param pharmacy 药房库存
     * @return 药房库存
     */
    @Override
    public List<PharmacyVo> selectPharmacyList(PharmacyDTO pharmacy)
    {
        return pharmacyMapper.selectPharmacyList(pharmacy);
    }

    /**
     * 新增药房库存
     * 
     * @param pharmacy 药房库存
     * @return 结果
     */
    @Override
    public int insertPharmacy(Pharmacy pharmacy)
    {
        return pharmacyMapper.insertPharmacy(pharmacy);
    }

    /**
     * 修改药房库存
     * 
     * @param pharmacy 药房库存
     * @return 结果
     */
    @Override
    public int updatePharmacy(Pharmacy pharmacy)
    {
        return pharmacyMapper.updatePharmacy(pharmacy);
    }

    /**
     * 批量删除药房库存
     * 
     * @param pharmacyIds 需要删除的药房库存主键
     * @return 结果
     */
    @Override
    public int deletePharmacyByPharmacyIds(Long[] pharmacyIds)
    {
        return pharmacyMapper.deletePharmacyByPharmacyIds(pharmacyIds);
    }

    /**
     * 删除药房库存信息
     * 
     * @param pharmacyId 药房库存主键
     * @return 结果
     */
    @Override
    public int deletePharmacyByPharmacyId(Long pharmacyId)
    {
        return pharmacyMapper.deletePharmacyByPharmacyId(pharmacyId);
    }
}
