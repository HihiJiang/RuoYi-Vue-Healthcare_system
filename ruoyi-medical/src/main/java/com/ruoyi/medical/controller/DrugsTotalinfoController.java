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
import com.ruoyi.medical.domain.DrugsTotalinfo;
import com.ruoyi.medical.service.IDrugsTotalinfoService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 取药信息Controller
 * 
 * @author ruoyi
 * @date 2024-06-14
 */
@RestController
@RequestMapping("/medical/drugstotalinfo")
public class DrugsTotalinfoController extends BaseController
{
    @Autowired
    private IDrugsTotalinfoService drugsTotalinfoService;

    /**
     * 查询取药信息列表
     */
    @PreAuthorize("@ss.hasPermi('medical:drugstotalinfo:list')")
    @GetMapping("/list")
    public TableDataInfo list(DrugsTotalinfo drugsTotalinfo)
    {
        startPage();
        List<DrugsTotalinfo> list = drugsTotalinfoService.selectDrugsTotalinfoList(drugsTotalinfo);
        return getDataTable(list);
    }

    /**
     * 导出取药信息列表
     */
    @PreAuthorize("@ss.hasPermi('medical:drugstotalinfo:export')")
    @Log(title = "取药信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, DrugsTotalinfo drugsTotalinfo)
    {
        List<DrugsTotalinfo> list = drugsTotalinfoService.selectDrugsTotalinfoList(drugsTotalinfo);
        ExcelUtil<DrugsTotalinfo> util = new ExcelUtil<DrugsTotalinfo>(DrugsTotalinfo.class);
        util.exportExcel(response, list, "取药信息数据");
    }

    /**
     * 获取取药信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('medical:drugstotalinfo:query')")
    @GetMapping(value = "/{totalinfoId}")
    public AjaxResult getInfo(@PathVariable("totalinfoId") Long totalinfoId)
    {
        return success(drugsTotalinfoService.selectDrugsTotalinfoByTotalinfoId(totalinfoId));
    }

    /**
     * 新增取药信息
     */
    @PreAuthorize("@ss.hasPermi('medical:drugstotalinfo:add')")
    @Log(title = "取药信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody DrugsTotalinfo drugsTotalinfo)
    {
        return toAjax(drugsTotalinfoService.insertDrugsTotalinfo(drugsTotalinfo));
    }

    /**
     * 修改取药信息
     */
    @PreAuthorize("@ss.hasPermi('medical:drugstotalinfo:edit')")
    @Log(title = "取药信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody DrugsTotalinfo drugsTotalinfo)
    {
        return toAjax(drugsTotalinfoService.updateDrugsTotalinfo(drugsTotalinfo));
    }

    /**
     * 删除取药信息
     */
    @PreAuthorize("@ss.hasPermi('medical:drugstotalinfo:remove')")
    @Log(title = "取药信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{totalinfoIds}")
    public AjaxResult remove(@PathVariable Long[] totalinfoIds)
    {
        return toAjax(drugsTotalinfoService.deleteDrugsTotalinfoByTotalinfoIds(totalinfoIds));
    }
}
