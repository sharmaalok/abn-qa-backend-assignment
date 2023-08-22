package com.abnamro.tests;

import com.abnamro.base.BaseTest;
import com.abnamro.constants.APIConstants;
import com.abnamro.constants.APIHttpStatus;
import com.abnamro.pojo.Issue;
import com.abnamro.utils.ExcelUtil;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;
public class CreateIssueTest extends BaseTest {

//    @DataProvider
//	public Object[][] getIssuesTestData() {
//		return new Object[][] {
//			{"Test 123", "Description Test123", "issue"},
//			{"Test 234", "Description Test234", "incident"},
//          {"Test 345", "Description Test345", "test_case"}
//		};
//	}
    @DataProvider
    public Object[][] getIssueTestSheetData() {
        return ExcelUtil.getTestData(APIConstants.CREATE_ISSUE_SHEET_NAME);
    }
    @Test(dataProvider = "getIssueTestSheetData",description ="Create Issues as per the data in excel")
    public void createIssueTest(String title, String description, String issue_type) {
        //Creating Issues from the excel
        // Start of creation -->
        Issue issue = new Issue(title, description, issue_type);
        Integer iid = restClient.post(PROJECTS_ENDPOINT, "json", issue, true, true)
                .then().log().all()
                .assertThat().statusCode(APIHttpStatus.CREATED_201.getCode())
                .extract().path("iid");
        System.out.println("Issue id : " + iid);
        // End of creation -->

        //validating the created issues
        //start of validation -->
        restClient.get(ISSUES_ENDPOINT+"?iids[]="+iid, true,  true)
                .then().log().all()
                .assertThat().statusCode(APIHttpStatus.OK_200.getCode())
                .assertThat().body("iid[0]", equalTo(iid));
        //End of validation -->
     }
}
