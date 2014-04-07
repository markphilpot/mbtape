package com.mbtape.preset;

import org.apache.log4j.Logger;

import java.nio.ByteBuffer;

/**
 * Created by mphilpot on 4/6/14.
 */
public class Switches implements PresetComponent
{
    private static Logger log = Logger.getLogger(Switches.class);

    private int OFFSET = 10;

    public enum Switch
    {
        SW1((byte) 0x02),
        SW2((byte) 0x04),
        SW3((byte) 0x08),
        SW4((byte) 0x10);

        private byte value;

        Switch(byte value)
        {
            this.value = value;
        }

        public byte getValue()
        {
            return value;
        }
    }

    private byte switches;

    public Switches()
    {
        switches = 0x00;
    }

    public void setSwitch(Switch s)
    {
        switches |= s.getValue();
    }

    @Override
    public void init(byte[] preset)
    {
        switches = (byte)(preset[OFFSET] & 0xFE);
    }

    @Override
    public void compile(byte[] preset)
    {
        preset[OFFSET] &= 0x01;

        preset[OFFSET] |= switches;
    }
}
