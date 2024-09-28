package com.ruoyi.medical.controller;

import java.math.BigDecimal;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.medical.dto.CostOutpatientcardDTO;
import com.ruoyi.medical.dto.DoctorReceiverecordDTO;
import com.ruoyi.medical.vo.CostOutpatientcardVo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.medical.domain.CostOutpatientcard;
import com.ruoyi.medical.service.ICostOutpatientcardService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 门诊卡信息Controller
 * 
 * @author ruoyi
 * @date 2024-06-03
 */
@RestController
@RequestMapping("/medical/card")
public class CostOutpatientcardController extends BaseController
{
    @Autowired
    private ICostOutpatientcardService costOutpatientcardService;

    /**
     * 查询门诊卡信息列表
     */
    @PreAuthorize("@ss.hasPermi('medical:card:list')")
    @GetMapping("/list")
    public TableDataInfo list(CostOutpatientcardDTO costOutpatientcard)
    {
        startPage();
        List<CostOutpatientcardVo> list = costOutpatientcardService.selectCostOutpatientcardList(costOutpatientcard);
        return getDataTable(list);
    }

    /**
     * 导出门诊卡信息列表
     */
    @PreAuthorize("@ss.hasPermi('medical:card:export')")
    @Log(title = "门诊卡信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CostOutpatientcardDTO costOutpatientcard)
    {
        List<CostOutpatientcardVo> list = costOutpatientcardService.selectCostOutpatientcardList(costOutpatientcard);
        ExcelUtil<CostOutpatientcardVo> util = new ExcelUtil<CostOutpatientcardVo>(CostOutpatientcardVo.class);
        util.exportExcel(response, list, "门诊卡信息数据");
    }

    /**
     * 建档办卡
     */
    @PreAuthorize("@ss.hasPermi('medical:info:add')")
    @Log(title = "门诊信息", businessType = BusinessType.INSERT)
    @PostMapping("/addJiandang")
    public AjaxResult addJiandang(@RequestBody CostOutpatientcardDTO costOutpatientcard)
    {

        return costOutpatientcardService.addJiandang(costOutpatientcard);
    }
    /**
     * 获取门诊卡信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('medical:card:query')")
    @GetMapping(value = "/{outpatientId}")
    public AjaxResult getInfo(@PathVariable("outpatientId") String outpatientId)
    {
        return success(costOutpatientcardService.selectCostOutpatientcardByOutpatientId(outpatientId));
    }

    /**
     * 新增门诊卡信息
     */
    @PreAuthorize("@ss.hasPermi('medical:card:add')")
    @Log(title = "门诊卡信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CostOutpatientcard costOutpatientcard)
    {
        //生成门诊卡id
        String  outpatientId ="MZ"+System.currentTimeMillis();
        //获取当前用户id作为操作人id
        Long userId = SecurityUtils.getUserId();
        costOutpatientcard.setOperatorId(userId);
        costOutpatientcard.setOutpatientId(outpatientId);
        costOutpatientcard.setOutpatientBalance(BigDecimal.ZERO);
        return toAjax(costOutpatientcardService.insertCostOutpatientcard(costOutpatientcard));
    }

    /**
     * 修改门诊卡信息
     */
    @PreAuthorize("@ss.hasPermi('medical:card:edit')")
    @Log(title = "门诊卡信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CostOutpatientcard costOutpatientcard)
    {
        return toAjax(costOutpatientcardService.updateCostOutpatientcard(costOutpatientcard));
    }

    /**
     * 删除门诊卡信息
     */
    @PreAuthorize("@ss.hasPermi('medical:card:remove')")
    @Log(title = "门诊卡信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{outpatientIds}")
    public AjaxResult remove(@PathVariable String[] outpatientIds)
    {
        return toAjax(costOutpatientcardService.deleteCostOutpatientcardByOutpatientIds(outpatientIds));
    }

    /**
     * 新增门诊卡信息
     */
    @PreAuthorize("@ss.hasPermi('medical:card:addMoney')")
    @Log(title = "门诊卡信息", businessType = BusinessType.INSERT)
    @PostMapping("/addMoney")
    public AjaxResult addMoney(@RequestBody CostOutpatientcardDTO costOutpatientcard)
    {

        return costOutpatientcardService.addOutpatientBalance(costOutpatientcard);
    }

    /**
     * 根据部门编号获取部门医生信息
     */
    @PreAuthorize("@ss.hasPermi('medical:card:query')")
    @GetMapping(value = "/queryDoctorsByDetptId/{deptId}")
    public AjaxResult queryDoctorsByDtptId(@PathVariable("deptId") Long deptId)
    {
        return costOutpatientcardService.queryDoctorsByDetptId(deptId);
    }

    /**
     * 新增门诊卡信息
     */
    @PreAuthorize("@ss.hasPermi('medical:card:addMoney')")
    @Log(title = "门诊卡信息", businessType = BusinessType.INSERT)
    @PostMapping("/doctorRegistered")
    public AjaxResult doctorRegistered(@RequestBody DoctorReceiverecordDTO deceivercordDTO)
    {
        return costOutpatientcardService.doctorRegistered(deceivercordDTO);
    }

}
