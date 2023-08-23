package com.abnamro.tests;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import com.abnamro.pojo.Issue;
import org.testng.annotations.Test;
import com.abnamro.base.BaseTest;
import com.abnamro.constants.APIHttpStatus;
import com.abnamro.utils.StringUtils;

public class APISchemaValidationTest extends BaseTest{
	@Test(description ="API schema test for create issues api")
	public void createIssueAPISchemaTest() {

		Issue issue = new Issue(StringUtils.getRandomTitle(),StringUtils.getRandomDescription(),"incident","",false,null,"","","bug","","",1,"",null,null,null);

		 restClient.post(PROJECTS_ENDPOINT, "json", issue, true, true)
					.then().log().all()
						.assertThat().statusCode(APIHttpStatus.CREATED_201.getCode())
							.body(matchesJsonSchemaInClasspath("schema/createissueschema.json"));
	}

	@Test(description ="API schema test for get issues api")
	public void getIssueAPISchemaTest() {
		restClient.get(ISSUES_ENDPOINT, true,  true)
				.then().log().all()
				.assertThat().statusCode(APIHttpStatus.OK_200.getCode())
				.body(matchesJsonSchemaInClasspath("schema/getissueschema.json"));
	}
}
