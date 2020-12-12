package com.grepiu.faceapp.application.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import com.grepiu.faceapp.application.domain.FaceBoardVO;
import java.util.HashMap;
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
    if(documentSnapshot.exists()) {
      return documentSnapshot.toObject(FaceBoardVO.class);
    }
    return null;
  }

  @Override
  public int save(FaceBoardVO vo) throws Exception {
    Firestore firestore = FirestoreClient.getFirestore();
    ApiFuture<WriteResult> future = firestore.collection(COL_NAME).document(vo.getId()).set(vo);
    log.info( "update tile : {}", future.get().getUpdateTime());
    return 1;
  }
}
