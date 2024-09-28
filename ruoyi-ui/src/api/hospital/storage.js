import request from '@/utils/request'

// 查询药库库存列表
export function listStorage(query) {
  return request({
    url: '/hospital/storage/list',
    method: 'get',
    params: query
  })
}

// 查询药库库存详细
export function getStorage(storageId) {
  return request({
    url: '/hospital/storage/' + storageId,
    method: 'get'
  })
}

// 新增药库库存
export function addStorage(data) {
  return request({
    url: '/hospital/storage',
    method: 'post',
    data: data
  })
}

// 修改药库库存
export function updateStorage(data) {
  return request({
    url: '/hospital/storage',
    method: 'put',
    data: data
  })
}

// 删除药库库存
export function delStorage(storageId) {
  return request({
    url: '/hospital/storage/' + storageId,
    method: 'delete'
  })
}
