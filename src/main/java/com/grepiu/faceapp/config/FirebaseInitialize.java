package com.grepiu.faceapp.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import java.io.FileInputStream;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Service;

@Service
public class FirebaseInitialize {

  @PostConstruct
  public void initialize() {
    try {
      FileInputStream serviceAccount =
          new FileInputStream("C:/workgroup/services.json");

      FirebaseOptions options = new FirebaseOptions.Builder()
          .setCredentials(GoogleCredentials.fromStream(serviceAccount))
          .setDatabaseUrl("https://faceapp-c81a6.firebaseio.com")
          .build();

      FirebaseApp.initializeApp(options);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
