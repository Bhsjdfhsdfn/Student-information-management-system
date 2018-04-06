package com.dao;

import hxk.util.DBManager;
import hxk.util.GenericsUtils;
import hxk.util.SqlUtil;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.app.bean.bo.Po;
import com.app.bean.bo.Pram;
import com.app.bean.bo.WherePrams;

public class BaseDaoImpl<T extends Po, PK extends Serializable> implements
        BaseDao<T, PK>
{

    private Class<T> entityClass;

    private String tableName;

    private SqlUtil sqlUtil;

    private List<Pram> selectSqlParms;
    
    protected DBManager db=new DBManager();

    @SuppressWarnings({"unchecked", "static-access"})
    public BaseDaoImpl()
    {
        super();
        this.sqlUtil = new SqlUtil();
        this.entityClass = (Class<T>) GenericsUtils
                .getSuperClassGenricType(this.getClass());
        System.out.println(this.getClass() + "    " + this.entityClass);
        this.sqlUtil.getPramList(this.entityClass);
        this.selectSqlParms = this.sqlUtil
                .getPramListOfSelect(this.entityClass);
        this.tableName = this.sqlUtil.getTableName(this.entityClass);

    }

    public int addLocal(T po)
    {
        String sql = "insert into " + tableName + " (";
        String prams = "";
        String values = "";
        List<Pram> pramList = SqlUtil.getPramList(po);
        int index = 0;
        for (int i = 0; i < pramList.size(); i++)
        {
            if (pramList.get(i).getValue() == null
                    || (pramList.get(i).getValue() + "").equals("0"))
            {
                continue;
            }
            else
            {
                if (index > 0)
                {
                    prams += ",";
                    values += ",";
                }
                prams += pramList.get(i).getFile();
                Object value = pramList.get(i).getValue();
                if (value instanceof byte[])
                {
                    values += "'" + new String((byte[]) value) + "'";
                }
                else if (value instanceof Boolean)
                {
                    values += "'" + ((Boolean) value == true ? 1 : 0) + "'";
                }
                else
                {
                    values += "'" + value + "'";
                }

                index++;
            }
        }
        sql += prams + ") values (" + values + ");";

        SqlUtil.setFileValue(po, "id", nextId());
        System.out.println(sql);
        return db.executeUpdate(sql);
    }

    public int nextId()
    {
        String sql = "SELECT auto_increment FROM information_schema.`TABLES` WHERE TABLE_NAME='"
                + tableName + "' AND TABLE_SCHEMA=(select database())";
        ResultSet mResultSet = db.getResultSet(sql);
        int idVal = 1;
        try
        {
            while (mResultSet.next())
            {
                idVal = mResultSet.getInt(1);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return idVal;

    }

    public T get(PK id)
    {
        String sql = "select ";
        for (int i = 0; i < selectSqlParms.size(); i++)
        {
            sql += selectSqlParms.get(i).getFile();
            if (i < selectSqlParms.size() - 1)
            {
                sql += ",";
            }
            else
            {
                sql += " ";
            }
        }
        sql += " from " + tableName + " where id='" + id + "';";
        Map<String, Object> resultMap = db.ResultToMap(sql);
        return handleResult(resultMap, this.entityClass);
    }

    private T handleResult(Map<String, Object> resultMap, Class<T> tClazz)
    {
        if (null == resultMap)
        {
            return null;
        }
        T t = null;
        try
        {
            t = tClazz.newInstance();
        }
        catch (InstantiationException e)
        {
            System.out.println(e);
        }
        catch (IllegalAccessException e)
        {
            System.out.println(e);
        }
        for (Map.Entry<String, Object> entry : resultMap.entrySet())
        {
            String key = entry.getKey();
            Serializable val = (Serializable) entry.getValue();
            try
            {
                SqlUtil.setFileValue(t, key, val);
            }
            catch (Exception e)
            {
                System.out.println(e);
            }
        }
        return t;
    }

    public T get(WherePrams where)
    {
        String sql = "select ";
        for (int i = 0; i < selectSqlParms.size(); i++)
        {
            sql += selectSqlParms.get(i).getFile();
            if (i < selectSqlParms.size() - 1)
            {
                sql += ",";
            }
            else
            {
                sql += " ";
            }
        }
        sql += "from " + tableName + where.getWherePrams() + ";";
        Map<String, Object> resultMap = db.ResultToMap(sql);
        return handleResult(resultMap, this.entityClass);
    }

    @SuppressWarnings("static-access")
    public int updateLocal(T po)
    {
        Serializable id = sqlUtil.getFileValue(po, "id");
        if (null == id)
        {
            return 0;
        }
        String sql = "update " + tableName + " set ";
        List<Pram> prams = sqlUtil.getPramList(po);
        for (int i = 0; i < prams.size(); i++)
        {
            if (null != prams.get(i).getValue())
            {
                sql += prams.get(i).getFile() + "=";
                Object value = prams.get(i).getValue();
                if (value instanceof byte[])
                {
                    sql += "'" + new String((byte[]) value) + "'";
                }
                else if (value instanceof Boolean)
                {
                    sql += "'" + ((Boolean) value == true ? 1 : 0) + "'";
                }
                else
                {
                    sql += "'" + value + "'";
                }
                System.out.println(i + " " + (prams.size() - 1));
                if (i < prams.size() - 1)
                {
                    sql += ",";
                }
            }
        }
        sql += " where id=" + id + ";";
        System.out.println(sql);
        return db.executeUpdate(sql);
    }

    public int del(PK id)
    {
        String sql = "delete from " + tableName + " where id=" + id;
        return db.executeUpdate(sql);
    }

    public List<T> list(WherePrams where)
    {
        String sql = "select ";
        for (int i = 0; i < selectSqlParms.size(); i++)
        {
            sql += selectSqlParms.get(i).getFile();
            if (i < selectSqlParms.size() - 1)
            {
                sql += ",";
            }
            else
            {
                sql += " ";
            }
        }
        sql += "from " + tableName + where.getWherePrams() + ";";
        List<Map<String, Object>> selectList = db
                .ResultToListMap(sql);
        List<T> list = new ArrayList<T>();
        for (Map<String, Object> map : selectList)
        {
            T t = handleResult(map, this.entityClass);
            list.add(t);
        }
        return list;
    }

}
