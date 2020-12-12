package com.grepiu.faceapp.application.service;

import com.grepiu.faceapp.application.domain.AWSFileUploadVO;
import com.grepiu.faceapp.application.domain.FaceBoardVO;
import com.grepiu.faceapp.application.domain.FaceResultVO;
import com.grepiu.faceapp.extend.S3ServiceClient;
import java.util.UUID;
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
  public FaceResultVO executeEngine(MultipartFile file) throws Exception {
    AWSFileUploadVO fileUploadVO = awsService
        .uploadFile(S3ServiceClient.FACE.getServicePath(), file);
    // set Id
    String id = UUID.randomUUID().toString();
    // save
    firebaseService.save(
        FaceBoardVO.builder()
            .id(id)
            .oriImagePath(fileUploadVO.getFileUrl())
            .message(fileUploadVO.getMessage())
            .build()
    );
    // 결과값
    return FaceResultVO
        .builder()
        .id(id)
        .imageUrl(fileUploadVO.getFileUrl())
        .resultText(fileUploadVO.getMessage())
        .build();
  }

  @Override
  public FaceBoardVO fetchFaceImageById(String id) throws Exception {
    //awsService.fetchImage(fileName).getImageUrl()
    return firebaseService.findById(id);
  }
}