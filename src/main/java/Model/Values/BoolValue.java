package Model.Values;

import Model.Types.BoolType;
import Model.Types.Type;

public class BoolValue implements Value{
    boolean val;

    @Override
    public boolean equals(Object another){
        if (another instanceof BoolValue)
            return true;
        else
            return false;
    }
    public BoolValue(boolean v){val=v;}

    public BoolValue(){val=false;}

    public boolean getVal() {return val;}

    @Override
    public String toString() {return Boolean.toString(val);}

    @Override
    public Type getType() { return new BoolType();}
}
