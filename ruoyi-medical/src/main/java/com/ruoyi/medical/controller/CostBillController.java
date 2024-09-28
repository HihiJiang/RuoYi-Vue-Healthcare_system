package com.ruoyi.medical.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.medical.dto.CostBillDto;
import com.ruoyi.medical.vo.CostBillVo;
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
import com.ruoyi.medical.domain.CostBill;
import com.ruoyi.medical.service.ICostBillService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 患者费用账单Controller
 * 
 * @author ruoyi
 * @date 2024-05-31
 */
@RestController
@RequestMapping("/medical/bill")
public class CostBillController extends BaseController
{
    @Autowired
    private ICostBillService costBillService;

    /**
     * 查询患者费用账单列表
     */
    @PreAuthorize("@ss.hasPermi('medical:bill:list')")
    @GetMapping("/list")
    public TableDataInfo list(CostBillDto costBill)
    {
        startPage();
        List<CostBillVo> list = costBillService.selectCostBillList(costBill);
        return getDataTable(list);
    }

    /**
     * 导出患者费用账单列表
     */
    @PreAuthorize("@ss.hasPermi('medical:bill:export')")
    @Log(title = "患者费用账单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CostBillDto costBill)
    {
        List<CostBillVo> list = costBillService.selectCostBillList(costBill);
        ExcelUtil<CostBillVo> util = new ExcelUtil<CostBillVo>(CostBillVo.class);
        util.exportExcel(response, list, "患者费用账单数据");
    }

    /**
     * 获取患者费用账单详细信息
     */
    @PreAuthorize("@ss.hasPermi('medical:bill:query')")
    @GetMapping(value = "/{billId}")
    public AjaxResult getInfo(@PathVariable("billId") String billId)
    {
        return success(costBillService.selectCostBillByBillId(billId));
    }

    /**
     * 新增患者费用账单
     */
    @PreAuthorize("@ss.hasPermi('medical:bill:add')")
    @Log(title = "患者费用账单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CostBill costBill)
    {
        return toAjax(costBillService.insertCostBill(costBill));
    }

    /**
     * 修改患者费用账单
     */
    @PreAuthorize("@ss.hasPermi('medical:bill:edit')")
    @Log(title = "患者费用账单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CostBill costBill)
    {
        return toAjax(costBillService.updateCostBill(costBill));
    }

    /**
     * 删除患者费用账单
     */
    @PreAuthorize("@ss.hasPermi('medical:bill:remove')")
    @Log(title = "患者费用账单", businessType = BusinessType.DELETE)
	@DeleteMapping("/{billIds}")
    public AjaxResult remove(@PathVariable String[] billIds)
    {
        return toAjax(costBillService.deleteCostBillByBillIds(billIds));
    }
}
