package com.stackroute.activitystream.test;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.activitystream.CircleService.CircleServiceApplication;
import com.stackroute.activitystream.dao.CircleDao;
import com.stackroute.activitystream.model.Circle;
import com.stackroute.activitystream.restcontroller.CircleController;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { CircleServiceApplication.class })
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT,classes=CircleServiceApplication.class)
public class CircleControllerTest {
	
	  private MockMvc mockMvc;
	  
	  @Autowired
	    private WebApplicationContext webApplicationContext;
	  
	    @MockBean
		private CircleDao circleDAO;
	    
	    @Before
	    public void setUp() {
	        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	    }
	    @Before
		public void setup() throws Exception {
		    this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
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
	    
	    @Ignore
	    @Test
		public void allCircles() throws Exception {
			//"http://localhost:9012/api/user/authenticate"
		    		this.mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:9013/api/circle/allcircles"))
		    		.andExpect(status().isOk());	    			           	  
		}
	    @Ignore
	    @Test
		public void testGetCirclesByUser_NoError() throws Exception {
			Circle circle=new Circle();
			circle.setCreatedBy("sweta@gmail.com");
			
			mockMvc.perform(post("http://localhost:9013/api/circle/getCirclesByUser").content(asJsonString(circle)).contentType(MediaType.APPLICATION_JSON)
					  .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
			
		}
	    @Ignore
	    @Test
	  		public void testGetCirclesByUser_Error() throws Exception {
	  			Circle circle=new Circle();
	  			circle.setCreatedBy("xyz@gmail.com");
	  			
	  			mockMvc.perform(post("http://localhost:9013/api/circle/getCirclesByUser").content(asJsonString(circle)).contentType(MediaType.APPLICATION_JSON)
	  					  .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	  			
	  		}
	    @Ignore
	    @Test
		public void testCreateCircle_NOError() throws Exception
		{
			Circle circle=new Circle();
			circle.setCircleName("newcircle");
			circle.setCreatedBy("mitali@gmail.com");
			circle.setStatus("active");
			
			mockMvc.perform(post("http://localhost:9012/api/circle/create").content(asJsonString(circle)).contentType(MediaType.APPLICATION_JSON)
					  .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
			
		}
	 
	    @Test
		public void testCreateCircle_Error() throws Exception
		{
			Circle circle=new Circle();
			circle.setCircleName("newcircle");
			circle.setCreatedBy("mitali@gmail.com");
			circle.setStatus("active");
			
			mockMvc.perform(post("http://localhost:9012/api/circle/create").content(asJsonString(circle)).contentType(MediaType.APPLICATION_JSON)
					  .accept(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());
			
		}
	    @Test
	  		public void testRemoveCircle_NOError() throws Exception
	  		{
	  			Circle circle=new Circle();
	  			circle.setCircleName("newcircle");		
	  			mockMvc.perform(post("http://localhost:9012/api/circle/removeCircle").content(asJsonString(circle)).contentType(MediaType.APPLICATION_JSON)
	  					  .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	  			
	  		}
	    
	    @Test
	  		public void testRemoveCircle_Error() throws Exception
	  		{
	  			Circle circle=new Circle();
	  			circle.setCircleName("circle1");		
	  			mockMvc.perform(post("http://localhost:9012/api/circle/removeCircle").content(asJsonString(circle)).contentType(MediaType.APPLICATION_JSON)
	  					  .accept(MediaType.APPLICATION_JSON)).andExpect(status().isFound());
	  			
	  		}
	    
	 
}
