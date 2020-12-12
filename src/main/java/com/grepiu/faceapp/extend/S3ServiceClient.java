package com.grepiu.faceapp.extend;

/**
 *
 * Aws s3 Service
 *
 */
public enum S3ServiceClient {
  FACE("face/");

  S3ServiceClient(String service) {
    this.service = service;
  }

  // 서비스
  private String service;

  // 경로를 가져온다.
  public String getServicePath() {
    return this.service;
  }
}
