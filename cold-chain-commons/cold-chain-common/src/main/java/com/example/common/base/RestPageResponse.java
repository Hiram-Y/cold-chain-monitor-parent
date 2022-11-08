package com.example.common.base;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author yuelimin
 * @version 1.0.0
 * @since 11
 */
@Data
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RestPageResponse<T> implements Serializable {

    private static final long serialVersionUID = -5970554385331690822L;

    /**
     * 总条数
     */
    private Long totalCount;

    /**
     * 每页条数
     */
    private Long pageSize;

    /**
     * 总页数
     */
    private Long totalPage;

    /**
     * 当前页数
     */
    private Long currentPage;

    /**
     * 分页结果
     */
    private T pageResult;
}
