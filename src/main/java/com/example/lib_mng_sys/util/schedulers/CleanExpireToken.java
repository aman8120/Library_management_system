package com.example.lib_mng_sys.util.schedulers;


import com.example.lib_mng_sys.model.TokenBlackList;
import com.example.lib_mng_sys.repository.TokenBlackListRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Slf4j
@Component
@AllArgsConstructor
public class CleanExpireToken {


    private final TokenBlackListRepository tokenBlackListRepository;


    @Transactional
    @Scheduled(cron = "0 5 * * * *") // Runs every 5 Min
    public void cleanExpireTokens() {
        List<TokenBlackList> tokens = tokenBlackListRepository.findByExpirationLessThan(Instant.now().toEpochMilli());
        tokenBlackListRepository.deleteAll(tokens);
        log.info("Deleted {} expired tokens.", tokens.size());
    }

}
