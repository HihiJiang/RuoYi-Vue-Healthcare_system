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
import com.ruoyi.medical.domain.ServiceType;
import com.ruoyi.medical.service.IServiceTypeService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 医疗服务Controller
 * 
 * @author ruoyi
 * @date 2024-06-12
 */
@RestController
@RequestMapping("/medical/type")
public class ServiceTypeController extends BaseController
{
    @Autowired
    private IServiceTypeService serviceTypeService;

    /**
     * 查询医疗服务列表
     */
    @PreAuthorize("@ss.hasPermi('medical:type:list')")
    @GetMapping("/list")
    public TableDataInfo list(ServiceType serviceType)
    {
        startPage();
        List<ServiceType> list = serviceTypeService.selectServiceTypeList(serviceType);
        return getDataTable(list);
    }

    /**
     * 导出医疗服务列表
     */
    @PreAuthorize("@ss.hasPermi('medical:type:export')")
    @Log(title = "医疗服务", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ServiceType serviceType)
    {
        List<ServiceType> list = serviceTypeService.selectServiceTypeList(serviceType);
        ExcelUtil<ServiceType> util = new ExcelUtil<ServiceType>(ServiceType.class);
        util.exportExcel(response, list, "医疗服务数据");
    }

    /**
     * 获取医疗服务详细信息
     */
    @PreAuthorize("@ss.hasPermi('medical:type:query')")
    @GetMapping(value = "/{servicetypeId}")
    public AjaxResult getInfo(@PathVariable("servicetypeId") String servicetypeId)
    {
        return success(serviceTypeService.selectServiceTypeByServicetypeId(servicetypeId));
    }

    /**
     * 新增医疗服务
     */
    @PreAuthorize("@ss.hasPermi('medical:type:add')")
    @Log(title = "医疗服务", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ServiceType serviceType)
    {
        return toAjax(serviceTypeService.insertServiceType(serviceType));
    }

    /**
     * 修改医疗服务
     */
    @PreAuthorize("@ss.hasPermi('medical:type:edit')")
    @Log(title = "医疗服务", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ServiceType serviceType)
    {
        return toAjax(serviceTypeService.updateServiceType(serviceType));
    }

    /**
     * 删除医疗服务
     */
    @PreAuthorize("@ss.hasPermi('medical:type:remove')")
    @Log(title = "医疗服务", businessType = BusinessType.DELETE)
	@DeleteMapping("/{servicetypeIds}")
    public AjaxResult remove(@PathVariable String[] servicetypeIds)
    {
        return toAjax(serviceTypeService.deleteServiceTypeByServicetypeIds(servicetypeIds));
    }

    /**
     * 根据serviceType=“pacs/lis”查询医疗服务大类信息
     *
     */
    @PreAuthorize("@ss.hasPermi('medical:type:query')")
    @GetMapping(value = "/queryServiceTypeByDetials/{servicetype}")
    public AjaxResult queryServiceTypeByDetials(@PathVariable("servicetype") String servicetype)
    {
        return success(serviceTypeService.queryServiceTypeByDetails(servicetype));
    }
}
