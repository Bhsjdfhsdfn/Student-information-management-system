package com.dao;

import java.io.Serializable;
import java.util.List;

import com.app.bean.bo.Po;
import com.app.bean.bo.WherePrams;

public interface BaseDao<T extends Po, PK extends Serializable>
{
    /**
     * 添加不为空的记录（只将不为空字段入库，效率高）
     * 
     * @param po
     * @return 受改变的记录数
     */
    public int addLocal(T po);

    /**
     * 通过主键获取某个记录
     * 
     * @param id
     *            主键
     * @return PO
     */
    public T get(PK id);

    /**
     * 条件获取一条记录
     * 
     * @param t
     * @param 条件表达式
     * @return PO
     */
    public T get(WherePrams where);

    /**
     * 更新不为null的PO字段
     * 
     * @param po
     * @return 受影响的行数
     */
    public int updateLocal(T po);

    /**
     * 删除某个记录
     * 
     * @param id
     *            主键
     * @return 受影响的行数
     */
    public int del(PK id);

    /**
     * 条件查询列表
     * 
     * @param where
     *            条件表达式
     * @return PO列表
     */
    public List<T> list(WherePrams where);

}
