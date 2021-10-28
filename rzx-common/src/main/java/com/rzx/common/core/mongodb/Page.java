package com.rzx.common.core.mongodb;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 分页
 *
 * @author zy
 * @date 2020/01/21
 */
@ToString
@Data
public class Page implements Serializable {

    private static final long serialVersionUID = 8791879943778390534L;

    /**
     * 一页数据默认20条
     */
    private Integer pageSize = 10;

    /**
     * 当前页码
     */
    private Integer pageNum = 1;

    /**
     * 一共有多少条数据
     */
    private long total = 0;
}
