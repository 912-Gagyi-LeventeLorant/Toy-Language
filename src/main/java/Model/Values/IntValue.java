package Model.Values;

import Model.Types.IntType;
import Model.Types.Type;

public class IntValue implements Value{
        int val;
        public IntValue(int v){val=v;}

        @Override
        public boolean equals(Object another){
                if (another instanceof IntValue)
                        return true;
                else
                        return false;
        }

        public IntValue(){val=0;}

        public int getVal() {return val;}

        @Override
        public String toString() {return Integer.toString(val);}

        @Override
        public Type getType() { return new IntType();}
        }