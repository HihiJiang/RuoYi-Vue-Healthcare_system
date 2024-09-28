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
import com.ruoyi.medical.domain.DoctorResult;
import com.ruoyi.medical.service.IDoctorResultService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 医生看病结果Controller
 * 
 * @author ruoyi
 * @date 2024-06-14
 */
@RestController
@RequestMapping("/medical/result")
public class DoctorResultController extends BaseController
{
    @Autowired
    private IDoctorResultService doctorResultService;

    /**
     * 查询医生看病结果列表
     */
    @PreAuthorize("@ss.hasPermi('medical:result:list')")
    @GetMapping("/list")
    public TableDataInfo list(DoctorResult doctorResult)
    {
        startPage();
        List<DoctorResult> list = doctorResultService.selectDoctorResultList(doctorResult);
        return getDataTable(list);
    }

    /**
     * 导出医生看病结果列表
     */
    @PreAuthorize("@ss.hasPermi('medical:result:export')")
    @Log(title = "医生看病结果", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, DoctorResult doctorResult)
    {
        List<DoctorResult> list = doctorResultService.selectDoctorResultList(doctorResult);
        ExcelUtil<DoctorResult> util = new ExcelUtil<DoctorResult>(DoctorResult.class);
        util.exportExcel(response, list, "医生看病结果数据");
    }

    /**
     * 获取医生看病结果详细信息
     */
    @PreAuthorize("@ss.hasPermi('medical:result:query')")
    @GetMapping(value = "/{resultId}")
    public AjaxResult getInfo(@PathVariable("resultId") Long resultId)
    {
        return success(doctorResultService.selectDoctorResultByResultId(resultId));
    }

    /**
     * 新增医生看病结果
     */
    @PreAuthorize("@ss.hasPermi('medical:result:add')")
    @Log(title = "医生看病结果", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody DoctorResult doctorResult)
    {
        return toAjax(doctorResultService.insertDoctorResult(doctorResult));
    }

    /**
     * 修改医生看病结果
     */
    @PreAuthorize("@ss.hasPermi('medical:result:edit')")
    @Log(title = "医生看病结果", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody DoctorResult doctorResult)
    {
        return toAjax(doctorResultService.updateDoctorResult(doctorResult));
    }

    /**
     * 删除医生看病结果
     */
    @PreAuthorize("@ss.hasPermi('medical:result:remove')")
    @Log(title = "医生看病结果", businessType = BusinessType.DELETE)
	@DeleteMapping("/{resultIds}")
    public AjaxResult remove(@PathVariable Long[] resultIds)
    {
        return toAjax(doctorResultService.deleteDoctorResultByResultIds(resultIds));
    }
}
