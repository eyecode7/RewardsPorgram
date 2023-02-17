package com.charter.rewards.rewardsdemo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface CustomerRepository extends JpaRepository<CustomerBean,Integer > {

	String SELECT_TOTALS_BY_CUST = "select name, sum (rewards_points) as totalRewardsPoints FROM CUSTOMER_BEAN group by name";
	String SELECT_MONTHLY_TOTALS_BY_CUST = "SELECT monthname (created_date) as RewardMonth, name,\n"
			+ "       SUM(rewards_points) as TotalPointsPerMonth\n"
			+ "FROM CUSTOMER_BEAN\n"
			+ "GROUP BY monthname (created_date), name order by name";
	String CUST_LIST = "select distinct(NAME)  FROM CUSTOMER_BEAN";
	
	
	@Query(value = SELECT_TOTALS_BY_CUST, nativeQuery = true)
	public List<RewardsTotal> totalRewardsPoint();
	
	@Query(value = SELECT_MONTHLY_TOTALS_BY_CUST, nativeQuery = true)
	public List<MonthlyTotal> monthlyTotalRewards();
	
	@Query(value = CUST_LIST, nativeQuery = true)
	public List<String> custList();
	
	

}
