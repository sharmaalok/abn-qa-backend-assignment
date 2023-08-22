package com.abnamro.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import com.abnamro.base.BaseTest;
import com.abnamro.constants.APIHttpStatus;

import static org.hamcrest.Matchers.*;


public class GetIssuesTest extends BaseTest{
	@Test(description="This test will get all the issues with authorisation header")
	public void getAllIssues() {
		restClient.get(ISSUES_ENDPOINT, true,  true)
					.then().log().all()
						.assertThat().statusCode(APIHttpStatus.OK_200.getCode());
						
	}
	@Test(enabled=false, description="This test will get all the issues with authorisation header and with query parameter(s)")
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
		restClient.getgroupissues(GROUPS_ENDPOINT, true,  true)
				.then().log().all()
				.assertThat().statusCode(APIHttpStatus.OK_200.getCode());

	}
	@Test(enabled=false,description="This test will get all the issues for a group with authorisation header and with query parameter(s)")
	public void getIssuesForGroupByQueryParameters() {
		Map<String,Object> queryParams = new HashMap<>();
		queryParams.put("confidential", 0 );   //implies boolean confidential=true
		queryParams.put("labels", "bug");

		restClient.getgroupissues(GROUPS_ENDPOINT, queryParams, null,true, true)
				.then().log().all()
				.assertThat().statusCode(APIHttpStatus.OK_200.getCode())
				.assertThat().body("size()", is(1));
//						.assertThat().body("iid[0]", equalTo(27));
//						.assertThat().body("iid[1]".toString(), equalTo(26));
	}

	@Test(description="This test will get all the issues for a project with authorisation header")
	public void getAllIssuesForProject() {
		restClient.getprojectissues(PROJECTS_ENDPOINT, true,  true)
				.then().log().all()
				.assertThat().statusCode(APIHttpStatus.OK_200.getCode());

	}
	@Test(enabled=false,description="This test will get all the issues for a project with authorisation header and with query parameter(s)")
	public void getIssuesForProjectByQueryParameters() {
		Map<String,Object> queryParams = new HashMap<>();
		queryParams.put("confidential", 0 );   //implies boolean confidential=true
		queryParams.put("labels", "bug");

		restClient.getprojectissues(PROJECTS_ENDPOINT, queryParams, null,true, true)
				.then().log().all()
				.assertThat().statusCode(APIHttpStatus.OK_200.getCode())
				.assertThat().body("size()", is(1));
//						.assertThat().body("iid[0]", equalTo(27));
//						.assertThat().body("iid[1]", equalTo(26));
	}

	@Test(enabled = false,description="This test will get single issues with authorisation header" +
			"Expectation is Forbidden message as the account is not an administrator account")
	public void getSingleIsue() {

		int issueId = 48544464; //Enter a valid Issue Id here

		restClient.get(ISSUES_ENDPOINT+"/"+issueId, true,  true)
				.then().log().all()
				.assertThat().statusCode(APIHttpStatus.FORBIDDEN_403.getCode());
	}

	@Test(enabled = false,description="This test will single project issues with authorisation header")
	public void getSingleProjectIsue() {
		Integer issueIid = 35;//Enter a valid issue_iid here
		restClient.getsingleprojectissue(PROJECTS_ENDPOINT, issueIid,true,  true)
				.then().log().all()
				.assertThat().statusCode(APIHttpStatus.OK_200.getCode());
	}
}
