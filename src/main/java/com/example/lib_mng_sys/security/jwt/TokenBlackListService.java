package com.example.lib_mng_sys.security.jwt;


import com.example.lib_mng_sys.model.TokenBlackList;
import com.example.lib_mng_sys.repository.TokenBlackListRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TokenBlackListService {

    private final JWTService jwtService;
    private final TokenBlackListRepository tokenBlackListRepository;

    public boolean isBlackListed(String token) {

        return tokenBlackListRepository.existsByToken(token);
    }

    public void blackListToken(String token) {
        long exp = jwtService.parseToken(token).getExpiration().getTime();

        TokenBlackList tokenBlackList = new TokenBlackList();
        tokenBlackList.setToken(token);
        tokenBlackList.setExpiration(exp);

        tokenBlackListRepository.save(tokenBlackList);
    }

}
