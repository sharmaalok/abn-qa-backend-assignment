package com.abnamro.tests;

import com.abnamro.base.BaseTest;
import com.abnamro.constants.APIHttpStatus;
import com.abnamro.utils.JsonPathValidator;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import java.util.List;

public class DeleteAllIssuesTest extends BaseTest{
	
	@Test(description ="Delete All Issues")
	public void deleteAllIssues() {
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
}
