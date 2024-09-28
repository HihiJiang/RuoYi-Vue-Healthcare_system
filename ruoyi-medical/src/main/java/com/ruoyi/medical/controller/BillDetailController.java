package com.ruoyi.medical.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
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
import com.ruoyi.medical.domain.BillDetail;
import com.ruoyi.medical.service.IBillDetailService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 患者费用明细Controller
 * 
 * @author ruoyi
 * @date 2024-05-31
 */
@RestController
@RequestMapping("/medical/detail")
public class BillDetailController extends BaseController
{
    @Autowired
    private IBillDetailService billDetailService;

    /**
     * 查询患者费用明细列表
     */
    @PreAuthorize("@ss.hasPermi('medical:detail:list')")
    @GetMapping("/list")
    public TableDataInfo list(BillDetail billDetail)
    {
        startPage();
        List<BillDetail> list = billDetailService.selectBillDetailList(billDetail);
        return getDataTable(list);
    }

    /**
     * 导出患者费用明细列表
     */
    @PreAuthorize("@ss.hasPermi('medical:detail:export')")
    @Log(title = "患者费用明细", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BillDetail billDetail)
    {
        List<BillDetail> list = billDetailService.selectBillDetailList(billDetail);
        ExcelUtil<BillDetail> util = new ExcelUtil<BillDetail>(BillDetail.class);
        util.exportExcel(response, list, "患者费用明细数据");
    }

    /**
     * 获取患者费用明细详细信息
     */
    @PreAuthorize("@ss.hasPermi('medical:detail:query')")
    @GetMapping(value = "/{billdetailId}")
    public AjaxResult getInfo(@PathVariable("billdetailId") Long billdetailId)
    {
        return success(billDetailService.selectBillDetailByBilldetailId(billdetailId));
    }

    /**
     * 新增患者费用明细
     */
    @PreAuthorize("@ss.hasPermi('medical:detail:add')")
    @Log(title = "患者费用明细", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BillDetail billDetail)
    {
        return toAjax(billDetailService.insertBillDetail(billDetail));
    }

    /**
     * 修改患者费用明细
     */
    @PreAuthorize("@ss.hasPermi('medical:detail:edit')")
    @Log(title = "患者费用明细", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BillDetail billDetail)
    {
        return toAjax(billDetailService.updateBillDetail(billDetail));
    }

    /**
     * 删除患者费用明细
     */
    @PreAuthorize("@ss.hasPermi('medical:detail:remove')")
    @Log(title = "患者费用明细", businessType = BusinessType.DELETE)
	@DeleteMapping("/{billdetailIds}")
    public AjaxResult remove(@PathVariable Long[] billdetailIds)
    {
        return toAjax(billDetailService.deleteBillDetailByBilldetailIds(billdetailIds));
    }
}
