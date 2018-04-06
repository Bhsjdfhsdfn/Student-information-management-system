package hxk.util;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DBManager
{
    private Connection conn = null;

    private static Statement stmt = null;

    private static ResultSet rs = null;

    public DBManager()
    {
        String dri = "com.mysql.jdbc.Driver";
        String user = "root";
        String pass = "dxq0219";
        String url = "jdbc:mysql://127.0.0.1:3306/mydb?characterEncoding=utf-8";
        try
        {
            Class.forName(dri);
        }
        catch (Exception e)
        {
            System.out.println("Load Driver Error");
        }
        try
        {
            conn = DriverManager.getConnection(url, user, pass);
            if (conn != null)
            {
                stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                        ResultSet.CONCUR_UPDATABLE);
            }
        }
        catch (Exception e)
        {
            System.out.println("Connect DB Error");
        }
    }

    public ResultSet getResultSet(String sql)
    {
        try
        {
            rs = stmt.executeQuery(sql);
            if (rs != null)
            {
                return rs;
            }
            else
            {
                return null;
            }
        }
        catch (Exception e)
        {
            System.out.println("Get RecordSet Error!"+e);
        }
        return rs;
    }

   
    public void execute(String sql)
    {
        try
        {
            stmt.execute(sql);
        }
        catch (Exception e)
        {
            System.out.println("Execute DB Sql Error!" + e);
        }
    }

    public boolean isExist(ResultSet rs) throws SQLException
    {
        int result = 0;
        rs.next();
        result = rs.getInt(1);
        rs.close();
        if (result == 0)
            return false;
        else
            return true;
    }

    public void closeConnection()
    {
        try
        {
            conn.close();
        }
        catch (SQLException e)
        {
            System.out.println("Close DB Connection Error!" + e);
        }
    }

    public int executeUpdate(String sql)
    {
        int count = 0;
        try
        {
            count = stmt.executeUpdate(sql);
        }
        catch (Exception e)
        {
            System.out.println("Update DB Error!" + e);
        }
        return count;
    }

    /**
     * ��ResultSet����еļ�¼ӳ�䵽Map������.
     * 
     * @param fieldClassName
     *            ��JDBC API�е��������,
     * @param fieldName
     *            ���ֶ���
     * @param rs
     *            ��һ��ResultSet��ѯ���,
     * @param fieldValue
     *            Map����,���ڴ���һ����¼.
     * @throws SQLException
     */
    @SuppressWarnings({"unchecked"})
    private void _recordMappingToMap(String fieldClassName, String fieldName,
            ResultSet rs, Map fieldValue) throws SQLException
    {
        fieldName = fieldName.toLowerCase();
        // ���ȹ��򣺳������Ϳ�ǰ
        if (fieldClassName.equals("java.lang.String"))
        {
            String s = rs.getString(fieldName);
            if (rs.wasNull())
            {
                fieldValue.put(fieldName, null);
            }
            else
            {
                fieldValue.put(fieldName, s);
            }
        }
        else if (fieldClassName.equals("java.lang.Integer"))
        {
            int s = rs.getInt(fieldName);
            if (rs.wasNull())
            {
                fieldValue.put(fieldName, null);
            }
            else
            {
                fieldValue.put(fieldName, s);// ����jdk��Ҫ��װ��jdk1.5����Ҫ��װ
            }
        }
        else if (fieldClassName.equals("java.lang.Long"))
        {
            long s = rs.getLong(fieldName);
            if (rs.wasNull())
            {
                fieldValue.put(fieldName, null);
            }
            else
            {
                fieldValue.put(fieldName, s);
            }
        }
        else if (fieldClassName.equals("java.lang.Boolean"))
        {
            boolean s = rs.getBoolean(fieldName);
            if (rs.wasNull())
            {
                fieldValue.put(fieldName, null);
            }
            else
            {
                fieldValue.put(fieldName, s);
            }
        }
        else if (fieldClassName.equals("java.lang.Short"))
        {
            short s = rs.getShort(fieldName);
            if (rs.wasNull())
            {
                fieldValue.put(fieldName, null);
            }
            else
            {
                fieldValue.put(fieldName, s);
            }
        }
        else if (fieldClassName.equals("java.lang.Float"))
        {
            float s = rs.getFloat(fieldName);
            if (rs.wasNull())
            {
                fieldValue.put(fieldName, null);
            }
            else
            {
                fieldValue.put(fieldName, s);
            }
        }
        else if (fieldClassName.equals("java.lang.Double"))
        {
            double s = rs.getDouble(fieldName);
            if (rs.wasNull())
            {
                fieldValue.put(fieldName, null);
            }
            else
            {
                fieldValue.put(fieldName, s);
            }
        }
        else if (fieldClassName.equals("java.sql.Timestamp"))
        {
            java.sql.Timestamp s = rs.getTimestamp(fieldName);
            if (rs.wasNull())
            {
                fieldValue.put(fieldName, null);
            }
            else
            {
                fieldValue.put(fieldName, s);
            }
        }
        else if (fieldClassName.equals("java.sql.Date")
                || fieldClassName.equals("java.util.Date"))
        {
            java.util.Date s = rs.getDate(fieldName);
            if (rs.wasNull())
            {
                fieldValue.put(fieldName, null);
            }
            else
            {
                fieldValue.put(fieldName, s);
            }
        }
        else if (fieldClassName.equals("java.sql.Time"))
        {
            java.sql.Time s = rs.getTime(fieldName);
            if (rs.wasNull())
            {
                fieldValue.put(fieldName, null);
            }
            else
            {
                fieldValue.put(fieldName, s);
            }
        }
        else if (fieldClassName.equals("java.lang.Byte"))
        {
            byte s = rs.getByte(fieldName);
            if (rs.wasNull())
            {
                fieldValue.put(fieldName, null);
            }
            else
            {
                fieldValue.put(fieldName, new Byte(s));
            }
        }
        else if (fieldClassName.equals("[B") || fieldClassName.equals("byte[]"))
        {
            // byte[]������SQL Server��
            byte[] s = rs.getBytes(fieldName);
            if (rs.wasNull())
            {
                fieldValue.put(fieldName, null);
            }
            else
            {
                fieldValue.put(fieldName, s);
            }
        }
        else if (fieldClassName.equals("java.math.BigDecimal"))
        {
            BigDecimal s = rs.getBigDecimal(fieldName);
            if (rs.wasNull())
            {
                fieldValue.put(fieldName, null);
            }
            else
            {
                fieldValue.put(fieldName, s);
            }
        }
        else if (fieldClassName.equals("java.lang.Object")
                || fieldClassName.equals("oracle.sql.STRUCT"))
        {
            Object s = rs.getObject(fieldName);
            if (rs.wasNull())
            {
                fieldValue.put(fieldName, null);
            }
            else
            {
                fieldValue.put(fieldName, s);
            }
        }
        else if (fieldClassName.equals("java.sql.Array")
                || fieldClassName.equals("oracle.sql.ARRAY"))
        {
            java.sql.Array s = rs.getArray(fieldName);
            if (rs.wasNull())
            {
                fieldValue.put(fieldName, null);
            }
            else
            {
                fieldValue.put(fieldName, s);
            }
        }
        else if (fieldClassName.equals("java.sql.Clob"))
        {
            java.sql.Clob s = rs.getClob(fieldName);
            if (rs.wasNull())
            {
                fieldValue.put(fieldName, null);
            }
            else
            {
                fieldValue.put(fieldName, s);
            }
        }
        else if (fieldClassName.equals("java.sql.Blob"))
        {
            java.sql.Blob s = rs.getBlob(fieldName);
            if (rs.wasNull())
            {
                fieldValue.put(fieldName, null);
            }
            else
            {
                fieldValue.put(fieldName, s);
            }
        }
        else
        {// ���������κ�δ֪���͵Ĵ���
            Object s = rs.getObject(fieldName);
            if (rs.wasNull())
            {
                fieldValue.put(fieldName, null);
            }
            else
            {
                fieldValue.put(fieldName, s);
            }
        }
    }

    public Map<String, Object> ResultToMap(String sql)
    {
        Map<String, Object> valueMap = null;
        try
        {
            ResultSet rs = this.getResultSet(sql); // ִ��sql��ѯ��䣬���ز�ѯ��ݵĽ��
            ResultSetMetaData rsmd = rs.getMetaData();
            int fieldCount = rsmd.getColumnCount();
            while (rs.next())
            {
                valueMap = new LinkedHashMap<String, Object>();
                for (int i = 1; i <= fieldCount; i++)
                {
                    String fieldClassName = rsmd.getColumnClassName(i);
                    String fieldName = rsmd.getColumnName(i);
                    this._recordMappingToMap(fieldClassName, fieldName, rs,
                            valueMap);
                }
            }
            conn.close(); // �ر���ݿ�����
        }
        catch (SQLException e)
        {
            System.out.println("��ѯ���ʧ��" + e);
        }
        return valueMap;
    }

    public List<Map<String, Object>> ResultToListMap(String sql)
    {
        Map<String, Object> valueMap = null;
        List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
        try
        {
            ResultSet rs = this.getResultSet(sql); // ִ��sql��ѯ��䣬���ز�ѯ��ݵĽ��
            ResultSetMetaData rsmd = rs.getMetaData();
            int fieldCount = rsmd.getColumnCount();
            while (rs.next())
            {
                valueMap = new LinkedHashMap<String, Object>();
                for (int i = 1; i <= fieldCount; i++)
                {
                    String fieldClassName = rsmd.getColumnClassName(i);
                    String fieldName = rsmd.getColumnName(i);
                    this._recordMappingToMap(fieldClassName, fieldName, rs,
                            valueMap);
                }
                listMap.add(valueMap);
            }
            conn.close(); // �ر���ݿ�����
        }
        catch (SQLException e)
        {
            System.out.println("��ѯ���ʧ��" + e);
        }
        return listMap;
    }

    public static void main(String[] args) {
		System.out.println((double)((double)(770/10)/100));
	}

}