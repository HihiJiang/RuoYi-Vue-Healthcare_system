<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="患者姓名" prop="patientName">
        <el-input
          v-model="queryParams.patientName"
          placeholder="请输入患者姓名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
   
      <el-form-item label="执行状态" prop="pacsStatus">
        <el-select v-model="queryParams.pacsStatus" placeholder="请选择执行状态" clearable>
          <el-option
            v-for="dict in dict.type.execute_status"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-table v-loading="loading" :data="pacsList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="序号" type="index" :index="indexMethod"/>
      <el-table-column label="pacs检查项目" align="center" prop="pacsPartname"  width="200" />
      <el-table-column label="患者姓名" align="center" prop="patientName"  width="150"/>
      <el-table-column label="医生姓名" align="center" prop="nickName" />
      <el-table-column label="执行状态" align="center" prop="pacsStatus">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.execute_status" :value="scope.row.pacsStatus"/>
        </template>
      </el-table-column>
      <el-table-column label="创建时间" align="center" prop="pacsCreatedate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.pacsCreatedate, '{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
          v-if="scope.row.pacsStatus==1"
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleExecute(scope.row)"
            v-hasPermi="['medical:pacs:edit']"
          >执行</el-button>
          <el-button
          v-if="scope.row.pacsStatus==2"
            size="mini"
            type="text"
            icon="el-icon-document"
            @click="handleDetail(scope.row)"
            v-hasPermi="['medical:pacs:edit']"
          >详情</el-button>
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
    <!-- 执行pacs影像信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-row>
          <el-col :span="24">
            <el-form-item label="检查项目" prop="pacsPartname">
              <el-input v-model="form.pacsPartname" readonly="readonly" />
          </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="患者姓名" prop="patientName">
          <el-input v-model="form.patientName" readonly="readonly" />
        </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="医生姓名" prop="nickName">
          <el-input v-model="form.nickName" readonly="readonly" />
        </el-form-item>
          </el-col>
        </el-row>
        <el-form-item label="病情名称" prop="pacsSickname">
          <el-input v-model="form.pacsSickname" placeholder="请输入病情名称"  />
        </el-form-item>
        <el-form-item label="检查结果" prop="pacsResult">
          <el-input type="textarea" resize="none" rows="4" v-model="form.pacsResult" placeholder="请输入检查结果" />
        </el-form-item>
      </el-form>
      
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
    <!-- 执行pacs详情 -->
    <el-dialog :title="title" :visible.sync="openxq" width="500px" append-to-body>
      <el-form ref="formxq" :model="formxq" :rules="rules" label-width="80px">
      
      <el-col :span="12">
        <el-descriptions direction="vertical" border>
          <el-descriptions-item label="患者姓名">{{ formxq.patientName }}</el-descriptions-item>
        </el-descriptions>
      </el-col>
      <el-col :span="12">
        <el-descriptions direction="vertical" border>
          <el-descriptions-item label="检验项目">{{ formxq.pacsPartname }}</el-descriptions-item>
        </el-descriptions>
      </el-col>
      <el-row>
        <el-col :span="12">
          <el-descriptions direction="vertical" border>
            <el-descriptions-item label="医生名称">{{ formxq.nickName }}</el-descriptions-item>
          </el-descriptions>
        </el-col>

        <el-col :span="12">
          <el-descriptions direction="vertical" border>
            <el-descriptions-item label="创建时间">{{ formxq.pacsCreatedate }}</el-descriptions-item>
          </el-descriptions>
        </el-col>
      </el-row>
      <el-row>
        <el-col>
          <el-descriptions direction="vertical" border>
            <el-descriptions-item label="病情名称">{{ formxq.pacsSickname }}</el-descriptions-item>
          </el-descriptions>
        </el-col>
      </el-row>
      <el-row>
        <el-col>
          <el-descriptions direction="vertical" border>
            <el-descriptions-item label="病情结果">{{ formxq.pacsResult }}</el-descriptions-item>

          </el-descriptions>
        </el-col>
      </el-row>


    </el-form>
      
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listPacs, getPacs, delPacs, addPacs, updatePacs } from "@/api/medical/pacs";

export default {
  name: "Pacs",
  dicts: ['execute_status'],
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
      // pacs影像信息表格数据
      pacsList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      openxq:false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        patientName: null,
        pacsStatus: null,
       
      },
      // 表单参数
      form: {},
      formxq:{},
      // 表单校验
      rules: {
        pacsSickname: [
          { required: true, message: "病情名称不能为空", trigger: "blur" }
        ],
        pacsResult: [
          { required: true, message: "检查结果不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    indexMethod(index) {
        return index+1;
      },
    /** 查询pacs影像信息列表 */
    getList() {
      this.loading = true;
      listPacs(this.queryParams).then(response => {
        this.pacsList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.openxq=false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        pacsId: null,
        pacsPartname: null,
        patientId: null,
        patientName:null,
        nickName:null,
        doctorId: null,
        pacsStatus: null,
        pacsCreatedate: null,
        recordsId: null,
        pacsSickname: null,
        pacsResult: null
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
      this.ids = selection.map(item => item.pacsId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加pacs影像信息";
    },
    /** 执行操作 */
    handleExecute(row) {
      this.title="执行Pacs检查"
      this.open=true;
      this.form={
        "patientName":row.patientName,
        "nickName":row.nickName,
        "pacsPartname":row.pacsPartname,
        "pacsId":row.pacsId,
        "resultDescription":row.resultDescription,
       
      }
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.form["pacsStatus"]=2;
            updatePacs(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
        }
      });
    },
    /** 查看详情按钮操作 */
    handleDetail(row) {
      this.title="执行Pacs检查详情"
      this.openxq=true;
      this.formxq={
        "patientName":row.patientName,
        "nickName":row.nickName,
        "pacsPartname":row.pacsPartname,
        "pacsSickname":row.pacsSickname,
        "resultDescription":row.resultDescription,
        "pacsResult":row.pacsResult,
        "pacsCreatedate":row.pacsCreatedate,
       
      }
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('medical/pacs/export', {
        ...this.queryParams
      }, `pacs_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
