package com.heqichao.springBootDemo.megprotocol;

public interface IErrorCode {
  /**
   * 获取错误编码<br>
   *
   * @return 错误码
   */
  int getCode();

  /**
   * 获取错误信息<br>
   *
   * @return 错误信息
   */
  String getMsg();
}
