package com.mbtape;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * Created by mphilpot on 4/6/14.
 */
public class EmptyTest
{
    private static Logger log = Logger.getLogger(EmptyTest.class);

    @Test
    public void main() throws IOException
    {
        byte[] reference = IOUtils.toByteArray(EmptyTest.class.getClassLoader().getResourceAsStream("sysex/01_empty.syx"));

        SysEx se = new SysEx();
        se.compile();

        assertThat(se.getSysEx(), equalTo(reference));

    }

}
