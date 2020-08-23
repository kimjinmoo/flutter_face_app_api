package com.grepiu.faceapp.application.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import com.grepiu.faceapp.application.domain.FaceBoardVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 *
 * 파이어 베이스 구현체
 *
 * <pre>
 *   v1
 * </pre>
 *
 */
@Service
@Slf4j
public class FirebaseServiceV1Impl implements FirebaseService {

  public static final String COL_NAME = "sample";

  @Override
  public FaceBoardVO findById(String id) throws Exception {
    Firestore firestore = FirestoreClient.getFirestore();
    DocumentReference documentReference = firestore.collection(COL_NAME).document(id);
    ApiFuture<DocumentSnapshot> apiFuture = documentReference.get();
    DocumentSnapshot documentSnapshot = apiFuture.get();
    FaceBoardVO samples = new FaceBoardVO();
    if(documentSnapshot.exists()) {
      samples = documentSnapshot.toObject(FaceBoardVO.class);
      return samples;
    }
    return null;
  }
}
