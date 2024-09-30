package com.mycompany.customeraccount.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.mycompany.customeraccount.model.CustomerAccount;

/**
 * The Interface CustomerAccountRepository
 */
@Repository
public interface CustomerAccountRepository extends JpaRepository<CustomerAccount, Long> {

}
