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
import com.ruoyi.medical.domain.PersonArchives;
import com.ruoyi.medical.service.IPersonArchivesService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 患者档案信息Controller
 * 
 * @author ruoyi
 * @date 2024-06-01
 */
@RestController
@RequestMapping("/medical/archives")
public class PersonArchivesController extends BaseController
{
    @Autowired
    private IPersonArchivesService personArchivesService;

    /**
     * 查询患者档案信息列表
     */
    @PreAuthorize("@ss.hasPermi('medical:archives:list')")
    @GetMapping("/list")
    public TableDataInfo list(PersonArchives personArchives)
    {
        startPage();
        List<PersonArchives> list = personArchivesService.selectPersonArchivesList(personArchives);
        return getDataTable(list);
    }

    /**
     * 导出患者档案信息列表
     */
    @PreAuthorize("@ss.hasPermi('medical:archives:export')")
    @Log(title = "患者档案信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PersonArchives personArchives)
    {
        List<PersonArchives> list = personArchivesService.selectPersonArchivesList(personArchives);
        ExcelUtil<PersonArchives> util = new ExcelUtil<PersonArchives>(PersonArchives.class);
        util.exportExcel(response, list, "患者档案信息数据");
    }

    /**
     * 获取患者档案信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('medical:archives:query')")
    @GetMapping(value = "/{patientId}")
    public AjaxResult getInfo(@PathVariable("patientId") String patientId)
    {
        return success(personArchivesService.selectPersonArchivesByPatientId(patientId));
    }

    /**
     * 新增患者档案信息
     */
    @PreAuthorize("@ss.hasPermi('medical:archives:add')")
    @Log(title = "患者档案信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PersonArchives personArchives)
    {
        return toAjax(personArchivesService.insertPersonArchives(personArchives));
    }

    /**
     * 修改患者档案信息
     */
    @PreAuthorize("@ss.hasPermi('medical:archives:edit')")
    @Log(title = "患者档案信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PersonArchives personArchives)
    {
        return toAjax(personArchivesService.updatePersonArchives(personArchives));
    }

    /**
     * 删除患者档案信息
     */
    @PreAuthorize("@ss.hasPermi('medical:archives:remove')")
    @Log(title = "患者档案信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{patientIds}")
    public AjaxResult remove(@PathVariable String[] patientIds)
    {
        return toAjax(personArchivesService.deletePersonArchivesByPatientIds(patientIds));
    }
}
