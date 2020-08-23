package com.grepiu.faceapp.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.FirestoreOptions;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import java.io.FileInputStream;
import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 *
 * 파이어 베이스를 초기화 한다.
 *
 * <pre>
 *   application.properties에 grepiu.firebase.account.path 인증
 * </pre>
 *
 */
@Service
public class FirebaseInitialize {

  @Value(value = "${grepiu.firebase.account.path}")
  private String accountPath;

  @PostConstruct
  public void initialize() {
    try {
      FileInputStream serviceAccount =
          new FileInputStream(accountPath);

      FirebaseOptions options = new FirebaseOptions.Builder()
          .setCredentials(GoogleCredentials.fromStream(serviceAccount))
          .setDatabaseUrl("https://faceapp-c81a6.firebaseio.com")
          .setFirestoreOptions(FirestoreOptions.newBuilder().build())
          .build();

      FirebaseApp.initializeApp(options);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
