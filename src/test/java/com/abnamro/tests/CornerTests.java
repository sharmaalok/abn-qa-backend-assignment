package com.abnamro.tests;

import com.abnamro.base.BaseTest;
import com.abnamro.constants.APIHttpStatus;
import com.abnamro.pojo.Issue;
import com.abnamro.utils.StringUtils;
import org.testng.annotations.Test;

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
}
