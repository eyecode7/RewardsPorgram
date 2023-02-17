package com.charter.rewards.rewardsdemo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RewardsService {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	int twoPointRewards ;
	int onePointRewards ; 
	int points;
	int totalPoints;
	
	public CustomerBean createPurchase(CustomerBean customerBean) {
		
		customerBean.setRewardsPoints(calculateRewards(customerBean.getPurchaseAmount()));
		
		
		return customerRepository.save(customerBean);
	}
	
	public List<CustomerBean> createPurchases(List<CustomerBean> customerBean) {
		List<CustomerBean> rewardsCustomer = customerBean;
		
		for (CustomerBean customer : rewardsCustomer) {
			customer.setRewardsPoints(calculateRewards(customer.getPurchaseAmount()));
		}
		
		
		return customerRepository.saveAll(rewardsCustomer);
	}
	
	public CustomerBean getCustomer(int id) {
		return customerRepository.findById(id).orElse(null);
	}
	
	public List<CustomerBean> getAllCustomer() {
		calculateMonthlyandTotalRewards();
		return customerRepository.findAll();
	}
	
	public int calculateRewards(int purchaseAmount) {
		//A customer receives 2 points for every dollar spent over $100 in each transaction, plus 1 point for every dollar spent between $50 and $100 in each transaction.
		//(e.g. a $120 purchase = 2x$20 + 1x$50 = 90 points).
		int rewards = 0;
		
	if (purchaseAmount > 100){
			
			points = purchaseAmount - 100;
			twoPointRewards= points * 2;
			rewards = twoPointRewards + 50;
			System.out.println("purchaseAmount "+ purchaseAmount + " twoPointRewards " + twoPointRewards + " totalPoints " + rewards );
			
			
		}if (purchaseAmount <= 100 && purchaseAmount > 50  ){
				
			onePointRewards= purchaseAmount - 50;
			rewards = onePointRewards;
			System.out.println("onePointRewards " + rewards );
			
		}else if (purchaseAmount <= 50) {
			System.out.println("Sorry no rewards :(");
		}
		
		
		return rewards;
	}
	
	
	public List <CustomerRewardsTotalBean> calculateMonthlyandTotalRewards() {
		List<CustomerRewardsTotalBean> crbList = new ArrayList<CustomerRewardsTotalBean>();
		List<MonthlyRewardBean> mbList = new ArrayList<MonthlyRewardBean>();
		List<MonthlyRewardBean> mbsList = new ArrayList<MonthlyRewardBean>();
		List<RewardsTotal>	cb = customerRepository.totalRewardsPoint();
		List<MonthlyTotal> mt = customerRepository.monthlyTotalRewards();

		for (RewardsTotal cb2 : cb) {
			CustomerRewardsTotalBean crb = new CustomerRewardsTotalBean();
			crb.setName(cb2.getName());
			crb.setTotalRewardsPoints(cb2.getTotalRewardsPoints());
			crb.setMonthlyRewardsPoints(mbsList);
			crbList.add(crb);
			System.out.println("Name " + cb2.getName() + " total Rewards " + cb2.getTotalRewardsPoints());
		}

		for (MonthlyTotal cb3 : mt) {

			MonthlyRewardBean mb = new MonthlyRewardBean();

			mb.setName(cb3.getName());
			mb.setMonth(cb3.getRewardMonth());
			mb.setTotalPoints(cb3.getTotalPointsPerMonth());
			System.out.println("Name " + cb3.getName() + " total Rewards Monthly " + cb3.getTotalPointsPerMonth()
					+ " Reward Month " + cb3.getRewardMonth());
			mbList.add(mb);

		}
		
		
		for (CustomerRewardsTotalBean custrewards : crbList) {
			assignMonthlyRewards(custrewards,mbList);
			
		}

		return crbList;

	}
	
	public void assignMonthlyRewards(CustomerRewardsTotalBean customerRewardsTotalBean, List<MonthlyRewardBean> monthlyRewards ) {
		List<MonthlyRewardBean> mbList = new ArrayList<MonthlyRewardBean>();
		for (MonthlyRewardBean monthlyRewardBean : monthlyRewards) {
			if(monthlyRewardBean.getName().equalsIgnoreCase(customerRewardsTotalBean.getName())) {
				MonthlyRewardBean mb = new MonthlyRewardBean();
				mb.setName(monthlyRewardBean.getName());
				mb.setMonth(monthlyRewardBean.getMonth());
				mb.setTotalPoints(monthlyRewardBean.getTotalPoints());
				mbList.add(mb);
				
				
				customerRewardsTotalBean.setMonthlyRewardsPoints(mbList);
			}
			
		}
		
		
		
	}
	

}
