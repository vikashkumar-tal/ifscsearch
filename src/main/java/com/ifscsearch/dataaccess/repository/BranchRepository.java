package com.ifscsearch.dataaccess.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.ifscsearch.dataaccess.domain.Branch;

public interface BranchRepository extends MongoRepository<Branch, String> {

	@Query("{bank : ?0}")
	public List<Branch> findByBank(String bank);

	@Query("{bank : ?0, state : ?1}")
	public List<Branch> findByBankState(String bank, String state);

	@Query("{bank : ?0, state : ?1, district : ?2}")
	public List<Branch> findByBankStateDistrict(String bank, String state,
			String district);

	@Query("{bank : ?0, state : ?1, district : ?2, city : ?3}")
	public List<Branch> findByBankStateDistrictCity(String bank, String state,
			String district, String city);

	@Query("{bank : ?0, state : ?1, district : ?2, city : ?3, name : ?4}")
	public List<Branch> findByBankStateDistrictCityName(String bank,
			String state, String district, String city, String name);
	
	@Query("{ifsc : ?0}")
	public Branch findByIfsc(String ifsc);
}
