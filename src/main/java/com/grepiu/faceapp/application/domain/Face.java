package com.grepiu.faceapp.application.domain;

import java.util.Map;
import lombok.Data;

@Data
public class Face {

  private Map<String, Object> emotions;
}
