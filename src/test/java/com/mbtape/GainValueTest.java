package com.mbtape;

import com.mbtape.preset.Setting;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * Created by mphilpot on 4/6/14.
 */
public class GainValueTest
{
    private static Logger log = Logger.getLogger(GainValueTest.class);

    @Test
    public void main() throws IOException
    {
        String files[] = {
                "sysex/02_preset1_gain001.syx",
                "sysex/02_preset1_gain002.syx",
                "sysex/02_preset1_gain003.syx",
                "sysex/02_preset1_gain004.syx",
                "sysex/02_preset1_gain005.syx",
                "sysex/02_preset1_gain006.syx",
                "sysex/02_preset1_gain007.syx",
                "sysex/02_preset1_gain008.syx",
                "sysex/02_preset1_gain009.syx",
                "sysex/02_preset1_gain035.syx",
                "sysex/02_preset1_gain045.syx",
                "sysex/02_preset1_gain055.syx",
                "sysex/02_preset1_gain065.syx",
                "sysex/02_preset1_gain075.syx",
                "sysex/02_preset1_gain100.syx"
        };

        Setting.Value[] values = {
                Setting.Value.v10,
                Setting.Value.v20,
                Setting.Value.v30,
                Setting.Value.v40,
                Setting.Value.v50,
                Setting.Value.v60,
                Setting.Value.v70,
                Setting.Value.v80,
                Setting.Value.v90,
                Setting.Value.v35,
                Setting.Value.v45,
                Setting.Value.v55,
                Setting.Value.v65,
                Setting.Value.v75,
                Setting.Value.v100
        };

        for(int i = 0; i < files.length; i++)
        {
            test(files[i], values[i]);
        }
    }

    public void test(String file, Setting.Value v) throws IOException
    {
        byte[] reference = IOUtils.toByteArray(GainValueTest.class.getClassLoader().getResourceAsStream(file));

        SysEx se = new SysEx();
        se.getPresets()[0].setGain(v);
        se.compile();

        assertThat(se.getSysEx(), equalTo(reference));

    }

    @Test
    public void test20() throws IOException
    {
        byte[] reference = IOUtils.toByteArray(GainValueTest.class.getClassLoader().getResourceAsStream("sysex/02_preset1_gain002.syx"));

        SysEx se = new SysEx();
        se.getPresets()[0].setGain(Setting.Value.v20);
        se.compile();

        assertThat(se.getSysEx(), equalTo(reference));

    }

    @Test
    public void test30() throws IOException
    {
        byte[] reference = IOUtils.toByteArray(GainValueTest.class.getClassLoader().getResourceAsStream("sysex/02_preset1_gain003.syx"));

        SysEx se = new SysEx();
        se.getPresets()[0].setGain(Setting.Value.v30);
        se.compile();

        assertThat(se.getSysEx(), equalTo(reference));

    }

    @Test
    public void test40() throws IOException
    {
        byte[] reference = IOUtils.toByteArray(GainValueTest.class.getClassLoader().getResourceAsStream("sysex/02_preset1_gain004.syx"));

        SysEx se = new SysEx();
        se.getPresets()[0].setGain(Setting.Value.v40);
        se.compile();

        assertThat(se.getSysEx(), equalTo(reference));

    }

}
