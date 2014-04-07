package com.mbtape.constants;

import com.mbtape.preset.Preset;
import com.mbtape.program.Program;

/**
 * Created by mphilpot on 4/6/14.
 */
public class TriAxis
{
    public static final int SYSEX_NUM_BYTES = 2746;

    // Presets are 1 set
    public static final int NUM_PRESETS = 90;

    // Programs are 1 set
    public static final int NUM_PROGRAMS = 128;

    public static final byte[] PREAMBLE = {(byte) 0xF0, 0x00, 0x00, 0x4A, 0x02};

    public static final byte[] TERMINATOR = {(byte) 0xF7};

    public static final int PRESET_OFFSET      = PREAMBLE.length;
    public static final int PROGRAM_OFFSET     = PRESET_OFFSET + (Preset.NUM_BYTES * NUM_PRESETS);
    public static final int CONTROLLERS_OFFSET = PROGRAM_OFFSET + (Program.NUM_BYTES * NUM_PROGRAMS);
}
