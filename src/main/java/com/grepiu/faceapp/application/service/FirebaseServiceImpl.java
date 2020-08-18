package com.grepiu.faceapp.application.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import com.grepiu.faceapp.application.domain.Sample;
import org.springframework.stereotype.Service;

@Service
public class FirebaseServiceImpl implements FirebaseService {

  public static final String COL_NAME = "sample";

  @Override
  public Sample findSampleAll() throws Exception {
    Firestore firestore = FirestoreClient.getFirestore();
    DocumentReference documentReference = firestore.collection(COL_NAME).document();
    ApiFuture<DocumentSnapshot> apiFuture = documentReference.get();
    DocumentSnapshot documentSnapshot = apiFuture.get();
    Sample samples = new Sample();
    if(documentSnapshot.exists()) {
      samples = documentSnapshot.toObject(Sample.class);
      return samples;
    }
    return null;
  }
}
