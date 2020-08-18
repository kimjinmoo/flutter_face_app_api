package com.grepiu.faceapp.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import java.io.FileInputStream;
import org.springframework.stereotype.Service;

@Service
public class FirebaseInitialize {

  public void initialize() {
    try {
      FileInputStream serviceAccount =
          new FileInputStream("C:/workgroup/service.json");

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
