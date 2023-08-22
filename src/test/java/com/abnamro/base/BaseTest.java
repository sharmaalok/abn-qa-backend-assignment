package com.abnamro.base;
import org.testng.annotations.BeforeTest;
import com.abnamro.client.RestClient;
import com.abnamro.configuration.ConfigurationManager;
public class BaseTest extends ConfigurationManager{

	public static final String ISSUES_ENDPOINT = "/issues";
	public final String PROJECTS_ENDPOINT = "/projects";
	public final String GROUPS_ENDPOINT = "/groups/"+groupId+"/issues";
	//Service URLs:
	public static String baseURI;
	public static String token;
	public static String projectId;
	public static String groupId;
	protected RestClient restClient;
	@BeforeTest
	public void setUp() {
		ConfigurationManager config = new ConfigurationManager();
		prop = config.initProp();
		baseURI = prop.getProperty("baseURI");
		token = prop.getProperty("token");
		projectId = prop.getProperty("projectId");
		groupId = prop.getProperty("groupId");
		restClient = new RestClient(prop,baseURI,token,projectId,groupId);
	}
}