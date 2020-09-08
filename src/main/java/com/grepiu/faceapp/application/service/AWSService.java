package com.grepiu.faceapp.application.service;

import com.grepiu.faceapp.application.domain.AWSFileUploadVO;
import com.grepiu.faceapp.application.domain.AWSImageInfoVO;
import java.io.File;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * AWS 서비스를 처리한다.
 *
 */
public interface AWSService {

  public AWSFileUploadVO uploadFile(final MultipartFile multipartFile);

  public AWSImageInfoVO fetchImage(final String fileName);
}
