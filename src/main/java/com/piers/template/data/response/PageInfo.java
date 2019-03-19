package com.piers.template.data.response;


import lombok.Data;

/**
 * @author piers
 */
@Data
class PageInfo {
    int pageSize;
    int totalPage;
    long total;

    public PageInfo(int pageSize, long total) {
        this.pageSize = pageSize;
        this.total = total;
        if(pageSize > 0) {
            if(total%pageSize == 0) {
                this.totalPage = (int)(total / pageSize);
            }else{
                this.totalPage = (int)(total / pageSize) + 1;
            }
        }
    }
}
