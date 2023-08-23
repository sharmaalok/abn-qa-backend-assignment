package com.abnamro.tests;

import java.util.HashMap;
import java.util.Map;
import com.abnamro.base.BaseTest;
import com.abnamro.constants.APIHttpStatus;
import com.abnamro.utils.JsonPathValidator;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.equalTo;

public class CornerTests extends BaseTest{

	@Test(description ="Corner Tests for edit issues api. Validating the mandatory field message.")
	public void editIssueCornerTest() {

		Response allissues = restClient.get(ISSUES_ENDPOINT, true,  true);
		allissues.then().log().all()
				.assertThat().statusCode(APIHttpStatus.OK_200.getCode());
		JsonPathValidator js = new JsonPathValidator();
		Integer iid = js.read(allissues, "$.[0].iid"); //js.readList(allissues, "$..iid"); //get all existing iid's
		
		Map<String, Object> queryParams = new HashMap<>();
		queryParams.put("subscribed", true);

		restClient.put(PROJECTS_ENDPOINT,iid,"json", queryParams,true, true)
				.then().log().all()
				.assertThat().statusCode(APIHttpStatus.BAD_REQUEST_400.getCode())
				.assertThat().body("error", equalTo("assignee_id, assignee_ids, confidential, created_at, description, discussion_locked, due_date, labels, add_labels, remove_labels, milestone_id, state_event, title, issue_type, weight, epic_id, epic_iid are missing, at least one parameter must be provided"));
	}

	@Test(description ="Corner Tests for create issues api. validating that the title is mandatory")
	public void createIssueCornerTest() {

		Map<String, Object> queryParams = new HashMap<>();
		queryParams.put("description", true);

		restClient.post(PROJECTS_ENDPOINT, "json", queryParams, true, true)
				.then().log().all()
				.assertThat().statusCode(APIHttpStatus.BAD_REQUEST_400.getCode())
				.assertThat().body("error", equalTo("title is missing"));
	

	}

	@Test(description ="Error when deleting a non existing Issue")
	public void deleteIssueError()
	{
		Integer iid = 15000;
		restClient.delete(PROJECTS_ENDPOINT,iid,true,true)
				.then().log().all()
				.assertThat().statusCode(APIHttpStatus.NOT_FOUND_404.getCode())
				.assertThat().body("message", equalTo("404 Issue Not Found"));
	}

}
