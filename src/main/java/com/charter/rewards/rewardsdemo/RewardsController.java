package com.charter.rewards.rewardsdemo;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/charter/rewards")
public class RewardsController {
	@Autowired
	private RewardsService rewardsService;
//	
//	A retailer offers a rewards program to its customers, awarding points based on each recorded purchase.
//	A customer receives 2 points for every dollar spent over $100 in each transaction, plus 1 point for every dollar spent between $50 and $100 in each transaction.
//	(e.g. a $120 purchase = 2x$20 + 1x$50 = 90 points).
//	Given a record of every transaction during a three month period, calculate the reward points earned for each customer per month and total.
	
	@PostMapping("/addCustomer")
	public CustomerBean addCustomer(@RequestBody CustomerBean customerBean  ) {
		return rewardsService.createPurchase(customerBean);
	}
	
	@PostMapping("/addCustomers")
	public List<CustomerBean> addCustomers(@RequestBody List<CustomerBean> customerBean  ) {
		return rewardsService.createPurchases(customerBean);
	}
	
	
	@GetMapping("/customer/{id}")
	public CustomerBean getCustomerById(@PathVariable int id) {
			return rewardsService.getCustomer(id);
			
		}
	@GetMapping("/customers")
	public List<CustomerBean> getAllCustomers(){ 
		return rewardsService.getAllCustomer();
		
	}
	
	@GetMapping("/customersTotals")
	public List<CustomerRewardsTotalBean> getCustomersTotals(){ 
		return rewardsService.calculateMonthlyandTotalRewards();
		
	}

		
	}


