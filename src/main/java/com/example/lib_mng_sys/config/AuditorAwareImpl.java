package com.example.lib_mng_sys.config;


import com.example.lib_mng_sys.security.util.UserIdentity;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Configuration
@AllArgsConstructor
@Component("AuditorAwareImpl")
public class AuditorAwareImpl implements AuditorAware {
    private final UserIdentity userIdentity;

    @Override
    public Optional<String> getCurrentAuditor() {
        try {
            return Optional.ofNullable(userIdentity.getCurrentUserEmail());
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
