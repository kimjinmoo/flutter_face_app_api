package com.grepiu.faceapp.application.domain;

import com.google.cloud.Timestamp;
import java.util.List;
import lombok.Builder;
import lombok.Data;

/**
 *
 * 얼굴 결과 Value Object
 *
 * <pre>
 *   서버에서 응답 인터페이스를 정의한다.
 * </pre>
 */
@Builder
@Data
public class FaceResultVO {
  private String id;
  private String imageUrl;
  private String resultText;
  private String json;
  private List<Face> face;
  private Timestamp regDate;
}
