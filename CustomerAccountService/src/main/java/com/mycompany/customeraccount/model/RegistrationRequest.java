package com.mycompany.customeraccount.model;

/**
 * The Class RegistrationRequest
 */
public class RegistrationRequest {
  
  /** The customer name. */  
  private String customerName;
  /** The customer mobile. */  
  private String customerMobile;
  /** The customer email. */  
  private String customerEmail;
  /** The address 1. */  
  private String address1;
  /** The address 2. */  
  private String address2;
  /** The account type. */  
  private String accountType;
  
  /**
   * Gets customer name.
   * 
   * @return customerName the customer name
   */
  public String getCustomerName() {
    return customerName;
  }
  
  /**
   * Sets customer name.
   * 
   * @param customerName the customer name
   */
  public void setCustomerName(String customerName) {
    this.customerName = customerName;
  }
  
  /**
   * Gets customer mobile.
   * 
   * @return customerMobile the customer mobile
   */
  public String getCustomerMobile() {
    return customerMobile;
  }
  
  /**
   * Sets customer mobile.
   * 
   * @param customerMobile the customer mobile
   */
  public void setCustomerMobile(String customerMobile) {
    this.customerMobile = customerMobile;
  }
  
  /**
   * Gets customer email.
   * 
   * @return customerEmail the customer email
   */
  public String getCustomerEmail() {
    return customerEmail;
  }
  
  /**
   * Sets customer email.
   * 
   * @param customerEmail the customer email
   */
  public void setCustomerEmail(String customerEmail) {
    this.customerEmail = customerEmail;
  }

  /**
   * Gets address 1.
   * 
   * @return address1 the address
   */
  public String getAddress1() {
    return address1;
  }
  
  /**
   * Sets address 1.
   * 
   * @param address1 the address 1
   */
  public void setAddress1(String address1) {
    this.address1 = address1;
  }

  /**
   * Gets address 2.
   * 
   * @return address2 the address
   */
  public String getAddress2() {
    return address2;
  }
  
  /**
   * Sets account type.
   * 
   * @param accountType the account type
   */
  public void setAddress2(String address2) {
    this.address2 = address2;
  }
  
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
}
