import request from '@/utils/request'

// 查询Lis检验信息列表
export function listLis(query) {
  return request({
    url: '/medical/lis/list',
    method: 'get',
    params: query
  })
}

// 查询Lis检验信息详细
export function getLis(lisId) {
  return request({
    url: '/medical/lis/' + lisId,
    method: 'get'
  })
}

// 新增Lis检验信息
export function addLis(data) {
  return request({
    url: '/medical/lis',
    method: 'post',
    data: data
  })
}

// 修改Lis检验信息
export function updateLis(data) {
  return request({
    url: '/medical/lis',
    method: 'put',
    data: data
  })
}

// 删除Lis检验信息
export function delLis(lisId) {
  return request({
    url: '/medical/lis/' + lisId,
    method: 'delete'
  })
}
