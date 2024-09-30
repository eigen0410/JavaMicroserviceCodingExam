package com.mycompany.customeraccount.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * The Class Savings
 */
@Entity
public class Savings {
  
  @Id
  @SequenceGenerator(
      name = "accnum_sequence",
      sequenceName = "accnum_sequence",
      initialValue=10000,
      allocationSize = 1
  )
  @GeneratedValue(
      strategy = GenerationType.SEQUENCE,
      generator = "accnum_sequence"
  )
  /** The account number. */  
  private long accountNumber;
  
  @NotEmpty
  /** The account type. */  
  private String accountType;
  
  @NotNull
  /** The available balance. */  
  private long availableBalance;
  
  /**
   * Gets account number.
   * 
   * @return accountNumber the account number
   */
  public long getAccountNumber() {
    return accountNumber;
  }
  
  /**
   * Sets account number.
   * 
   * @param accountNumber the account number
   */
  public void setAccountNumber(long accountNumber) {
    this.accountNumber = accountNumber;
  }
  
  /**
   * Gets account type.
   * 
   * @return accountType the account type
   */
  public String getAccountType() {
    return accountType;
  }
  
  /**
   * Sets account type.
   * 
   * @param accountType the account type
   */
  public void setAccountType(String accountType) {
    this.accountType = accountType;
  }
  
  /**
   * Gets available balance.
   * 
   * @return availableBalance the available balance
   */
  public long getAvailableBalance() {
    return availableBalance;
  }
  
  /**
   * Sets available balance.
   * 
   * @param availableBalance the available balance
   */
  public void setAvailableBalance(long availableBalance) {
    this.availableBalance = availableBalance;
  }
  
}
