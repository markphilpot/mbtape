package com.mbtape.preset;

import org.apache.log4j.Logger;

/**
 * Created by mphilpot on 4/6/14.
 */
public class Fx implements PresetComponent
{
    private static Logger log = Logger.getLogger(Fx.class);

    private int OFFSET = 10;

    public enum Switch {
        ON((byte) 0x01),
        OFF((byte) 0x00);

        byte value;

        Switch(byte value)
        {
            this.value = value;
        }

        public byte getValue()
        {
            return value;
        }
    }

    private Switch fx;

    public Fx()
    {
        fx = Switch.OFF;
    }

    public void setFx(Switch f)
    {
        fx = f;
    }

    public Switch getFx()
    {
        return fx;
    }


    @Override
    public void init(byte[] preset)
    {
        if( (byte)(preset[OFFSET] & 0x01) == 0x01 )
        {
            fx = Switch.ON;
        }
        else
        {
            fx = Switch.OFF;
        }
    }

    @Override
    public void compile(byte[] preset)
    {
        preset[OFFSET] &= 0xFE;

        preset[OFFSET] |= fx.getValue();
    }
}
