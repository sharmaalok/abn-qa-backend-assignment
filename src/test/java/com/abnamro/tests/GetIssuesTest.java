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
		queryParams.put("confidential", 0 );   //implies boolean confidential=false
		queryParams.put("labels", "bug");

		restClient.get(ISSUES_ENDPOINT, queryParams, null,true, true)
					.then().log().all()
						.assertThat().statusCode(APIHttpStatus.OK_200.getCode())
						.assertThat().body("size()", is(1));													
	}

	@Test(description="This test will get all the issues for a GROUP with authorisation header")
	public void getAllIssuesForGroup() {
		restClient.get(GROUPS_ENDPOINT+"/"+groupId+"/issues", true,  true)
				.then().log().all()
				.assertThat().statusCode(APIHttpStatus.OK_200.getCode());

	}
	@Test(description="This test will get all the issues for a GROUP with authorisation header and with query parameter(s)")
	public void getIssuesForGroupByQueryParameters() {
		Map<String,Object> queryParams = new HashMap<>();
		queryParams.put("confidential", 0 );   //implies boolean confidential=false
		queryParams.put("labels", "bug");

		restClient.get(GROUPS_ENDPOINT+"/"+groupId+"/issues", queryParams, null,true, true)
				.then().log().all()
				.assertThat().statusCode(APIHttpStatus.OK_200.getCode())
				.assertThat().body("size()", is(1));
	}

	@Test(description="This test will get all the issues for a PROJECT with authorisation header")
	public void getAllIssuesForProject() {
		restClient.get(PROJECTS_ENDPOINT+"/"+projectId+"/issues", true,  true)
				.then().log().all()
				.assertThat().statusCode(APIHttpStatus.OK_200.getCode());

	}
	@Test(description="This test will get all the issues for a PROJECT with authorisation header and with query parameter(s)")
	public void getIssuesForProjectByQueryParameters() {
		Map<String,Object> queryParams = new HashMap<>();
		queryParams.put("confidential", 0 );   //implies boolean confidential=true
		queryParams.put("labels", "bug");

		restClient.get(PROJECTS_ENDPOINT+"/"+projectId+"/issues", queryParams, null,true, true)
				.then().log().all()
				.assertThat().statusCode(APIHttpStatus.OK_200.getCode())
				.assertThat().body("size()", is(1));
	}

	@Test(description="This test will get SINGLE ISSUE with authorisation header" +
			"Expectation is Forbidden message as the account is not an administrator account")
	public void getSingleIsue() {

		Response allissues = restClient.get(ISSUES_ENDPOINT, true,  true);
		allissues.then().log().all()
				.assertThat().statusCode(APIHttpStatus.OK_200.getCode());

		JsonPathValidator js = new JsonPathValidator();
		Integer issueId = js.read(allissues, "$.[0].id");

		restClient.get(ISSUES_ENDPOINT+"/"+issueId, true,  true)
				.then().log().all()
				.assertThat().statusCode(APIHttpStatus.FORBIDDEN_403.getCode());
	}

	@Test(description="This test will SINGLE PROJECT ISSUE with authorisation header. Input is a unique issue iid")
	public void getSingleProjectIsue() {
		Response allissues = restClient.get(ISSUES_ENDPOINT, true,  true);
		allissues.then().log().all()
				.assertThat().statusCode(APIHttpStatus.OK_200.getCode());

		JsonPathValidator js = new JsonPathValidator();
		Integer iid = js.read(allissues, "$.[0].iid");

		restClient.get(PROJECTS_ENDPOINT+"/"+projectId+"/issues/"+iid,true,  true)
				.then().log().all()
				.assertThat().statusCode(APIHttpStatus.OK_200.getCode());
	}
}
