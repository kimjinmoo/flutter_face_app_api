package com.grepiu.faceapp.application.domain;

import lombok.Data;

/**
 *
 * 얼굴 결과 Value Object
 *
 * <pre>
 *   서버에서 응답 인터페이스를 정의한다.
 * </pre>
 */
@Data
public class FaceResultVO {

  private String imageUrl;
  private String resultText;
}
