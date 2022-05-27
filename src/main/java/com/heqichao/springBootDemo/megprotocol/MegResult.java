package com.heqichao.springBootDemo.megprotocol;

import lombok.Data;

@Data
public class MegResult {
  private IErrorCode errorCode;

  private Object data;

  public MegResult() {
  }

  public MegResult(IErrorCode errorCode) {
    this.errorCode = errorCode;
  }

  public MegResult(IErrorCode errorCode, Object data) {
    this.errorCode = errorCode;
    this.data = data;
  }
}

