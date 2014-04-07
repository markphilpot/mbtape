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
        v10((byte) 0x01),
        v20((byte) 0x02),
        v30((byte) 0x03),
        v35((byte) 0x04),
        v40((byte) 0x05),
        v45((byte) 0x06),
        v50((byte) 0x07),
        v55((byte) 0x08),
        v60((byte) 0x09),
        v65((byte) 0x0A),
        v70((byte) 0x0B),
        v75((byte) 0x0C),
        v80((byte) 0x0D),
        v90((byte) 0x0E),
        v100((byte) 0x0F);

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
