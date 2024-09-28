import request from '@/utils/request'

// 查询住院治疗处方列表
export function listPrescription(query) {
  return request({
    url: '/hospital/prescription/list',
    method: 'get',
    params: query
  })
}

// 查询住院治疗处方详细
export function getPrescription(presId) {
  return request({
    url: '/hospital/prescription/' + presId,
    method: 'get'
  })
}

// 新增住院治疗处方
export function addPrescription(data) {
  return request({
    url: '/hospital/prescription',
    method: 'post',
    data: data
  })
}

// 修改住院治疗处方
export function updatePrescription(data) {
  return request({
    url: '/hospital/prescription',
    method: 'put',
    data: data
  })
}

// 删除住院治疗处方
export function delPrescription(presId) {
  return request({
    url: '/hospital/prescription/' + presId,
    method: 'delete'
  })
}


// 查询药房库存列表
export function listPharmacy(query) {
  return request({
    url: '/hospital/pharmacy/list',
    method: 'get',
    params: query
  })
}

// 查询医疗服务类型列表
export function listType(query) {
  return request({
    url: '/medical/type/list',
    method: 'get',
    params: query
  })
}