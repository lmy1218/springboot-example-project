package com.yang.api.pojo.vo;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.yang.api.pojo.dto.PagedQueryDto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

/**
 * @author Yang
 * @version 1.0.0
 * @description 自定义 Page
 * @createTime 2020年12月08日 16:24:00
 */
@JsonIgnoreProperties({"orders", "optimizeCountSql", "hitCount", "searchCount"})
public class ApiPageVo<T> extends Page<T> {

    private static final long serialVersionUID = 8545996863226528798L;

    /** 查询数据列表 */
    @JsonProperty(value = "items")
    protected List<T> records = Collections.emptyList();

    /** 总数 */
    protected long total;

    /** 每页显示条数，默认 10 */
    protected long size = 10;

    /** 当前页 */
    protected long current = 1;

    public ApiPageVo() {
        this(1, 10);
    }

    public ApiPageVo(PagedQueryDto queryDto) {
        this(queryDto.getPage(), queryDto.getLimit());
    }

    /**
     * 分页构造函数
     *
     * @param current 当前页
     * @param size 每页显示条数
     */
    public ApiPageVo(Integer current, Integer size) {
        this(current, size, 0);
    }

    public ApiPageVo(Integer current, Integer size, Integer total) {
        this(current, size, total, true);
    }

    public ApiPageVo(Integer current, Integer size, Integer total, boolean isSearchCount) {
        if (current != null && 1 < current) {
            this.current = current;
        }
        if (size != null && 1 <= size) {
            this.size = size;
        }
        this.total = total;
        this.isSearchCount = isSearchCount;
    }

    @Override
    public List<T> getRecords() {
        return this.records;
    }

    @Override
    public ApiPageVo<T> setRecords(List<T> records) {
        this.records = records;
        return this;
    }

    @Override
    public long getTotal() {
        return this.total;
    }

    @Override
    public ApiPageVo<T> setTotal(long total) {
        this.total = total;
        return this;
    }

    @Override
    public long getSize() {
        return this.size;
    }

    @Override
    public ApiPageVo<T> setSize(long size) {
        this.size = size;
        return this;
    }

    @Override
    public long getCurrent() {
        return this.current;
    }

    @Override
    public ApiPageVo<T> setCurrent(long current) {
        this.current = current;
        return this;
    }

    /**
     * 查找 order 中正序排序的字段数组
     *
     * @param filter 过滤器
     * @return 返回正序排列的字段数组
     */
    private String[] mapOrderToArray(Predicate<OrderItem> filter) {
        List<String> columns = new ArrayList<>(orders.size());
        orders.forEach(
                i -> {
                    if (filter.test(i)) {
                        columns.add(i.getColumn());
                    }
                });
        return columns.toArray(new String[0]);
    }

    /**
     * 移除符合条件的条件
     *
     * @param filter 条件判断
     */
    private void removeOrder(Predicate<OrderItem> filter) {
        for (int i = orders.size() - 1; i >= 0; i--) {
            if (filter.test(orders.get(i))) {
                orders.remove(i);
            }
        }
    }
}
