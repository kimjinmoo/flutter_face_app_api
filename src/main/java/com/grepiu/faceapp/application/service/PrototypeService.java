package com.grepiu.faceapp.application.service;

import com.grepiu.faceapp.application.domain.FaceBoardVO;
import com.grepiu.faceapp.application.domain.FaceResultVO;
import java.util.List;
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
   * @param uid 유저 유니크 ID
   * @param pushId 유저 Push ID
   * @return FaceResultVO 객체
   */
  FaceResultVO executeEngine(MultipartFile file, String uid, String pushId) throws Exception;

  /**
   *
   * 엔진 callback 처리
   *
   * @param uid 유니크 ID
   * @param resultJson JSON
   * @return FaceResultVO
   * @throws Exception
   */
  FaceResultVO callbackEngine(String uid, String resultJson) throws Exception;
  
  /**
   *
   * 이미지를 불러온다.
   *
   * @param id DB ID
   * @return 경로
   */
  FaceBoardVO fetchFaceImageById(String id) throws Exception;

  /**
   *
   * 유저 업로드 이미지를 가져온다.
   *
   * @param uid 유저 ID
   * @return List<FaceBoardVO>
   * @throws Exception
   */
  List<FaceResultVO> fetchFaceImageByUid(String uid) throws Exception;

  /**
   *
   * 이미지를 삭제한다.
   *
   * @param id 삭제
   */
  int deleteById(String id) throws Exception;
}
