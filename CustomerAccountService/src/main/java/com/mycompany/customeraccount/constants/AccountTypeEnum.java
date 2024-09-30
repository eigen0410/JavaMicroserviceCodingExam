package com.mycompany.customeraccount.constants;

/**
 * The Enum AccountTypeEnum
 */
public enum AccountTypeEnum {
  S("Savings"),
  C("Checking");
  
  /** The customer number. */  
  private String type;
  
  /**
   * Account type enum constructor.
   * 
   * @param type the type
   */
  AccountTypeEnum(String type) {
    this.type = type;
  }
  
  /**
   * Gets type.
   * 
   * @return type the type
   */
  public String getType() {
    return this.type;
  }
}
