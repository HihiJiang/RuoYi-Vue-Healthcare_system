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
import com.ruoyi.medical.domain.DrugsInfo;
import com.ruoyi.medical.service.IDrugsInfoService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 药品信息Controller
 * 
 * @author ruoyi
 * @date 2024-06-14
 */
@RestController
@RequestMapping("/medical/drugsinfo")
public class DrugsInfoController extends BaseController
{
    @Autowired
    private IDrugsInfoService drugsInfoService;

    /**
     * 查询药品信息列表
     */
    @PreAuthorize("@ss.hasPermi('medical:drugsinfo:list')")
    @GetMapping("/list")
    public TableDataInfo list(DrugsInfo drugsInfo)
    {
        startPage();
        List<DrugsInfo> list = drugsInfoService.selectDrugsInfoList(drugsInfo);
        return getDataTable(list);
    }

    /**
     * 导出药品信息列表
     */
    @PreAuthorize("@ss.hasPermi('medical:drugsinfo:export')")
    @Log(title = "药品信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, DrugsInfo drugsInfo)
    {
        List<DrugsInfo> list = drugsInfoService.selectDrugsInfoList(drugsInfo);
        ExcelUtil<DrugsInfo> util = new ExcelUtil<DrugsInfo>(DrugsInfo.class);
        util.exportExcel(response, list, "药品信息数据");
    }

    /**
     * 获取药品信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('medical:drugsinfo:query')")
    @GetMapping(value = "/{drugsId}")
    public AjaxResult getInfo(@PathVariable("drugsId") Long drugsId)
    {
        return success(drugsInfoService.selectDrugsInfoByDrugsId(drugsId));
    }

    /**
     * 新增药品信息
     */
    @PreAuthorize("@ss.hasPermi('medical:drugsinfo:add')")
    @Log(title = "药品信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody DrugsInfo drugsInfo)
    {
        return toAjax(drugsInfoService.insertDrugsInfo(drugsInfo));
    }

    /**
     * 修改药品信息
     */
    @PreAuthorize("@ss.hasPermi('medical:drugsinfo:edit')")
    @Log(title = "药品信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody DrugsInfo drugsInfo)
    {
        return toAjax(drugsInfoService.updateDrugsInfo(drugsInfo));
    }

    /**
     * 删除药品信息
     */
    @PreAuthorize("@ss.hasPermi('medical:drugsinfo:remove')")
    @Log(title = "药品信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{drugsIds}")
    public AjaxResult remove(@PathVariable Long[] drugsIds)
    {
        return toAjax(drugsInfoService.deleteDrugsInfoByDrugsIds(drugsIds));
    }


    /**
     * 获取药品信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('medical:drugsinfo:query')")
    @GetMapping(value = "/selectDrugsByPingyin/{kw}")
    public AjaxResult selectDrugsByPingyin(@PathVariable("kw")String kw  )
    {
        DrugsInfo drugsInfo = new DrugsInfo();
        drugsInfo.setDrugsPinyin(kw);
        return success(drugsInfoService.selectDrugsByPingyin(drugsInfo));
    }

}
