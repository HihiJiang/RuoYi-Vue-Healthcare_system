import request from '@/utils/request'

// 查询床位记录列表
export function listBed(query) {
  return request({
    url: '/hospital/bed/list',
    method: 'get',
    params: query
  })
}

// 查询床位记录详细
export function getBed(bedId) {
  return request({
    url: '/hospital/bed/' + bedId,
    method: 'get'
  })
}

// 新增床位记录
export function addBed(data) {
  return request({
    url: '/hospital/bed',
    method: 'post',
    data: data
  })
}

// 修改床位记录
export function updateBed(data) {
  return request({
    url: '/hospital/bed',
    method: 'put',
    data: data
  })
}

// 删除床位记录
export function delBed(bedId) {
  return request({
    url: '/hospital/bed/' + bedId,
    method: 'delete'
  })
}

// 查询患者档案信息列表
export function listArchives(query) {
  return request({
    url: '/medical/archives/list',
    method: 'get',
    params: query
  })
}

// 修改住院登记
export function updateSplitbed(data) {
  return request({
    url: '/hospital/splitbed',
    method: 'put',
    data: data
  })
}