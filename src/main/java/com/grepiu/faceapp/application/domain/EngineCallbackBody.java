package com.grepiu.faceapp.application.domain;

import lombok.Data;

@Data
public class EngineCallbackBody {

  private String uid;
  private String resultJson;
}