<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">

      <el-form-item label="接诊ID" prop="recordsId">
        <el-input
        readonly="readonly"
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
        v-if="resultList.length==0"
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['medical:result:add']"
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
          v-hasPermi="['medical:result:edit']"
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
          v-hasPermi="['medical:result:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['medical:result:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="resultList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="序号" type="index" :index="indexMethod"/>
      <el-table-column label="病情名称" align="center" prop="resultName" />
      <el-table-column label="症状描述" align="center" prop="resultDescription" />
      <el-table-column label="医嘱" align="center" prop="resultRemark" />
      <el-table-column label="创建时间" align="center" prop="resultCreatedate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.resultCreatedate, '{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
      
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['medical:result:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['medical:result:remove']"
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

    <!-- 添加或修改医生看病结果对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="病情名称" prop="resultName">
          <el-input v-model="form.resultName" placeholder="请输入病名称" />
        </el-form-item>
        <el-form-item label="症状描述" prop="resultDescription">
          <el-input type="textarea" resize="none" rows="4" v-model="form.resultDescription" placeholder="请输入症状描述" />
        </el-form-item>
        <el-form-item label="医生医嘱"   prop="resultRemark">
          <el-input type="textarea" resize="none" rows="4" v-model="form.resultRemark" placeholder="请输入医嘱" />
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
import { listResult, getResult, delResult, addResult, updateResult } from "@/api/medical/result";

export default {
  name: "Result",
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
      // 医生看病结果表格数据
      resultList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        resultName: null,
        resultDescription: null,
        resultRemark: null,
        resultCreatedate: null,
        recordsId: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        resultName: [
          { required: true, message: "病情名称不能为空", trigger: "blur" }
        ],
        resultDescription: [
          { required: true, message: "症状描述不能为空", trigger: "blur" }
        ],
        resultRemark: [
          { required: true, message: "医生医嘱不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.queryParams.recordsId = this.$route.params && this.$route.params.recordsId;
    this.getList();
  },
  methods: {
    indexMethod(index) {
        return index+1;
      },
    /** 查询医生看病结果列表 */
    getList() {
      this.loading = true;
      listResult(this.queryParams).then(response => {
        this.resultList = response.rows;
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
        resultId: null,
        resultName: null,
        resultDescription: null,
        resultRemark: null,
        resultCreatedate: null,
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
      this.ids = selection.map(item => item.resultId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加医生看病结果";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const resultId = row.resultId || this.ids
      getResult(resultId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改医生看病结果";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
         this.form['recordsId'] =this.queryParams.recordsId;
          if (this.form.resultId != null) {
            updateResult(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addResult(this.form).then(response => {
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
      const resultIds = row.resultId || this.ids;
      this.$modal.confirm('是否确认删除医生看病结果编号为"' + resultIds + '"的数据项？').then(function() {
        return delResult(resultIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('medical/result/export', {
        ...this.queryParams
      }, `result_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
