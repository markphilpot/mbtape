package com.mbtape.preset;

import org.apache.log4j.Logger;

import javax.management.remote.rmi._RMIConnection_Stub;
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
        SW1((byte) 0x02, 0),
        SW2((byte) 0x04, 1),
        SW3((byte) 0x08, 2),
        SW4((byte) 0x10, 3);

        byte value;
        int index;

        Switch(byte value, int index)
        {
            this.value = value;
            this.index = index;
        }

        public byte getValue()
        {
            return value;
        }

        public int getIndex()
        {
            return index;
        }
    }

    private Switch[] switches;

    public Switches()
    {
        switches = new Switch[4];
    }

    public Switch[] getSwitches()
    {
        return switches;
    }

    public void setSwitchOn(Switch s)
    {
        switches[s.getIndex()] = s;
    }

    public void setSwitchOff(Switch s)
    {
        switches[s.getIndex()] = null;
    }

    @Override
    public void init(byte[] preset)
    {
        byte s = (byte)(preset[OFFSET] & 0xFE);

        for(Switch sw : Switch.values())
        {
            if(s == sw.getValue())
            {
                switches[sw.getIndex()] = sw;
            }
        }
    }

    @Override
    public void compile(byte[] preset)
    {
        preset[OFFSET] &= 0x01;

        byte b = 0x00;

        for(Switch sw : switches)
        {
            if(sw != null)
            {
                b |= sw.getValue();
            }
        }

        preset[OFFSET] |= b;
    }
}
