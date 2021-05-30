package com.pcc.pc_configurator.Controller;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.pcc.pc_configurator.DTO.UserDTO;
import com.pcc.pc_configurator.Login.FirebaseInitializer;
import com.pcc.pc_configurator.entities.User;
import com.pcc.pc_configurator.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@CrossOrigin("*")
public class UserController {
    private final UserRepository userRepository;

    List<UserDTO> userDtoList = new ArrayList<>();

    @Autowired
    FirebaseInitializer firebaseInitializer;

    @Autowired
    public void userToDto(ModelMapper modelMapper) {
        for(var user : userRepository.findAll())
            userDtoList.add(modelMapper.map(user,UserDTO.class));
    }

    @GetMapping("/test")
    public List<User> test() throws ExecutionException, InterruptedException {
        List<User> userList = new ArrayList<>();
        CollectionReference fbDB = firebaseInitializer.getFirebase().collection("user");
        ApiFuture<QuerySnapshot> querySnapshotApiFuture = fbDB.get();
        for(DocumentSnapshot doc:querySnapshotApiFuture.get().getDocuments()) {
            User user = doc.toObject(User.class);
            userList.add(user);
        }
        return userList;
    }

    //@Bean
    //public void fillFirebase() {
    //    CollectionReference fbDB = firebaseInitializer.getFirebase().collection("user");
    //    ApiFuture<WriteResult> writeResultApiFuture = fbDB.document().set(userDtoList);
    //    try {
    //        System.out.println(writeResultApiFuture.get().getUpdateTime());
    //    } catch (InterruptedException e) {
    //        e.printStackTrace();
    //    } catch (ExecutionException e) {
    //        e.printStackTrace();
    //    }
    //}

    @GetMapping
    public List<UserDTO> getAllUser() { return userDtoList;}
}