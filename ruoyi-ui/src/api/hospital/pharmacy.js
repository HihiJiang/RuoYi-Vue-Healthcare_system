import request from '@/utils/request'

// 查询药房库存列表
export function listPharmacy(query) {
  return request({
    url: '/hospital/pharmacy/list',
    method: 'get',
    params: query
  })
}

// 查询药房库存详细
export function getPharmacy(pharmacyId) {
  return request({
    url: '/hospital/pharmacy/' + pharmacyId,
    method: 'get'
  })
}

// 新增药房库存
export function addPharmacy(data) {
  return request({
    url: '/hospital/pharmacy',
    method: 'post',
    data: data
  })
}

// 修改药房库存
export function updatePharmacy(data) {
  return request({
    url: '/hospital/pharmacy',
    method: 'put',
    data: data
  })
}

// 删除药房库存
export function delPharmacy(pharmacyId) {
  return request({
    url: '/hospital/pharmacy/' + pharmacyId,
    method: 'delete'
  })
}
