import request from '@/utils/request'

// 查询门诊卡充值取现记录列表
export function listRecord(query) {
  return request({
    url: '/medical/record/list',
    method: 'get',
    params: query
  })
}

// 查询门诊卡充值取现记录详细
export function getRecord(rechargeId) {
  return request({
    url: '/medical/record/' + rechargeId,
    method: 'get'
  })
}

// 新增门诊卡充值取现记录
export function addRecord(data) {
  return request({
    url: '/medical/record',
    method: 'post',
    data: data
  })
}

// 修改门诊卡充值取现记录
export function updateRecord(data) {
  return request({
    url: '/medical/record',
    method: 'put',
    data: data
  })
}

// 删除门诊卡充值取现记录
export function delRecord(rechargeId) {
  return request({
    url: '/medical/record/' + rechargeId,
    method: 'delete'
  })
}
