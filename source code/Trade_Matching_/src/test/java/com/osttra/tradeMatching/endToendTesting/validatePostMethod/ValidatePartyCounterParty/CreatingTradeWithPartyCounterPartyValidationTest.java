package com.osttra.tradeMatching.endToendTesting.validatePostMethod.ValidatePartyCounterParty;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;

import java.time.LocalDate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import io.restassured.RestAssured;

@SpringBootTest @DisplayName("Party CounterParty Validation")
public class CreatingTradeWithPartyCounterPartyValidationTest {
	LocalDate datenow = LocalDate.now();
	LocalDate nextMonthDate = LocalDate.now().plusMonths(1);
	LocalDate endDate = LocalDate.now().plusYears(1);
	int randomId = ((int)(Math.random() * 1200) + 1);
	
	@Test @DisplayName("Test_Case Same PartyCounterParty")
	void CreateTradeWithSamePartyCounterParty() {
		// 
		RestAssured.given().header("content-type","application/json").and().body("{\r\n"
				+ "  \"buyer\": \"HDFCM\",\r\n"
				+ "  \"counterParty\": \"HDFCM\",\r\n"
				+ "  \"counterPartyFullname\": \"HDFC MUMBAI\",\r\n"
				+ "  \"counterPartyInstitution\": \"HDFC\",\r\n"
				+ "  \"currency\": \"INR\",\r\n"
				+ "  \"effectiveDate\": \""+nextMonthDate+"\",\r\n"
				+ "  \"instrumentId\": \"Bonds\",\r\n"
				+ "  \"maturityDate\": \""+endDate+"\",\r\n"
				+ "  \"notionalAmount\": 100,\r\n"
				+ "  \"party\": \"HDFCM\",\r\n"
				+ "  \"partyFullname\": \"HDFC MUMBAI\",\r\n"
				+ "  \"partyInstitution\": \"HDFC\",\r\n"
				+ "  \"seller\": \"HdfcM\",\r\n"
				+ "  \"tradeDate\": \""+datenow+"\",\r\n"
				+ "  \"tradeId\": \""+randomId+"\"\r\n"
				+ "}").post("http://localhost:8080/trade/inserttrade").
		then().
		assertThat().
		body("size()",is(greaterThanOrEqualTo(1))).and().statusCode(400);
	}
	
	//This will check the allowed parties validation.

	@Test @DisplayName("Test_Case Not Allowed parties counterparty to trade")
	void CreateTradeWithPartyCounterPartyNotFromAllowedPartyOrCounterParty() {
		String party = "SBIU";
		String partyFullName = "SBI Unknown";
		String partyInstitution = "SBI";
		RestAssured.given().header("content-type","application/json").and().body("{\r\n"
				+ "  \"buyer\": \"ICICID\",\r\n"
				+ "  \"counterParty\": \"ICICID\",\r\n"
				+ "  \"counterPartyFullname\": \"ICICI DELHI\",\r\n"
				+ "  \"counterPartyInstitution\": \"ICICI\",\r\n"
				+ "  \"currency\": \"INR\",\r\n"
				+ "  \"effectiveDate\": \""+nextMonthDate+"\",\r\n"
				+ "  \"instrumentId\": \"Bonds\",\r\n"
				+ "  \"maturityDate\": \""+endDate+"\",\r\n"
				+ "  \"notionalAmount\": 100,\r\n"
				+ "  \"party\": \""+party+"\",\r\n"
				+ "  \"partyFullname\": \""+partyFullName+"\",\r\n"
				+ "  \"partyInstitution\": \""+partyInstitution+"\",\r\n"
				+ "  \"seller\": \""+party+"\",\r\n"
				+ "  \"tradeDate\": \""+datenow+"\",\r\n"
				+ "  \"tradeId\": \""+randomId+"\"\r\n"
				+ "}").post("http://localhost:8080/trade/inserttrade").
		then().
		assertThat().
		body("size()",  is(greaterThanOrEqualTo(1))).and().statusCode(400);
	}
	
//	@Test @DisplayName("Test_Case NULL parties counterParty")
	void CreateTradeWithNullPartyCounterParty() {
		String party = "";
		String partyFullName = "";
		String partyInstitution = "";
		RestAssured.given().header("content-type","application/json").and().body("{\r\n"
				+ "  \"buyer\": \"ICICID\",\r\n"
				+ "  \"counterParty\": \""+party+"\",\r\n"
				+ "  \"counterPartyFullname\": \""+partyFullName+"\",\r\n"
				+ "  \"counterPartyInstitution\": \""+partyInstitution+"\",\r\n"
				+ "  \"currency\": \"INR\",\r\n"
				+ "  \"effectiveDate\": \""+nextMonthDate+"\",\r\n"
				+ "  \"instrumentId\": \"Bonds\",\r\n"
				+ "  \"maturityDate\": \""+endDate+"\",\r\n"
				+ "  \"notionalAmount\": 100,\r\n"
				+ "  \"party\": \""+party+"\",\r\n"
				+ "  \"partyFullname\": \""+partyFullName+"\",\r\n"
				+ "  \"partyInstitution\": \""+partyInstitution+"\",\r\n"
				+ "  \"seller\": \""+party+"\",\r\n"
				+ "  \"tradeDate\": \""+datenow+"\",\r\n"
				+ "  \"tradeId\": \""+randomId+"\"\r\n"
				+ "}").post("http://localhost:8080/trade/inserttrade").
		then().
		assertThat().
		body("size()",is(greaterThanOrEqualTo(1))).and().statusCode(400);
		
	}
	
	@Test  @DisplayName("Test_Case InValid parties and Valid counterParty")
	void CreateTradeWithInValidPartyAndValidCounterParty() {
		String party = "";
		String partyFullName = "";
		String partyInstitution = "";
		String c_party = "HdfcD";
		String c_partyFullName = "Hdfc Delhi";
		String c_partyInstitution = "Hdfc";
		RestAssured.given().header("content-type","application/json").and().body("{\r\n"
				+ "  \"buyer\": \"ICICID\",\r\n"
				+ "  \"counterParty\": \""+c_party+"\",\r\n"
				+ "  \"counterPartyFullname\": \""+c_partyFullName+"\",\r\n"
				+ "  \"counterPartyInstitution\": \""+c_partyInstitution+"\",\r\n"
				+ "  \"currency\": \"INR\",\r\n"
				+ "  \"effectiveDate\": \""+nextMonthDate+"\",\r\n"
				+ "  \"instrumentId\": \"Bonds\",\r\n"
				+ "  \"maturityDate\": \""+endDate+"\",\r\n"
				+ "  \"notionalAmount\": 100,\r\n"
				+ "  \"party\": \""+party+"\",\r\n"
				+ "  \"partyFullname\": \""+partyFullName+"\",\r\n"
				+ "  \"partyInstitution\": \""+partyInstitution+"\",\r\n"
				+ "  \"seller\": \""+party+"\",\r\n"
				+ "  \"tradeDate\": \""+datenow+"\",\r\n"
				+ "  \"tradeId\": \""+randomId+"\"\r\n"
				+ "}").post("http://localhost:8080/trade/inserttrade").
		then().
		assertThat().
		body("size()",is(greaterThanOrEqualTo(1))).and().statusCode(400);
		
	}
	
	@Test  @DisplayName("Test_Case Valid parties and InValid counterParty")
	void CreateTradeWithValidPartyAndInValidCounterParty() {
		String c_party = "";
		String c_partyFullName = "";
		String c_partyInstitution = "";
		String party = "HdfcD";
		String partyFullName = "Hdfc Delhi";
		String partyInstitution = "Hdfc";
		RestAssured.given().header("content-type","application/json").and().body("{\r\n"
				+ "  \"buyer\": \"ICICID\",\r\n"
				+ "  \"counterParty\": \""+c_party+"\",\r\n"
				+ "  \"counterPartyFullname\": \""+c_partyFullName+"\",\r\n"
				+ "  \"counterPartyInstitution\": \""+c_partyInstitution+"\",\r\n"
				+ "  \"currency\": \"INR\",\r\n"
				+ "  \"effectiveDate\": \""+nextMonthDate+"\",\r\n"
				+ "  \"instrumentId\": \"Bonds\",\r\n"
				+ "  \"maturityDate\": \""+endDate+"\",\r\n"
				+ "  \"notionalAmount\": 100,\r\n"
				+ "  \"party\": \""+party+"\",\r\n"
				+ "  \"partyFullname\": \""+partyFullName+"\",\r\n"
				+ "  \"partyInstitution\": \""+partyInstitution+"\",\r\n"
				+ "  \"seller\": \""+party+"\",\r\n"
				+ "  \"tradeDate\": \""+datenow+"\",\r\n"
				+ "  \"tradeId\": \""+randomId+"\"\r\n"
				+ "}").post("http://localhost:8080/trade/inserttrade").
		then().
		assertThat().
		body("size()",is(greaterThanOrEqualTo(1))).and().statusCode(400);
		
	}
}
