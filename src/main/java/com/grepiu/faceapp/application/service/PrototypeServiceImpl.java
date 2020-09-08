package com.grepiu.faceapp.application.service;

import com.grepiu.faceapp.application.domain.AWSFileUploadVO;
import com.grepiu.faceapp.application.domain.FaceResultVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * PrototypeService 구현체
 */
@Service
@RequiredArgsConstructor
public class PrototypeServiceImpl implements PrototypeService {

  final AWSService awsService;

  final FirebaseService firebaseService;

  @Override
  public FaceResultVO executeEngine(MultipartFile file) {
    AWSFileUploadVO fileUploadVO = awsService.uploadFile(file);
    return FaceResultVO
        .builder()
        .imageUrl(fileUploadVO.getFileUrl())
        .resultText(fileUploadVO.getMessage())
        .build();
  }

  @Override
  public String fetchImageUrl(String fileName) {
    return awsService.fetchImage(fileName).getImageUrl();
  }
}
