package com.abnamro.tests;

import com.abnamro.base.BaseTest;
import com.abnamro.constants.APIHttpStatus;
import com.abnamro.utils.JsonPathValidator;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class DeleteIssuesTest extends BaseTest{

	@Test(description ="Delete an Issue by iid")
	public void deleteIssueByIid()
	{
		//First we fetch an idd of an issue. This issue would be deleted. 
		//-->START
		Response allissues = restClient.get(ISSUES_ENDPOINT, true,  true);
		allissues.then().log().all()
				.assertThat().statusCode(APIHttpStatus.OK_200.getCode());
		JsonPathValidator js = new JsonPathValidator();
		Integer iid = js.read(allissues, "$.[0].iid"); //js.readList(allissues, "$..iid"); //get all existing iid's
		//-->END

		//--> START of delete request
		restClient.delete(PROJECTS_ENDPOINT,iid,true,true)
					.then().log().all()
						.assertThat().statusCode(APIHttpStatus.NO_CONTENT_204.getCode());
		//-->END
	}
}
