package com.mycompany.customeraccount.model;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * The Class CustomerAccount
 */
@Entity
public class CustomerAccount {

  @Id
  @SequenceGenerator(
      name = "custnum_sequence",
      sequenceName = "custnum_sequence",
      initialValue=10000000,
      allocationSize = 1
  )
  @GeneratedValue(
      strategy = GenerationType.SEQUENCE,
      generator = "custnum_sequence"
  )
  /** The customer number. */ 
  private long customerNumber;
  
  @NotEmpty
  @Size(max=50)
  /** The customer name. */ 
  private String customerName;
  @NotEmpty
  @Size(max=20)
  /** The customer number. */ 
  private String customerMobile;
  @NotEmpty
  @Size(max=50)
  /** The customer email. */ 
  private String customerEmail;
  @NotEmpty
  @Size(max=100)
  /** The address 1. */ 
  private String address1;
  @Size(max=100)
  /** The address 2. */ 
  private String address2;
  
  @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
  /** The savings. */ 
  private List<Savings> savings;
  
  /**
   * Gets customer number.
   * 
   * @return customerNumber the customer number
   */
  public long getCustomerNumber() {
    return customerNumber;
  }
  
  /**
   * Sets customer number.
   * 
   * @param customerNumber the customer number
   */
  public void setCustomerNumber(long customerNumber) {
    this.customerNumber = customerNumber;
  }
  
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

  /**
   * Gets savings.
   * 
   * @return savings the savings
   */
  public List<Savings> getSavings() {
    return savings;
  }

  
  /**
   * Sets savings.
   * 
   * @param savings the savings
   */
  public void setSavings(List<Savings> savings) {
    this.savings = savings;
  }
}
