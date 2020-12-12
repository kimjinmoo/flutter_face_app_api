package com.grepiu.faceapp.application.service;

import com.grepiu.faceapp.application.domain.FaceBoardVO;
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
  FaceResultVO executeEngine(MultipartFile file) throws Exception;

  /**
   *
   * 이미지를 불러온다.
   *
   * @param id DB ID
   * @return 경로
   */
  FaceBoardVO fetchFaceImageById(String id) throws Exception;
}
