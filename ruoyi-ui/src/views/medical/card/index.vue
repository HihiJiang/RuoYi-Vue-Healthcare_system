<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="卡上余额" prop="outpatientBalance">
        <el-input v-model="queryParams.outpatientBalance" placeholder="请输入卡上余额" clearable
          @keyup.enter.native="handleQuery" />
      </el-form-item>
      <!-- <el-form-item label="启用时间" prop="outpatientEnabletime">
        <el-date-picker clearable
          v-model="queryParams.outpatientEnabletime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择启用时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="挂失时间" prop="outpatientLosstime">
        <el-date-picker clearable
          v-model="queryParams.outpatientLosstime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择挂失时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="作废时间" prop="outpatientDeadtime">
        <el-date-picker clearable
          v-model="queryParams.outpatientDeadtime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择作废时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="退卡时间" prop="outpatientReturntime">
        <el-date-picker clearable
          v-model="queryParams.outpatientReturntime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择退卡时间">
        </el-date-picker>
      </el-form-item> -->
      <!-- <el-form-item label="门诊卡状态" prop="outpatientStatus">
        <el-select v-model="queryParams.outpatientStatus" placeholder="请选择门诊卡状态" clearable>
          <el-option
            v-for="dict in dict.type.outpatient_status"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item> -->
      <el-form-item label="押金" prop="outpatientDeposit">
        <el-input v-model="queryParams.outpatientDeposit" placeholder="请输入押金" clearable
          @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="患者姓名" prop="patientName">
        <el-input v-model="queryParams.patientName" placeholder="请输入患者姓名" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>


      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <!-- <el-col :span="1.5">
        <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handleJiandang"
          v-hasPermi="['medical:card:add']">建档办卡</el-button>
      </el-col> -->
      <el-col :span="1.5">
        <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handleAdd"
          v-hasPermi="['medical:card:add']">新增门诊卡</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="primary" :disabled="single" plain icon="el-icon-plus" size="mini" @click="openRegister"
          v-hasPermi="['medical:card:add']">挂号</el-button>
      </el-col>
      <!-- <el-col :span="1.5">
        <el-button type="success" plain icon="el-icon-edit" size="mini" :disabled="single" @click="handleUpdate"
          v-hasPermi="['medical:card:edit']">修改</el-button>
      </el-col> -->
      <el-col :span="1.5">
        <el-button type="primary" plain size="mini" @click="upcard('1')">启用</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="primary" plain size="mini" @click="upcard('2')" v-hasPermi="['medical:card:edit']">挂失</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="primary" plain size="mini" @click="upcard('3')" v-hasPermi="['medical:card:edit']">作废</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="primary" plain size="mini" @click="upcard('4')" v-hasPermi="['medical:card:edit']">退卡</el-button>
      </el-col>

      <el-col :span="1.5">
        <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport"
          v-hasPermi="['medical:card:export']">导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="cardList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="门诊卡号" align="center" prop="outpatientId" width="180px">
        <template slot-scope="scope">
          <router-link :to="'/medical/record/index/' + scope.row.outpatientId" class="link-type">
            <span>{{ scope.row.outpatientId }}</span>
          </router-link>
        </template>
      </el-table-column>
      <el-table-column label="患者姓名" align="center" prop="patientName" width="120" />
      <el-table-column label="卡上余额" align="center" prop="outpatientBalance" />
      <!-- <el-table-column label="启用时间" align="center" prop="outpatientEnabletime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.outpatientEnabletime, '{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="挂失时间" align="center" prop="outpatientLosstime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.outpatientLosstime, '{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="作废时间" align="center" prop="outpatientDeadtime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.outpatientDeadtime, '{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="退卡时间" align="center" prop="outpatientReturntime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.outpatientReturntime, '{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column> -->
      <el-table-column label="门诊卡状态" align="center" prop="outpatientStatus">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.outpatient_status" :value="scope.row.outpatientStatus" />
        </template>
      </el-table-column>
      <el-table-column label="押金" align="center" prop="outpatientDeposit" />
      <el-table-column label="患者档案编号" align="center" prop="patientId" width="180px" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width" width="160px">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)"
            v-hasPermi="['medical:card:remove']">删除</el-button>
          <el-dropdown @command="handleCommand" trigger="click">
            <span class="el-dropdown-link">
              充值取现<i class="el-icon-arrow-down el-icon--right"></i>
            </span>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item
                :command="scope.row.outpatientId + ',' + scope.row.patientName + ',' + scope.row.outpatientBalance + ',in,' + scope.row.outpatientStatus"
                icon="el-icon-circle-plus-outline">充值</el-dropdown-item>
              <el-dropdown-item
                :command="scope.row.outpatientId + ',' + scope.row.patientName + ',' + scope.row.outpatientBalance + ',out,' + scope.row.outpatientStatus"
                icon="el-icon-remove-outline">取现</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize"
      @pagination="getList" />

    <!-- 新增门诊卡对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="750px" append-to-body>

      <el-row>
        <el-col :span="8">
          <!-- 搜索栏 -->
          <el-input placeholder="请输入患者姓名" v-model="patientName" class="input-with-select">
            <el-button slot="append" icon="el-icon-search" @click="queryPatientName"></el-button>
          </el-input>
          <!-- 数据表格 -->
          <el-table :data="tablePatientNameData" height="200" style="width: 100%" size="mini" :show-header="false">
            <el-table-column prop="patientName">
              <template slot-scope="scope">
                <el-link @click="selectPatientName(scope.row)" type="primary">{{ scope.row.patientName }} ({{
                  scope.row.patientPhone }})</el-link>
              </template>
            </el-table-column>
          </el-table>
        </el-col>
        <el-col :span="16" style="padding-left: 10px;">
          <el-descriptions labelStyle="width:90px" contentStyle="width:220px" class="margin-top" :column="2" :size="size"
            border>
            <el-descriptions-item>
              <template slot="label">
                患者姓名
              </template>
              {{ form.patientName }}
            </el-descriptions-item>
            <el-descriptions-item>
              <template slot="label">
                手机号码
              </template>
              {{ form.patientPhone }}
            </el-descriptions-item>
            <el-descriptions-item>
              <template slot="label">
                患者性别
              </template>

              <dict-tag :options="dict.type.sys_user_sex" :value="form.patientSex" />
            </el-descriptions-item>
            <el-descriptions-item>
              <template slot="label">
                身份证号
              </template>
              {{ form.patientIdcard }}
            </el-descriptions-item>
            <el-descriptions-item>
              <template slot="label">
                家庭住址
              </template>
              {{ form.patientAddress }}
            </el-descriptions-item>
          </el-descriptions>

          <el-form ref="form" :model="form" :rules="rules" label-width="100px" style="margin-top: 20px;">
            <el-form-item label="门诊卡押金" prop="outpatientDeposit">
              <el-input v-model="form.outpatientDeposit" placeholder="请输入门诊卡押金" />
            </el-form-item>
            <el-form-item label="患者档案ID" prop="patientId">
              <el-input readonly="readonly" v-model="form.patientId" placeholder="请输入患者档案ID" />
            </el-form-item>
            <!--<el-form-item label="门诊卡状态" prop="outpatientStatus">
          <el-select v-model="form.outpatientStatus" placeholder="请选择门诊卡状态">
            <el-option
              v-for="dict in dict.type.outpatient_status"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="押金" prop="outpatientDeposit">
          <el-input v-model="form.outpatientDeposit" placeholder="请输入押金" />
        </el-form-item>
        <el-form-item label="患者档案编号" prop="patientId">
          <el-input v-model="form.patientId" placeholder="请输入患者档案编号" />
        </el-form-item>
        <el-form-item label="操作人ID" prop="operatorId">
          <el-input v-model="form.operatorId" placeholder="请输入操作人ID" />
        </el-form-item>
        <el-form-item label="逻辑删除标记" prop="deleted">
          <el-input v-model="form.deleted" placeholder="请输入逻辑删除标记" />
        </el-form-item> -->
          </el-form>
        </el-col>
      </el-row>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 充值取现 -->
    <el-dialog :title="title" :visible.sync="open1" width="450px" append-to-body>
      <el-form :model="form1" label-width="80px" style="margin-top: 20px;">
        <el-form-item label="门诊卡号">
          <el-input readonly="readonly" v-model="form1.outpatientId" />
        </el-form-item>
        <el-form-item label="患者名称">
          <el-input readonly="readonly" v-model="form1.patientName" />
        </el-form-item>
        <el-form-item label="卡上余额">
          <el-input readonly="readonly" v-model="form1.outpatientBalance" />
        </el-form-item>
        <el-form-item :label="title + '余额'">
          <el-input v-model="form1.outpatientMoney" :placeholder="'请输入' + title + '金额'" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="addRechange">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
    <!-- 挂号对话框 -->
    <el-dialog :title="title" :visible.sync="open2" width="650px" append-to-body>
      <el-form :model="form1" label-width="80px" style="margin-top: 20px;">
        <el-row>
          <el-col :span="12">
            <el-form-item label="患者名称">
              <el-input readonly="readonly" v-model="form1.patientName" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="门诊卡号">
              <el-input readonly="readonly" v-model="form1.outpatientId" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="挂号科室">
              <el-cascader style="width:100%;" v-model="form1.deptId" :options="options"></el-cascader>
            </el-form-item>
          </el-col>
          <el-col :span="12" style="padding-left:10px">
            <el-button type="primary" @click="queryDoctors">查询</el-button>
          </el-col>
        </el-row>
      </el-form>

      <el-table v-loading="loading" :data="doctorsList" height="200px" @selection-change="handleSelectionChange">
        <el-table-column label="医生名称" align="center" prop="nick_name" width="120px" />
        <el-table-column label="医生职称" align="center" prop="post_name" />
        <el-table-column label="联系电话" align="center" prop="phonenumber" />
        <el-table-column label="挂号费用" align="center" prop="toll" />
        <el-table-column label="操作" align="center" class-name="small-padding fixed-width" width="160px">
          <template slot-scope="scope">
            <el-button size="mini" type="text" icon="el-icon-circle-plus" @click="handleRegister(scope.row)">挂号</el-button>
          </template>
        </el-table-column>
      </el-table>
      <div slot="footer" class="dialog-footer">
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
    <!-- 添加或修改患者档案信息对话框 -->
    <!-- <el-dialog :title="title" :visible.sync="open1" width="700px" append-to-body>
      <el-form ref="form1" :model="form1" :rules="rules1" label-width="80px">
        <el-row>
          <el-col :span="12">
            <el-form-item label="患者姓名" prop="patientName">
              <el-input v-model="form1.patientName" placeholder="请输入患者姓名" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="患者性别" prop="patientSex">
              <el-select style="width:100%" v-model="form1.patientSex" placeholder="请选择患者性别">
                <el-option v-for="dict in dict.type.sys_user_sex" :key="dict.value" :label="dict.label"
                  :value="dict.value"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="联系电话" prop="patientPhone">
              <el-input v-model="form1.patientPhone" placeholder="请输入联系电话" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="身份证号" prop="patientIdcard">
              <el-input v-model="form1.patientIdcard" placeholder="请输入患者身份证号" />
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="押金" prop="outpatientDeposit">
              <el-input v-model="form1.outpatientDeposit" placeholder="请输入押金" />
            </el-form-item>
          </el-col>

        </el-row>

      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="saveJiandang">确 定</el-button>
        <el-button @click="cancelJiandang">取 消</el-button>
      </div>
    </el-dialog> -->
  </div>
</template>

<script>
import { doctorRegistered, addMoney, queryDoctorsByDetptId, listCard, getCard, delCard, addCard, updateCard, addJiandang, queryDeptsByDeptPid } from "@/api/medical/card";
import { listArchives } from "@/api/medical/archives";

export default {
  name: "Card",
  dicts: ['outpatient_status', 'sys_user_sex'],

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
      // 门诊卡信息表格数据
      cardList: [],
      //新增门诊卡搜索患者信息表格数据
      tablePatientNameData: [],
      //患者名称
      patientName: '',
      //医生
      doctorsList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      open1: false,
      open2: false,
      //当前行门诊卡信息
      row: null,
      //科室列表
      options: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        outpatientBalance: null,
        outpatientEnabletime: null,
        outpatientLosstime: null,
        outpatientDeadtime: null,
        outpatientReturntime: null,
        outpatientStatus: null,
        outpatientDeposit: null,
        patientId: null,
        operatorId: null,
        deleted: null
      },
      // 表单参数
      form: {},
      form1: {},
      // 表单校验
      // rules1: {
      //   patientName: [
      //     { required: true, message: "患者姓名不能为空", trigger: "blur" }
      //   ],
      //   patientSex: [
      //     { required: true, message: "患者性别不能为空", trigger: "change" }
      //   ],
      //   patientPhone: [
      //     { required: true, message: "手机号码不能为空", trigger: "blur" }
      //   ],
      //   patientIdcard: [
      //     { required: true, message: "身份证号不能为空", trigger: "blur" }
      //   ],
      //   outpatientDeposit: [
      //     { required: true, message: "押金不能为空", trigger: "blur" }
      //   ],
      // },
      rules: {
        outpatientBalance: [
          { required: true, message: "卡上余额不能为空", trigger: "blur" }
        ],
        outpatientEnabletime: [
          { required: true, message: "启用时间不能为空", trigger: "blur" }
        ],
        outpatientDeposit: [
          { required: true, message: "押金不能为空", trigger: "blur" }
        ],
        patientId: [
          { required: true, message: "患者档案编号不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {


    /**挂号 */
    handleRegister(r) {
      this.$confirm('是否挂该科室"' + r.nick_name + '"医生', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        let params = {
          //门诊卡号
          outpatientId: this.form1.outpatientId,
          //医生编号
          recordsDoctorid: r.user_id,
          //部门编号
          recordsDeptid: this.form1.deptId[1],
          //挂号费
          toll: r.toll,
        }
        doctorRegistered(params).then(response => {
          if (response.code == 200) {
            this.$modal.msgSuccess("操作成功");
            this.open2 = false;
            this.doctorsList = [];
            this.form1.deptId = null;
            this.getList();
          } else {
            this.$modal.msgSuccess(response.msg);
          }
        });
      }).catch(() => { });

      

    },

    /**根据选中科室查询医生信息 */
    queryDoctors() {
      //判断是否选择挂号科室
      if (this.form1.deptId == null) {
        this.$modal.msgError("请选择挂号科室信息");
        return false;
      }
      // console.log(this.form1.deptId)
      // return 
      queryDoctorsByDetptId(this.form1.deptId[1]).then(response => {
        console.log(response.data);
        this.doctorsList = response.data
      })

    },
    /**挂号对话框 */
    openRegister() {
      //设置标题
      this.title = "挂号";
      //针对打开的对话框赋值
      this.form1 = {
        outpatientId: this.row.outpatientId,
        patientName: this.row.patientName
      }
      this.doctorsList = [];
      //动态打开绑定科室信息
      queryDeptsByDeptPid().then(response => {
        let root = response.data;
        root.forEach(root => root.children.forEach(node => {
          delete node['children']
        }));
        this.options = root
      });
      //设置对话框显示
      this.open2 = true;
    },

    /**充值取现 */
    addRechange() {
      //判断充值取现的金额是否符合要求
      if (isNaN(this.form1.outpatientMoney) || this.form1.outpatientMoney <= 0) {
        this.$modal.msgError("请输入正确的充值取现金额！")
        return false;
      }
      if (this.title == "取现" && parseFloat(this.form1.outpatientMoney) > parseFloat(this.form1.outpatientBalance)) {
        this.$modal.msgError("余额不足！")
        return false;
      }
      this.form1['rechargeType'] = this.title == '充值' ? "1" : "2";
      addMoney(this.form1).then(response => {
        this.$modal.msgSuccess("操作成功");
        this.open1 = false;
        this.getList();
      });
    },
    /**门诊卡列表中的充值取现按钮点击事件，弹出充值取现对话框 */
    handleCommand(c) {
      //使用split对传递参数进行分割
      let params = c.split(',');
      //判断门诊卡的状态是否是启用状态
      if (params[4] != 1) {
        this.$modal.msgWarning("只有启用状态的门诊卡才能进行充值取现操作")
        return false;
      }
      //根据执行的充值取现充值对title标题进行赋值
      this.title = (params[3] == "in") ? "充值" : "取现";
      //针对form1充值取现表单进行赋值
      this.form1 = {
        outpatientId: params[0],
        patientName: params[1],
        outpatientBalance: params[2],
      }
      //弹出对话框
      this.open1 = true;
    },
    /**选中患者，获取患者信息 */
    selectPatientName(row) {
      this.form = {
        patientAddress: row.patientAddress,
        patientId: row.patientId,
        patientIdcard: row.patientIdcard,
        patientName: row.patientName,
        patientSex: row.patientSex,
        patientPhone: row.patientPhone
      }
    },
    /**新增门诊卡对话框中的查询患者方法*/
    queryPatientName() {
      this.loading = true;
      listArchives({ 'patientName': this.patientName }).then(response => {
        this.tablePatientNameData = response.rows;
        console.log(this.tablePatientNameData)
        this.total = response.total;
        this.loading = false;

      });
    },
    /**保存建档办卡信息 */
    saveJiandang() {
      this.$refs["form1"].validate(valid => {
        if (valid) {
          addJiandang(this.form1).then(response => {
            this.$modal.msgSuccess("新增成功");
            this.open1 = false;
            this.getList();
          });
        }
      });
    },

    /** 查询门诊卡信息列表 */
    getList() {
      this.loading = true;
      listCard(this.queryParams).then(response => {
        this.cardList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.open1 = false;
      this.open2 = false;
      this.reset();
    },
    /**关闭建档办卡 */
    cancelJiandang() {
      this.open1 = false;
      this.reset();
    },
    // 表单重置
    reset() {
      // this.form1 = {
      //   patientName: null,
      //   patientIdcard: null,
      //   patientSex: null,
      //   outpatientDeposit: null,
      //   patientPhone: null
      // };
      //清空患者表格
      this.tablePatientNameData = [],
        this.form1 = {
          outpatientId: null,
          patientName: null,
          outpatientBalance: null,
          outpatientMoney: null,
          deptId: null
        }
      this.form = {
        outpatientId: null,
        outpatientBalance: null,
        outpatientEnabletime: null,
        outpatientLosstime: null,
        outpatientDeadtime: null,
        outpatientReturntime: null,
        outpatientStatus: null,
        outpatientDeposit: null,
        patientId: null,
        operatorId: null,
        deleted: null
      };
      this.resetForm("form");
      this.resetForm("form1");
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
      this.ids = selection.map(item => item.outpatientId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
      //获取当前选中行对象
      this.row = selection[0]
    },
    /**建档办卡 */
    // handleJiandang() {
    //   this.reset();
    //   this.open1 = true;
    //   this.title = "建档办卡";
    // },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加门诊卡信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const outpatientId = row.outpatientId || this.ids
      getCard(outpatientId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改门诊卡信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.outpatientId != null) {
            updateCard(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addCard(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }

        }
      });
    },
    /**修改门诊卡状态 ，1启用2挂失3作废4退卡，默认1*/
    upcard(n) {
      let a = [];
      this.form.outpatientStatus = n;
      //循环打印ids
      for (var i = 0; i < this.ids.length; i++) {
        this.form.outpatientId = this.ids[i];
        updateCard(this.form).then(response => {
          this.getList();
        }).catch(response => {
          a.push(i);
        });
      }

      // if(a.length == this.ids.length){
      //   this.$modal.msgSuccess("修改成功");
      //   this.getList();
      // }else{
      //   pderror = '(';
      //   for(var i=0;i<this.ids.length;i++){
      //     if(a.includes(i) == true){
      //       pderror = pderror + this.ids[i] + ',';
      //     }
      //   }
      //   pderror = pderror + ')';
      //   this.$modal.msgError("修改失败,失败的门诊卡ID"+pderror);
      // }
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const outpatientIds = row.outpatientId || this.ids;
      this.$modal.confirm('是否确认删除门诊卡信息编号为"' + outpatientIds + '"的数据项？').then(function () {
        return delCard(outpatientIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => { });
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('medical/card/export', {
        ...this.queryParams
      }, `card_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
