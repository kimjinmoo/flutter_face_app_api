package com.grepiu.faceapp.application.service;

import com.grepiu.faceapp.application.domain.FaceResultVO;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * PrototypeService 구현체
 *
 */
@Service
public class PrototypeServiceImpl implements PrototypeService {

  @Override
  public FaceResultVO executeEngine(MultipartFile file) {
    return null;
  }
}
