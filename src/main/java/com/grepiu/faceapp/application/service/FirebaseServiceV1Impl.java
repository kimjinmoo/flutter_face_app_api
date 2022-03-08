package com.grepiu.faceapp.application.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.Timestamp;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.Query.Direction;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;
import com.google.gson.Gson;
import com.grepiu.faceapp.application.domain.FaceBoardVO;
import com.grepiu.faceapp.application.domain.FaceResultVO;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 파이어 베이스 구현체
 *
 * <pre>
 *   v1
 * </pre>
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
    if (documentSnapshot.exists()) {
      return documentSnapshot.toObject(FaceBoardVO.class);
    }
    return null;
  }

  @Override
  public List<FaceResultVO> findByUid(String uid) throws Exception {
    Firestore firestore = FirestoreClient.getFirestore();
    Query capitalCities = firestore.collection(COL_NAME).whereEqualTo("uid", uid);

    ApiFuture<QuerySnapshot> apiFuture = capitalCities.orderBy("regDate", Direction.DESCENDING)
        .get();
    QuerySnapshot querySnapshot = apiFuture.get();
    List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
    if (!documents.isEmpty()) {
      List<FaceResultVO> list = new ArrayList<>();
      documents.forEach(o -> {
        String json = o.get("resultJson", String.class);
        log.info("json : {}", json);
        list.add(FaceResultVO
            .builder().id(o.getId())
            .imageUrl(o.get("oriImagePath", String.class))
            .json(json)
            .face(new Gson().fromJson(json, List.class))
            .regDate(o.get("regDate", Timestamp.class))
            .build());
      });
      return list;
    }
    return null;
  }

  @Override
  public int save(FaceBoardVO vo) throws Exception {
    // init
    Firestore firestore = FirestoreClient.getFirestore();
    // save
    firestore.collection(COL_NAME).document(vo.getId()).set(vo);
    return 1;
  }

  @Override
  public int delete(String id) throws Exception {
    // init
    Firestore firestore = FirestoreClient.getFirestore();
    // delete
    firestore.collection(COL_NAME).document(id).delete();

    return 1;
  }
}
