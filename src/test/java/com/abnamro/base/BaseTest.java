package com.abnamro.base;
import org.testng.annotations.BeforeTest;
import com.abnamro.client.RestClient;
import com.abnamro.configuration.ConfigurationManager;

public class BaseTest extends ConfigurationManager{

	protected static final String ISSUES_ENDPOINT = "/issues";
	protected final String PROJECTS_ENDPOINT = "/projects";
	protected final String GROUPS_ENDPOINT = "/groups";
	protected static String baseURI;
	protected static String token;
	protected static String projectId;
	protected static String groupId;
	protected RestClient restClient;
	@BeforeTest
	public void setUp() {
		ConfigurationManager config = new ConfigurationManager();
		prop = config.initProp();
		baseURI = prop.getProperty("baseURI");
		token = prop.getProperty("token");
		projectId = prop.getProperty("projectId");
		groupId = prop.getProperty("groupId");
		// restClient = new RestClient(prop,baseURI,token,projectId,groupId);
		restClient = new RestClient(baseURI,token,projectId);
	}
}