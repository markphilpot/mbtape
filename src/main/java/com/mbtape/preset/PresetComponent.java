package com.mbtape.preset;

import java.nio.ByteBuffer;

/**
 * Created by mphilpot on 4/6/14.
 */
public interface PresetComponent
{
    void init(byte[] preset);
    void compile(byte[] preset);
}
