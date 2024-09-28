<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="患者姓名" prop="patientName">
        <el-input
          v-model="queryParams.lisPartname"
          placeholder="请输入患者姓名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="执行状态" prop="lisStatus">
        <el-select v-model="queryParams.lisStatus" placeholder="请选择执行状态" clearable>
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


    <el-table v-loading="loading" :data="lisList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="序号" type="index" :index="indexMethod"/>
      <el-table-column label="检验项目" align="center" prop="lisPartname"  width="150"/>
      <el-table-column label="患者姓名" align="center" prop="patientName"  width="150"/>
      <el-table-column label="医生姓名" align="center" prop="nickName" />
      <el-table-column label="执行状态" align="center" prop="lisStatus">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.execute_status" :value="scope.row.lisStatus"/>
        </template>
      </el-table-column>
      <el-table-column label="创建时间" align="center" prop="lisCreatedate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.lisCreatedate, '{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
      
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            v-if="scope.row.lisStatus==1"
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleExecute(scope.row)"
            v-hasPermi="['medical:lis:edit']"
          >执行</el-button>
          <el-button
          v-if="scope.row.lisStatus==2"
            size="mini"
            type="text"
            icon="el-icon-document"
            @click="handleDetail(scope.row)"
            v-hasPermi="['medical:lis:edit']"
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

    <!-- 执行lis检验对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-row>
          <el-col :span="24">
            <el-form-item label="检验项目" prop="lisPartname">
              <el-input v-model="form.lisPartname" readonly="readonly" />
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
        <el-form-item label="病情名称" prop="lisSickname">
          <el-input v-model="form.lisSickname" placeholder="请输入病情名称"  />
        </el-form-item>
        <el-form-item label="检验结果" prop="lisResult">
          <el-input type="textarea" resize="none" rows="4" v-model="form.lisResult" placeholder="请输入检验结果" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 执行lis详情对话框 -->
    <el-dialog :title="title" :visible.sync="openxq" width="500px" append-to-body>
      <el-form ref="formxq" :model="formxq" :rules="rules" label-width="80px">
      
      <el-col :span="12">
        <el-descriptions direction="vertical" border>
          <el-descriptions-item label="患者姓名">{{ formxq.patientName }}</el-descriptions-item>
        </el-descriptions>
      </el-col>
      <el-col :span="12">
        <el-descriptions direction="vertical" border>
          <el-descriptions-item label="检验项目">{{ formxq.lisPartname }}</el-descriptions-item>
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
            <el-descriptions-item label="创建时间">{{ formxq.lisCreatedate }}</el-descriptions-item>
          </el-descriptions>
        </el-col>
      </el-row>
      <el-row>
        <el-col>
          <el-descriptions direction="vertical" border>
            <el-descriptions-item label="病情名称">{{ formxq.lisSickname }}</el-descriptions-item>
          </el-descriptions>
        </el-col>
      </el-row>
      <el-row>
        <el-col>
          <el-descriptions direction="vertical" border>
            <el-descriptions-item label="病情结果">{{ formxq.lisResult }}</el-descriptions-item>

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
import { listLis, getLis, delLis, addLis, updateLis } from "@/api/medical/lis";

export default {
  name: "Lis",
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
      // Lis检验信息表格数据
      lisList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      opne1:false,
      openxq:false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        patientName: null,
        lisStatus: null,
       
      },
      // 表单参数
      form: {},
      formxq: {

      },
      // 表单校验
      rules: {
        lisResult: [
          { required: true, message: "检验结果不能为空", trigger: "blur" }
        ],
        lisSickname: [
          { required: true, message: "病情名称不能为空", trigger: "blur" }
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
    /** 查询Lis检验信息列表 */
    getList() {
      this.loading = true;
      listLis(this.queryParams).then(response => {
        this.lisList = response.rows;
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
        lisId: null,
        lisPartname: null,
        patientId: null,
        doctorId: null,
        patientName:null,
        nickName:null,
        lisStatus: null,
        lisCreatedate: null,
        recordsId: null,
        lisSickname: null,
        lisResult: null
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
      this.ids = selection.map(item => item.lisId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加Lis检验信息";
    },
    /** 执行操作 */
    handleExecute(row) {
      this.title="执行Lis检验"
      this.open=true;
      this.form={
        "patientName":row.patientName,
        "nickName":row.nickName,
        "lisPartname":row.lisPartname,
        "lisId":row.lisId,
        "resultDescription":row.resultDescription,
        "lisSickname":row.lisSickname
      }
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.form["lisStatus"]=2;
            updateLis(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
        }
      });
    },
    /** 查看详情 */
    handleDetail(row) {
      this.title="执行Lis检验详情"
      this.openxq=true;
      this.formxq={
        "patientName":row.patientName,
        "nickName":row.nickName,
        "lisPartname":row.lisPartname,
        "resultRemark":row.resultRemark,
        "lisCreatedate":row.lisCreatedate,
        "lisResult":row.lisResult,
        "lisSickname":row.lisSickname
      }
      this.getList();
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('medical/lis/export', {
        ...this.queryParams
      }, `lis_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
