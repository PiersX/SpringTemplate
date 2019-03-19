package com.piers.template.data.response;

import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author piers
 */
@Data
class PageData<T> {
    List<T> list;
    PageInfo page;

    public PageData(List<T> list, PageInfo page) {
        this.list = list;
        this.page = page;
    }

    public PageData(Page<T> tPage) {
        list = tPage.getContent();
        page = new PageInfo(tPage.getSize(), tPage.getTotalElements());
    }

//    public PageData(com.github.pagehelper.PageInfo<T> tPage) {
//        list = tPage.getList();
//        page = new PageInfo(tPage.getSize(), tPage.getTotal());
//    }
}
