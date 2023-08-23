package com.abnamro.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import com.abnamro.base.BaseTest;
import com.abnamro.constants.APIHttpStatus;

import static org.hamcrest.Matchers.is;
import com.abnamro.utils.JsonPathValidator;
import io.restassured.response.Response;


public class GetIssuesTest extends BaseTest{
	@Test(description="This test will get all the issues with authorisation header")
	public void getAllIssues() {
		restClient.get(ISSUES_ENDPOINT, true,  true)
					.then().log().all()
						.assertThat().statusCode(APIHttpStatus.OK_200.getCode());
						
	}
	@Test(description="This test will get all the issues with authorisation header and with query parameter(s)")
	public void getIssuesByQueryParameters() {
		Map<String,Object> queryParams = new HashMap<>();
		queryParams.put("confidential", 0 );   //implies boolean confidential=true
		queryParams.put("labels", "bug");

		restClient.get(ISSUES_ENDPOINT, queryParams, null,true, true)
					.then().log().all()
						.assertThat().statusCode(APIHttpStatus.OK_200.getCode())
						.assertThat().body("size()", is(1));
//						.assertThat().body("iid[0]", equalTo(27));
//						.assertThat().body("iid[1]".toString(), equalTo(26));
													
	}

	@Test(description="This test will get all the issues for a group with authorisation header")
	public void getAllIssuesForGroup() {
		restClient.get(GROUPS_ENDPOINT+"/"+groupId+"/issues", true,  true)
				.then().log().all()
				.assertThat().statusCode(APIHttpStatus.OK_200.getCode());

	}
	@Test(description="This test will get all the issues for a group with authorisation header and with query parameter(s)")
	public void getIssuesForGroupByQueryParameters() {
		Map<String,Object> queryParams = new HashMap<>();
		queryParams.put("confidential", 0 );   //implies boolean confidential=true
		queryParams.put("labels", "bug");

		restClient.get(GROUPS_ENDPOINT+"/"+groupId+"/issues", queryParams, null,true, true)
				.then().log().all()
				.assertThat().statusCode(APIHttpStatus.OK_200.getCode())
				.assertThat().body("size()", is(1));
//						.assertThat().body("iid[0]", equalTo(27));
//						.assertThat().body("iid[1]".toString(), equalTo(26));
	}

	@Test(description="This test will get all the issues for a project with authorisation header")
	public void getAllIssuesForProject() {
		restClient.get(PROJECTS_ENDPOINT+"/"+projectId+"/issues", true,  true)
				.then().log().all()
				.assertThat().statusCode(APIHttpStatus.OK_200.getCode());

	}
	@Test(description="This test will get all the issues for a project with authorisation header and with query parameter(s)")
	public void getIssuesForProjectByQueryParameters() {
		Map<String,Object> queryParams = new HashMap<>();
		queryParams.put("confidential", 0 );   //implies boolean confidential=true
		queryParams.put("labels", "bug");

		restClient.get(PROJECTS_ENDPOINT+"/"+projectId+"/issues", queryParams, null,true, true)
				.then().log().all()
				.assertThat().statusCode(APIHttpStatus.OK_200.getCode())
				.assertThat().body("size()", is(1));
//						.assertThat().body("iid[0]", equalTo(27));
//						.assertThat().body("iid[1]", equalTo(26));
	}

	@Test(description="This test will get single issues with authorisation header" +
			"Expectation is Forbidden message as the account is not an administrator account")
	public void getSingleIsue() {

		int issueId = 48544464; //Enter a valid Issue Id here

		restClient.get(ISSUES_ENDPOINT+"/"+issueId, true,  true)
				.then().log().all()
				.assertThat().statusCode(APIHttpStatus.FORBIDDEN_403.getCode());
	}

	@Test(description="This test will single project issues with authorisation header")
	public void getSingleProjectIsue() {
		Response allissues = restClient.get(ISSUES_ENDPOINT, true,  true);
		allissues.then().log().all()
				.assertThat().statusCode(APIHttpStatus.OK_200.getCode());

		JsonPathValidator js = new JsonPathValidator();
		Integer iid = js.read(allissues, "$.[0].iid"); //js.readList(allissues, "$..iid"); //get all existing iid's

		// Integer issueIid = 35;//Enter a valid issue_iid here
		restClient.get(PROJECTS_ENDPOINT+"/"+projectId+"/issues/"+iid,true,  true)
				.then().log().all()
				.assertThat().statusCode(APIHttpStatus.OK_200.getCode());
	}
}
