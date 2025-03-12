package com.java_backend_code_challenge.hexagon.infrastructure;

import com.java_backend_code_challenge.hexagon.application.HexagonUserFinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HexagonUserController {

    private final HexagonUserFinder huf;

    @Autowired
    public HexagonUserController(HexagonUserFinder huf){
        this.huf = huf;
    }

    @GetMapping("/api/husers/")
    public String getListOfUsers(){
        return huf.findUsers();
    };

    @GetMapping("/api/husers/{username}/")
    public String getIndividualUser(@PathVariable String username){
        return huf.findUser(username);
    };

}
