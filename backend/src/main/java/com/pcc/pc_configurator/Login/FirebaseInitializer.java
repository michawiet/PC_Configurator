package com.pcc.pc_configurator.Login;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;

@Service
public class FirebaseInitializer {

    @PostConstruct
    private void init() {
        try {
            FileInputStream serviceAccount = new FileInputStream("C://IdeaProjects/PC_Configurator/src/main/resources/pcconfigurator-cbe15-firebase-adminsdk-70qx7-7cdf23d507.json");
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://pcconfigurator-cbe15-default-rtdb.firebaseio.com")
                    .build();
            if(FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Firestore getFirebase() {
        return FirestoreClient.getFirestore();
    }
}
