package com.grepiu.faceapp.application.domain;

import com.google.cloud.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * 등록된 정보를 처리 한다.
 *
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FaceBoardVO {
  // 파일 ID
  private String id;
  // 업로드 유저
  private String uid;
  // PushId
  private String pushId;
  // 오리지널 이미지
  private String oriImagePath;
  // 처리된 이미지
  private String processImagePath;
  // 메세제
  private String message;
  // 등록일
  private Timestamp regDate;
  // 처리 JSON
  private String resultJson;
  // 머신 러닝 메시지
  private boolean isProcessed;
}