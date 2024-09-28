import request from '@/utils/request'

// 查询pacs影像信息列表
export function listPacs(query) {
  return request({
    url: '/medical/pacs/list',
    method: 'get',
    params: query
  })
}

// 查询pacs影像信息详细
export function getPacs(pacsId) {
  return request({
    url: '/medical/pacs/' + pacsId,
    method: 'get'
  })
}

// 新增pacs影像信息
export function addPacs(data) {
  return request({
    url: '/medical/pacs',
    method: 'post',
    data: data
  })
}

// 修改pacs影像信息
export function updatePacs(data) {
  return request({
    url: '/medical/pacs',
    method: 'put',
    data: data
  })
}

// 删除pacs影像信息
export function delPacs(pacsId) {
  return request({
    url: '/medical/pacs/' + pacsId,
    method: 'delete'
  })
}
