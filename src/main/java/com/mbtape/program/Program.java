package com.mbtape.program;

import com.mbtape.constants.TriAxis;
import org.apache.log4j.Logger;

/**
 * Created by mphilpot on 4/6/14.
 */
public class Program
{
    private static      Logger log       = Logger.getLogger(Program.class);

    public static final int    NUM_BYTES = 1;

    private int program;

    public Program()
    {
        program = 1;
    }

    public Program(int preset)
    {
        program = preset;
    }

    public byte[] getProgram()
    {
        byte[] p = new byte[NUM_BYTES];
        p[0] = (byte) program;
        return p;
    }

    public void setProgram(int preset)
    {
        if(preset < 0 || preset >= TriAxis.NUM_PRESETS)
            throw new RuntimeException(String.format("Illegal Preset Value :: %d", preset));

        this.program = preset;
    }
}
