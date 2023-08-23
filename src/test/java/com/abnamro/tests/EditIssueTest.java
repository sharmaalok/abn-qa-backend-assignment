package com.abnamro.tests;
import java.util.HashMap;
import java.util.Map;
import com.abnamro.base.BaseTest;
import com.abnamro.constants.APIHttpStatus;
import com.abnamro.utils.JsonPathValidator;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;
import com.abnamro.pojo.Issue;
import static org.hamcrest.Matchers.equalTo;;

public class EditIssueTest extends BaseTest{

	@Test(description="This test will edit the issue with given parameters")
	public void editIssueWithParameter() {

		//First we fetch an idd of an issue. This issue would be edited. 
		//-->START

		Response allissues = restClient.get(ISSUES_ENDPOINT, true,  true);
		allissues.then().log().all()
				.assertThat().statusCode(APIHttpStatus.OK_200.getCode());
		JsonPathValidator js = new JsonPathValidator();
		Integer iid = js.read(allissues, "$.[0].iid"); //get a single issue iid's
		//-->END

		
		//--> START of put request
		Map<String, Object> queryParams = new HashMap<>();
		queryParams.put("title", "changed title for another issue"); //title to be changed via parameter
		queryParams.put("discussion_locked", 1); //discussion_locked bollean value to be changed via parameter

		restClient.put(PROJECTS_ENDPOINT,iid,"json", queryParams,true, true)
				.then().log().all()
				.assertThat().statusCode(APIHttpStatus.OK_200.getCode())
				.assertThat().body("title", equalTo("changed title for another issue"))
				.assertThat().body("discussion_locked", equalTo(true));
		//-->END
    }



	//Data to be used body of the put request.
	@DataProvider()
	public Object[][] getIssuesTestData() {
		return new Object[][] {
                {"Changed title for another issue", "Description changed", "issue","",false,false,"","","bug","","",1,"",null,null,null}
		};
	}
	@Test(dataProvider = "getIssuesTestData",description="This test will edit the issue with given body")
	public void editIssueWithBody(String title, String description, String issue_type,String assignee_id, Boolean confidential, Boolean discussion_locked, String created_at, String due_date,  String labels, String add_labels, String remove_labels,Integer milestone_id,String state_event,Integer weight,Integer epic_id,Integer epic_iid) 
    {

		//First we fetch an idd of an issue. This issue would be edited. 
		//-->START        
		Response allissues = restClient.get(ISSUES_ENDPOINT, true,  true);
		allissues.then().log().all()
				.assertThat().statusCode(APIHttpStatus.OK_200.getCode());
		JsonPathValidator js = new JsonPathValidator();
		Integer iid = js.read(allissues, "$.[0].iid");  //get a single issue iid's
		//-->END
		
		//--> START of put request
		//Body creation for edit API
		Issue issueDataToEdit = new Issue(title,description,issue_type,assignee_id,confidential,discussion_locked,created_at, due_date,labels,add_labels,remove_labels,milestone_id,state_event, weight,epic_id,epic_iid);
        //Empty values will be discarded
		restClient.put(PROJECTS_ENDPOINT,iid,"json", issueDataToEdit,true, true)
				.then().log().all()
				.assertThat().statusCode(APIHttpStatus.OK_200.getCode())
				.assertThat().body("title", equalTo("Changed title for another issue"))
				.assertThat().body("description", equalTo("Description changed"))
				.assertThat().body("discussion_locked", equalTo(false));
		//-->END
    }
}
