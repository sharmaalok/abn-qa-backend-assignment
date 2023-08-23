package com.abnamro.tests;

import com.abnamro.base.BaseTest;
import com.abnamro.constants.APIHttpStatus;
import com.abnamro.pojo.Issue;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.equalTo;
public class CreateIssueTest extends BaseTest {


        //body creation for create issue api.
   @DataProvider
	public Object[][] getIssuesTestData() {
		return new Object[][] {
                {"Test 1", "Description test1", "issue","",true,null,"","","bug","","",1,"",null,null,null},
                {"Test 2", "Description test2", "","",false,null,"","","incident","","",1,"",null,null,null},
                {"Test 3", "Description test3", "issue","",true,null,"","","bug","","",1,"",null,null,null},
                {"Test 4", "Description test4", "","",true,null,"","","incident","","",1,"",null,null,null}
		};
	}

    @Test(dataProvider = "getIssuesTestData",description ="Create Issues as per the data create")
    public void createIssueTest(String title, String description, String issue_type,String assignee_id, Boolean confidential, Boolean discussion_locked, String created_at, String due_date,  String labels, String add_labels, String remove_labels,Integer milestone_id,String state_event,Integer weight,Integer epic_id,Integer epic_iid) 
    {
        //body. Empty values will be discarded-->
        Issue issue = new Issue(title,description,issue_type,assignee_id,confidential,discussion_locked,created_at, due_date,labels,add_labels,remove_labels,milestone_id,state_event, weight,epic_id,epic_iid);
        
        //START of post request.
        Integer iid = restClient.post(PROJECTS_ENDPOINT, "json", issue, true, true)
                .then().log().all()
                .assertThat().statusCode(APIHttpStatus.CREATED_201.getCode())
                .extract().path("iid");
        //End of post request

        //validating the created issues
        //start of validation -->
        restClient.get(ISSUES_ENDPOINT+"?iids[]="+iid, true,  true)
                .then().log().all()
                .assertThat().statusCode(APIHttpStatus.OK_200.getCode())
                .assertThat().body("iid[0]", equalTo(iid));
        //End of validation -->
     }
}
