package hxk.util;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import com.app.bean.bo.AiyiIdTypeException;
import com.app.bean.bo.FieldName;
import com.app.bean.bo.Po;
import com.app.bean.bo.Pram;
import com.app.bean.bo.TableName;
import com.app.bean.bo.TempField;

public class SqlUtil
{

    public static List<Pram> getPramList(Po po)
    {
        List<Pram> list = new ArrayList<Pram>();
        Class<? extends Po> thisClass = po.getClass();
        Field[] fields = thisClass.getDeclaredFields();
        for (Field f : fields)
        {
            try
            {
                if (!f.getName().equalsIgnoreCase("ID")
                        && !f.isAnnotationPresent(TempField.class))
                {
                    String fName = f.getName();
                    if (f.isAnnotationPresent(FieldName.class))
                    {
                        String fieldName = f.getAnnotation(FieldName.class)
                                .name();
                        Pram pram = new Pram(fieldName, thisClass.getMethod(
                                "get" + fName.substring(0, 1).toUpperCase()
                                        + fName.substring(1)).invoke(po));
                        list.add(pram);
                    }
                    else
                    {
                        String fieldName = toTableString(fName);
                        Pram pram = new Pram(fieldName, thisClass.getMethod(
                                "get" + fName.substring(0, 1).toUpperCase()
                                        + fName.substring(1)).invoke(po));
                        list.add(pram);
                    }
                }
            }
            catch (NoSuchMethodException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            catch (SecurityException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            catch (IllegalAccessException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            catch (IllegalArgumentException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            catch (InvocationTargetException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return list;
    }

    public static List<Pram> getPramListOfSelect(Po po)
    {
        List<Pram> list = new ArrayList<Pram>();
        Class<? extends Po> thisClass = po.getClass();
        Field[] fields = thisClass.getDeclaredFields();
        for (Field f : fields)
        {
            try
            {
                if (!f.isAnnotationPresent(TempField.class))
                {
                    String fName = f.getName();
                    if (f.isAnnotationPresent(FieldName.class))
                    {
                        String fieldName = f.getAnnotation(FieldName.class)
                                .name();
                        Pram pram = new Pram(fieldName + " as " + fName,
                                thisClass.getMethod(
                                        "get"
                                                + fName.substring(0, 1)
                                                        .toUpperCase()
                                                + fName.substring(1))
                                        .invoke(po));
                        list.add(pram);
                    }
                    else
                    {
                        String fieldName = toTableString(fName);
                        Pram pram = new Pram(fieldName + " as " + fName,
                                thisClass.getMethod(
                                        "get"
                                                + fName.substring(0, 1)
                                                        .toUpperCase()
                                                + fName.substring(1))
                                        .invoke(po));
                        list.add(pram);
                    }
                }
            }
            catch (NoSuchMethodException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            catch (SecurityException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            catch (IllegalAccessException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            catch (IllegalArgumentException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            catch (InvocationTargetException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return list;
    }

    public static String getTableName(Po po)
    {
        Class<? extends Po> c = po.getClass();
        if (c.isAnnotationPresent(TableName.class))
        {
            return c.getAnnotation(TableName.class).name();
        }
        else
        {
            String className = po.getClass().getSimpleName();
            String tName = toTableString(className);
            String poName = tName.substring(tName.length() - 2, tName.length());
            if ("po".equals(poName))
            {
                tName = tName.substring(0, tName.length() - 3);
            }
            return tName;
        }

    }

    public static <T extends Po> List<Pram> getPramList(Class<T> po)
    {
        List<Pram> list = new ArrayList<Pram>();
        Class<? extends Po> thisClass = po;
        Field[] fields = thisClass.getDeclaredFields();
        try
        {
            Object o = thisClass.newInstance();
            for (Field f : fields)
            {
                if (!f.getName().equalsIgnoreCase("ID")
                        && !f.isAnnotationPresent(TempField.class))
                {
                    String fName = f.getName();
                    if (f.isAnnotationPresent(FieldName.class))
                    {
                        String fieldName = f.getAnnotation(FieldName.class)
                                .name();
                        Method get = thisClass.getMethod("get"
                                + fName.substring(0, 1).toUpperCase()
                                + fName.substring(1));
                        Object getValue = get.invoke(o);
                        Pram pram = new Pram(fieldName, getValue);
                        list.add(pram);
                    }
                    else
                    {
                        String fieldName = toTableString(fName);
                        Method get = thisClass.getMethod("get"
                                + fName.substring(0, 1).toUpperCase()
                                + fName.substring(1));
                        Object getValue = get.invoke(o);
                        Pram pram = new Pram(fieldName + fName, getValue);
                        list.add(pram);
                    }
                }
            }
        }
        catch (NoSuchMethodException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (SecurityException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (IllegalAccessException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (IllegalArgumentException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (InvocationTargetException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (InstantiationException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return list;
    }

    public static <T extends Po> List<Pram> getPramListOfSelect(Class<T> po)
    {
        List<Pram> list = new ArrayList<Pram>();
        Class<? extends Po> thisClass = po;
        Field[] fields = thisClass.getDeclaredFields();
        try
        {
            Object o = thisClass.newInstance();
            for (Field f : fields)
            {
                if (!f.isAnnotationPresent(TempField.class))
                {
                    String fName = f.getName();
                    if (f.isAnnotationPresent(FieldName.class))
                    {
                        String fieldName = f.getAnnotation(FieldName.class)
                                .name();
                        Method get = thisClass.getMethod("get"
                                + fName.substring(0, 1).toUpperCase()
                                + fName.substring(1));
                        Object getValue = get.invoke(o);
                        Pram pram = new Pram(fieldName + " as " + fName,
                                getValue);
                        list.add(pram);
                    }
                    else
                    {
                        String fieldName = toTableString(fName);
                        Method get = thisClass.getMethod("get"
                                + fName.substring(0, 1).toUpperCase()
                                + fName.substring(1));
                        Object getValue = get.invoke(o);
                        Pram pram = new Pram(fieldName + " as " + fName,
                                getValue);
                        list.add(pram);
                    }
                }
            }
        }
        catch (NoSuchMethodException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (SecurityException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (IllegalAccessException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (IllegalArgumentException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (InvocationTargetException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (InstantiationException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return list;
    }

    public static <T> List<Pram> getPramListByBean(Class<T> po)
    {
        List<Pram> list = new ArrayList<Pram>();
        Class<?> thisClass = po;
        Field[] fields = thisClass.getDeclaredFields();
        try
        {
            Object o = thisClass.newInstance();
            for (Field f : fields)
            {
                if (!f.getName().equalsIgnoreCase("ID")
                        && !f.isAnnotationPresent(TempField.class))
                {

                    String fName = f.getName();
                    if (f.isAnnotationPresent(FieldName.class))
                    {
                        String fieldName = f.getAnnotation(FieldName.class)
                                .name();
                        Method get = thisClass.getMethod("get"
                                + fName.substring(0, 1).toUpperCase()
                                + fName.substring(1));
                        Object getValue = get.invoke(o);
                        Pram pram = new Pram(fieldName + " as " + fName,
                                getValue);
                        list.add(pram);
                    }
                    else
                    {
                        String fieldName = toTableString(fName);
                        Method get = thisClass.getMethod("get"
                                + fName.substring(0, 1).toUpperCase()
                                + fName.substring(1));
                        Object getValue = get.invoke(o);
                        Pram pram = new Pram(fieldName + " as " + fName,
                                getValue);
                        list.add(pram);
                    }

                }
            }
        }
        catch (NoSuchMethodException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (SecurityException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (IllegalAccessException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (IllegalArgumentException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (InvocationTargetException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (InstantiationException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return list;
    }

    public static <T> List<Pram> getPramListByBeanOfSelect(Class<T> po)
    {
        List<Pram> list = new ArrayList<Pram>();
        Class<?> thisClass = po;
        Field[] fields = thisClass.getDeclaredFields();
        try
        {
            Object o = thisClass.newInstance();
            for (Field f : fields)
            {
                if (!f.isAnnotationPresent(TempField.class))
                {
                    String fName = f.getName();
                    if (f.isAnnotationPresent(FieldName.class))
                    {
                        String fieldName = f.getAnnotation(FieldName.class)
                                .name();
                        Method get = thisClass.getMethod("get"
                                + fName.substring(0, 1).toUpperCase()
                                + fName.substring(1));
                        Object getValue = get.invoke(o);
                        Pram pram = new Pram(fieldName + " as " + fName,
                                getValue);
                        list.add(pram);
                    }
                    else
                    {
                        String fieldName = toTableString(fName);
                        Method get = thisClass.getMethod("get"
                                + fName.substring(0, 1).toUpperCase()
                                + fName.substring(1));
                        Object getValue = get.invoke(o);
                        Pram pram = new Pram(fieldName + " as " + fName,
                                getValue);
                        list.add(pram);
                    }
                }
            }
        }
        catch (NoSuchMethodException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (SecurityException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (IllegalAccessException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (IllegalArgumentException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (InvocationTargetException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (InstantiationException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return list;
    }

    public static <T extends Po> String getTableName(Class<T> po)
    {
        if (po.isAnnotationPresent(TableName.class))
        {
            return po.getAnnotation(TableName.class).name();
        }
        else
        {
            String tName = toTableString(po.getSimpleName());
            String poName = tName.substring(tName.length() - 2, tName.length());
            if ("po".equals(poName))
            {
                tName = tName.substring(0, tName.length() - 3);
            }
            return tName;
        }
    }

    public static <T> String getTableNameByBean(Class<T> po)
    {
        if (po.isAnnotationPresent(TableName.class))
        {
            return po.getAnnotation(TableName.class).name();
        }
        else
        {
            String tName = toTableString(po.getSimpleName());
            if ("po".equals(tName.substring(tName.length() - 3,
                    tName.length() - 1)))
            {
                tName = tName.substring(0, tName.length() - 3);
            }
            return tName;
        }
    }

    public static <T> Serializable getFileValue(Class<T> po, String fileName)
    {
        try
        {
            Method method = po.getMethod("get"
                    + fileName.substring(0, 1).toUpperCase()
                    + fileName.substring(1));
            Object o = po.newInstance();
            Object invoke = method.invoke(o);
            return null == invoke ? null : (Serializable) invoke;
        }
        catch (NoSuchMethodException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (SecurityException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (IllegalAccessException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (IllegalArgumentException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (InvocationTargetException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (InstantiationException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public static <T> Serializable getFileValue(Po po, String fileName)
    {
        try
        {
            Class<? extends Po> cla = po.getClass();
            Method method = cla.getMethod("get"
                    + fileName.substring(0, 1).toUpperCase()
                    + fileName.substring(1));
            Object o = po;
            Object invoke = method.invoke(o);
            return null == invoke ? null : (Serializable) invoke;
        }
        catch (NoSuchMethodException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (SecurityException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (IllegalAccessException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (IllegalArgumentException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (InvocationTargetException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public static boolean setFileValue(Po po, String fileName,
            Serializable fileValue)
    {
        Class<? extends Po> thisClass = po.getClass();
        try
        {
            if ("ID".equalsIgnoreCase(fileName))
            {
                try
                {
                    Field field = thisClass.getDeclaredField(fileName);
                    String calssName = field.getType().getName();
                    if (calssName.equals("int")
                            || calssName.equals("java.lang.Integer"))
                    {
                        if (Integer.MAX_VALUE > new Integer("" + fileValue))
                        {
                            Integer val = new Integer("" + fileValue);
                            Method method = thisClass.getMethod("set"
                                    + fileName.substring(0, 1).toUpperCase()
                                    + fileName.substring(1), field.getType());
                            method.invoke(po, val);
                            return true;
                        }
                        else
                        {
                            throw new AiyiIdTypeException(
                                    "ID type is not a corresponding type at "
                                            + "set"
                                            + fileName.substring(0, 1)
                                                    .toUpperCase()
                                            + fileName.substring(1) + "\n"
                                            + "the will give value type is "
                                            + fileValue.getClass().getName()
                                            + "\n" + "the filed type type is "
                                            + field.getType().getName());
                        }
                    }
                    else if (calssName.equals("long")
                            || calssName.equals("java.lang.Long"))
                    {
                        Long val = new Long("" + fileValue);
                        Method method = thisClass.getMethod("set"
                                + fileName.substring(0, 1).toUpperCase()
                                + fileName.substring(1), field.getType());
                        method.invoke(po, val);
                        return true;
                    }
                    else
                    {
                        Method method = thisClass.getMethod("set"
                                + fileName.substring(0, 1).toUpperCase()
                                + fileName.substring(1), field.getType());
                        method.invoke(po, fileValue);
                        return true;
                    }
                }
                catch (AiyiIdTypeException e1)
                {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                catch (NoSuchFieldException e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            Method method = thisClass.getMethod("set"
                    + fileName.substring(0, 1).toUpperCase()
                    + fileName.substring(1), fileValue.getClass());
            method.invoke(po, fileValue);
            return true;
        }
        catch (NoSuchMethodException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (SecurityException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (IllegalAccessException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (IllegalArgumentException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (InvocationTargetException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }

    public static String toTableString(String text)
    {
        String tName = text.substring(0, 1).toLowerCase();
        for (int i = 1; i < text.length(); i++)
        {
            if (!Character.isLowerCase(text.charAt(i)))
            {
                tName += "_";
            }
            tName += text.substring(i, i + 1);
        }
        return tName.toLowerCase();
    }
}
