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
import com.ruoyi.medical.domain.DrugsTotal;
import com.ruoyi.medical.service.IDrugsTotalService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 取药记录Controller
 * 
 * @author ruoyi
 * @date 2024-06-14
 */
@RestController
@RequestMapping("/medical/drugstotal")
public class DrugsTotalController extends BaseController
{
    @Autowired
    private IDrugsTotalService drugsTotalService;

    /**
     * 查询取药记录列表
     */
    @PreAuthorize("@ss.hasPermi('medical:drugstotal:list')")
    @GetMapping("/list")
    public TableDataInfo list(DrugsTotal drugsTotal)
    {
        startPage();
        List<DrugsTotal> list = drugsTotalService.selectDrugsTotalList(drugsTotal);
        return getDataTable(list);
    }

    /**
     * 导出取药记录列表
     */
    @PreAuthorize("@ss.hasPermi('medical:drugstotal:export')")
    @Log(title = "取药记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, DrugsTotal drugsTotal)
    {
        List<DrugsTotal> list = drugsTotalService.selectDrugsTotalList(drugsTotal);
        ExcelUtil<DrugsTotal> util = new ExcelUtil<DrugsTotal>(DrugsTotal.class);
        util.exportExcel(response, list, "取药记录数据");
    }

    /**
     * 获取取药记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('medical:drugstotal:query')")
    @GetMapping(value = "/{totalId}")
    public AjaxResult getInfo(@PathVariable("totalId") String totalId)
    {
        return success(drugsTotalService.selectDrugsTotalByTotalId(totalId));
    }

    /**
     * 新增取药记录
     */
    @PreAuthorize("@ss.hasPermi('medical:drugstotal:add')")
    @Log(title = "取药记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody DrugsTotal drugsTotal)
    {
        return toAjax(drugsTotalService.insertDrugsTotal(drugsTotal));
    }

    /**
     * 修改取药记录
     */
    @PreAuthorize("@ss.hasPermi('medical:drugstotal:edit')")
    @Log(title = "取药记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody DrugsTotal drugsTotal)
    {
        return toAjax(drugsTotalService.updateDrugsTotal(drugsTotal));
    }

    /**
     * 删除取药记录
     */
    @PreAuthorize("@ss.hasPermi('medical:drugstotal:remove')")
    @Log(title = "取药记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{totalIds}")
    public AjaxResult remove(@PathVariable String[] totalIds)
    {
        return toAjax(drugsTotalService.deleteDrugsTotalByTotalIds(totalIds));
    }
}
