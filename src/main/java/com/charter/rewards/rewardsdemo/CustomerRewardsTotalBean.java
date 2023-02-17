package com.charter.rewards.rewardsdemo;

import java.util.List;

public class CustomerRewardsTotalBean {
	
	private String name;
	private int totalRewardsPoints;
	private List<MonthlyRewardBean> monthlyRewardsPoints;
	
	
//	public int getCustomerID() {
//		return customerID;
//	}
//	public void setCustomerID(int customerID) {
//		this.customerID = customerID;
//	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public int getTotalRewardsPoints() {
		return totalRewardsPoints;
	}
	public void setTotalRewardsPoints(int totalRewardsPoints) {
		this.totalRewardsPoints = totalRewardsPoints;
	}
	public List<MonthlyRewardBean> getMonthlyRewardsPoints() {
		return monthlyRewardsPoints;
	}
	public void setMonthlyRewardsPoints(List<MonthlyRewardBean> monthlyRewardsPoints) {
		this.monthlyRewardsPoints = monthlyRewardsPoints;
	}

}
