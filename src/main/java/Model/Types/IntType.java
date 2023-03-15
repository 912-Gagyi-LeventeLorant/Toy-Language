package Model.Types;

import Model.Values.BoolValue;
import Model.Values.IntValue;
import Model.Values.Value;

public class IntType implements Type{

    @Override
    public boolean equals(Object another){
        if (another instanceof IntType)
            return true;
        else
            return false;
    }
    @Override
    public String toString() { return "int";}

    public Value returnNewValue(){
        return new IntValue();
    }

    @Override
    public Value defaultValue() {
        return new IntValue();
    }
}