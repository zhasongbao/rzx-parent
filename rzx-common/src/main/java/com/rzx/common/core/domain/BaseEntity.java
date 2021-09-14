package com.rzx.common.core.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;


/**
 * Entity基类
 *
 * @author fm
 */
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = 3032650369548934808L;

    /**
     * 搜索值
     */
    @Setter
    @Getter
    @TableField(exist = false)
    private String searchValue;

    /**
     * 创建者
     */
    @Setter
    @Getter
    @TableField(value = "create_by")
    private String createBy;

    /**
     * 创建时间
     */
    @Getter
    @Setter
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField(value = "create_time")
    private LocalDateTime createTime;

    /**
     * 更新者
     */
    @Setter
    @Getter
    @TableField(value = "update_by")
    private String updateBy;

    /**
     * 更新时间
     */
    @Getter
    @Setter
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField(value = "update_time")
    private LocalDateTime updateTime;

    /**
     * 备注
     */
    @TableField(exist = false)
    @Setter
    @Getter
    private String remark;

    public Map<String, Object> getParams() {
        if (params == null) {
            params = new HashMap<>(2);
        }
        return params;
    }

    /**
     * 请求参数
     */
    @TableField(exist = false)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Map<String, Object> params;

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }
}
