package com.abnamro.tests;
import java.util.HashMap;
import java.util.Map;
import com.abnamro.constants.APIConstants;
import com.abnamro.base.BaseTest;
import com.abnamro.constants.APIHttpStatus;
import com.abnamro.utils.JsonPathValidator;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;
import com.abnamro.pojo.Issue;
import com.abnamro.utils.ExcelUtil;
import static org.hamcrest.Matchers.equalTo;;

public class EditIssueTest extends BaseTest{

	@Test(description="This test will edit the issue with given parameters")
	public void editIssueWithParameter() {

		Response allissues = restClient.get(ISSUES_ENDPOINT, true,  true);
		allissues.then().log().all()
				.assertThat().statusCode(APIHttpStatus.OK_200.getCode());
		JsonPathValidator js = new JsonPathValidator();
		Integer iid = js.read(allissues, "$.[0].iid"); //js.readList(allissues, "$..iid"); //get all existing iid's

		
		
		Map<String, Object> queryParams = new HashMap<>();
		queryParams.put("title", "changed title for another issue");
		queryParams.put("discussion_locked", 1);

		restClient.put(PROJECTS_ENDPOINT,iid,"json", queryParams,true, true)
				.then().log().all()
				.assertThat().statusCode(APIHttpStatus.OK_200.getCode())
				.assertThat().body("title", equalTo("changed title for another issue"))
				.assertThat().body("discussion_locked", equalTo(true));
    }


	@DataProvider
    public Object[][] getIssueTestSheetData() {
        return ExcelUtil.getTestData(APIConstants.CREATE_ISSUE_SHEET_NAME);
    }
	@Test(dataProvider = "getIssuesTestData",description="This test will edit the issue with given body from an excel")
	public void editIssueWithBody(String title, String description, String issue_type,String assignee_id, Boolean confidential, Boolean discussion_locked, String created_at, String due_date,  String labels, String add_labels, String remove_labels,Integer milestone_id,String state_event,Integer weight,Integer epic_id,Integer epic_iid) 
    {
        
		Response allissues = restClient.get(ISSUES_ENDPOINT, true,  true);
		allissues.then().log().all()
				.assertThat().statusCode(APIHttpStatus.OK_200.getCode());
		JsonPathValidator js = new JsonPathValidator();
		Integer iid = js.read(allissues, "$.[0].iid"); //js.readList(allissues, "$..iid"); //get all existing iid's
		
		//Creating Issues from the excel
        // Start of creation -->
        Issue issueDataToEdit = new Issue(title,description,issue_type,assignee_id,confidential,discussion_locked,created_at, due_date,labels,add_labels,remove_labels,milestone_id,state_event, weight,epic_id,epic_iid);
        //Empty values will be discarded
		restClient.put(PROJECTS_ENDPOINT,iid,"json", issueDataToEdit,true, true)
				.then().log().all()
				.assertThat().statusCode(APIHttpStatus.OK_200.getCode())
				.assertThat().body("title", equalTo("changed title"))
				.assertThat().body("discussion_locked", equalTo(false));
    }
}
