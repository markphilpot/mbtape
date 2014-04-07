package com.mbtape.preset;

import org.apache.log4j.Logger;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mphilpot on 4/6/14.
 */
public class Preset
{
    private static Logger log = Logger.getLogger(Preset.class);

    public static int NUM_BYTES = 11;

    private Map<String, PresetComponent> components;

    private byte[] preset;

    public Preset()
    {
        components = new HashMap<String, PresetComponent>(12);
        components.put("dVoice", new Setting(0));
        components.put("presense", new Setting(1));
        components.put("master", new Setting(2));
        components.put("lead2Drive", new Setting(3));
        components.put("lead1Drive", new Setting(4));
        components.put("bass", new Setting(5));
        components.put("middle", new Setting(6));
        components.put("treble", new Setting(7));
        components.put("gain", new Setting(8));
        components.put("tube", new Tube());
        components.put("switches", new Switches());
        components.put("fx", new Fx());

        preset = new byte[NUM_BYTES];
    }

    public byte[] getPreset()
    {
        compile();
        return preset;
    }

    public void init(byte[] buffer)
    {
        System.arraycopy(buffer, 0, preset, 0, NUM_BYTES);

        for(PresetComponent pc : components.values())
        {
            pc.init(preset);
        }
    }

    public void compile()
    {
        for(PresetComponent pc : components.values())
        {
            pc.compile(preset);
        }
    }
}
