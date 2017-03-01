/**
 * Copyright© 2003-2016 浙江汇信科技有限公司, All Rights Reserved. <br/>
 */
package com.icinfo.lpsp.notebook.note.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.icinfo.framework.mybatis.mapper.annotation.Before;

import java.io.Serializable;
import javax.persistence.*;

/**
 * 描述:  NBOOK_CATEGORY 对应的实体类.<br>
 * WARNING：不是表中字段的属性必须加@Transient注解
 *
 * @author framework generator
 * @date 2016年11月15日
 */
@Table(name = "NBOOK_CATEGORY")
public class Category implements Serializable {
    /**
     * 唯一标识
     */
    @Id
    @Column(name = "ID")
    @Before
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "select replace(uuid(), '-', '')")
    private String id;

    /**
     * 笔记类型名称
     */
    @Column(name = "NC_NAME")
    private String name;

    /**
     * 排序
     */
    @Column(name = "NC_ORDER_NUMBER")
    private Integer orderNumber;

    private static final long serialVersionUID = 1L;

    /**
     * 获取唯一标识
     *
     * @return ID - 唯一标识
     */
    public String getId() {
        return id;
    }

    /**
     * 设置唯一标识
     *
     * @param id 唯一标识
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取笔记类型名称
     *
     * @return NC_NAME - 笔记类型名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置笔记类型名称
     *
     * @param name 笔记类型名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取排序
     *
     * @return NC_ORDER_NUMBER - 排序
     */
    public Integer getOrderNumber() {
        return orderNumber;
    }

    /**
     * 设置排序
     *
     * @param orderNumber 排序
     */
    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    @Override
    public boolean equals(Object obj) {
        Category category = (Category) obj;
        if (id.equals(category.id)) {
            return true;
        }
        return super.equals(obj);
    }
}