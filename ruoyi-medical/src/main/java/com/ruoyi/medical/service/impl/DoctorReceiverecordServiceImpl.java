package com.ruoyi.medical.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.medical.domain.*;
import com.ruoyi.medical.dto.DoctorReceiverecordDTO;
import com.ruoyi.medical.dto.ServiceProjectDTO;
import com.ruoyi.medical.mapper.*;
import com.ruoyi.medical.vo.DoctorReceiverecordVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import com.ruoyi.medical.service.IDoctorReceiverecordService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 接诊记录Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-06-11
 */
@Service
public class DoctorReceiverecordServiceImpl implements IDoctorReceiverecordService
{
    @Autowired
    private DoctorReceiverecordMapper doctorReceiverecordMapper;

    @Autowired
    private CostBillMapper costBillMapper;

    @Autowired
    private BillDetailMapper billDetailMapper ;

    @Autowired
    private CostOutpatientcardMapper costOutpatientcardMapper;

    @Autowired
    private LisInfoMapper lisInfoMapper;
    @Autowired
    private PacsInfoMapper pacsInfoMapper;
    @Autowired
    private DoctorActionMapper doctorActionMapper;

    @Autowired

    private DrugsTotalinfoMapper drugsTotalinfoMapper;

    @Autowired
    private  DrugsTotalMapper drugsTotalMapper;

    @Autowired
    private DoctorResulthandleMapper doctorResulthandleMapper;



    /**
     * 查询接诊记录
     * 
     * @param recordsId 接诊记录主键
     * @return 接诊记录
     */
    @Override
    public DoctorReceiverecord selectDoctorReceiverecordByRecordsId(String recordsId)
    {
        return doctorReceiverecordMapper.selectDoctorReceiverecordByRecordsId(recordsId);
    }

    /**
     * 查询接诊记录列表
     * 
     * @param doctorReceiverecord 接诊记录
     * @return 接诊记录
     */
    @Override
    public List<DoctorReceiverecordVo> selectDoctorReceiverecordList(DoctorReceiverecordDTO doctorReceiverecord)
    {
        return doctorReceiverecordMapper.selectDoctorReceiverecordList(doctorReceiverecord);
    }

    /**
     * 新增接诊记录
     * 
     * @param doctorReceiverecord 接诊记录
     * @return 结果
     */
    @Override
    public int insertDoctorReceiverecord(DoctorReceiverecord doctorReceiverecord)
    {
        return doctorReceiverecordMapper.insertDoctorReceiverecord(doctorReceiverecord);
    }

    /**
     * 修改接诊记录
     * 
     * @param doctorReceiverecord 接诊记录
     * @return 结果
     */
    @Override
    public int updateDoctorReceiverecord(DoctorReceiverecord doctorReceiverecord)
    {
        return doctorReceiverecordMapper.updateDoctorReceiverecord(doctorReceiverecord);
    }

    /**
     * 批量删除接诊记录
     * 
     * @param recordsIds 需要删除的接诊记录主键
     * @return 结果
     */
    @Override
    public int deleteDoctorReceiverecordByRecordsIds(String[] recordsIds)
    {
        return doctorReceiverecordMapper.deleteDoctorReceiverecordByRecordsIds(recordsIds);
    }

    /**
     * 删除接诊记录信息
     * 
     * @param recordsId 接诊记录主键
     * @return 结果
     */
    @Override
    public int deleteDoctorReceiverecordByRecordsId(String recordsId)
    {
        return doctorReceiverecordMapper.deleteDoctorReceiverecordByRecordsId(recordsId);
    }

    /**
     *添加 检验、检查记录
     * @param receiverecordDTO
     * @return
     */
    @Transactional
    @Override
    public AjaxResult addLisAndPacs(DoctorReceiverecordDTO receiverecordDTO) {
        //1.新增检验检查记录
        //2.生成消费总账单和明细
        //3.生成看病流程记录表
        //4.门诊卡扣费

        //计算消费总额
     BigDecimal total=  receiverecordDTO.getPartLst().stream()

                .map(ServiceProject::getServiceprojectPrice)
                //reduce专门用于进行累计操作
                .reduce(BigDecimal.ZERO,BigDecimal::add);

        //判断门诊卡余额是否不足
        CostOutpatientcard card = costOutpatientcardMapper.selectCostOutpatientcardByOutpatientId(receiverecordDTO.getOutpatientId());
        if (card.getOutpatientBalance().compareTo(total)<0){
            return AjaxResult.error("余额不足！");
        }
        //生成消费总账单ID
        String billId ="XF"+System.currentTimeMillis();

        //定义Lis检验检查列表
        List<LisInfo> lis =new ArrayList<>();
        //定义Pacs检查列表
        List<PacsInfo> pacs=new ArrayList<>();
        //定义消费明细列表
        List<BillDetail> billDetails = new ArrayList<>();
        //定义看病流程列表
        List<DoctorAction> doctorActions = new ArrayList<>();
        //循环列表前端传入的所有消费项列表
        for (ServiceProjectDTO sp : receiverecordDTO.getPartLst()) {
            //判断当前操作是Lis还是pacs
            if(receiverecordDTO.getActionType().equalsIgnoreCase("1")){
               PacsInfo pacsinfo = new PacsInfo();
                pacsinfo.setPacsPartname(sp.getServiceprojectName());
                pacsinfo.setDoctorId(receiverecordDTO.getRecordsDoctorid());
                pacsinfo.setPatientId(receiverecordDTO.getPatientId());
                pacsinfo.setRecordsId(receiverecordDTO.getRecordsId());
               pacs.add(pacsinfo);
            }else{
            LisInfo lisinfo = new LisInfo();
                lisinfo.setLisPartname(sp.getServiceprojectName());
                lisinfo.setDoctorId(receiverecordDTO.getRecordsDoctorid());
                lisinfo.setPatientId(receiverecordDTO.getPatientId());
                lisinfo.setRecordsId(receiverecordDTO.getRecordsId());
                lis.add(lisinfo);
            }
            //添加消费明细
            BillDetail detail = new BillDetail();
            detail.setBillId(billId);
            System.out.println(billId);
            detail.setBilldetailPartname(sp.getServiceprojectName());
            detail.setBilldetaillPartnumber(sp.getNumber());
            detail.setBilldetailPrice(sp.getServiceprojectPrice());
            billDetails.add(detail);

            //添加看病流程
            DoctorAction action = new DoctorAction();
            action.setActionPartmoney(sp.getServiceprojectPrice());
            action.setActionPartname(sp.getServiceprojectName());
            action.setActionPartnumber(sp.getNumber());
            action.setActionPatienttype(receiverecordDTO.getActionPatienttype());
            action.setActionType(receiverecordDTO.getActionType());
            action.setRecordsId(receiverecordDTO.getRecordsId());
            doctorActions.add(action);
        }
        //保存消费账单和明细
        //总账
        CostBill costBill = new CostBill();
        costBill.setBillId(billId);
        costBill.setBillAmount(total);
        costBill.setOutpatientId(receiverecordDTO.getOutpatientId());
        costBill.setBillRemark(receiverecordDTO.getActionType().equalsIgnoreCase("1")?"检查项目收费":"检验项目收费");
        costBillMapper.insertCostBill(costBill);
        //明细
        billDetailMapper.addCostBillDetailBatch(billDetails);

        //批量新增检验、检查记录
        if(receiverecordDTO.getActionType().equalsIgnoreCase("1")){
            pacsInfoMapper.addPacsBatch(pacs);
        }else {
            lisInfoMapper.addLisInfoBatch(lis);

        }
        //批量保存看病流程记录
        doctorActionMapper.addDoctorActionBatch(doctorActions);
        //门诊卡扣费
        card.setOutpatientBalance(card.getOutpatientBalance().subtract(total));
      int  row=  costOutpatientcardMapper.updateCostOutpatientcard(card);
        return row >0 ?AjaxResult.success():AjaxResult.error();
    }

    /**
     *添加处置药方记录
     * @param receiverecordDTO
     * @return
     */
    @Transactional
    @Override
    public AjaxResult addDrugs(DoctorReceiverecordDTO receiverecordDTO) {
        //1.添加费用总账单和明细
        //2.添加看病流程记录
        //3.添加取药总记录和明细
        //4.门诊卡扣费
        //5.将接诊记录的状态修改为已结束
        //流程：检查和检验 ->开药走人 ->门诊挂号结束
        //              ->转住院

        //计算消费总额
        BigDecimal total=  receiverecordDTO.getPartLst().stream()

                .map(ServiceProject::getServiceprojectPrice)
                //reduce专门用于进行累计操作
                .reduce(BigDecimal.ZERO,BigDecimal::add);

        //判断门诊卡余额是否不足
        CostOutpatientcard card = costOutpatientcardMapper.selectCostOutpatientcardByOutpatientId(receiverecordDTO.getOutpatientId());
        if (card.getOutpatientBalance().compareTo(total)<0){
            return AjaxResult.error("余额不足！");
        }
        //生成消费总账单ID
        String billId ="XF"+System.currentTimeMillis();
        //生成取药总表记录id
        String totalId ="QY"+System.currentTimeMillis();
        //定义取药明细列表
        List<DrugsTotalinfo> totalinfos =new ArrayList<>();
        //定义消费明细列表
        List<BillDetail> billDetails = new ArrayList<>();
        //定义治疗方案列表
        List<DoctorResulthandle> doctorResulthandles = new ArrayList<>();
        //循环列表前端传入的所有消费项列表
        for (ServiceProjectDTO sp : receiverecordDTO.getPartLst()) {
            //添加取药明细
            DrugsTotalinfo totalinfo = new DrugsTotalinfo();
            totalinfo.setTotalId(totalId);
            totalinfo.setDrugsId(String.valueOf(sp.getServiceprojectId()));
            totalinfo.setTotalNo(sp.getNumber());
            totalinfos.add(totalinfo);

            //添加消费明细
            BillDetail detail = new BillDetail();
            detail.setBillId(billId);
            System.out.println(billId);
            detail.setBilldetailPartname(sp.getServiceprojectName());
            detail.setBilldetaillPartnumber(sp.getNumber());
            detail.setBilldetailPrice(sp.getServiceprojectPrice());
            billDetails.add(detail);

            //添加看病流程
           DoctorResulthandle resulthandle = new DoctorResulthandle();
           //接诊记录id
           resulthandle.setRecordsId(receiverecordDTO.getRecordsId());
           resulthandle.setTreatPartname(sp.getServiceprojectName());
           resulthandle.setTreatPartmoney(sp.getServiceprojectPrice());
           resulthandle.setTreatPartnumber(sp.getNumber());
           doctorResulthandles.add(resulthandle);
        }
        //保存消费账单和明细
        //总账
        CostBill costBill = new CostBill();
        costBill.setBillId(billId);
        costBill.setBillAmount(total);
        costBill.setOutpatientId(receiverecordDTO.getOutpatientId());
        costBill.setBillRemark("处置开药收费");
        costBillMapper.insertCostBill(costBill);
        //明细
        billDetailMapper.addCostBillDetailBatch(billDetails);

        //保存取药总记录和明细
        //总记录
        DrugsTotal drugsTotal = new DrugsTotal();
        drugsTotal.setTotalId(totalId);
        //门诊卡id
        drugsTotal.setOutpatientId(receiverecordDTO.getOutpatientId());
        //患者id
        drugsTotal.setPatientId(receiverecordDTO.getPatientId());
        drugsTotalMapper.insertDrugsTotal(drugsTotal);
        //明细
        drugsTotalinfoMapper.addDrugsTotalInfoBatch(totalinfos);

        //批量保存治疗方案记录
        doctorResulthandleMapper.addResulthandleBatch(doctorResulthandles);
        //门诊卡扣费
        card.setOutpatientBalance(card.getOutpatientBalance().subtract(total));
        int  row=  costOutpatientcardMapper.updateCostOutpatientcard(card);

        //修改接诊记录的状态为已结束
        receiverecordDTO.setRecordsStatus("4");
        doctorReceiverecordMapper.updateDoctorReceiverecord(receiverecordDTO);
        return row >0 ?AjaxResult.success():AjaxResult.error();
    }
}
