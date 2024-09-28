<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="消费事项" prop="treatPartname">
        <el-input
          v-model="queryParams.treatPartname"
          placeholder="请输入消费事项"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="消费数量" prop="treatPartnumber">
        <el-input
          v-model="queryParams.treatPartnumber"
          placeholder="请输入消费数量"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="单价" prop="treatPartmoney">
        <el-input
          v-model="queryParams.treatPartmoney"
          placeholder="请输入单价"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="创建时间" prop="treatCreatedate">
        <el-date-picker clearable
          v-model="queryParams.treatCreatedate"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择创建时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="接诊记录ID" prop="recordsId">
        <el-input
          v-model="queryParams.recordsId"
          placeholder="请输入接诊记录ID"
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
          v-hasPermi="['medical:resulthandle:add']"
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
          v-hasPermi="['medical:resulthandle:edit']"
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
          v-hasPermi="['medical:resulthandle:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['medical:resulthandle:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="resulthandleList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="治疗方案ID" align="center" prop="treatId" />
      <el-table-column label="消费事项" align="center" prop="treatPartname" />
      <el-table-column label="消费数量" align="center" prop="treatPartnumber" />
      <el-table-column label="单价" align="center" prop="treatPartmoney" />
      <el-table-column label="创建时间" align="center" prop="treatCreatedate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.treatCreatedate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="接诊记录ID" align="center" prop="recordsId" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['medical:resulthandle:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['medical:resulthandle:remove']"
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

    <!-- 添加或修改医生看病治疗方案对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="消费事项" prop="treatPartname">
          <el-input v-model="form.treatPartname" placeholder="请输入消费事项" />
        </el-form-item>
        <el-form-item label="消费数量" prop="treatPartnumber">
          <el-input v-model="form.treatPartnumber" placeholder="请输入消费数量" />
        </el-form-item>
        <el-form-item label="单价" prop="treatPartmoney">
          <el-input v-model="form.treatPartmoney" placeholder="请输入单价" />
        </el-form-item>
        <el-form-item label="创建时间" prop="treatCreatedate">
          <el-date-picker clearable
            v-model="form.treatCreatedate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择创建时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="接诊记录ID" prop="recordsId">
          <el-input v-model="form.recordsId" placeholder="请输入接诊记录ID" />
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
import { listResulthandle, getResulthandle, delResulthandle, addResulthandle, updateResulthandle } from "@/api/medical/resulthandle";

export default {
  name: "Resulthandle",
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
      // 医生看病治疗方案表格数据
      resulthandleList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        treatPartname: null,
        treatPartnumber: null,
        treatPartmoney: null,
        treatCreatedate: null,
        recordsId: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        treatCreatedate: [
          { required: true, message: "创建时间不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.queryParams.recordsId = this.$route.params && this.$route.params.recordsId;
    this.getList();
  },
  methods: {
    /** 查询医生看病治疗方案列表 */
    getList() {
      this.loading = true;
      listResulthandle(this.queryParams).then(response => {
        this.resulthandleList = response.rows;
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
        treatId: null,
        treatPartname: null,
        treatPartnumber: null,
        treatPartmoney: null,
        treatCreatedate: null,
        recordsId: null
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
      this.ids = selection.map(item => item.treatId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加医生看病治疗方案";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const treatId = row.treatId || this.ids
      getResulthandle(treatId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改医生看病治疗方案";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.treatId != null) {
            updateResulthandle(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addResulthandle(this.form).then(response => {
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
      const treatIds = row.treatId || this.ids;
      this.$modal.confirm('是否确认删除医生看病治疗方案编号为"' + treatIds + '"的数据项？').then(function() {
        return delResulthandle(treatIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('medical/resulthandle/export', {
        ...this.queryParams
      }, `resulthandle_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
