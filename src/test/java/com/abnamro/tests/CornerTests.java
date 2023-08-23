package com.abnamro.tests;

import com.abnamro.base.BaseTest;
import com.abnamro.constants.APIHttpStatus;
import com.abnamro.pojo.Issue;
import com.abnamro.utils.StringUtils;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.equalTo;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class CornerTests extends BaseTest{
	@Test(description ="Corner Tests for create issues api")
	public void createIssueCornerTest() {

		//// WRITE COMMENTS ON ALL TESTS
		/// run all tests
	}

	@Test(description ="Corner Tests for edit issues api")
	public void EditIssueCornerTest() {

	}

	@Test(description ="Corner Tests for getIssues api - No authorisation")
	public void GetIssueCornerTest() {

	}

	@Test(description ="Corner Tests for delete issues api")
	public void DeleteIssueCornerTest() {

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
