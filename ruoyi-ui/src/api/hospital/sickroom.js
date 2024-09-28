import request from '@/utils/request'

// 查询病房记录列表
export function listSickroom(query) {
  return request({
    url: '/hospital/sickroom/list',
    method: 'get',
    params: query
  })
}

// 查询病房记录详细
export function getSickroom(roomId) {
  return request({
    url: '/hospital/sickroom/' + roomId,
    method: 'get'
  })
}

// 新增病房记录
export function addSickroom(data) {
  return request({
    url: '/hospital/sickroom',
    method: 'post',
    data: data
  })
}

// 修改病房记录
export function updateSickroom(data) {
  return request({
    url: '/hospital/sickroom',
    method: 'put',
    data: data
  })
}

// 删除病房记录
export function delSickroom(roomId) {
  return request({
    url: '/hospital/sickroom/' + roomId,
    method: 'delete'
  })
}


export function listBed(query) {
  return request({
    url: '/hospital/bed/list',
    method: 'get',
    params: query
  })
}