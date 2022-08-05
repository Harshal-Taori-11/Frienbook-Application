//package com.cts.microservice.adminreporttest;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.jupiter.api.Assertions.*;
//import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
//import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;
//
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.context.TestConfiguration;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.client.MockRestServiceServer;
//
//import com.cts.microservice.adminreport.service.AdminService;
//import com.cts.microservice.adminreport.service.AdminServiceImpl;
//
//@RestClientTest(AdminServiceImpl.class)
//@RunWith(SpringRunner.class)
//@ContextConfiguration(classes = AdminServiceImpl.class)
//class adminServiceImplTest {
//	
//	@Autowired
//	private MockRestServiceServer mockRestServiceServer;
//	
//	@Autowired
//	private AdminService adminServiceImpl;
//	
//	@Test
//	void deleteThePostTest() {
//		this.mockRestServiceServer.expect(requestTo("http://localhost:8082/post/delete/3"))
//		.andRespond(withSuccess("Success", MediaType.TEXT_PLAIN));
//	
//	
//		String response = adminServiceImpl.deleteThePost((long)3); 
//		
//		assertThat(response).isEqualTo("Success");
//	}
//	
//	@Test
//	void removeThePostTest() {
//		this.mockRestServiceServer.expect(requestTo("http://localhost:8082/post/unreport/3"))
//		.andRespond(withSuccess("Success", MediaType.TEXT_PLAIN));
//	
//	
//		String response = adminServiceImpl.removeThePost((long)3); 
//		
//		assertThat(response).isEqualTo("Success");
//	}
//
//}
