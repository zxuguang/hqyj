// 查询列表接口
const getCategoryPage = (params) => {
  return $axios({
    url: '/category/page',
    method: 'get',
    params
  })
}
const getCategoryPage1 = (params) => {
  return $axios({
    url: '/category/page1',
    method: 'get',
    params
  })
}

// 编辑页面反查详情接口
const queryCategoryById = (id) => {
  return $axios({
    url: `/category/${id}`,
    method: 'get'
  })
}

// 删除当前列的接口
const deleCategory = (ids) => {
  return $axios({
    url: '/category',
    method: 'delete',
    params: { ids }
  })
}
const deleCategory1 = (ids) => {
  return $axios({
    url: '/category/delete',
    method: 'delete',
    params: { ids }
  })
}

// 修改接口
const editCategory = (params) => {
  return $axios({
    url: '/category',
    method: 'put',
    data: { ...params }
  })
}
//修改职位
const editCategory1 = (params) => {
  return $axios({
    url: '/category/edit',
    method: 'put',
    data: { ...params }
  })
}

// 新增接口
const addCategory = (params) => {
  return $axios({
    url:'/workers',
    method: 'post',
    data: { ...params }
  })


  const addWorkers = (params) =>{
    return $axios({
      url:'/workers',
      method: 'post',
      data: { ...params }
    })
  }

}
const addCategory1 = (params) => {
  return $axios({
    url:'/workers/add',
    method: 'post',
    data: { ...params }
  })


  const addWorkers = (params) =>{
    return $axios({
      url:'/workers/add',
      method: 'post',
      data: { ...params }
    })
  }





}