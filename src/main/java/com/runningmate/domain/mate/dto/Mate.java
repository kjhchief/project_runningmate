package com.runningmate.domain.mate.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Mate {
 private String email;
 private String name;
 private String password;
 private String gender;
 private String year;
 private String month;
 private String date;
 private String birthdate;
 private String phoneNumber;
 private String address;
 private String addressDetail;
 private String location;
 private String mateClass;
 private String kakaoaccYn;
 private float mannerTemp;
 private int commentCount;
 private int joinCount;
 private int hostCount;
 
 
 public void setBirthdate() {
	 this.birthdate  = getYear() + "." + getMonth() + "." + getDate();
 }
 
 public void setLocation() {
	 this.location  = getAddress() + "," + getAddressDetail();
 } 
 
 
 public void setAddress() {
	 String[] splitLocation = getLocation().split(",");
	 this.address = splitLocation[0];
 }
 
 public void setAddressDetail() {
	 String[] splitLocation = getLocation().split(",");
	 this.addressDetail = splitLocation[1];
 }
 
 public void setPhoneNumber() {
	 this.phoneNumber = phoneNumber.substring(0, 3) + "-" + phoneNumber.substring(3, 7) + "-" + phoneNumber.substring(7);
	
 } 

 
}
