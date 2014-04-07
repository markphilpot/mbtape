package com.mbtape;

import com.mbtape.constants.TriAxis;
import com.mbtape.preset.Preset;
import com.mbtape.program.Program;
import org.apache.log4j.Logger;

import java.nio.ByteBuffer;

/**
 * Created by mphilpot on 4/6/14.
 */
public class SysEx
{
    private static Logger log = Logger.getLogger(SysEx.class);

    private byte[] sysEx;

    // Elements
    private byte[]    preamble;
    private Preset[]  presets;
    private Program[] programs;

    // placeholder for now
    private byte[] controllers;

    private byte[] terminator;

    public SysEx()
    {
        sysEx = new byte[TriAxis.SYSEX_NUM_BYTES];
        preamble = TriAxis.PREAMBLE;
        presets = new Preset[TriAxis.NUM_PRESETS];
        programs = new Program[TriAxis.NUM_PROGRAMS];
        controllers = new byte[1622]; // Placeholder
        terminator = TriAxis.TERMINATOR;

        for(int i = 0; i < TriAxis.NUM_PRESETS; i++)
        {
            presets[i] = new Preset();
        }

        for(int i = 0 ; i < TriAxis.NUM_PROGRAMS; i++ )
        {
            programs[i] = new Program(i % TriAxis.NUM_PRESETS);
        }
    }

    public void init(byte[] buffer)
    {
        System.arraycopy(buffer, 0, sysEx, 0, TriAxis.SYSEX_NUM_BYTES);

        byte[] preset = new byte[Preset.NUM_BYTES];

        for(int i = 0; i < TriAxis.NUM_PRESETS; i++)
        {
            System.arraycopy(sysEx, TriAxis.PRESET_OFFSET + (i * Preset.NUM_BYTES), preset, 0, Preset.NUM_BYTES);
            presets[i].init(preset);
        }

        for(int i = 0; i < TriAxis.NUM_PROGRAMS; i++)
        {
            programs[i].setProgram(sysEx[TriAxis.PROGRAM_OFFSET + (i * Program.NUM_BYTES)]);
        }
    }

    public void compile()
    {
        ByteBuffer bb = ByteBuffer.wrap(sysEx);

        bb.put(preamble);

        for(int i = 0; i < TriAxis.NUM_PRESETS; i++)
        {
            bb.put(presets[i].getPreset());
            //System.arraycopy(presets[i].getPreset(), 0, sysEx, TriAxis.PRESET_OFFSET + (i * Preset.NUM_BYTES), Preset.NUM_BYTES);
        }

        for(int i = 0; i < TriAxis.NUM_PROGRAMS; i++)
        {
            bb.put(programs[i].getProgram());
            //System.arraycopy(programs[i].getProgram(), 0, sysEx, TriAxis.PROGRAM_OFFSET + (i * Program.NUM_BYTES), Program.NUM_BYTES);
        }

        //System.arraycopy(controllers, 0, sysEx, TriAxis.CONTROLLERS_OFFSET, controllers.length);
        bb.put(controllers);
        bb.put(terminator);
    }

    public byte[] getSysEx()
    {
        return sysEx;
    }
}
