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

  /**
   *
   * 이미지를 불러온다.
   *
   * @param fileName 파일명
   * @return 경로
   */
  public String fetchImageUrl(String fileName);
}
