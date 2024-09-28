<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      
      <el-form-item label="诊断状态" prop="recordsDiagnosticStatus">
        <el-select v-model="queryParams.recordsDiagnosticStatus" placeholder="请选择诊断状态" clearable>
          <el-option
            v-for="dict in dict.type.records_diagnostic_status"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="接诊状态" prop="recordsStatus">
        <el-select v-model="queryParams.recordsStatus" placeholder="请选择接诊状态" clearable>
          <el-option
            v-for="dict in dict.type.records_status"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="患者姓名" prop="patientName">
        <el-input
          v-model="queryParams.patientName"
          placeholder="请输入患者姓名"
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
          :disabled="single"
          size="mini"
          @click="handleRecordsStatus(2)"
          v-hasPermi="['medical:receiverecord:add']"
        >接诊</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button 
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          :disabled="single"
          @click="handleRecordsStatus(3)"
          v-hasPermi="['medical:receiverecord:add']"
        >顺延</el-button>
      </el-col>

      <el-col :span="1.5">
    <el-button-group>
        <el-button @click="openserviceType(1)" size="mini" :disabled="single" plain type="success" icon="el-icon-edit">检验</el-button>
        <el-button @click="openserviceType(2)" size="mini" :disabled="single" plain type="success" icon="el-icon-share">检查</el-button>
        <el-button @click="openserviceType(3)" size="mini" :disabled="single" plain type="success" icon="el-icon-share">处置</el-button>
    </el-button-group>
</el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="receiverecordList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="患者姓名" align="center" prop="patientName" >
        <template slot-scope="scope">
        <el-tooltip class="item" effect="dark" content="点击查看患者看病流程" placement="bottom">
            <router-link :to="'/medical/action/index/' + scope.row.recordsId" class="link-type">
            	<span>{{scope.row.patientName}}</span>
            </router-link>
        </el-tooltip>
    </template>
      </el-table-column>
      <el-table-column label="科室" align="center" prop="deptName" />
      <el-table-column label="医生" align="center" prop="nickName"/>
      <el-table-column label="接诊状态" align="center" prop="recordsStatus">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.records_status" :value="scope.row.recordsStatus"/>
        </template>
      </el-table-column>
      <el-table-column label="接诊时间" width="150" align="center" prop="recordsCreatedate">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.recordsCreatedate, '{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
      
      <el-table-column label="挂号顺序号" align="center" prop="recordsNo" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <router-link 
          v-if="scope.row.recordsStatus==2"
          :to="'/medical/result/index/' + scope.row.recordsId" class="link-type">
            	<span>诊断</span>
            </router-link>
            <router-link 
          v-if="scope.row.recordsStatus==4"
          :to="'/medical/Resulthandle/index/' + scope.row.recordsId" class="link-type">
            	<span>处方</span>
            </router-link>
      
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

    <!-- 检验检查对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="700px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
       <el-row>
        <el-col :span="6">
          <el-form-item label="患者姓名" prop="patientName">
          <el-input v-model="form.patientName" readonly="readonly" />
        </el-form-item>
        </el-col>
        <el-col :span="15">
          <el-form-item label="药房名称" v-if="title=='处置药方'">
            <el-select style="width:100%;" 
        v-model="form.drugsId"
        clearable
        filterable
        remote
        reserve-keyword
        placeholder="请输入药品拼音关键词"
        :remote-method="selectDrugs"
        :loading="loading">
            <el-option v-for="dr in drugsInfo"
            :key="dr.drugsId"
            :label="dr.drugsName"
            :value="dr.drugsId"
            />
    </el-select>
          </el-form-item>
          <el-form-item label="消费事项" v-if="title!='处置药方'" >
          <el-select style="width:120px" v-model="form.serviceTypeId" placeholder="请选择消费事项" @change="queryServiceProjects">
            <el-option
              v-for="st in serviceTypes"
              :key="st.servicetypeId"
              :label="st.servicetypeName"
              :value="st.servicetypeId"
            ></el-option>
          </el-select>
          <el-select style="width:180px" v-model="form.serviceProjectId" placeholder="请选择消费明细">
            <el-option
              v-for="sp in serviceProjects"
              :key="sp.serviceprojectId"
              :label="sp.serviceprojectName"
              :value="sp.serviceprojectId"
            ></el-option>
          </el-select>
        </el-form-item>
        </el-col>
        <el-col :span="3">
          <el-button style="margin-left: 10px;" type="primary" @click="addPartName">添加</el-button>
        </el-col>
       </el-row>
      </el-form>
      <!-- 消费列表 -->
      <el-table v-loading="loading" :data="partLst" height="200px" @selection-change="handleSelectionChange">
        <el-table-column label="序号" type="index" :index="indexMethod"/>
        <el-table-column label="消费事项" align="center" prop="serviceprojectName" width="180px" />
        <el-table-column label="费用" align="center" prop="serviceprojectPrice" />
        <el-table-column label="数量" align="center" prop="number">
          <template slot-scope="scope">
            <el-input-number v-model="scope.row.number" @change="(curremtValue,oldValue)=>{numberChange(curremtValue,oldValue,scope.row)}" size="mini" :min="1" style="width:90px" ></el-input-number>
          </template>
        </el-table-column>
        <el-table-column label="操作" align="center" class-name="small-padding fixed-width" width="160px">
          <template slot-scope="scope">
            <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handlePartDelete(scope.row.serviceprojectId)"
            v-hasPermi="['medical:receiverecord:remove']"
          >删除</el-button>
          </template>
        </el-table-column>
      </el-table>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { addDrugs,addLisAndPacs,listReceiverecord, getReceiverecord, delReceiverecord, addReceiverecord, updateReceiverecord } from "@/api/medical/receiverecord";
import{ queryServiceTypeByDetials } from "@/api/medical/type";
import { queryServiceProjectBySid } from "@/api/medical/project";
import{ accAdd,accSub } from '@/utils/number';
import{ selectDrugsByPingyin } from  '@/api/medical/drugsinfo'
export default {
  name: "Receiverecord",
  dicts: ['records_status', 'records_diagnostic_status', 'records_ampm'],
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
      // 接诊记录表格数据
      receiverecordList: [],
      //检验检查对话框的消费事项表格数据
      partLst:[],
      //消费大类数据
      serviceTypes:[],
      //消费小类数据
      serviceProjects:[],
      //药品数据
      drugsInfo:[],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        recordsDoctorid: null,
        recordsDiagnosticStatus: null,
        recordsStatus: null,
        recordsCreatedate: null,
        recordsAmpm: null,
        recordsDeptid: null,
        recordsNo: null,
        outpatientId: null,
        operatorId: null,
        enterdate: null,
        patientName:null
      },
      // 表单参数
      form: {
        serviceProjectId:'',
        serviceTypeId:'',
        drugsId:''
      },
      // 表单校验
      rules: {
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
      /**根据药品拼音关键字模糊查询药品信息 */
      selectDrugs(kw){
        if(kw !=""){
          selectDrugsByPingyin(kw).then(response=>{
            this.drugsInfo=response.data;
          })
        }else
        this.drugsInfo=[];
      },
      /**删除消费事项列表中的数据删除事件 */
      handlePartDelete(serviceProjectId){
        this.$confirm('确认删除吗？' , '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        console.log(serviceProjectId)
        this.partLst = this.partLst.filter(s => s.serviceprojectId != serviceProjectId)
      }).catch(()=>{})
      },
      /**消费事项计数器数量加减改变事件 */
      numberChange(curremtValue,oldValue,r){
        //计算消费事项的单价
        let price =r.serviceprojectPrice / oldValue
        //根据当前值与旧值比较来是否加减消费事项的价格
        if(curremtValue>oldValue){
          r.serviceprojectPrice =accAdd(r.serviceprojectPrice,price)
        }else{
          r.serviceprojectPrice=accSub(r.serviceprojectPrice,price)
        }
      },
      /**添加消费事项 */
      addPartName(){
        if(this.title!="处置药方"){
          //判断是否选择了消费事项
        if(this.form.serviceProjectId==""){
          this.$modal.msgWarning("请选择消费事项！");
          return false;
        }  
        }else{
          //判断是否选择了消费事项
          if(this.form.drugsId==""){
          this.$modal.msgWarning("请选择处方药品！");
          return false;
        }  
        }
          
        //用于获取小类的消费事项
        let rs = ""
        let partName = ""
        //判断是否是处置
        if(this.title=="处置药方"){
          rs = this.drugsInfo.find(s=>{
        return s.drugsId== this.form.drugsId});
        //从rs结果中筛选出指定的字段结果
        partName = (({drugsId,drugsName,drugsOutpay})=>({serviceprojectId:drugsId, serviceprojectName:drugsName,serviceprojectPrice:drugsOutpay}))(rs);
        }else{
          rs = this.serviceProjects.find(s=>{
        return s.serviceprojectId== this.form.serviceProjectId});
     
        //从rs结果中筛选出指定的字段结果
         partName = (({serviceprojectId,serviceprojectName,serviceprojectPrice})=>({serviceprojectId,serviceprojectName,serviceprojectPrice}))(rs);
        }

        //判断消费事项列表中是否存在相同的检验记录
        //1）如果存在，相同的检验记录数量+1
        //2）如果不存在，则直接加入到消费列表中
        if(this.partLst.length<=0){
            //消费事项初始化数量为1
            partName["number"]=1;
            //将结果添加到列表数据中
            this.partLst.push(partName)
        }else{
          //判断列表中是否已经存在了消费事项
          //findIndex:>=0 存在 -1 不存在
         let index= this.partLst.findIndex(s =>s.serviceprojectId ==partName.serviceprojectId)
          if(index>=0){
            //循环遍历消费事项
            this.partLst.forEach(items=>{
              if(items.serviceprojectId == partName.serviceprojectId){
                //数量加一
                items.number=items.number+1;
                //价格翻倍
               items.serviceprojectPrice = accAdd(items.serviceprojectPrice,partName.serviceprojectPrice)
              }
            })
          }else{
            //消费事项初始化数量为1
            partName["number"]=1;
            //将结果添加到列表数据中
            this.partLst.push(partName)
          }
        }
      },
      /**根据消费大类查询消费事项 */
      queryServiceProjects(serviceId){
        //先清空消费原有数据
        this.serviceProjects=[],
        //根据消费大类id查询子类数据
        queryServiceProjectBySid(serviceId).then(response=>{
          this.serviceProjects=response.data;
        })
      },
    /**打开检验检查对话框type=1 lis检验 ，type=2 pacs检查*/
    openserviceType(type){
      //判断是否是接诊状态
      if(this.row.recordsStatus!=2){
        this.$modal.msgWarning("只有接诊状态才能进行检验");
        return false
      }
      this.title=type==3?"处置药方":type==1?"检验项目":"检查项目";
      this.open=true;
      //清空partLst
      this.partLst=[];
      //初始化数据绑定
      this.form={
        outpatientId:this.row.outpatientId,
        patientName:this.row.patientName,
        recordsId:this.row.recordsId,
        serviceProjectId:"",
        serviceTypeId:"",
        drugsId:""
      }
      if(type!=3){
        //绑定大类的下拉框数据
        queryServiceTypeByDetials(type==1?"lis":"pacs").then(response =>{
        this.serviceTypes=response.data;
      })
      }
     
    },
    /**接诊和顺延操作 */
    handleRecordsStatus(status){
      let params={
        "recordsStatus" : status,
        "recordsId" : this.row.recordsId
      }
      if(status==2){
        //如果当前已接诊，不可重复接诊
        if(this.row.recordsStatus==2){
          this.$modal.msgWarning("已接诊，不可重复接诊")
          return false;
        }
        //如果接诊已结束，不能再次接诊
        if(this.row.recordsStatus==4){
          this.$modal.
          msgWarning("接诊已结束，不能再次接诊")
          return false;
        }
        params["recordsCreatedate"] =new Date().toLocaleString().replaceAll("/","-")
      }else{
       
      }
      let s = (status==2?'接诊':'顺延')
      this.$confirm('患者【'+this.row.patientName+'】确认'+s+'吗？', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          updateReceiverecord(params).then(response=>{
            console.log(response.data)
            this.$modal.msgWarning(s+"成功");
            this.getList();

          })
        }).catch(() => {});
    },

    /** 查询接诊记录列表 */
    getList() {
      this.loading = true;
      listReceiverecord(this.queryParams).then(response => {
        this.receiverecordList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.partLst=[];
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        recordsId: null,
        outpatientId: null,
        patientName: null,
        serviceTypeId: null,
        serviceProjectId:null,
        drugsId:null
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
      this.ids = selection.map(item => item.recordsId)
      this.single = selection.length!==1
      this.multiple = !selection.length
      //获取当前选中行对象
    this.row=selection[0]
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加接诊记录";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const recordsId = row.recordsId || this.ids
      getReceiverecord(recordsId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改接诊记录";
      });
    },
    /** 添加检验检查 */
    submitForm() {
 //判断消费列表是否存在记录
  if(this.partLst.length==0){
        this.$modal.msgWarning("请添加检验/检查/处置的消费记录！")
        return false;
      }
      let params={
        "partLst":this.partLst,
        "outpatientId":this.row.outpatientId,
        "patientId":this.row.patientId,
        "recordsDoctorid":this.row.recordsDoctorid,
        "actionType":this.title=="检验项目"?"2":(this.title=="处置药方"?"3":"1"),
        "actionPatienttype":"1",
        "recordsId":this.row.recordsId
      }
       console.log(params)
      if(params.actionType!="3"){
        addLisAndPacs(params).then(response=>{
       this.$modal.msgSuccess("保存成功！")
       this.open=false;
        this.getList()
      });
      }else{
        addDrugs(params).then(response =>{
          this.$modal.msgSuccess("保存成功！")
       this.open=false;
        this.getList()
        })
      }
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const recordsIds = row.recordsId || this.ids;
      this.$modal.confirm('是否确认删除接诊记录编号为"' + recordsIds + '"的数据项？').then(function() {
        return delReceiverecord(recordsIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('medical/receiverecord/export', {
        ...this.queryParams
      }, `receiverecord_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
