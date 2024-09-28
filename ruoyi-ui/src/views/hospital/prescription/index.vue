<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="医嘱记录ID" prop="ordersId">
        <el-input
          v-model="queryParams.ordersId"
          placeholder="请输入医嘱记录ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="消费事项" prop="presPartname">
        <el-input
          v-model="queryParams.presPartname"
          placeholder="请输入消费事项"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="消费数量" prop="presPartnumber">
        <el-input
          v-model="queryParams.presPartnumber"
          placeholder="请输入消费数量"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="消费类别" prop="presType">
        <el-select v-model="queryParams.presType" placeholder="请选择消费类别" clearable>
          <el-option
            v-for="dict in dict.type.pres_type"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="创建时间" prop="presCreatetime">
        <el-date-picker clearable
          v-model="queryParams.presCreatetime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择创建时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="备注" prop="presRemark">
        <el-input
          v-model="queryParams.presRemark"
          placeholder="请输入备注"
          clearable
          @keyup.enter.native="handleQuery"
        />
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
          v-hasPermi="['hospital:prescription:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="getpharmacy"
        >添加药品</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="getyltype('-1')"
        >检验</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="tjyh(2)"
        >检查</el-button>
      </el-col>
      <!-- <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['hospital:prescription:edit']"
        >修改</el-button>
      </el-col> -->
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['hospital:prescription:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['hospital:prescription:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="prescriptionList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <!-- <el-table-column label="治疗处方ID" align="center" prop="presId" /> -->
      <!-- <el-table-column label="医嘱记录ID" align="center" prop="ordersId" /> -->
      <el-table-column label="消费事项" align="center" prop="presPartname" />
      <el-table-column label="消费数量" align="center" prop="presPartnumber" />
      <el-table-column label="消费类别" align="center" prop="presType">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.pres_type" :value="scope.row.presType"/>
        </template>
      </el-table-column>
      <el-table-column label="创建时间" align="center" prop="presCreatetime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.presCreatetime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      
      <el-table-column label="备注" align="center" prop="presRemark" />
      <el-table-column label="价格" align="center" prop="porice" />
      <!-- <el-table-column label="操作人ID" align="center" prop="operatorId" /> -->
      <el-table-column label="操作人ID" align="center" prop="operatorId" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['hospital:prescription:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="removeFundBtn(scope.row)"
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

    <!-- 添加药品弹窗 -->
    <el-drawer
      title="选择药品添加"
      :visible.sync="bgtable"
      direction="rtl"
      size="50%">
      <el-table v-loading="loading" :data="pharmacyList" @selection-change="handleSelectionChangepharmacy">
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
      <!-- <el-table-column label="药品数量" align="center" prop="pharmacyNo" /> -->
      </el-table>
      <pagination
      v-show="pharmacytotal>0"
      :total="pharmacytotal"
      :page.sync="queryPharmacy.pageNum"
      :limit.sync="queryPharmacy.pageSize"
      @pagination="getpharmacyPage"
    />

      <button class="btn" @click="hqpharmacy">确认</button>
      </el-drawer>

      <!-- 检验检查 -->
      <el-drawer
        title="我是外面的 Drawer"
        :visible.sync="jcwc"
        size="60%">
          <!-- <el-select v-model="servicetypeId" clearable placeholder="请选择">
            <el-option
              v-for="item in yltype"
              :key="item.servicetypeId"
              :label="item.servicetypeName"
              :value="item.servicetypeId">
            </el-option>
          </el-select> -->
          <el-cascader
            placeholder="输入名称即可搜素"
            :options="yltype"
            filterable
            clearable
            style="width: 100%;"
            >
            <!-- :show-all-levels="false" -->
          </el-cascader>
      </el-drawer>

    <!-- 添加或修改住院治疗处方对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="医嘱记录编号" prop="ordersId">
          <el-input v-model="form.ordersId" placeholder="请输入医嘱记录编号" />
        </el-form-item>
        <el-form-item label="消费事项" prop="presPartname">
          <el-input v-model="form.presPartname" placeholder="请输入消费事项" />
        </el-form-item>
        <el-form-item label="消费数量" prop="presPartnumber">
          <el-input v-model="form.presPartnumber" placeholder="请输入消费数量" />
        </el-form-item>
        <el-form-item label="消费类别" prop="presType">
          <el-select v-model="form.presType" placeholder="请选择消费类别 ">
            <el-option
              v-for="dict in dict.type.pres_type"
              :key="dict.value"
              :label="dict.label"
              :value="parseInt(dict.value)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="创建时间" prop="presCreatetime">
          <el-date-picker clearable
            v-model="form.presCreatetime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择创建时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="备注" prop="presRemark">
          <el-input v-model="form.presRemark" placeholder="请输入备注" />
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
import { listPrescription, getPrescription, delPrescription, addPrescription, updatePrescription,listPharmacy, listType} from "@/api/hospital/prescription";
import { Button } from "element-ui";

export default {
  name: "Prescription",
  dicts: ['pres_type','total_type'],
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      //药品选中的数组
      idspharmacy :[],
      idspharmacy2 :[],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 住院治疗处方表格数据
      prescriptionList: [],
      bgtable:false,
      pharmacyList:[],
      pharmacytotal :0,
      queryPharmacy:{
        pageNum: 1,
        pageSize: 10,
      },
      //医疗服务类型
      jcwc:false,
      yltype:[],
      yltypeIds:null,
      servicetypeId: null,
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        ordersId: null,
        presPartname: null,
        presPartnumber: null,
        presType: null,
        presCreatetime: null,
        presRemark: null,
        operatorId: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        presCreatetime: [
          { required: true, message: "创建时间不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询住院治疗处方列表 */
    getList() {
      this.loading = true;
      listPrescription(this.queryParams).then(response => {
        this.prescriptionList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    getpharmacyPage(){
      listPharmacy(this.queryPharmacy).then(response => {
        this.pharmacyList = response.rows;
        this.pharmacytotal = response.total;
      });
    },
    //添加药品按钮点击事件
    getpharmacy(){
      this.bgtable = true;
      this.loading = true;
      listPharmacy().then(response => {
        this.pharmacyList = response.rows;
        this.pharmacytotal = response.total;
        this.loading = false;
      });
    },
    //添加检验按钮点击事件
    getyltype(servicetypeId){
      this.jcwc = true;
      let abc =[]
      this.getttyy(servicetypeId).then(response => {
        let abc = this.getabc(response);
        this.yltype = abc;
      })
    },

getabc(data) {
  for (var i = 0; i < data.length; i++){
    if(data[i]['children'].length > 0){
      this.getabc(data[i]['children'])
    }else{
      data[i]['children'] = undefined;
    }
  }
  return data;
},

async getttyy(servicetypeId) {
    try {
        const response = await listType({ "servicetypeId": servicetypeId });
        return await Promise.all(response.rows.map(async row => ({
            value: row.servicetypeId,
            label: row.servicetypeName,
            children: await this.getttyy(row.servicetypeId)
        })));
    } catch (error) {
        console.error("Error in getttyy: ", error);
        return [];
    }
},
cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        presId: null,
        ordersId: null,
        presPartname: null,
        presPartnumber: null,
        presType: null,
        presCreatetime: null,
        presRemark: null,
        operatorId: null,
        porice: null,
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
      this.ids = selection.map(item => item.presId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    // 药品多选框选中数据
    handleSelectionChangepharmacy(selection) {
      this.idspharmacy = selection.map(item => item)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    //添加药品获取选中的药品
    hqpharmacy(){
      //循环打印idspharmac
      if(this.prescriptionList.length == 0){
        let par ={
            presId: null,
            ordersId: null,
            presPartname:null,
            porice:null,
            presPartnumber: 1,
            presType: 0,
            presCreatetime: null,
            presRemark: null,
            operatorId: null
          }
        for(var i=0;i<this.idspharmacy.length;i++){
          par.presPartname = this.idspharmacy[i].drugsName
          par.porice = this.idspharmacy[i].drugsPrice
        }
        this.prescriptionList.push(par)
      }else{
        for(var i=0;i<this.idspharmacy.length;i++){
          if(this.prescriptionList.findIndex((v)=>(v.presPartname==this.idspharmacy[i].drugsName)) != -1){
            let ind = this.prescriptionList.findIndex((v)=>(v.presPartname==this.idspharmacy[i].drugsName))
            this.prescriptionList[ind].presPartnumber = this.prescriptionList[ind].presPartnumber +1
          }else{
            const newRow ={
              presId: null,
              ordersId: null,
              presPartname: this.idspharmacy[i].drugsName,
              presPartnumber: 1,
              presType: 0,
              porice : this.idspharmacy[i].drugsPrice,
              presCreatetime: null,
              presRemark: null,
              operatorId: null
            }
            this.prescriptionList.push(newRow)
          }
        }
    }
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加住院治疗处方";
    },
    tjyh(type){
      const newRow ={
        presId: null,
        ordersId: null,
        presPartname: null,
        presPartnumber: null,
        presType: type,
        presCreatetime: null,
        presRemark: null,
        operatorId: null

      }
      this.prescriptionList.push(newRow)
    },
    removeFundBtn(params) {
                this.prescriptionList = this.prescriptionList.filter((ele) => {
                  var flag = false
                  // 如果不一致，则保留该行
                  for (const key in params) {
                    if (ele[key] != params[key]) {
                      flag = true
                      break
                    }
                  }
                  return flag
                })
                // 如果全部删除后没有可以点击的一行了，需要加一行空行
                // if (!this.baseForm.demoList.length) {
                //   this.addLine()
                // }
              },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const presId = row.presId || this.ids
      getPrescription(presId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改住院治疗处方";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.presId != null) {
            updatePrescription(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addPrescription(this.form).then(response => {
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
      const presIds = row.presId || this.ids;
      this.$modal.confirm('是否确认删除住院治疗处方编号为"' + presIds + '"的数据项？').then(function() {
        return delPrescription(presIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('hospital/prescription/export', {
        ...this.queryParams
      }, `prescription_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
