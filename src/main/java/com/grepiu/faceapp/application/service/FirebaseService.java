package com.grepiu.faceapp.application.service;

import com.grepiu.faceapp.application.domain.FaceBoardVO;

/**
 *
 * 파이어 베이스 데이터 베이스를 처리한다.
 *
 */
public interface FirebaseService {

  public FaceBoardVO findById(String id) throws Exception;
}
