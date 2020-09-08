package com.grepiu.faceapp.application.domain;

import lombok.Builder;
import lombok.Data;

/**
 * 
 * AWS 이미지 정보
 * 
 */
@Builder
@Data
public class AWSImageInfoVO {
  private String imageUrl;
}
