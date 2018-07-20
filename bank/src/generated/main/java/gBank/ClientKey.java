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

public class ClientKey implements java.lang.Cloneable,
                                  java.io.Serializable
{
    public String key;

    public ClientKey()
    {
        this.key = "";
    }

    public ClientKey(String key)
    {
        this.key = key;
    }

    public boolean equals(java.lang.Object rhs)
    {
        if(this == rhs)
        {
            return true;
        }
        ClientKey r = null;
        if(rhs instanceof ClientKey)
        {
            r = (ClientKey)rhs;
        }

        if(r != null)
        {
            if(this.key != r.key)
            {
                if(this.key == null || r.key == null || !this.key.equals(r.key))
                {
                    return false;
                }
            }

            return true;
        }

        return false;
    }

    public int hashCode()
    {
        int h_ = 5381;
        h_ = com.zeroc.IceInternal.HashUtil.hashAdd(h_, "::gBank::ClientKey");
        h_ = com.zeroc.IceInternal.HashUtil.hashAdd(h_, key);
        return h_;
    }

    public ClientKey clone()
    {
        ClientKey c = null;
        try
        {
            c = (ClientKey)super.clone();
        }
        catch(CloneNotSupportedException ex)
        {
            assert false; // impossible
        }
        return c;
    }

    public void ice_writeMembers(com.zeroc.Ice.OutputStream ostr)
    {
        ostr.writeString(this.key);
    }

    public void ice_readMembers(com.zeroc.Ice.InputStream istr)
    {
        this.key = istr.readString();
    }

    static public void ice_write(com.zeroc.Ice.OutputStream ostr, ClientKey v)
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

    static public ClientKey ice_read(com.zeroc.Ice.InputStream istr)
    {
        ClientKey v = new ClientKey();
        v.ice_readMembers(istr);
        return v;
    }

    static public void ice_write(com.zeroc.Ice.OutputStream ostr, int tag, java.util.Optional<ClientKey> v)
    {
        if(v != null && v.isPresent())
        {
            ice_write(ostr, tag, v.get());
        }
    }

    static public void ice_write(com.zeroc.Ice.OutputStream ostr, int tag, ClientKey v)
    {
        if(ostr.writeOptional(tag, com.zeroc.Ice.OptionalFormat.FSize))
        {
            int pos = ostr.startSize();
            ice_write(ostr, v);
            ostr.endSize(pos);
        }
    }

    static public java.util.Optional<ClientKey> ice_read(com.zeroc.Ice.InputStream istr, int tag)
    {
        if(istr.readOptional(tag, com.zeroc.Ice.OptionalFormat.FSize))
        {
            istr.skip(4);
            return java.util.Optional.of(ClientKey.ice_read(istr));
        }
        else
        {
            return java.util.Optional.empty();
        }
    }

    private static final ClientKey _nullMarshalValue = new ClientKey();

    public static final long serialVersionUID = 5836122282389305684L;
}
