package com.java_backend_code_challenge.HexagonUser.application;

import com.java_backend_code_challenge.HexagonUser.domain.HexagonUser;
import com.java_backend_code_challenge.HexagonUser.domain.HexagonUserService;
import com.java_backend_code_challenge.user.model.User;
import com.java_backend_code_challenge.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HexagonUserFinder {
    private HexagonUserService hexagonUserService;

    @Autowired
    public HexagonUserFinder(HexagonUserService hexagonUserService){
        this.hexagonUserService = hexagonUserService;
    }

    public String findUser(String username){
        return hexagonUserService.find_user_by_id(username).toString();
    };

    public String findUsers(){
        StringBuilder SB = new StringBuilder();
        for (HexagonUser user : hexagonUserService.find_all_users()){
            SB.append(user.toString());
        }
        return SB.toString();
    };

}
