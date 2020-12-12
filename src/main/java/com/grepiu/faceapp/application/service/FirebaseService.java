package com.grepiu.faceapp.application.service;

import com.grepiu.faceapp.application.domain.FaceBoardVO;

/**
 * 파이어 베이스 데이터 베이스를 처리한다.
 */
public interface FirebaseService {

  /**
   * id를 통해 이미지를 가져온다.
   *
   * @param id ID
   * @return FaceBoardVO
   * @throws Exception 예외
   */
  FaceBoardVO findById(String id) throws Exception;

  /**
   * 이미지를 저장한다.
   *
   * @param vo FaceBoardVO
   * @return 등록건수
   * @throws Exception 예
   */
  int save(FaceBoardVO vo) throws Exception;
}
