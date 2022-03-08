package com.grepiu.faceapp.application.service;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.grepiu.faceapp.application.domain.AWSFileUploadVO;
import com.grepiu.faceapp.application.domain.AWSImageInfoVO;
import com.grepiu.faceapp.extend.S3ServiceClient;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * AWS 서비스 구현체
 *
 * <pre>
 *   v1
 * </pre>
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class AWSServiceV1Impl implements AWSService {

  final private AmazonS3 amazonS3;

  @Value("${aws.s3.bucket}")
  private String bucket;

  @Override
  public AWSFileUploadVO uploadFile(final String prefixPath, final MultipartFile multipartFile) {
    try {
      final File file = convertMultiPartFileToFile(multipartFile);
      AWSFileUploadVO awsFile =  AWSFileUploadVO
          .builder()
          .fileUrl(uploadFileToS3Bucket(bucket, prefixPath, file))
          .isSuccess(true)
          .message("정상적으로 업로드 하였습니다.")
          .build();
      // 처리 후 파일은 삭제한다.
      file.delete();
      return awsFile;
    } catch (final AmazonServiceException ex) {
      log.info("file upload fail");
      log.error("error : {}", ex.getMessage());

      return AWSFileUploadVO
          .builder()
          .isSuccess(false)
          .message(ex.getMessage())
          .build();
    }
  }

  private File convertMultiPartFileToFile(final MultipartFile multipartFile) {
    final File file = new File(multipartFile.getOriginalFilename());
    try (final FileOutputStream outputStream = new FileOutputStream(file)) {
      outputStream.write(multipartFile.getBytes());
    } catch (final IOException ex) {
      log.error("Error converting the multi-part file to file= ", ex.getMessage());
    }
    return file;
  }

  /**
   *
   * AWS 에 파일 업로드
   *
   * @param bucketName 버킷명
   * @param file       파일
   * @param prefixPath 서비스 경로
   * @return AWS 이미지 경로
   */
  private String uploadFileToS3Bucket(final String bucketName, final String prefixPath, final File file) {
    final String uniqueFileName = prefixPath + UUID.randomUUID() + file.getName();
    final PutObjectRequest putObjectRequest = new PutObjectRequest(
        bucketName,
        uniqueFileName,
        file);
    amazonS3.putObject(putObjectRequest);
    // 파일을 업로드 한다.
    return amazonS3.getUrl(bucketName, uniqueFileName).toExternalForm();
  }

  /**
   * AWS에서 이미지 정보를 가져온다.
   *
   * @param fileName 파일명
   * @return AWSImageInfoVO 객체
   */
  @Override
  public AWSImageInfoVO fetchFaceImage(String fileName) {
    return AWSImageInfoVO
        .builder()
        .imageUrl(amazonS3.getUrl(bucket, S3ServiceClient.FACE.getServicePath()+fileName).toString())
        .build();
  }

  @Override
  public void deleteFaceImage(String prefixPath, String fileName) {
    log.info("delete path : {}", fileName.substring(fileName.indexOf(prefixPath)));
    final DeleteObjectRequest deleteObjectRequest = new DeleteObjectRequest(
        bucket,
        fileName.substring(fileName.indexOf(prefixPath))
        );
    amazonS3.deleteObject(deleteObjectRequest);
  }
}
