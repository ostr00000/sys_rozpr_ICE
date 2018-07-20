// **********************************************************************
//
// Copyright (c) 2003-2018 ZeroC, Inc. All rights reserved.
//
// This copy of Ice is licensed to you under the terms described in the
// ICE_LICENSE file included in this distribution.
//
// **********************************************************************
//
// Ice version 3.7.1
//
// <auto-generated>
//
// Generated from file `bank.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>
//

package gLoan;

public class Loan implements java.lang.Cloneable,
                             java.io.Serializable
{
    public Date repaymentDate;

    public String CurrencyCode;

    public double money;

    public Loan()
    {
        this.repaymentDate = new Date();
        this.CurrencyCode = "";
    }

    public Loan(Date repaymentDate, String CurrencyCode, double money)
    {
        this.repaymentDate = repaymentDate;
        this.CurrencyCode = CurrencyCode;
        this.money = money;
    }

    public boolean equals(java.lang.Object rhs)
    {
        if(this == rhs)
        {
            return true;
        }
        Loan r = null;
        if(rhs instanceof Loan)
        {
            r = (Loan)rhs;
        }

        if(r != null)
        {
            if(this.repaymentDate != r.repaymentDate)
            {
                if(this.repaymentDate == null || r.repaymentDate == null || !this.repaymentDate.equals(r.repaymentDate))
                {
                    return false;
                }
            }
            if(this.CurrencyCode != r.CurrencyCode)
            {
                if(this.CurrencyCode == null || r.CurrencyCode == null || !this.CurrencyCode.equals(r.CurrencyCode))
                {
                    return false;
                }
            }
            if(this.money != r.money)
            {
                return false;
            }

            return true;
        }

        return false;
    }

    public int hashCode()
    {
        int h_ = 5381;
        h_ = com.zeroc.IceInternal.HashUtil.hashAdd(h_, "::gLoan::Loan");
        h_ = com.zeroc.IceInternal.HashUtil.hashAdd(h_, repaymentDate);
        h_ = com.zeroc.IceInternal.HashUtil.hashAdd(h_, CurrencyCode);
        h_ = com.zeroc.IceInternal.HashUtil.hashAdd(h_, money);
        return h_;
    }

    public Loan clone()
    {
        Loan c = null;
        try
        {
            c = (Loan)super.clone();
        }
        catch(CloneNotSupportedException ex)
        {
            assert false; // impossible
        }
        return c;
    }

    public void ice_writeMembers(com.zeroc.Ice.OutputStream ostr)
    {
        Date.ice_write(ostr, this.repaymentDate);
        ostr.writeString(this.CurrencyCode);
        ostr.writeDouble(this.money);
    }

    public void ice_readMembers(com.zeroc.Ice.InputStream istr)
    {
        this.repaymentDate = Date.ice_read(istr);
        this.CurrencyCode = istr.readString();
        this.money = istr.readDouble();
    }

    static public void ice_write(com.zeroc.Ice.OutputStream ostr, Loan v)
    {
        if(v == null)
        {
            _nullMarshalValue.ice_writeMembers(ostr);
        }
        else
        {
            v.ice_writeMembers(ostr);
        }
    }

    static public Loan ice_read(com.zeroc.Ice.InputStream istr)
    {
        Loan v = new Loan();
        v.ice_readMembers(istr);
        return v;
    }

    static public void ice_write(com.zeroc.Ice.OutputStream ostr, int tag, java.util.Optional<Loan> v)
    {
        if(v != null && v.isPresent())
        {
            ice_write(ostr, tag, v.get());
        }
    }

    static public void ice_write(com.zeroc.Ice.OutputStream ostr, int tag, Loan v)
    {
        if(ostr.writeOptional(tag, com.zeroc.Ice.OptionalFormat.FSize))
        {
            int pos = ostr.startSize();
            ice_write(ostr, v);
            ostr.endSize(pos);
        }
    }

    static public java.util.Optional<Loan> ice_read(com.zeroc.Ice.InputStream istr, int tag)
    {
        if(istr.readOptional(tag, com.zeroc.Ice.OptionalFormat.FSize))
        {
            istr.skip(4);
            return java.util.Optional.of(Loan.ice_read(istr));
        }
        else
        {
            return java.util.Optional.empty();
        }
    }

    private static final Loan _nullMarshalValue = new Loan();

    public static final long serialVersionUID = -8725064499747609880L;
}