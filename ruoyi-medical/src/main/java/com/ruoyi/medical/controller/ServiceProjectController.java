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
import com.ruoyi.medical.domain.ServiceProject;
import com.ruoyi.medical.service.IServiceProjectService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 医疗服务项目Controller
 * 
 * @author ruoyi
 * @date 2024-06-12
 */
@RestController
@RequestMapping("/medical/project")
public class ServiceProjectController extends BaseController
{
    @Autowired
    private IServiceProjectService serviceProjectService;

    /**
     * 查询医疗服务项目列表
     */
    @PreAuthorize("@ss.hasPermi('medical:project:list')")
    @GetMapping("/list")
    public TableDataInfo list(ServiceProject serviceProject)
    {
        startPage();
        List<ServiceProject> list = serviceProjectService.selectServiceProjectList(serviceProject);
        return getDataTable(list);
    }

    /**
     * 导出医疗服务项目列表
     */
    @PreAuthorize("@ss.hasPermi('medical:project:export')")
    @Log(title = "医疗服务项目", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ServiceProject serviceProject)
    {
        List<ServiceProject> list = serviceProjectService.selectServiceProjectList(serviceProject);
        ExcelUtil<ServiceProject> util = new ExcelUtil<ServiceProject>(ServiceProject.class);
        util.exportExcel(response, list, "医疗服务项目数据");
    }

    /**
     * 获取医疗服务项目详细信息
     */
    @PreAuthorize("@ss.hasPermi('medical:project:query')")
    @GetMapping(value = "/{serviceprojectId}")
    public AjaxResult getInfo(@PathVariable("serviceprojectId") Long serviceprojectId)
    {
        return success(serviceProjectService.selectServiceProjectByServiceprojectId(serviceprojectId));
    }

    /**
     * 新增医疗服务项目
     */
    @PreAuthorize("@ss.hasPermi('medical:project:add')")
    @Log(title = "医疗服务项目", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ServiceProject serviceProject)
    {
        return toAjax(serviceProjectService.insertServiceProject(serviceProject));
    }

    /**
     * 修改医疗服务项目
     */
    @PreAuthorize("@ss.hasPermi('medical:project:edit')")
    @Log(title = "医疗服务项目", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ServiceProject serviceProject)
    {
        return toAjax(serviceProjectService.updateServiceProject(serviceProject));
    }

    /**
     * 删除医疗服务项目
     */
    @PreAuthorize("@ss.hasPermi('medical:project:remove')")
    @Log(title = "医疗服务项目", businessType = BusinessType.DELETE)
	@DeleteMapping("/{serviceprojectIds}")
    public AjaxResult remove(@PathVariable Long[] serviceprojectIds)
    {
        return toAjax(serviceProjectService.deleteServiceProjectByServiceprojectIds(serviceprojectIds));
    }


    /**
     * 根据消费大类查询子类信息
     */
    @PreAuthorize("@ss.hasPermi('medical:project:query')")
    @GetMapping(value = "/queryServiceProjectBySid/{serviceId}")
    public AjaxResult queryServiceProjectBySid(@PathVariable("serviceId") Long serviceId)
    {
        return success(serviceProjectService.queryServiceProjectBySid(serviceId));
    }
}
