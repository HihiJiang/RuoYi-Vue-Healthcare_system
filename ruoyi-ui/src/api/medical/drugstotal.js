import request from '@/utils/request'

// 查询取药记录列表
export function listDrugstotal(query) {
  return request({
    url: '/medical/drugstotal/list',
    method: 'get',
    params: query
  })
}

// 查询取药记录详细
export function getDrugstotal(totalId) {
  return request({
    url: '/medical/drugstotal/' + totalId,
    method: 'get'
  })
}

// 新增取药记录
export function addDrugstotal(data) {
  return request({
    url: '/medical/drugstotal',
    method: 'post',
    data: data
  })
}

// 修改取药记录
export function updateDrugstotal(data) {
  return request({
    url: '/medical/drugstotal',
    method: 'put',
    data: data
  })
}

// 删除取药记录
export function delDrugstotal(totalId) {
  return request({
    url: '/medical/drugstotal/' + totalId,
    method: 'delete'
  })
}
