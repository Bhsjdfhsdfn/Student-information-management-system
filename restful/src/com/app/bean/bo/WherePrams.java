package com.app.bean.bo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class WherePrams
{

    private String pram;

    private String limit;

    private String orderBy;

    private List<Serializable> parms = new ArrayList<Serializable>();

    private List<Serializable> limitParms = new ArrayList<Serializable>();

    public WherePrams(String file, String where, Serializable value)
    {

        if (null == file && null == where && value == where)
        {
            return;
        }

        if (null == value)
        {

            if (where.equals("="))
            {
                where = " is";
            }
            else
            {
                where = " not ";
            }
            this.pram = " where " + file + where + "null";
        }
        else
        {
            if ("like".equals(where))
            {
                this.pram = " where " + file + where + " '%" + value + "%'";
            }
            else
            {
                this.pram = " where " + file + where + " '" + value + "'";
            }
        }
    }

    public WherePrams and(String file, String where, Serializable value)
    {
        if (null == value)
        {
            if (where.equals("="))
            {
                where = " is";
            }
            else
            {
                where = " not ";
            }
            this.pram = " and " + file + where + "null";
        }
        else
        {
            if ("like".equals(where))
            {
                this.pram += " and " + file + " " + where + " '%" + value
                        + "%'";
            }
            else
            {
                this.pram += " and " + file + " " + where + " '" + value + "'";
            }
        }

        return this;
    }

    public WherePrams and(String file, C c, Serializable value)
    {
        String where = C.getSqlWhere(c);
        return and(file, where, value);
    }

    public WherePrams or(String file, String where, Serializable value)
    {

        if (null == value)
        {
            if (where.equals("="))
            {
                where = " is";
            }
            else
            {
                where = " not ";
            }
            this.pram = " or " + file + where + "null";
        }
        else
        {
            if ("like".equals(where))
            {
                this.pram += " or " + file + where + " '%" + value + "%'";
            }
            else
            {
                this.pram += " or " + file + where + " '" + value + "'";
            }
        }

        return this;
    }

    public WherePrams or(String file, C c, Serializable value)
    {
        String where = C.getSqlWhere(c);
        return or(file, where, value);
    }

    public WherePrams limit(int startNum, int length)
    {

        this.limit = " limit " + startNum + " , " + length + " ";
        return this;
    }

    public WherePrams orderBy(String order)
    {
        if (this.orderBy != null)
        {
            this.orderBy += "," + order;
        }
        else
        {
            this.orderBy = order;
        }
        return this;
    }

    @Override
    public String toString()
    {
        return "WherePrams [pram=" + pram + "]";
    }

    public String getWherePrams()
    {
        String p = "";
        p += null == this.pram ? "" : this.pram;
        p += null == this.orderBy ? "" : (" order by " + this.orderBy);
        p += null == this.limit ? "" : this.limit;
        return p;
    }

    public Serializable[] listParmsByFmt()
    {
        parms.addAll(limitParms);
        return parms.toArray(new Serializable[parms.size()]);
    }

    public Serializable[] listParms()
    {
        int length = getWherePrams().indexOf("?");
        if (-1 == length)
        {
            return new Serializable[0];
        }
        parms.addAll(limitParms);
        return parms.toArray(new Serializable[parms.size()]);
    }
}
