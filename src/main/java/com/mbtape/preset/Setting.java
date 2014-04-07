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
        v00((byte) 0x00),
        v10((byte) 0x00),
        v20((byte) 0x00),
        v30((byte) 0x00),
        v35((byte) 0x00),
        v40((byte) 0x00),
        v45((byte) 0x00),
        v50((byte) 0x00),
        v55((byte) 0x00),
        v60((byte) 0x00),
        v65((byte) 0x00),
        v70((byte) 0x00),
        v75((byte) 0x00),
        v80((byte) 0x00),
        v90((byte) 0x00),
        v100((byte) 0x00);

        byte value;

        Value(byte value)
        {
            this.value = value;
        }

        public byte getValue()
        {
            return value;
        }
    }

    private int offset;

    private byte value;

    public Setting(int offset)
    {
        this.offset = offset;
        this.value = 0x00;
    }

    public void setValue(Value v)
    {
        value = v.getValue();
    }

    @Override
    public void init(byte[] preset)
    {
        value = preset[offset];
    }

    @Override
    public void compile(byte[] preset)
    {
        preset[offset] = value;
    }
}
