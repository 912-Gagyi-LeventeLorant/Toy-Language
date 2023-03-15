package Model.Values;

import Model.Types.IntType;
import Model.Types.StringType;
import Model.Types.Type;

public class StringValue implements Value{
    String val;

    public StringValue(String v){val=v;}

    @Override
    public boolean equals(Object another){
        if (another instanceof StringValue)
            return true;
        else
            return false;
    }

    public StringValue(){val="";}

    public String getVal() {return val;}

    @Override
    public String toString() {return val;}

    @Override
    public Type getType() { return new StringType();}

}
