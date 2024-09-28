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
import com.ruoyi.medical.domain.DoctorAction;
import com.ruoyi.medical.service.IDoctorActionService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 医生看病流程Controller
 * 
 * @author ruoyi
 * @date 2024-06-13
 */
@RestController
@RequestMapping("/medical/action")
public class DoctorActionController extends BaseController
{
    @Autowired
    private IDoctorActionService doctorActionService;

    /**
     * 查询医生看病流程列表
     */
    @PreAuthorize("@ss.hasPermi('medical:action:list')")
    @GetMapping("/list")
    public TableDataInfo list(DoctorAction doctorAction)
    {
        startPage();
        List<DoctorAction> list = doctorActionService.selectDoctorActionList(doctorAction);
        return getDataTable(list);
    }

    /**
     * 导出医生看病流程列表
     */
    @PreAuthorize("@ss.hasPermi('medical:action:export')")
    @Log(title = "医生看病流程", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, DoctorAction doctorAction)
    {
        List<DoctorAction> list = doctorActionService.selectDoctorActionList(doctorAction);
        ExcelUtil<DoctorAction> util = new ExcelUtil<DoctorAction>(DoctorAction.class);
        util.exportExcel(response, list, "医生看病流程数据");
    }

    /**
     * 获取医生看病流程详细信息
     */
    @PreAuthorize("@ss.hasPermi('medical:action:query')")
    @GetMapping(value = "/{actionId}")
    public AjaxResult getInfo(@PathVariable("actionId") Long actionId)
    {
        return success(doctorActionService.selectDoctorActionByActionId(actionId));
    }

    /**
     * 新增医生看病流程
     */
    @PreAuthorize("@ss.hasPermi('medical:action:add')")
    @Log(title = "医生看病流程", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody DoctorAction doctorAction)
    {
        return toAjax(doctorActionService.insertDoctorAction(doctorAction));
    }

    /**
     * 修改医生看病流程
     */
    @PreAuthorize("@ss.hasPermi('medical:action:edit')")
    @Log(title = "医生看病流程", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody DoctorAction doctorAction)
    {
        return toAjax(doctorActionService.updateDoctorAction(doctorAction));
    }

    /**
     * 删除医生看病流程
     */
    @PreAuthorize("@ss.hasPermi('medical:action:remove')")
    @Log(title = "医生看病流程", businessType = BusinessType.DELETE)
	@DeleteMapping("/{actionIds}")
    public AjaxResult remove(@PathVariable Long[] actionIds)
    {
        return toAjax(doctorActionService.deleteDoctorActionByActionIds(actionIds));
    }
}
