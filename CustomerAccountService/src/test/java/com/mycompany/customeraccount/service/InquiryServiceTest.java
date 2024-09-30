package com.mycompany.customeraccount.service;

import static com.mycompany.customeraccount.constants.InquiryServiceConstants.CUSTOMER_ACCOUNT_FOUND;
import static com.mycompany.customeraccount.constants.InquiryServiceConstants.CUSTOMER_NOT_FOUND;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.HttpStatus;
import org.springframework.test.util.ReflectionTestUtils;
import com.google.gson.Gson;
import com.mycompany.customeraccount.model.AccountInquiryResponse;
import com.mycompany.customeraccount.model.CustomerAccount;
import com.mycompany.customeraccount.model.Savings;
import com.mycompany.customeraccount.repository.CustomerAccountRepository;

/**
 * The Class RegistrationServiceTest
 */
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class InquiryServiceTest {

  /** The customer account repository. */
  @Mock
  CustomerAccountRepository accountRepo;
  /** The inquiry service. */
  @InjectMocks
  InquiryServiceImpl service;
  /** The gson. */
  private Gson gson = new Gson();
  /** The customer account. */
  private Optional<CustomerAccount> idFound;

  /**
   * The setUp.
   * 
   */
  @BeforeEach
  void setUp() {
    ReflectionTestUtils.setField(service, "accountRepo", accountRepo);
    CustomerAccount customerAccount = new CustomerAccount();
    customerAccount.setCustomerNumber(10000000);
    customerAccount.setCustomerName("Test");
    customerAccount.setCustomerName("09081234567");
    customerAccount.setCustomerName("test12345@gmail.com");
    customerAccount.setCustomerName("test");
    customerAccount.setCustomerName("test");
    List<Savings> savingsList = new ArrayList<>();
    Savings savings = new Savings();
    savings.setAccountNumber(10000);
    savings.setAccountType("Savings");
    savings.setAvailableBalance(0);
    savingsList.add(savings);
    customerAccount.setSavings(savingsList);
    
    idFound = Optional.of(customerAccount);
    Mockito.when(accountRepo.findById(ArgumentMatchers.any())).thenReturn(idFound);
  }

  /**
   * Test inquire account found.
   * 
   */
  @Test
  void inquireAccount_Found() {
    AccountInquiryResponse response = service.inquireAccount("10000000");
    
    assertThat(response).isNotNull();
    assertThat(response.getCustomerAccount().getCustomerNumber()).isEqualTo(10000000);
    assertThat(response.getTransactionStatusCode()).isEqualTo(HttpStatus.FOUND.value());
    assertThat(response.getTransactionStatusDescription()).isEqualTo(CUSTOMER_ACCOUNT_FOUND);
  }

  /**
   * Test inquire account not found.
   * 
   */
  @Test
  void inquireAccount_NotFound() {
    idFound = Optional.empty();
    Mockito.when(accountRepo.findById(ArgumentMatchers.any())).thenReturn(idFound);
    AccountInquiryResponse response = service.inquireAccount("1");
    
    assertThat(response).isNotNull();
    assertThat(response.getCustomerAccount()).isNull();
    assertThat(response.getTransactionStatusCode()).isEqualTo(HttpStatus.NOT_FOUND.value());
    assertThat(response.getTransactionStatusDescription()).isEqualTo(CUSTOMER_NOT_FOUND);
  }

  /**
   * Test inquire account exception.
   * 
   */
  @Test
  void inquireAccount_Exception() {
    AccountInquiryResponse response = service.inquireAccount("asdsd");
    
    assertThat(response).isNotNull();
    assertThat(response.getCustomerAccount()).isNull();
    assertThat(response.getTransactionStatusCode()).isEqualTo(HttpStatus.NOT_FOUND.value());
    assertThat(response.getTransactionStatusDescription()).isEqualTo(CUSTOMER_NOT_FOUND);
  }
}
