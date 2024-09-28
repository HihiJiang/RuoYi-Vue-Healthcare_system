package com.ruoyi.medical.service.impl;

import java.math.BigDecimal;
import java.util.List;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.medical.domain.*;
import com.ruoyi.medical.dto.CostOutpatientcardDTO;
import com.ruoyi.medical.dto.DoctorReceiverecordDTO;
import com.ruoyi.medical.mapper.*;
import com.ruoyi.medical.vo.CostOutpatientcardVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.medical.service.ICostOutpatientcardService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 门诊卡信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-06-03
 */
@Service
public class CostOutpatientcardServiceImpl implements ICostOutpatientcardService 
{
    @Autowired
    private CostOutpatientcardMapper costOutpatientcardMapper;

    @Autowired
    private PersonArchivesMapper PersonArchivesMapper;

    @Autowired
    private CostOutpatientRechargeRecordMapper costOutpatientRechargeRecordMapper;

    //患者费用账单
    @Autowired
    private CostBillMapper costBillMapper;
    //患者费用明细
    @Autowired
    private BillDetailMapper billDetailMapper;

    //接诊
    @Autowired
    private DoctorReceiverecordMapper doctorReceiverecordMapper;
    /**
     * 查询门诊卡信息
     * 
     * @param outpatientId 门诊卡信息主键
     * @return 门诊卡信息
     */
    @Override
    public CostOutpatientcard selectCostOutpatientcardByOutpatientId(String outpatientId)
    {
        return costOutpatientcardMapper.selectCostOutpatientcardByOutpatientId(outpatientId);
    }

    /**
     * 查询门诊卡信息列表
     * 
     * @param costOutpatientcard 门诊卡信息
     * @return 门诊卡信息
     */
    @Override
    public List<CostOutpatientcardVo> selectCostOutpatientcardList(CostOutpatientcardDTO costOutpatientcard)
    {
        return costOutpatientcardMapper.selectCostOutpatientcardList(costOutpatientcard);
    }

    /**
     * 新增门诊卡信息
     * 
     * @param costOutpatientcard 门诊卡信息
     * @return 结果
     */
    @Override
    public int insertCostOutpatientcard(CostOutpatientcard costOutpatientcard)
    {
        return costOutpatientcardMapper.insertCostOutpatientcard(costOutpatientcard);
    }

    /**
     * 修改门诊卡信息
     * 
     * @param costOutpatientcard 门诊卡信息
     * @return 结果
     */
    @Override
    public int updateCostOutpatientcard(CostOutpatientcard costOutpatientcard)
    {
        return costOutpatientcardMapper.updateCostOutpatientcard(costOutpatientcard);
    }

    /**
     * 批量删除门诊卡信息
     * 
     * @param outpatientIds 需要删除的门诊卡信息主键
     * @return 结果
     */
    @Override
    public int deleteCostOutpatientcardByOutpatientIds(String[] outpatientIds)
    {
        return costOutpatientcardMapper.deleteCostOutpatientcardByOutpatientIds(outpatientIds);
    }

    /**
     * 删除门诊卡信息信息
     * 
     * @param outpatientId 门诊卡信息主键
     * @return 结果
     */
    @Override
    public int deleteCostOutpatientcardByOutpatientId(String outpatientId)
    {
        return costOutpatientcardMapper.deleteCostOutpatientcardByOutpatientId(outpatientId);
    }

    @Transactional
    @Override
    public AjaxResult addJiandang(CostOutpatientcardDTO costOutpatientcard) {
        //1.新增患者档案
        PersonArchives costPersonArchives = new PersonArchives();
        //生成患者档案
        String archiveId ="HZ"+System.currentTimeMillis();
        costPersonArchives.setPatientId(archiveId);
        //患者姓名
        costPersonArchives.setPatientName(costOutpatientcard.getPatientName());
        //患者性别
        costPersonArchives.setPatientSex(costOutpatientcard.getPatientSex());
        //患者联系电话
        costPersonArchives.setPatientPhone(costOutpatientcard.getPatientPhone());
        //身份证
        costPersonArchives.setPatientIdcard(costOutpatientcard.getPatientIdcard());
        PersonArchivesMapper.insertPersonArchives(costPersonArchives);
        //2.新增门诊卡信息
        //生成门诊卡
        String outpatienId ="MZ"+System.currentTimeMillis();
        costOutpatientcard.setOutpatientId(outpatienId);
        //患者编号
        costOutpatientcard.setPatientId(archiveId);
        //押金
        costOutpatientcard.setOutpatientBalance(BigDecimal.ZERO);
        int row = costOutpatientcardMapper.insertCostOutpatientcard(costOutpatientcard);

        return row > 0 ? AjaxResult.success() : AjaxResult.error();
    }

    @Transactional
    @Override
    public AjaxResult addOutpatientBalance(CostOutpatientcardDTO costOutpatientcardDTO) {
        //根据门诊卡ID修改门诊卡的金额（充值+ ，取现-）
     int row =  costOutpatientcardMapper.updataOutpatientBalanceById(costOutpatientcardDTO);
        //2生成一条充值取现记录
        CostOutpatientRechargeRecord record = new CostOutpatientRechargeRecord();
        record.setOperatorId(SecurityUtils.getUserId());
        record.setOutpatientId(costOutpatientcardDTO.getOutpatientId());
        record.setRechargeAmount(costOutpatientcardDTO.getOutpatientMoney());
        record.setRechargeType(costOutpatientcardDTO.getRechargeType());
        row= costOutpatientRechargeRecordMapper.insertCostOutpatientRechargeRecord(record);
        return row > 0 ? AjaxResult.success():AjaxResult.error();
    }

    @Override
    public AjaxResult queryDoctorsByDetptId(Long deptId) {
        return AjaxResult.success(costOutpatientcardMapper.queryDoctorsByDetptId(deptId));
    }

    /**
     * 挂号
     * @param deceivercordDTO
     * @return
     */
    @Transactional
    @Override
    public AjaxResult doctorRegistered(DoctorReceiverecordDTO deceivercordDTO) {
        //1判断门诊卡的金额是否充足
        CostOutpatientcard card = costOutpatientcardMapper.selectCostOutpatientcardByOutpatientId(deceivercordDTO.getOutpatientId());
        if (card.getOutpatientBalance().compareTo(deceivercordDTO.getToll())<0){
            return AjaxResult.error("余额不足！");
        }
        //2.生成费用总账单和明细
        String billId = "FY"+System.currentTimeMillis();
        //创建费用总账单对象
        CostBill costBill = new CostBill();
        costBill.setBillId(billId);
        costBill.setBillAmount(deceivercordDTO.getToll());

        costBill.setBillRemark("挂号费");
        costBill.setOutpatientId(deceivercordDTO.getOutpatientId());
        //创建费用账单明细对象
        BillDetail billDetail = new BillDetail();
        billDetail.setBillId(billId);
        billDetail.setBilldetailPartname("挂号费");
        billDetail.setBilldetaillPartnumber(1L);
        billDetail.setBilldetailPrice(deceivercordDTO.getToll());
        //保存费用总账单和明细
      int row=  costBillMapper.insertCostBill(costBill);
        row= billDetailMapper.insertBillDetail(billDetail);
        //3.往接诊记录表中推送挂号信息
        //设置操作人id
        deceivercordDTO.setOperatorId(SecurityUtils.getUserId());
        //生成接诊id
        String recordId="JZ"+System.currentTimeMillis();
        //获取接诊id
        deceivercordDTO.setRecordsId(recordId);
        //保存接诊记录
        doctorReceiverecordMapper.insertDoctorReceiverecord(deceivercordDTO);
        //4.根据门诊卡id扣减余额
        card.setOutpatientBalance(card.getOutpatientBalance().subtract(deceivercordDTO.getToll()));
      row=  costOutpatientcardMapper.updateCostOutpatientcard(card);
        return row>0 ? AjaxResult.success():AjaxResult.error();
    }


}
