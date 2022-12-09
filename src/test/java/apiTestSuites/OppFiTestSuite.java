package apiTestSuites;

import apiModals.*;
import autoFramework.AutoTestBase;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class OppFiTestSuite extends AutoTestBase {

    // TODO: provide this as env variables with TestSetUp()
    private String resource = null;
    private String apiKey = null;

    @BeforeSuite
    public void TestSetUp()
    {

    }

    @AfterMethod
    public void TestTearDown()
    {
        ResetSteps();
    }

    @Test(description = "OffersApiAcceptedTest is testing to return an approved loan.")
    public void OffersApiAcceptedTest() throws Exception {

        Offer offer = new Offer();
        Address address = new Address();
        PersonalInfo personalInfo = new PersonalInfo();
        BankInfo bankInfo = new BankInfo();
        IncomeInfo incomeInfo = new IncomeInfo();
        EmploymentInfo employmentInfo = new EmploymentInfo();

        Step("Set up Offer API Request Body.");
        offer.setIsProduction(false);
        offer.setLanguage("en");
        offer.setCurrency("USD");
        offer.setCampaignId("11-50-newhope");
        offer.setSocialSecurityNumber("123456780");
        offer.setLeadOfferId("20160912-21EC2020-3AEA-4069-A2DD-08002B30309D");
        offer.setEmail("test_customer@gmail.com");
        offer.setStateCode("FL");
        offer.setGrossMonthlyIncome(100000);

        personalInfo.setFirstName("Jennifer");
        personalInfo.setLastName("Smith");
        personalInfo.setDateOfBirth("19451009");
        address.setStreetAddress("123 Main Street");
        address.setCity("Miami");
        address.setZip("33125");
        address.setCountryCode("US");

        personalInfo.setAddress(address);
        personalInfo.setMobilePhone("3224340098");
        personalInfo.setHomePhone("4523452232");
        offer.setPersonalInfo(personalInfo);

        bankInfo.setBankName("Chase");
        bankInfo.setAbaRoutingNumber("123456789");
        bankInfo.setAccountNumber("012345789");
        bankInfo.setAccountType(1);
        bankInfo.setAccountLength(6);
        offer.setBankInfo(bankInfo);

        incomeInfo.setIncomeType("Employment");
        incomeInfo.setPayrollType("DirectDeposit");
        incomeInfo.setPayrollFrequency(1);
        incomeInfo.setLastPayrollDate("20160915");
        offer.setIncomeInfo(incomeInfo);

        employmentInfo.setEmployerName("ToysRUs");
        employmentInfo.setHireDate("20110516");
        offer.setEmploymentInfo(employmentInfo);
        offer.setRequestedLoanAmount(1500);

        Step("Make a post call.");
        Offer response = null;

        try {
            response =  offersApi.PostOffers(offer, resource, apiKey);
        }
        catch (Exception e)
        {
            throw new Exception("Exception hit", e);
        }

        Step("Verify loan was accepted.");
        // TODO: Verify loan was accepted
        /*compare(true, response.getAccepted(), "request accepted", "approved");
        weakAssert.assertEquals(response.getAccepted(), true, "Verify that request body was accepted.");
        weakAssert.assertAll();*/
    }

    @Test(description = "OffersApiDeclinedTest is testing to return a declined loan.")
    public void OffersApiGetsDeclinedTest() throws Exception
    {
        Offer offer = new Offer();
        Address address = new Address();
        PersonalInfo personalInfo = new PersonalInfo();
        BankInfo bankInfo = new BankInfo();
        IncomeInfo incomeInfo = new IncomeInfo();
        EmploymentInfo employmentInfo = new EmploymentInfo();

        Step("Set up Offer API Request Body.");
        offer.setIsProduction(false);
        offer.setLanguage("en");
        offer.setCurrency("USD");
        offer.setCampaignId("11-50-newhope");
        // SSN should return declined
        offer.setSocialSecurityNumber("123450000");
        offer.setLeadOfferId("20160912-21EC2020-3AEA-4069-A2DD-08002B30309D");
        offer.setEmail("test_customer@gmail.com");
        offer.setStateCode("FL");
        offer.setGrossMonthlyIncome(100000);

        personalInfo.setFirstName("Jennifer");
        personalInfo.setLastName("Smith");
        personalInfo.setDateOfBirth("19451009");
        address.setStreetAddress("123 Main Street");
        address.setCity("Miami");
        address.setZip("33125");
        address.setCountryCode("US");

        personalInfo.setAddress(address);
        personalInfo.setMobilePhone("3224340098");
        personalInfo.setHomePhone("4523452232");
        offer.setPersonalInfo(personalInfo);

        bankInfo.setBankName("Chase");
        bankInfo.setAbaRoutingNumber("123456789");
        bankInfo.setAccountNumber("012345789");
        bankInfo.setAccountType(1);
        bankInfo.setAccountLength(6);
        offer.setBankInfo(bankInfo);

        incomeInfo.setIncomeType("Employment");
        incomeInfo.setPayrollType("DirectDeposit");
        incomeInfo.setPayrollFrequency(1);
        incomeInfo.setLastPayrollDate("20160915");
        offer.setIncomeInfo(incomeInfo);

        employmentInfo.setEmployerName("ToysRUs");
        employmentInfo.setHireDate("20110516");
        offer.setEmploymentInfo(employmentInfo);
        offer.setRequestedLoanAmount(1500);

        Step("Make a post call.");

        Offer response = null;

        try {
            response =  offersApi.PostOffers(offer, resource, apiKey);
        }
        catch (Exception e)
        {
            throw new Exception("Exception hit", e);
        }

        Step("Verify loan was not accepted.");
        // TODO: Verify loan was not accepted
        /*compare(false, response.getAccepted(), "request accepted", "approved");
        weakAssert.assertEquals(response.getAccepted(), false, "Verify loan was not accepted");
        weakAssert.assertAll();*/
    }

    @Test(description = "OffersApiCallGetsBadDataTest is testing to return an a bad API call.")
    public void OffersApiCallGetsBadDataTest() throws Exception
    {
        Step("Set up bad API Request Body.");
        String badSampleRequest = "{\n" +
                "\"isProduction\": false,\n" +
                "\"language\": \"en\",\n" +
                "\"currency\": \"USD\",\n" +
                "\"campaignId\": \"11-50-newhope\", \"socialSecurityNumber\":\"123456780\",\n" +
                "\"leadOfferId\": \"20160912-21EC2020-3AEA-4069-A2DD-08002B30309D\", \"email\": \"test_customer@gmail.com\",\n" +
                "\"stateCode\": \"FL\",\n" +
                " \n" +
                "\"grossMonthlyIncome\": \"hello\", \"personalInfo\": {\n" +
                "\"firstName\": \"Jennifer\", \"lastName\": \"Smith\", \"dateOfBirth\": \"19451009\", \"address\": {\n" +
                "\"streetAddress\": \"123 Main Street\", \"city\": \"Miami\",\n" +
                "\"zip\": \"33125\",\n" +
                "\"countryCode\":\"US\"\n" +
                "},\n" +
                "\"mobilePhone\": \"3224340098\", \"homePhone\": \"4523452232\"\n" +
                "}, \"bankInfo\": {\n" +
                "\"bankName\": \"Chase\", \"abaRoutingNumber\": \"123456789\", \"accountNumber\": \"012345789\", \"accountType\": 1, \"accountLength\": 6\n" +
                "}, \"incomeInfo\": {\n" +
                "\"incomeType\": \"Employment\", \"payrollType\": \"DirectDeposit\", \"payrollFrequency\": 1, \"lastPayrollDate\": \"20160915\"\n" +
                "}, \"employmentInfo\": {\n" +
                "\"employerName\": \"ToysRUs\",\n" +
                "\"hireDate\": \"20110516\" },\n" +
                "\"requestedLoanAmount\": 1500}";

        Step("Make a post call.");

        int response;

        try {
            response =  offersApi.PostOffersInt(badSampleRequest, resource, apiKey);
        }
        catch (Exception e)
        {
            throw new Exception("Exception hit", e);
        }

        Step("Verify bad data was returned: status code 500.");
        // TODO: Verify bad data was returned
        /*weakAssert.assertEquals(response, 500);
        weakAssert.assertAll();*/
    }


}
