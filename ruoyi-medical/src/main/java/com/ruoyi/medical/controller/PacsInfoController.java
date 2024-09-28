package com.ruoyi.medical.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.medical.dto.PacsInfoDTO;
import com.ruoyi.medical.vo.PacsInfoVo;
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
import com.ruoyi.medical.domain.PacsInfo;
import com.ruoyi.medical.service.IPacsInfoService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * pacs影像信息Controller
 * 
 * @author ruoyi
 * @date 2024-06-13
 */
@RestController
@RequestMapping("/medical/pacs")
public class PacsInfoController extends BaseController
{
    @Autowired
    private IPacsInfoService pacsInfoService;

    /**
     * 查询pacs影像信息列表
     */
    @PreAuthorize("@ss.hasPermi('medical:pacs:list')")
    @GetMapping("/list")
    public TableDataInfo list(PacsInfoDTO pacsInfo)
    {
        startPage();
        List<PacsInfoVo> list = pacsInfoService.selectPacsInfoList(pacsInfo);
        return getDataTable(list);
    }

    /**
     * 导出pacs影像信息列表
     */
    @PreAuthorize("@ss.hasPermi('medical:pacs:export')")
    @Log(title = "pacs影像信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PacsInfoDTO pacsInfo)
    {
        List<PacsInfoVo> list = pacsInfoService.selectPacsInfoList(pacsInfo);
        ExcelUtil<PacsInfoVo> util = new ExcelUtil<PacsInfoVo>(PacsInfoVo.class);
        util.exportExcel(response, list, "pacs影像信息数据");
    }

    /**
     * 获取pacs影像信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('medical:pacs:query')")
    @GetMapping(value = "/{pacsId}")
    public AjaxResult getInfo(@PathVariable("pacsId") Long pacsId)
    {
        return success(pacsInfoService.selectPacsInfoByPacsId(pacsId));
    }

    /**
     * 新增pacs影像信息
     */
    @PreAuthorize("@ss.hasPermi('medical:pacs:add')")
    @Log(title = "pacs影像信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PacsInfo pacsInfo)
    {
        return toAjax(pacsInfoService.insertPacsInfo(pacsInfo));
    }

    /**
     * 修改pacs影像信息
     */
    @PreAuthorize("@ss.hasPermi('medical:pacs:edit')")
    @Log(title = "pacs影像信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PacsInfo pacsInfo)
    {
        return toAjax(pacsInfoService.updatePacsInfo(pacsInfo));
    }

    /**
     * 删除pacs影像信息
     */
    @PreAuthorize("@ss.hasPermi('medical:pacs:remove')")
    @Log(title = "pacs影像信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{pacsIds}")
    public AjaxResult remove(@PathVariable Long[] pacsIds)
    {
        return toAjax(pacsInfoService.deletePacsInfoByPacsIds(pacsIds));
    }
}
