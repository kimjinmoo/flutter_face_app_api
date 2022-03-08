package com.grepiu.faceapp.application.service;

import com.google.cloud.Timestamp;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.grepiu.faceapp.application.domain.AWSFileUploadVO;
import com.grepiu.faceapp.application.domain.FaceBoardVO;
import com.grepiu.faceapp.application.domain.FaceResultVO;
import com.grepiu.faceapp.extend.Middleware;
import com.grepiu.faceapp.extend.S3ServiceClient;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * PrototypeService 구현체
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class PrototypeServiceImpl implements PrototypeService {

  final AWSService awsService;

  final FirebaseService firebaseService;

  final Middleware middleware;

  @Override
  public FaceResultVO executeEngine(MultipartFile file, String uid, String pushId)
      throws Exception {
    AWSFileUploadVO fileUploadVO = awsService
        .uploadFile(S3ServiceClient.FACE.getServicePath() + uid + "/", file);
    // set Id
    String fileUid = UUID.randomUUID().toString();
    // save
    firebaseService.save(
        FaceBoardVO.builder()
            .id(fileUid)
            .uid(uid)
            .pushId(pushId)
            .oriImagePath(fileUploadVO.getFileUrl())
            .message(fileUploadVO.getMessage())
            .regDate(Timestamp.now())
            .build()
    );
    // 엔진 실행
    middleware.executeEngine(
        fileUid,
        fileUploadVO.getFileUrl()
    );
    // 결과값
    return FaceResultVO
        .builder()
        .id(fileUid)
        .imageUrl(fileUploadVO.getFileUrl())
        .resultText(fileUploadVO.getMessage())
        .build();
  }

  @Override
  public FaceResultVO callbackEngine(String id, String resultJson) throws Exception {
    FaceBoardVO vo = firebaseService.findById(id);
    vo.setResultJson(resultJson);
    log.info("d: {}", resultJson);
    // DB 업데이트
    firebaseService.save(vo);
    // push로 전파
    Message message = Message.builder()
        .putData("result","OK")
        .setToken(vo.getPushId())
        .build();

    FirebaseMessaging.getInstance().send(message);
    return FaceResultVO
        .builder()
        .id(vo.getId())
        .imageUrl(vo.getOriImagePath())
        .resultText(vo.getResultJson())
        .build();
  }

  @Override
  public FaceBoardVO fetchFaceImageById(String id) throws Exception {
    return firebaseService.findById(id);
  }

  @Override
  public List<FaceResultVO> fetchFaceImageByUid(String uid) throws Exception {
    return firebaseService.findByUid(uid);
  }

  @Override
  public int deleteById(String id) throws Exception {
    FaceBoardVO face = firebaseService.findById(id);
    if(Optional.ofNullable(face).isPresent()) {
      log.info("delete : {}", face);
      // firebase 삭제
      firebaseService.delete(face.getId());
      // s3 삭제
      awsService.deleteFaceImage(S3ServiceClient.FACE.getServicePath(), face.getOriImagePath());
    }
    return 1;
  }
}
