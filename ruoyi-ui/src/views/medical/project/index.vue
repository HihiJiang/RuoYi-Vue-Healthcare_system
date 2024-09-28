<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="服务项目名称" prop="serviceprojectName">
        <el-input
          v-model="queryParams.serviceprojectName"
          placeholder="请输入服务项目名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="服务项目价格" prop="serviceprojectPrice">
        <el-input
          v-model="queryParams.serviceprojectPrice"
          placeholder="请输入服务项目价格"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="服务项目说明" prop="serviceprojectDetails">
        <el-input
          v-model="queryParams.serviceprojectDetails"
          placeholder="请输入服务项目说明"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="服务项目计价单位" prop="serviceprojectUnit">
        <el-input
          v-model="queryParams.serviceprojectUnit"
          placeholder="请输入服务项目计价单位"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="服务类型ID" prop="serviceId">
        <el-input
          v-model="queryParams.serviceId"
          placeholder="请输入服务类型ID"
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
          v-hasPermi="['medical:project:add']"
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
          v-hasPermi="['medical:project:edit']"
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
          v-hasPermi="['medical:project:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['medical:project:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="projectList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label=" 服务项目ID" align="center" prop="serviceprojectId" />
      <el-table-column label="服务项目名称" align="center" prop="serviceprojectName" />
      <el-table-column label="服务项目价格" align="center" prop="serviceprojectPrice" />
      <el-table-column label="服务项目说明" align="center" prop="serviceprojectDetails" />
      <el-table-column label="服务项目计价单位" align="center" prop="serviceprojectUnit" />
      <el-table-column label="服务类型ID" align="center" prop="serviceId" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['medical:project:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['medical:project:remove']"
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

    <!-- 添加或修改医疗服务项目对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="服务项目名称" prop="serviceprojectName">
          <el-input v-model="form.serviceprojectName" placeholder="请输入服务项目名称" />
        </el-form-item>
        <el-form-item label="服务项目价格" prop="serviceprojectPrice">
          <el-input v-model="form.serviceprojectPrice" placeholder="请输入服务项目价格" />
        </el-form-item>
        <el-form-item label="服务项目说明" prop="serviceprojectDetails">
          <el-input v-model="form.serviceprojectDetails" placeholder="请输入服务项目说明" />
        </el-form-item>
        <el-form-item label="服务项目计价单位" prop="serviceprojectUnit">
          <el-input v-model="form.serviceprojectUnit" placeholder="请输入服务项目计价单位" />
        </el-form-item>
        <el-form-item label="服务类型ID" prop="serviceId">
          <el-input v-model="form.serviceId" placeholder="请输入服务类型ID" />
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
import { listProject, getProject, delProject, addProject, updateProject } from "@/api/medical/project";

export default {
  name: "Project",
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
      // 医疗服务项目表格数据
      projectList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        serviceprojectName: null,
        serviceprojectPrice: null,
        serviceprojectDetails: null,
        serviceprojectUnit: null,
        serviceId: null
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
    /** 查询医疗服务项目列表 */
    getList() {
      this.loading = true;
      listProject(this.queryParams).then(response => {
        this.projectList = response.rows;
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
        serviceprojectId: null,
        serviceprojectName: null,
        serviceprojectPrice: null,
        serviceprojectDetails: null,
        serviceprojectUnit: null,
        serviceId: null
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
      this.ids = selection.map(item => item.serviceprojectId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加医疗服务项目";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const serviceprojectId = row.serviceprojectId || this.ids
      getProject(serviceprojectId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改医疗服务项目";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.serviceprojectId != null) {
            updateProject(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addProject(this.form).then(response => {
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
      const serviceprojectIds = row.serviceprojectId || this.ids;
      this.$modal.confirm('是否确认删除医疗服务项目编号为"' + serviceprojectIds + '"的数据项？').then(function() {
        return delProject(serviceprojectIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('medical/project/export', {
        ...this.queryParams
      }, `project_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
