package com.mbtape.preset;

import org.apache.log4j.Logger;

import java.nio.ByteBuffer;

/**
 * Created by mphilpot on 4/6/14.
 */
public class Tube implements PresetComponent
{
    private static Logger log = Logger.getLogger(Tube.class);

    private int OFFSET = 9;

    public enum TubeSetting {
        RHYTHM_GREEN((byte)0x7E),
        RHYTHM_YELLOW((byte)0x7D),
        LEAD1_GREEN((byte)0x7B),
        LEAD1_YELLOW((byte)0x77),
        LEAD1_RED((byte)0x6F),
        LEAD2_GREEN((byte)0x5F),
        LEAD2_YELLOW((byte)0x3F),
        LEAD2_RED((byte)0x7F);

        private byte value;

        TubeSetting(byte value)
        {
            this.value = value;
        }

        public byte getValue()
        {
            return value;
        }
    }

    private TubeSetting tube;

    public Tube()
    {
        tube = TubeSetting.RHYTHM_GREEN;
    }

    public void setTube(TubeSetting setting)
    {
        tube = setting;
    }

    public TubeSetting getTube()
    {
        return tube;
    }

    public void init(byte[] preset)
    {
        for(TubeSetting ts : TubeSetting.values())
        {
            if(preset[OFFSET] == ts.getValue())
            {
                tube = ts;
            }
        }
    }

    public void compile(byte[] preset)
    {
        preset[OFFSET] = tube.getValue();
    }
}
