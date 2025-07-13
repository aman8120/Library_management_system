package com.example.lib_mng_sys.repository;

import com.example.lib_mng_sys.model.TokenBlackList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface TokenBlackListRepository extends JpaRepository<TokenBlackList, String> {

   public boolean existsByToken(String token);


   @Modifying
   @Transactional
   List<TokenBlackList> findByExpirationLessThan(long time);

}
