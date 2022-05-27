package com.heqichao.springBootDemo.megprotocol;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Platform;

public class ThirdLibrary {

    public interface SSL extends Library {
        //linux下如用ssl将会优先调用系统的libssl资源,windows平台不需要
        SSL INSTANTCE = (Platform.isLinux() ? (SSL) Native.load("ssl_1_0_2", SSL.class) : null);
    }

    public interface MXML extends Library {
        MXML INSTANTCE = (Platform.isLinux() ? (MXML) Native.load("mxml", MXML.class) : null);
    }

    public interface CRYPTO extends Library {
        //linux下如用crypto 将会优先调用系统的libcrypto资源,windows平台不需要
        CRYPTO INSTANTCE = (Platform.isLinux() ? (CRYPTO) Native.load("crypto_1_0_0", CRYPTO.class) : null);
    }

    public interface TARO extends Library {
        TARO INSTANTCE = (Platform.isLinux() ? (TARO) Native.load("taro", TARO.class) : null);
    }
}
