package com.grepiu.faceapp.application.domain;

import lombok.Builder;
import lombok.Data;

/**
 * 
 * 파일 업로드 결과
 * 
 */
@Builder
@Data
public class AWSFileUploadVO {
  private String fileUrl;
  private String message;
  private boolean isSuccess;
}
