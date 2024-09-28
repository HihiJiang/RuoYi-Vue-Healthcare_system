import request from '@/utils/request'

// 查询门诊卡信息列表
export function listCard(query) {
  return request({
    url: '/medical/card/list',
    method: 'get',
    params: query
  })
}

// 建档办卡
export function addJiandang(data) {
  return request({
    url: '/medical/card/addJiandang',
    method: 'post',
    data: data
  })
}


// 查询门诊卡信息详细
export function getCard(outpatientId) {
  return request({
    url: '/medical/card/' + outpatientId,
    method: 'get'
  })
}

// 新增门诊卡信息
export function addCard(data) {
  return request({
    url: '/medical/card',
    method: 'post',
    data: data
  })
}

// 修改门诊卡信息
export function updateCard(data) {
  return request({
    url: '/medical/card',
    method: 'put',
    data: data
  })
}

// 删除门诊卡信息
export function delCard(outpatientId) {
  return request({
    url: '/medical/card/' + outpatientId,
    method: 'delete'
  })
}


// 充值取现
export function addMoney(data) {
  return request({
    url: '/medical/card/addMoney',
    method: 'post',
    data: data
  })
}

// 动态绑定挂号科室
export function queryDeptsByDeptPid() {
  return request({
    url: '/system/dept/queryDeptsByDeptPid',
    method: 'get'
    
  })


}


  // 根据部门id获取挂号医生信息
  export function queryDoctorsByDetptId(deptId) {
    return request({
      url: '/medical/card/queryDoctorsByDetptId/'+deptId,
      method: 'get'  
    })
  }

  //挂号
export function doctorRegistered(data) {
  return request({
    url: '/medical/card/doctorRegistered',
    method: 'post',
    data: data
  })
}