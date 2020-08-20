package com.grepiu.faceapp.application.controller;

import com.grepiu.faceapp.application.domain.Sample;
import com.grepiu.faceapp.application.service.FirebaseService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {

  private final FirebaseService firebaseService;

  public SampleController(FirebaseService firebaseService) {
    this.firebaseService = firebaseService;
  }

  @GetMapping("/sample")
  public Sample findSampleAll() throws Exception {
    return firebaseService.findSampleAll();
  }
}
