package com.grepiu.faceapp.application.controller;

import com.grepiu.faceapp.application.domain.FaceResultVO;
import com.grepiu.faceapp.application.domain.FaceBoardVO;
import com.grepiu.faceapp.application.service.FirebaseService;
import com.grepiu.faceapp.application.service.PrototypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "/prototype")
@RequiredArgsConstructor
public class PrototypeController {

  private final PrototypeService prototypeService;

  @Operation(
      summary = "이미지 처리 API",
      description = "이미지 처리 후 결과를 리턴한다.",
      tags = "프로토타입"
  )
  @ApiResponses(
      value = {
          @ApiResponse(responseCode = "200", description = "프로세스 처리 완료")
      }
  )
  @PostMapping(value = "/engine/images", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public ResponseEntity<FaceResultVO> upload(
      @RequestParam("file") MultipartFile images
  ) throws Exception {
    return ResponseEntity.ok().body(prototypeService.executeEngine(images));
  }

  @Operation(
      summary = "처리 이미지 다시 보기",
      description = "이미지 처리된 과거 데이터를 불러온다.",
      tags = "프로토타입")
  @GetMapping(value = "/image/{id}")
  public ResponseEntity<Object> findById(@PathVariable("id") String id) throws Exception {
    return ResponseEntity.ok(prototypeService.fetchFaceImageById(id));
  }
}
