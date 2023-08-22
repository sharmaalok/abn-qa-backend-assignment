package com.abnamro.tests;

import com.abnamro.base.BaseTest;
import com.abnamro.constants.APIHttpStatus;
import com.abnamro.utils.JsonPathValidator;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import java.util.List;
import static org.hamcrest.Matchers.*;

public class DeleteIssuesTest extends BaseTest{
	@Test(description ="Delete All Issues")
	public void DeleteAllIssues() {
		Response allissues = restClient.get(ISSUES_ENDPOINT, true,  true);
		allissues.then().log().all()
				.assertThat().statusCode(APIHttpStatus.OK_200.getCode());

		JsonPathValidator js = new JsonPathValidator();
		List<Integer> iids = js.readList(allissues, "$..iid"); //get all existing iid's
		for (Integer id: iids){
			restClient.delete(PROJECTS_ENDPOINT,id,true,true)
					.then().log().all()
						.assertThat().statusCode(APIHttpStatus.NO_CONTENT_204.getCode());
		}

	}

	@Test(enabled = false,description ="Delete an Issue by iid")
	public void deleteIssueByIid()
	{
		Integer iid = 36;//Enter a valid issue iid here
		restClient.delete(PROJECTS_ENDPOINT,iid,true,true)
					.then().log().all()
						.assertThat().statusCode(APIHttpStatus.NO_CONTENT_204.getCode());
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
