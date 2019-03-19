package com.piers.template.data.response;

import com.piers.template.utils.Const;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Page的基础返回类
 *
 * @author piers
 * @date 19-3-11 下午1:41
 */
@Data
public class PageResponse<T> {
    /**
     * 是否成功
     */
    int resultCode;

    /**
     * 说明
     */
    String resultMsg;

    /**
     * 返回数据
     */
    PageData<T> data;


    public PageResponse(int resultCode) {
        this.resultCode = resultCode;
        //setResultMsg(ResultUtil.getResultMsg(resultCode));
    }

    public PageResponse(List<T> list) {
        if(list != null) {
            PageInfo pageInfo = new PageInfo(Const.PAGE_SIZE, list.size());
            this.data = new PageData(list, pageInfo);
        }
    }

    public PageResponse(Page<T> page) {
        if(page != null) {
            this.data = new PageData(page);
        }
    }

}
