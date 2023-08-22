package com.abnamro.tests;
import java.util.HashMap;
import java.util.Map;
import com.abnamro.base.BaseTest;
import com.abnamro.constants.APIHttpStatus;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;

public class EditIssueTest extends BaseTest{
	@Test(description="This test will edit the issue with given parameters")
	public void EditIssueWithParameter() {

		Map<String, Object> queryParams = new HashMap<>();
		queryParams.put("title", "changed title");
		queryParams.put("discussion_locked", 0);
		Integer iid = 63;

		restClient.put(PROJECTS_ENDPOINT,iid,"json", queryParams,true, true)
				.then().log().all()
				.assertThat().statusCode(APIHttpStatus.OK_200.getCode())
				.assertThat().body("title", equalTo("changed title"))
				.assertThat().body("discussion_locked", equalTo(false));
    }
}
