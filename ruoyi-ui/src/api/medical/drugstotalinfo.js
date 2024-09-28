import request from '@/utils/request'

// 查询取药信息列表
export function listDrugstotalinfo(query) {
  return request({
    url: '/medical/drugstotalinfo/list',
    method: 'get',
    params: query
  })
}

// 查询取药信息详细
export function getDrugstotalinfo(totalinfoId) {
  return request({
    url: '/medical/drugstotalinfo/' + totalinfoId,
    method: 'get'
  })
}

// 新增取药信息
export function addDrugstotalinfo(data) {
  return request({
    url: '/medical/drugstotalinfo',
    method: 'post',
    data: data
  })
}

// 修改取药信息
export function updateDrugstotalinfo(data) {
  return request({
    url: '/medical/drugstotalinfo',
    method: 'put',
    data: data
  })
}

// 删除取药信息
export function delDrugstotalinfo(totalinfoId) {
  return request({
    url: '/medical/drugstotalinfo/' + totalinfoId,
    method: 'delete'
  })
}
