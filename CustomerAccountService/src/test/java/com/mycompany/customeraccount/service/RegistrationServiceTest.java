package com.mycompany.customeraccount.service;

import static com.mycompany.customeraccount.constants.RegistrationServiceConstants.ACCOUNT_CREATED_DESC;
import static com.mycompany.customeraccount.constants.RegistrationServiceConstants.ACCOUNT_TYPE_REQUIRED_DESC;
import static com.mycompany.customeraccount.constants.RegistrationServiceConstants.ADDRESS1_INVALID_LENGTH;
import static com.mycompany.customeraccount.constants.RegistrationServiceConstants.ADDRESS1_REQUIRED_DESC;
import static com.mycompany.customeraccount.constants.RegistrationServiceConstants.ADDRESS2_INVALID_LENGTH;
import static com.mycompany.customeraccount.constants.RegistrationServiceConstants.EMAIL_INVALID_LENGTH;
import static com.mycompany.customeraccount.constants.RegistrationServiceConstants.EMAIL_REQUIRED_DESC;
import static com.mycompany.customeraccount.constants.RegistrationServiceConstants.INVALID_ACCOUNT_TYPE_DESC;
import static com.mycompany.customeraccount.constants.RegistrationServiceConstants.MOBILE_INVALID_LENGTH;
import static com.mycompany.customeraccount.constants.RegistrationServiceConstants.MOBILE_REQUIRED_DESC;
import static com.mycompany.customeraccount.constants.RegistrationServiceConstants.NAME_INVALID_LENGTH;
import static com.mycompany.customeraccount.constants.RegistrationServiceConstants.NAME_REQUIRED_DESC;
import static com.mycompany.customeraccount.constants.TestConstants.ACCOUNT_CREATION_REQUEST;
import static com.mycompany.customeraccount.constants.TestConstants.INVALID_CHAR_LENGTH;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doThrow;
import java.util.ArrayList;
import java.util.List;
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
import org.springframework.transaction.TransactionSystemException;
import com.google.gson.Gson;
import com.mycompany.customeraccount.model.CustomerAccount;
import com.mycompany.customeraccount.model.RegistrationRequest;
import com.mycompany.customeraccount.model.RegistrationResponse;
import com.mycompany.customeraccount.model.Savings;
import com.mycompany.customeraccount.repository.CustomerAccountRepository;

/**
 * The Class RegistrationServiceTest
 */
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class RegistrationServiceTest {
  
  /** The customer account repository. */
  @Mock
  CustomerAccountRepository accountRepo;
  /** The registration service. */
  @InjectMocks
  RegistrationServiceImpl service;
  /** The gson. */
  private Gson gson = new Gson();

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

    Mockito.when(accountRepo.save(ArgumentMatchers.any())).thenReturn(customerAccount);
  }

  /**
   * Test register account success.
   * 
   */
  @Test
  void registerAccount_Success() {
    RegistrationRequest request =
        gson.fromJson(ACCOUNT_CREATION_REQUEST, RegistrationRequest.class);
    RegistrationResponse response = service.registerAccount(request);
    
    assertThat(response).isNotNull();
    assertThat(response.getCustomerNumber()).isEqualTo(10000000);
    assertThat(response.getTransactionStatusCode()).isEqualTo(HttpStatus.CREATED.value());
    assertThat(response.getTransactionStatusDescription()).isEqualTo(ACCOUNT_CREATED_DESC);
  }

  /**
   * Test register account invalid account type.
   * 
   */
  @Test
  void registerAccount_InvalidAccountType() {
    RegistrationRequest request =
        gson.fromJson(ACCOUNT_CREATION_REQUEST, RegistrationRequest.class);
    request.setAccountType("INVALID");
    RegistrationResponse response = service.registerAccount(request);
    
    assertThat(response).isNotNull();
    assertThat(response.getCustomerNumber()).isNull();
    assertThat(response.getTransactionStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    assertThat(response.getTransactionStatusDescription()).isEqualTo(INVALID_ACCOUNT_TYPE_DESC);
  }

  /**
   * Test register account invalid customer name length.
   * 
   */
  @Test
  void registerAccount_InvalidNameLength() {
    RegistrationRequest request =
        gson.fromJson(ACCOUNT_CREATION_REQUEST, RegistrationRequest.class);
    request.setCustomerName(INVALID_CHAR_LENGTH);
    
    doThrow(new TransactionSystemException("Error")).when(accountRepo).save(ArgumentMatchers.any());
    RegistrationResponse response = service.registerAccount(request);
    
    assertThat(response).isNotNull();
    assertThat(response.getCustomerNumber()).isNull();
    assertThat(response.getTransactionStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    assertThat(response.getTransactionStatusDescription()).isEqualTo(NAME_INVALID_LENGTH);
  }

  /**
   * Test register account invalid customer mobile length.
   * 
   */
  @Test
  void registerAccount_InvalidMobileLength() {
    RegistrationRequest request =
        gson.fromJson(ACCOUNT_CREATION_REQUEST, RegistrationRequest.class);
    request.setCustomerMobile(INVALID_CHAR_LENGTH);
    
    doThrow(new TransactionSystemException("Error")).when(accountRepo).save(ArgumentMatchers.any());
    RegistrationResponse response = service.registerAccount(request);
    
    assertThat(response).isNotNull();
    assertThat(response.getCustomerNumber()).isNull();
    assertThat(response.getTransactionStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    assertThat(response.getTransactionStatusDescription()).isEqualTo(MOBILE_INVALID_LENGTH);
  }

  /**
   * Test register account invalid customer email length.
   * 
   */
  @Test
  void registerAccount_InvalidEmailLength() {
    RegistrationRequest request =
        gson.fromJson(ACCOUNT_CREATION_REQUEST, RegistrationRequest.class);
    request.setCustomerEmail(INVALID_CHAR_LENGTH);
    
    doThrow(new TransactionSystemException("Error")).when(accountRepo).save(ArgumentMatchers.any());
    RegistrationResponse response = service.registerAccount(request);
    
    assertThat(response).isNotNull();
    assertThat(response.getCustomerNumber()).isNull();
    assertThat(response.getTransactionStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    assertThat(response.getTransactionStatusDescription()).isEqualTo(EMAIL_INVALID_LENGTH);
  }

  /**
   * Test register account invalid address 1 length.
   * 
   */
  @Test
  void registerAccount_InvalidAddress1Length() {
    RegistrationRequest request =
        gson.fromJson(ACCOUNT_CREATION_REQUEST, RegistrationRequest.class);
    request.setAddress1(INVALID_CHAR_LENGTH);
    
    doThrow(new TransactionSystemException("Error")).when(accountRepo).save(ArgumentMatchers.any());
    RegistrationResponse response = service.registerAccount(request);
    
    assertThat(response).isNotNull();
    assertThat(response.getCustomerNumber()).isNull();
    assertThat(response.getTransactionStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    assertThat(response.getTransactionStatusDescription()).isEqualTo(ADDRESS1_INVALID_LENGTH);
  }

  /**
   * Test register account invalid address 2 length.
   * 
   */
  @Test
  void registerAccount_InvalidAddress2Length() {
    RegistrationRequest request =
        gson.fromJson(ACCOUNT_CREATION_REQUEST, RegistrationRequest.class);
    request.setAddress2(INVALID_CHAR_LENGTH);
    
    doThrow(new TransactionSystemException("Error")).when(accountRepo).save(ArgumentMatchers.any());
    RegistrationResponse response = service.registerAccount(request);
    
    assertThat(response).isNotNull();
    assertThat(response.getCustomerNumber()).isNull();
    assertThat(response.getTransactionStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    assertThat(response.getTransactionStatusDescription()).isEqualTo(ADDRESS2_INVALID_LENGTH);
  }

  /**
   * Test register account empty name.
   * 
   */
  @Test
  void registerAccount_EmptyName() {
    RegistrationRequest request =
        gson.fromJson(ACCOUNT_CREATION_REQUEST, RegistrationRequest.class);
    request.setCustomerName(null);
    doThrow(new NullPointerException("Error")).when(accountRepo).save(ArgumentMatchers.any());
    RegistrationResponse response = service.registerAccount(request);
    
    assertThat(response).isNotNull();
    assertThat(response.getCustomerNumber()).isNull();
    assertThat(response.getTransactionStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    assertThat(response.getTransactionStatusDescription()).isEqualTo(NAME_REQUIRED_DESC);
  }

  /**
   * Test register account empty mobile.
   * 
   */
  @Test
  void registerAccount_EmptyMobile() {
    RegistrationRequest request =
        gson.fromJson(ACCOUNT_CREATION_REQUEST, RegistrationRequest.class);
    request.setCustomerMobile(null);
    doThrow(new NullPointerException("Error")).when(accountRepo).save(ArgumentMatchers.any());
    RegistrationResponse response = service.registerAccount(request);
    
    assertThat(response).isNotNull();
    assertThat(response.getCustomerNumber()).isNull();
    assertThat(response.getTransactionStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    assertThat(response.getTransactionStatusDescription()).isEqualTo(MOBILE_REQUIRED_DESC);
  }

  /**
   * Test register account empty email.
   * 
   */
  @Test
  void registerAccount_EmptyEmail() {
    RegistrationRequest request =
        gson.fromJson(ACCOUNT_CREATION_REQUEST, RegistrationRequest.class);
    request.setCustomerEmail(null);
    doThrow(new NullPointerException("Error")).when(accountRepo).save(ArgumentMatchers.any());
    RegistrationResponse response = service.registerAccount(request);
    
    assertThat(response).isNotNull();
    assertThat(response.getCustomerNumber()).isNull();
    assertThat(response.getTransactionStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    assertThat(response.getTransactionStatusDescription()).isEqualTo(EMAIL_REQUIRED_DESC);
  }

  /**
   * Test register account empty address.
   * 
   */
  @Test
  void registerAccount_EmptyAddress() {
    RegistrationRequest request =
        gson.fromJson(ACCOUNT_CREATION_REQUEST, RegistrationRequest.class);
    request.setAddress1(null);
    doThrow(new NullPointerException("Error")).when(accountRepo).save(ArgumentMatchers.any());
    RegistrationResponse response = service.registerAccount(request);
    
    assertThat(response).isNotNull();
    assertThat(response.getCustomerNumber()).isNull();
    assertThat(response.getTransactionStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    assertThat(response.getTransactionStatusDescription()).isEqualTo(ADDRESS1_REQUIRED_DESC);
  }

  /**
   * Test register account empty account type.
   * 
   */
  @Test
  void registerAccount_EmptyAccountType() {
    RegistrationRequest request =
        gson.fromJson(ACCOUNT_CREATION_REQUEST, RegistrationRequest.class);
    request.setAccountType(null);
    RegistrationResponse response = service.registerAccount(request);
    
    assertThat(response).isNotNull();
    assertThat(response.getCustomerNumber()).isNull();
    assertThat(response.getTransactionStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    assertThat(response.getTransactionStatusDescription()).isEqualTo(ACCOUNT_TYPE_REQUIRED_DESC);
  }
}
