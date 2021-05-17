package com.pcc.pc_configurator;

import com.pcc.pc_configurator.repositories.CpuRepository;
import com.pcc.pc_configurator.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class Start {

    private UserRepository userRepository;

    @Autowired
    public Start(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void runExample() {
        //nowy rekord
        //User user = new User("d","b","c");
        //userRepo.save(user);
        //wyświetlenie rekordów
        //Iterable<Cpu> all = cpuRepo.findAll();
        //all.forEach(System.out::println);
        //usuwanie
        //userRepo.deleteById(1129L);
        //przykłąd listy
        //Iterable<User> allEmail = userRepo.findAllByEmail("emalinsro@clickbank.net");
        //allEmail.forEach(System.out::println);

    }
}
