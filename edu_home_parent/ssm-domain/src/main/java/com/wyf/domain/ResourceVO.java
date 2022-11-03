package com.wyf.domain;

// 接收资源列表的分页查询和多条件查询
public class ResourceVO {
    // 当前页
    private Integer currentPage;
    // 每页显示的条数
    private Integer pageSize;
    // 资源的名称
    private String name;
    // 资源的分类id
    private Integer categoryId;
    // 资源路径
    private String url;

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
