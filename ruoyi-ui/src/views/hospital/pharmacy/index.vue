<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="药房类型" prop="pharmacyType">
        <el-select v-model="queryParams.pharmacyType" placeholder="请选择药房类型" clearable>
          <el-option
            v-for="dict in dict.type.total_type"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="药品编号" prop="drugsId">
        <el-input
          v-model="queryParams.drugsId"
          placeholder="请输入药品编号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="药品数量" prop="pharmacyNo">
        <el-input
          v-model="queryParams.pharmacyNo"
          placeholder="请输入药品数量"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="更新时间" prop="pharmacyUpdatetime">
        <el-date-picker clearable
          v-model="queryParams.pharmacyUpdatetime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择更新时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="操作人编号" prop="operatorId">
        <el-input
          v-model="queryParams.operatorId"
          placeholder="请输入操作人编号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['hospital:pharmacy:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['hospital:pharmacy:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['hospital:pharmacy:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['hospital:pharmacy:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="pharmacyList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <!-- <el-table-column label="唯一标识" align="center" prop="pharmacyId" /> -->
      <el-table-column label="药房类型" align="center" prop="pharmacyType">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.total_type" :value="scope.row.pharmacyType"/>
        </template>
      </el-table-column>
      <!-- <el-table-column label="药品编号" align="center" prop="drugsId" /> -->
      <el-table-column label="药品名称" align="center" prop="drugsName" />
      <el-table-column label="药品规格" align="center" prop="drugsSize" />
      <el-table-column label="药品售价" align="center" prop="drugsOutpay" />
      <el-table-column label="药品数量" align="center" prop="pharmacyNo" />
      <!-- <el-table-column label="更新时间" align="center" prop="pharmacyUpdatetime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.pharmacyUpdatetime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column> -->
      <el-table-column label="操作人编号" align="center" prop="operatorId" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['hospital:pharmacy:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['hospital:pharmacy:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改药房库存对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="药房类型" prop="pharmacyType">
          <el-select v-model="form.pharmacyType" placeholder="请选择药房类型">
            <el-option
              v-for="dict in dict.type.total_type"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="药品编号" prop="drugsId">
          <el-input v-model="form.drugsId" placeholder="请输入药品编号" />
        </el-form-item>
        <el-form-item label="药品数量" prop="pharmacyNo">
          <el-input v-model="form.pharmacyNo" placeholder="请输入药品数量" />
        </el-form-item>
        <el-form-item label="更新时间" prop="pharmacyUpdatetime">
          <el-date-picker clearable
            v-model="form.pharmacyUpdatetime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择更新时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="操作人编号" prop="operatorId">
          <el-input v-model="form.operatorId" placeholder="请输入操作人ID" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listPharmacy, getPharmacy, delPharmacy, addPharmacy, updatePharmacy } from "@/api/hospital/pharmacy";

export default {
  name: "Pharmacy",
  dicts: ['total_type'],
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 药房库存表格数据
      pharmacyList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        pharmacyType: null,
        drugsId: null,
        pharmacyNo: null,
        pharmacyUpdatetime: null,
        operatorId: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询药房库存列表 */
    getList() {
      this.loading = true;
      listPharmacy(this.queryParams).then(response => {
        this.pharmacyList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        pharmacyId: null,
        pharmacyType: null,
        drugsId: null,
        pharmacyNo: null,
        pharmacyUpdatetime: null,
        operatorId: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.pharmacyId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加药房库存";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const pharmacyId = row.pharmacyId || this.ids
      getPharmacy(pharmacyId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改药房库存";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.pharmacyId != null) {
            updatePharmacy(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addPharmacy(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const pharmacyIds = row.pharmacyId || this.ids;
      this.$modal.confirm('是否确认删除药房库存编号为"' + pharmacyIds + '"的数据项？').then(function() {
        return delPharmacy(pharmacyIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('hospital/pharmacy/export', {
        ...this.queryParams
      }, `pharmacy_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
