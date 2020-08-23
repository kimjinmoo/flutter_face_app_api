package com.grepiu.faceapp.application.service;

import com.grepiu.faceapp.application.domain.FaceResultVO;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * API 서비스를 처리 한다.
 *
 */
public interface PrototypeService {

  /**
   *
   * 이미지를 처리 한다.
   *
   * @param file MultipartFile 객체
   * @return FaceResultVO 객체
   */
  public FaceResultVO executeEngine(MultipartFile file);
}
