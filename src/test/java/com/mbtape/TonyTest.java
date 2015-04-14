package com.mbtape;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * Created by mphilpot on 4/14/15.
 */
public class TonyTest
{
    private static final Logger log = Logger.getLogger(TonyTest.class);

    @Test
    public void main() throws Exception
    {
        byte[] reference = IOUtils.toByteArray(GainValueTest.class.getClassLoader().getResourceAsStream("sysex/tony.syx"));

        SysEx se = new SysEx();
        se.init(reference);

        se.compile();

//        IOUtils.write(se.getSysEx(), new FileOutputStream(new File("processed.syx")));
        assertThat(se.getSysEx(), equalTo(reference));
    }
}
