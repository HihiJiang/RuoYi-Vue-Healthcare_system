package com.ruoyi.hospital.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.hospital.DTO.CpoeBedDTO;
import com.ruoyi.hospital.Vo.CpoeBedVo;
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
import com.ruoyi.hospital.domain.CpoeBed;
import com.ruoyi.hospital.service.ICpoeBedService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 床位记录Controller
 * 
 * @author ruoyi
 * @date 2024-06-04
 */
@RestController
@RequestMapping("/hospital/bed")
public class CpoeBedController extends BaseController
{
    @Autowired
    private ICpoeBedService cpoeBedService;

    /**
     * 查询床位记录列表
     */
    @PreAuthorize("@ss.hasPermi('hospital:bed:list')")
    @GetMapping("/list")
    public TableDataInfo list(CpoeBedDTO cpoeBed)
    {
        startPage();
        List<CpoeBedVo> list = cpoeBedService.selectCpoeBedList(cpoeBed);
        return getDataTable(list);
    }

    /**
     * 导出床位记录列表
     */
    @PreAuthorize("@ss.hasPermi('hospital:bed:export')")
    @Log(title = "床位记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CpoeBedDTO cpoeBed)
    {
        List<CpoeBedVo> list = cpoeBedService.selectCpoeBedList(cpoeBed);
        ExcelUtil<CpoeBedVo> util = new ExcelUtil<CpoeBedVo>(CpoeBedVo.class);
        util.exportExcel(response, list, "床位记录数据");
    }

    /**
     * 获取床位记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('hospital:bed:query')")
    @GetMapping(value = "/{bedId}")
    public AjaxResult getInfo(@PathVariable("bedId") Long bedId)
    {
        return success(cpoeBedService.selectCpoeBedByBedId(bedId));
    }

    /**
     * 新增床位记录
     */
    @PreAuthorize("@ss.hasPermi('hospital:bed:add')")
    @Log(title = "床位记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CpoeBed cpoeBed)
    {
        return toAjax(cpoeBedService.insertCpoeBed(cpoeBed));
    }

    /**
     * 修改床位记录
     */
    @PreAuthorize("@ss.hasPermi('hospital:bed:edit')")
    @Log(title = "床位记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CpoeBed cpoeBed)
    {
        return toAjax(cpoeBedService.updateCpoeBed(cpoeBed));
    }

    /**
     * 删除床位记录
     */
    @PreAuthorize("@ss.hasPermi('hospital:bed:remove')")
    @Log(title = "床位记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{bedIds}")
    public AjaxResult remove(@PathVariable Long[] bedIds)
    {
        return toAjax(cpoeBedService.deleteCpoeBedByBedIds(bedIds));
    }
}
