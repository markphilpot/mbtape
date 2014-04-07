package com.mbtape.preset;

import org.apache.log4j.Logger;

/**
 * Created by mphilpot on 4/6/14.
 */
public class Setting implements PresetComponent
{
    private static Logger log = Logger.getLogger(Setting.class);

    public enum Value
    {
        v00((byte) 0x00, "0.0"),
        v10((byte) 0x01, "1.0"),
        v20((byte) 0x02, "2.0"),
        v30((byte) 0x03, "3.0"),
        v35((byte) 0x04, "3.5"),
        v40((byte) 0x05, "4.0"),
        v45((byte) 0x06, "4.5"),
        v50((byte) 0x07, "5.0"),
        v55((byte) 0x08, "5.5"),
        v60((byte) 0x09, "6.0"),
        v65((byte) 0x0A, "6.5"),
        v70((byte) 0x0B, "7.0"),
        v75((byte) 0x0C, "7.5"),
        v80((byte) 0x0D, "8.0"),
        v90((byte) 0x0E, "9.0"),
        v100((byte) 0x0F, "10");

        byte value;
        String display;

        Value(byte value, String display)
        {
            this.value = value;
            this.display = display;
        }

        public byte getValue()
        {
            return value;
        }

        public String getDisplay()
        {
            return display;
        }
    }

    private int offset;

    private Value value;

    public Setting(int offset)
    {
        this.offset = offset;
        this.value = Value.v00;
    }

    public void setValue(Value v)
    {
        value = v;
    }

    public Value getValue()
    {
        return value;
    }

    @Override
    public void init(byte[] preset)
    {
        for(Value v : Value.values())
        {
            if(preset[offset] == v.getValue())
            {
                value = v;
            }
        }
    }

    @Override
    public void compile(byte[] preset)
    {
        preset[offset] = value.getValue();
    }
}
