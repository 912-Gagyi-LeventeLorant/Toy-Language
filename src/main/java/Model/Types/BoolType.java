package Model.Types;

import Model.Values.BoolValue;
import Model.Values.Value;

public class BoolType implements Type{

    @Override
    public boolean equals(Object another){
        if (another instanceof BoolType)
            return true;
        else
            return false;
    }
    @Override
    public String toString() { return "bool";}

    public Value returnNewValue(){
        return new BoolValue();
    }

    @Override
    public Value defaultValue() {
        return new BoolValue();
    }

}