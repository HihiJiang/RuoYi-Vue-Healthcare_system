<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="门诊卡编号" prop="outpatientId">
        <el-input
          v-model="queryParams.outpatientId"
          placeholder="请输入门诊卡编号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="患者编号" prop="patientId">
        <el-input
          v-model="queryParams.patientId"
          placeholder="请输入患者编号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
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
      <el-form-item label="取药状态" prop="totalStatus">
        <el-select v-model="queryParams.totalStatus" placeholder="请选择取药状态" clearable>
          <el-option
            v-for="dict in dict.type.total_status"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="取药时间" prop="totalGettime">
        <el-date-picker clearable
          v-model="queryParams.totalGettime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择取药时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="创建时间" prop="totalCreatetime">
        <el-date-picker clearable
          v-model="queryParams.totalCreatetime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择创建时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="操作人ID" prop="operatorId">
        <el-input
          v-model="queryParams.operatorId"
          placeholder="请输入操作人ID"
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
          v-hasPermi="['medical:drugstotal:add']"
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
          v-hasPermi="['medical:drugstotal:edit']"
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
          v-hasPermi="['medical:drugstotal:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['medical:drugstotal:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="drugstotalList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="取药编号" align="center" prop="totalId" />
      <el-table-column label="门诊卡编号" align="center" prop="outpatientId" />
      <el-table-column label="患者编号" align="center" prop="patientId" />
      <el-table-column label="药房类型" align="center" prop="pharmacyType">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.total_type" :value="scope.row.pharmacyType"/>
        </template>
      </el-table-column>
      <el-table-column label="取药状态" align="center" prop="totalStatus">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.total_status" :value="scope.row.totalStatus"/>
        </template>
      </el-table-column>
      <el-table-column label="取药时间" align="center" prop="totalGettime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.totalGettime, '{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="创建时间" align="center" prop="totalCreatetime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.totalCreatetime, '{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作人编号" align="center" prop="operatorId" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['medical:drugstotal:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['medical:drugstotal:remove']"
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

    <!-- 添加或修改取药记录对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="门诊卡编号" prop="outpatientId">
          <el-input v-model="form.outpatientId" placeholder="请输入门诊卡编号" />
        </el-form-item>
        <el-form-item label="患者编号" prop="patientId">
          <el-input v-model="form.patientId" placeholder="请输入患者编号" />
        </el-form-item>
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
        <el-form-item label="取药状态" prop="totalStatus">
          <el-select v-model="form.totalStatus" placeholder="请选择取药状态">
            <el-option
              v-for="dict in dict.type.total_status"
              :key="dict.value"
              :label="dict.label"
              :value="parseInt(dict.value)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="取药时间" prop="totalGettime">
          <el-date-picker clearable
            v-model="form.totalGettime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择取药时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="创建时间" prop="totalCreatetime">
          <el-date-picker clearable
            v-model="form.totalCreatetime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择创建时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="操作人编号" prop="operatorId">
          <el-input v-model="form.operatorId" placeholder="请输入操作人编号" />
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
import { listDrugstotal, getDrugstotal, delDrugstotal, addDrugstotal, updateDrugstotal } from "@/api/medical/drugstotal";

export default {
  name: "Drugstotal",
  dicts: ['total_type', 'total_status'],
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
      // 取药记录表格数据
      drugstotalList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        outpatientId: null,
        patientId: null,
        pharmacyType: null,
        totalStatus: null,
        totalGettime: null,
        totalCreatetime: null,
        operatorId: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        totalCreatetime: [
          { required: true, message: "创建时间不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询取药记录列表 */
    getList() {
      this.loading = true;
      listDrugstotal(this.queryParams).then(response => {
        this.drugstotalList = response.rows;
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
        totalId: null,
        outpatientId: null,
        patientId: null,
        pharmacyType: null,
        totalStatus: null,
        totalGettime: null,
        totalCreatetime: null,
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
      this.ids = selection.map(item => item.totalId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加取药记录";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const totalId = row.totalId || this.ids
      getDrugstotal(totalId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改取药记录";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.totalId != null) {
            updateDrugstotal(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addDrugstotal(this.form).then(response => {
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
      const totalIds = row.totalId || this.ids;
      this.$modal.confirm('是否确认删除取药记录编号为"' + totalIds + '"的数据项？').then(function() {
        return delDrugstotal(totalIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('medical/drugstotal/export', {
        ...this.queryParams
      }, `drugstotal_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
