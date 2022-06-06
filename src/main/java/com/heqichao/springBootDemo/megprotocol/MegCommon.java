package com.heqichao.springBootDemo.megprotocol;

import com.sun.jna.Callback;
import com.sun.jna.Pointer;
import com.sun.jna.Structure;

import java.util.Arrays;
import java.util.List;

public class MegCommon {
    public static class MediaFrame extends Structure {
        public int type;
        public int seq;
        public int totalLength;
        public int infoLen;
        public Pointer info;
        public Pointer data;

        public static class ByReference extends MediaFrame implements Structure.ByReference {
        }
        public static class ByValue extends MediaFrame implements Structure.ByValue {
        }

        @Override
        protected List<String> getFieldOrder() {
            return Arrays.asList("type", "seq", "totalLength", "infoLen", "info", "data");
        }
    }

    public static class BinDataInfo extends Structure {
        public int type;
        public int size;
        public Pointer binData;

        public static class ByReference extends BinDataInfo implements Structure.ByReference {
        }
        public static class ByValue extends BinDataInfo implements Structure.ByValue {
        }

        @Override
        protected List<String> getFieldOrder() {
            return Arrays.asList("type", "size", "binData");
        }
    }

    public static class BinDataList extends Structure {
        public int binDataNumSize;
        public BinDataInfo.ByReference binDataInfos;

        public static class ByReference extends BinDataList implements Structure.ByReference {
        }
        public static class ByValue extends BinDataList implements Structure.ByValue {
        }

        @Override
        protected List<String> getFieldOrder() {
            return Arrays.asList("binDataNumSize", "binDataInfos");
        }
    }

    public static class AlarmMsg extends Structure {
        public int infoLen;
        public Pointer jsonInfo;
        public BinDataList.ByValue data;

        public static class ByReference extends AlarmMsg implements Structure.ByReference {
        }
        public static class ByValue extends AlarmMsg implements Structure.ByValue {
        }

        @Override
        protected List<String> getFieldOrder() {
            return Arrays.asList("infoLen", "jsonInfo", "data");
        }
    }
    public static class AlarmSnCodeCbArg extends Structure {
        public String callback;
        public String sn;
        public AlarmSnCodeCbArg() {
            super();
        }
        @Override
        protected List<String> getFieldOrder() {
            return Arrays.asList("callback", "sn");
        }

        public AlarmSnCodeCbArg(Pointer peer) {
            super(peer);
        }
        public static class ByReference extends AlarmSnCodeCbArg implements Structure.ByReference { }
        public static class ByValue extends AlarmSnCodeCbArg implements Structure.ByValue { }
    }

    public class PacketType
    {
        public static final int DOWNLOAD_PACKET_START = 1;
        public static final int DOWNLOAD_PACKET_MID = 2;
        public static final int DOWNLOAD_PACKET_END = 3;
    }

    public static class DownloadPacket extends Structure {
        int            type;           //包类型:1-视频; 2-音频; 3-图片; 4-智能
        int            seq;            //序列号
        int            totalLength;    //总长度，包含头信息
        int            sme;            //PacketType
        Pointer        data;           //数据

        public static class ByReference extends DownloadPacket implements Structure.ByReference {
        }
        public static class ByValue extends DownloadPacket implements Structure.ByValue {
        }

        @Override
        protected List<String> getFieldOrder() {
            return Arrays.asList("type", "seq", "totalLength", "sme", "data");
        }
    }

    public static interface AsyncSendMsgCb extends Callback {
        void invoke(int result, String resp, Pointer userArg);
    }

    public static interface MediaStreamCb extends Callback {
        void invoke(int channelId, MediaFrame.ByReference frame, Pointer userArg);
    }

    public static interface MediaVideoStreamCb extends Callback {
        void invoke(MediaFrame.ByReference frame, Pointer userArg);
    }

    public static interface PlaybackStreamCb extends Callback {
        void invoke(int handle, Pointer userArg);
    }

    public static interface DownloadStreamCb extends Callback {
        void invoke(DownloadPacket.ByReference downloadPacket, Pointer userArg);
    }

    public static interface ALarmCb extends Callback {
        void invoke(int handle, Pointer userArg);
    }

    public static interface StreamCloseCb extends Callback {
        void invoke(int handle);
    }
}
