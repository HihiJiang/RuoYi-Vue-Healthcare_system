import request from '@/utils/request'

// 查询患者档案信息列表
export function listArchives(query) {
  return request({
    url: '/medical/archives/list',
    method: 'get',
    params: query
  })
}

// 查询患者档案信息详细
export function getArchives(patientId) {
  return request({
    url: '/medical/archives/' + patientId,
    method: 'get'
  })
}

// 新增患者档案信息
export function addArchives(data) {
  return request({
    url: '/medical/archives',
    method: 'post',
    data: data
  })
}

// 修改患者档案信息
export function updateArchives(data) {
  return request({
    url: '/medical/archives',
    method: 'put',
    data: data
  })
}

// 删除患者档案信息
export function delArchives(patientId) {
  return request({
    url: '/medical/archives/' + patientId,
    method: 'delete'
  })
}
