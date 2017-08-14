package com.stackroute.activitystream.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.activitystream.CircleService.CircleServiceApplication;
import com.stackroute.activitystream.dao.UserCircleDao;
import com.stackroute.activitystream.model.UserCircle;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { CircleServiceApplication.class })
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT,classes=CircleServiceApplication.class)
public class UserCircleControllerTest {
	
	  private MockMvc mockMvc;
	  
	  @Autowired
	    private WebApplicationContext webApplicationContext;
	  
	    @MockBean
		private UserCircleDao userCircleDAO;
	    
	    @Before
	    public void setUp() {
	        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	    }
	    
	    
	    private static String asJsonString(final Object obj) {
		    try {
		        final ObjectMapper mapper = new ObjectMapper();
		        final String jsonContent = mapper.writeValueAsString(obj);
		        return jsonContent;
		    } catch (Exception e) {
		        throw new RuntimeException(e);
		    }
		}  
	    @Test
	    public void testGetUserByCircle_NOError() throws Exception
	    {
	    	UserCircle userCircle =new UserCircle();
	    	userCircle.setCircleName("doc");
	    	
	    	mockMvc.perform(post("http://localhost:9013/api/userCircle/geUserByCircleName").content(asJsonString(userCircle)).contentType(MediaType.APPLICATION_JSON)
					  .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
			
	    }
	    
	    @Test
	    public void testGetUserByCircle_Error() throws Exception
	    {
	    	UserCircle userCircle =new UserCircle();
	    	userCircle.setCircleName("xyz");
	    	
	    	mockMvc.perform(post("http://localhost:9013/api/userCircle/geUserByCircleName").content(asJsonString(userCircle)).contentType(MediaType.APPLICATION_JSON)
					  .accept(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());
			
	    }
	    
	    @Test
	    public void testaddUserToCircle_NOError() throws Exception
	    {
	    	UserCircle userCircle =new UserCircle();
	    	userCircle.setEmailId("sweta@mail.com");
	    	userCircle.setCircleName("task");

	    	mockMvc.perform(post("http://localhost:9013/api/userCircle/addUserToCircle").content(asJsonString(userCircle)).contentType(MediaType.APPLICATION_JSON)
					  .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());	  	
	    }

	    @Test
	    public void testaddUserToCircle_Error() throws Exception
	    {
	    	UserCircle userCircle =new UserCircle();
	    	userCircle.setEmailId("sweta@mail.com");
	    	userCircle.setCircleName("doc");

	    	mockMvc.perform(post("http://localhost:9013/api/userCircle/addUserToCircle").content(asJsonString(userCircle)).contentType(MediaType.APPLICATION_JSON)
					  .accept(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());	  	
	    }
}
