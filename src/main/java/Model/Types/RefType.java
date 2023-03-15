package Model.Types;

import Model.Values.RefValue;
import Model.Values.Value;

public class RefType implements Type{
    Type inner;

    public RefType(Type inner) {this.inner=inner;}

    public RefType(){this.inner = null;}

    public Type getInner() {return inner;}

    public boolean equals(Object another){
        if (another instanceof RefType)
            return (inner.equals(((RefType) another).getInner()) || ((RefType) another).getInner() == null);
        else
            return false;
    }
    public String toString() { return "Ref(" +inner.toString()+")";}

    public Value defaultValue() { return new RefValue(0,inner);}
    @Override
    public Value returnNewValue() {
        return new RefValue(0,inner);
    }
}