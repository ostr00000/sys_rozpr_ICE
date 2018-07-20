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

package gBank;

public class PersonalData implements java.lang.Cloneable,
                                     java.io.Serializable
{
    public String name;

    public String surname;

    public String pesel;

    public double income;

    public PersonalData()
    {
        this.name = "";
        this.surname = "";
        this.pesel = "";
    }

    public PersonalData(String name, String surname, String pesel, double income)
    {
        this.name = name;
        this.surname = surname;
        this.pesel = pesel;
        this.income = income;
    }

    public boolean equals(java.lang.Object rhs)
    {
        if(this == rhs)
        {
            return true;
        }
        PersonalData r = null;
        if(rhs instanceof PersonalData)
        {
            r = (PersonalData)rhs;
        }

        if(r != null)
        {
            if(this.name != r.name)
            {
                if(this.name == null || r.name == null || !this.name.equals(r.name))
                {
                    return false;
                }
            }
            if(this.surname != r.surname)
            {
                if(this.surname == null || r.surname == null || !this.surname.equals(r.surname))
                {
                    return false;
                }
            }
            if(this.pesel != r.pesel)
            {
                if(this.pesel == null || r.pesel == null || !this.pesel.equals(r.pesel))
                {
                    return false;
                }
            }
            if(this.income != r.income)
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
        h_ = com.zeroc.IceInternal.HashUtil.hashAdd(h_, "::gBank::PersonalData");
        h_ = com.zeroc.IceInternal.HashUtil.hashAdd(h_, name);
        h_ = com.zeroc.IceInternal.HashUtil.hashAdd(h_, surname);
        h_ = com.zeroc.IceInternal.HashUtil.hashAdd(h_, pesel);
        h_ = com.zeroc.IceInternal.HashUtil.hashAdd(h_, income);
        return h_;
    }

    public PersonalData clone()
    {
        PersonalData c = null;
        try
        {
            c = (PersonalData)super.clone();
        }
        catch(CloneNotSupportedException ex)
        {
            assert false; // impossible
        }
        return c;
    }

    public void ice_writeMembers(com.zeroc.Ice.OutputStream ostr)
    {
        ostr.writeString(this.name);
        ostr.writeString(this.surname);
        ostr.writeString(this.pesel);
        ostr.writeDouble(this.income);
    }

    public void ice_readMembers(com.zeroc.Ice.InputStream istr)
    {
        this.name = istr.readString();
        this.surname = istr.readString();
        this.pesel = istr.readString();
        this.income = istr.readDouble();
    }

    static public void ice_write(com.zeroc.Ice.OutputStream ostr, PersonalData v)
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

    static public PersonalData ice_read(com.zeroc.Ice.InputStream istr)
    {
        PersonalData v = new PersonalData();
        v.ice_readMembers(istr);
        return v;
    }

    static public void ice_write(com.zeroc.Ice.OutputStream ostr, int tag, java.util.Optional<PersonalData> v)
    {
        if(v != null && v.isPresent())
        {
            ice_write(ostr, tag, v.get());
        }
    }

    static public void ice_write(com.zeroc.Ice.OutputStream ostr, int tag, PersonalData v)
    {
        if(ostr.writeOptional(tag, com.zeroc.Ice.OptionalFormat.FSize))
        {
            int pos = ostr.startSize();
            ice_write(ostr, v);
            ostr.endSize(pos);
        }
    }

    static public java.util.Optional<PersonalData> ice_read(com.zeroc.Ice.InputStream istr, int tag)
    {
        if(istr.readOptional(tag, com.zeroc.Ice.OptionalFormat.FSize))
        {
            istr.skip(4);
            return java.util.Optional.of(PersonalData.ice_read(istr));
        }
        else
        {
            return java.util.Optional.empty();
        }
    }

    private static final PersonalData _nullMarshalValue = new PersonalData();

    public static final long serialVersionUID = -8004625989595133254L;
}
