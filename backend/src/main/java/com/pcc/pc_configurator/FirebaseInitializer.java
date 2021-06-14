package com.pcc.pc_configurator;

import java.io.IOException;
import java.io.InputStream;

import javax.annotation.PostConstruct;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import org.springframework.stereotype.Service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

@Service
public class FirebaseInitializer {

    FirebaseApp app;
    FirebaseAuth defaultAuth;

    @PostConstruct
    private void initDB() throws IOException {
        InputStream serviceAccount = this.getClass().getClassLoader()
                .getResourceAsStream("./pc-conf-login-firebase-adminsdk-aa0io-7bcf50dc04.json");

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build();

        if (FirebaseApp.getApps().isEmpty()) {
            app = FirebaseApp.initializeApp(options);
            defaultAuth = FirebaseAuth.getInstance(app);
        }

    }

    public FirebaseApp getApp() {
        return app;
    }

    public Firestore getFirebase() {
        return FirestoreClient.getFirestore();
    }

    public String verifyTokenAndGetEmail(String idToken) throws FirebaseAuthException {
        return defaultAuth.verifyIdToken(idToken).getEmail();
    }
}
