package com.ifscsearch.web;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import au.com.bytecode.opencsv.CSVParser;
import au.com.bytecode.opencsv.CSVReader;

import com.ifscsearch.bean.BankRequest;
import com.ifscsearch.bean.BranchRequest;
import com.ifscsearch.bean.CityRequest;
import com.ifscsearch.bean.DistrictRequest;
import com.ifscsearch.dataaccess.domain.Branch;
import com.ifscsearch.dataaccess.repository.BranchRepository;
import com.ifscsearch.exception.IfscException;

@RestController
@RequestMapping("/bank")
public class BranchController {

	@Autowired
	private BranchRepository branchRepo;

	@Autowired
	private MongoDbFactory mongoDbFactory;

	@RequestMapping("/find")
	public List<Branch> findByDetails(@RequestBody @Valid BranchRequest bRequest)
			throws IfscException {
		String bank = bRequest.getBank();
		String city = bRequest.getCity();
		String state = bRequest.getState();
		String district = bRequest.getDistrict();
		String branchName = bRequest.getBranch();
		if (state != null && district != null && city != null
				&& branchName != null) {
			return branchRepo.findByBankStateDistrictCityName(bank, state,
					district, city, branchName);
		} else if (state != null && district != null && city != null
				&& branchName == null) {
			return branchRepo.findByBankStateDistrictCity(bank, state,
					district, city);
		} else {
			throw new IfscException(1,
					"Bank name, state, district, city are must");
		}

	}

	@RequestMapping("/findbranch/{ifsc}")
	public Branch findByIfsc(@PathVariable(value = "ifsc") String ifsc) {
		return branchRepo.findByIfsc(ifsc);
	}

	@RequestMapping("/delete")
	public void delete() {
		branchRepo.deleteAll();
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("/findbanks")
	public List<String> findBanks() {
		List<String> bankList = mongoDbFactory.getDb().getCollection("branch")
				.distinct("bank");
		return bankList;
	}

	@RequestMapping("/findstates")
	public Set<String> findStates(@RequestBody BankRequest bRequest) {
		List<Branch> branches = (List<Branch>) branchRepo.findByBank(bRequest
				.getBank());
		Set<String> stateSet = new HashSet<String>();
		for (Branch branch : branches) {
			stateSet.add(branch.getState());
		}
		return stateSet;
	}

	@RequestMapping("/finddistricts")
	public Set<String> findDistricts(
			@RequestBody @Valid DistrictRequest dRequest) {
		List<Branch> branches = (List<Branch>) branchRepo.findByBankState(
				dRequest.getBank(), dRequest.getState());
		Set<String> districtSet = new HashSet<String>();
		for (Branch branch : branches) {
			districtSet.add(branch.getDistrict());
		}
		return districtSet;
	}

	@RequestMapping("/findcities")
	public Set<String> findCities(@RequestBody @Valid CityRequest cRequest) {
		List<Branch> branches = (List<Branch>) branchRepo
				.findByBankStateDistrict(cRequest.getBank(),
						cRequest.getState(), cRequest.getDistrict());
		Set<String> citySet = new HashSet<String>();
		for (Branch branch : branches) {
			citySet.add(branch.getCity());
		}
		return citySet;
	}

	@RequestMapping("/upload")
	public void uploadBranches(
			@RequestParam(value = "ifsc") MultipartFile ifscFile)
			throws IfscException {
		try {
			readCSV(ifscFile.getInputStream());
		} catch (Exception ex) {
			throw new IfscException(1, "Invalid File");
		}
	}

	@RequestMapping("/read")
	public void uploadFromClasspath() throws IfscException {
		ClassPathResource resource = new ClassPathResource("/upload/ifsc.csv");
		try {
			readCSV(resource.getInputStream());
		} catch (Exception ex) {
			throw new IfscException(1, "Invalid File");
		}
	}

	@SuppressWarnings("unchecked")
	private void readCSV(InputStream inputStream) throws IOException {
		Reader reader = new InputStreamReader(inputStream);
		CSVReader csvReader = new CSVReader(reader,
				CSVParser.DEFAULT_SEPARATOR, CSVParser.DEFAULT_QUOTE_CHARACTER,
				0);
		List<String[]> fileContents = csvReader.readAll();
		csvReader.close();
		List<Branch> saveList = new LinkedList<Branch>();
		List<String> ifscList = mongoDbFactory.getDb().getCollection("branch")
				.distinct("ifsc");

		for (String[] row : fileContents) {
			int pos = 0;
			if (row[0].trim().equalsIgnoreCase("BANK")) {
				continue;
			}
			String bank = row[pos++];
			String ifsc = row[pos++];
			String micr = row[pos++];
			String branchName = row[pos++];
			String address = row[pos++];
			String contact = row[pos++];
			String city = row[pos++];
			String district = row[pos++];
			String state = row[pos++];
			if (!ifscList.contains(ifsc)) {
				Branch branch = new Branch(branchName, bank, ifsc, micr, state,
						city, district, address, contact);
				saveList.add(branch);
			}
		}
		branchRepo.save(saveList);
	}

}