package Model.Values;

import Model.Types.RefType;
import Model.Types.StringType;
import Model.Types.Type;

public class RefValue implements Value{
    int address;
    Type locationType;


    public RefValue(int location, Type locType){
        address = location;
        locationType = locType;
    }

    public int getAddr() {return address;}

    public Type getType() { return new RefType(locationType);}

    public Type getLocationType() {return locationType;}

    public String toString()
    {
        return ("(" + Integer.toString(address) + "," + locationType + ')');
    }

}