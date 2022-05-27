package com.heqichao.springBootDemo.megprotocol;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Platform;
import com.sun.jna.Pointer;

public interface Clibrary extends Library {
    Clibrary INSTANCE = Native.load((Platform.isWindows() ? "msvcrt" : "c"), Clibrary.class);

    Pointer malloc(int len);

    void free(Pointer p);

    Pointer memcpy(Pointer dst, Pointer src, long size);
}
