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
// 	public Object[][] getIssuesTestData() {
// 		return new Object[][] {
//                 {"Test 3459", "test", "issue","",true,null,"","","bug","","",1,"",null,null,null},
//                 {"Test 3460", "test 123", "","",false,null,"","","incident","","",1,"",null,null,null}
// 		};
// 	}

    @DataProvider
    public Object[][] getIssueTestSheetData() {
        return ExcelUtil.getTestData(APIConstants.CREATE_ISSUE_SHEET_NAME);
    }
    @Test(dataProvider = "getIssuesTestData",description ="Create Issues as per the data in excel")
    public void createIssueTest(String title, String description, String issue_type,String assignee_id, Boolean confidential, Boolean discussion_locked, String created_at, String due_date,  String labels, String add_labels, String remove_labels,Integer milestone_id,String state_event,Integer weight,Integer epic_id,Integer epic_iid) 
    {
        //Creating Issues from the excel
        // Start of creation -->
        Issue issue = new Issue(title,description,issue_type,assignee_id,confidential,discussion_locked,created_at, due_date,labels,add_labels,remove_labels,milestone_id,state_event, weight,epic_id,epic_iid);
        //Empty values will be discarded
        Integer iid = restClient.post(PROJECTS_ENDPOINT, "json", issue, true, true)
                .then().log().all()
                .assertThat().statusCode(APIHttpStatus.CREATED_201.getCode())
                .extract().path("iid");
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
