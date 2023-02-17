package com.charter.rewards.rewardsdemo;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
public class CustomerBean {
	
@Id
@GeneratedValue
	private int customerID;
	private String name;
	private int purchaseAmount; 
	private int rewardsPoints;
	private Date createdDate;
	
	 
	
	public int getCustomerID() {
		return customerID;
	}
	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		//DateFormat df = new SimpleDateFormat("yyy-MM-dd");
		//Date date = new Date();
		
		this.createdDate = createdDate;
	}
	public int getPurchaseAmount() {
		return purchaseAmount;
	}
	public void setPurchaseAmount(int purchaseAmount) {
		this.purchaseAmount = purchaseAmount;
	}
	public int getRewardsPoints() {
		return rewardsPoints;
	}
	public void setRewardsPoints(int rewardsPoints) {
		this.rewardsPoints = rewardsPoints;
	}


}