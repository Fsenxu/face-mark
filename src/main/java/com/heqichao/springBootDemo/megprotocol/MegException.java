package com.heqichao.springBootDemo.megprotocol;

import lombok.Getter;

import java.io.IOException;

public class MegException extends IOException implements IErrorCode {
  @Getter private final int code;
  @Getter private final String msg;

  /**
   * @param errorCode 枚举的错误码类型,其中包含了错误码和对应的错误码描述信息.
   */
  public MegException(IErrorCode errorCode) {
    super();
    this.code = errorCode.getCode();
    this.msg = errorCode.getMsg();
  }

  /**
   * @param errorCode 枚举的错误码类型,其中包含了错误码和对应的错误码描述信息.
   * @param message 最好是自定义的详细描述信息,用于打日志,也可以和相同
   */
  public MegException(IErrorCode errorCode, String message) {
    super(message);
    this.code = errorCode.getCode();
    this.msg = errorCode.getMsg();
  }

  /**
   * @param errorCode 枚举的错误码类型,其中包含了错误码和对应的错误码描述信息.
   * @param cause 嵌套的错误堆栈
   */
  public MegException(IErrorCode errorCode, Throwable cause) {
    super(cause);
    this.code = errorCode.getCode();
    this.msg = errorCode.getMsg();
  }

  /**
   * @param errorCode 枚举的错误码类型,其中包含了错误码和对应的错误码描述信息.
   * @param message 最好是自定义的详细描述信息,用于打日志,也可以和相同
   * @param cause 嵌套的错误堆栈
   */
  public MegException(IErrorCode errorCode, String message, Throwable cause) {
    super(message, cause);
    this.code = errorCode.getCode();
    this.msg = errorCode.getMsg();
  }
}
