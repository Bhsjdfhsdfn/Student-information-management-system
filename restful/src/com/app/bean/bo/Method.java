package com.app.bean.bo;

import java.io.Serializable;

public class Method
{
    public static WherePrams where(String file, String where, Serializable value)
    {
        return new WherePrams(file, where, value);
    }

    public static WherePrams where(String file, C c, Serializable value)
    {
        String where = C.getSqlWhere(c);
        return where(file, where, value);
    }

    public static WherePrams createDefault()
    {
        return new WherePrams(null, null, null);
    }

}
