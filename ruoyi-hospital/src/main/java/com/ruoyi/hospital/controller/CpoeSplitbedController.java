package com.ruoyi.hospital.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.hospital.DTO.CpoeSplitbedDTO;
import com.ruoyi.hospital.Vo.CpoeSplitbedVo;
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
import com.ruoyi.hospital.domain.CpoeSplitbed;
import com.ruoyi.hospital.service.ICpoeSplitbedService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 住院登记Controller
 * 
 * @author ruoyi
 * @date 2024-06-06
 */
@RestController
@RequestMapping("/hospital/splitbed")
public class CpoeSplitbedController extends BaseController
{
    @Autowired
    private ICpoeSplitbedService cpoeSplitbedService;

    /**
     * 查询住院登记列表
     */
    @PreAuthorize("@ss.hasPermi('hospital:splitbed:list')")
    @GetMapping("/list")
    public TableDataInfo list(CpoeSplitbedDTO cpoeSplitbed)
    {
        startPage();
        List<CpoeSplitbedVo> list = cpoeSplitbedService.selectCpoeSplitbedList(cpoeSplitbed);
        return getDataTable(list);
    }

    /**
     * 导出住院登记列表
     */
    @PreAuthorize("@ss.hasPermi('hospital:splitbed:export')")
    @Log(title = "住院登记", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CpoeSplitbedDTO cpoeSplitbed)
    {
        List<CpoeSplitbedVo> list = cpoeSplitbedService.selectCpoeSplitbedList(cpoeSplitbed);
        ExcelUtil<CpoeSplitbedVo> util = new ExcelUtil<CpoeSplitbedVo>(CpoeSplitbedVo.class);
        util.exportExcel(response, list, "住院登记数据");
    }

    /**
     * 获取住院登记详细信息
     */
    @PreAuthorize("@ss.hasPermi('hospital:splitbed:query')")
    @GetMapping(value = "/{splitbedId}")
    public AjaxResult getInfo(@PathVariable("splitbedId") Long splitbedId)
    {
        return success(cpoeSplitbedService.selectCpoeSplitbedBySplitbedId(splitbedId));
    }

    /**
     * 新增住院登记
     */
    @PreAuthorize("@ss.hasPermi('hospital:splitbed:add')")
    @Log(title = "住院登记", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CpoeSplitbed cpoeSplitbed)
    {
        return toAjax(cpoeSplitbedService.insertCpoeSplitbed(cpoeSplitbed));
    }

    /**
     * 修改住院登记
     */
    @PreAuthorize("@ss.hasPermi('hospital:splitbed:edit')")
    @Log(title = "住院登记", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CpoeSplitbed cpoeSplitbed)
    {
        return toAjax(cpoeSplitbedService.updateCpoeSplitbed(cpoeSplitbed));
    }

    /**
     * 删除住院登记
     */
    @PreAuthorize("@ss.hasPermi('hospital:splitbed:remove')")
    @Log(title = "住院登记", businessType = BusinessType.DELETE)
	@DeleteMapping("/{splitbedIds}")
    public AjaxResult remove(@PathVariable Long[] splitbedIds)
    {
        return toAjax(cpoeSplitbedService.deleteCpoeSplitbedBySplitbedIds(splitbedIds));
    }
}
