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
        components.put("presence", new Setting(1));
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

    public void setDynamicVoice(Setting.Value v)
    {
        ((Setting)components.get("dVoice")).setValue(v);
    }

    public Setting.Value getDynamicVoice()
    {
        return ((Setting)components.get("dVoice")).getValue();
    }

    public void setPresence(Setting.Value v)
    {
        ((Setting)components.get("presence")).setValue(v);
    }

    public Setting.Value getPresence()
    {
        return ((Setting)components.get("presence")).getValue();
    }

    public void setMaster(Setting.Value v)
    {
        ((Setting)components.get("master")).setValue(v);
    }

    public Setting.Value getMaster()
    {
        return ((Setting)components.get("master")).getValue();
    }

    public void setLead2Drive(Setting.Value v)
    {
        ((Setting)components.get("lead2Drive")).setValue(v);
    }

    public Setting.Value getLead2Drive()
    {
        return ((Setting)components.get("lead2Drive")).getValue();
    }

    public void setLead1Drive(Setting.Value v)
    {
        ((Setting)components.get("lead1Drive")).setValue(v);
    }

    public Setting.Value getLead1Drive()
    {
        return ((Setting)components.get("lead1Drive")).getValue();
    }

    public void setBass(Setting.Value v)
    {
        ((Setting)components.get("bass")).setValue(v);
    }

    public Setting.Value getBass()
    {
        return ((Setting)components.get("bass")).getValue();
    }

    public void setMiddle(Setting.Value v)
    {
        ((Setting)components.get("middle")).setValue(v);
    }

    public Setting.Value getMiddle()
    {
        return ((Setting)components.get("middle")).getValue();
    }

    public void setTreble(Setting.Value v)
    {
        ((Setting)components.get("treble")).setValue(v);
    }

    public Setting.Value getTreble()
    {
        return ((Setting)components.get("treble")).getValue();
    }

    public void setGain(Setting.Value v)
    {
        ((Setting)components.get("gain")).setValue(v);
    }

    public Setting.Value getGain()
    {
        return ((Setting)components.get("gain")).getValue();
    }

    public void setTube(Tube.TubeSetting ts)
    {
        ((Tube)components.get("tube")).setTube(ts);
    }

    public Tube.TubeSetting getTube()
    {
        return ((Tube)components.get("tube")).getTube();
    }

    public void setSwitchOn(Switches.Switch s)
    {
        ((Switches)components.get("switches")).setSwitchOn(s);
    }

    public void setSwitchOff(Switches.Switch s)
    {
        ((Switches)components.get("switches")).setSwitchOff(s);
    }

    public Switches.Switch[] getSwitches()
    {
        return ((Switches)components.get("switches")).getSwitches();
    }

    public void setFx(Fx.Switch fx)
    {
        ((Fx)components.get("fx")).setFx(fx);
    }

    public Fx.Switch getFx()
    {
        return ((Fx)components.get("fx")).getFx();
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
