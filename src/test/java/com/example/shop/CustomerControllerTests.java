package com.example.shop;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@RunWith(SpringRunner.class)
@WebMvcTest(CustomerController.class)
class CustomerControllerTests {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private CustomerService customerService;

	@MockBean
	private  CustomerRepository customerRepository;
	@Test
	public void addCustomer() throws Exception {
		mvc.perform(post("/customers")
		.contentType(MediaType.APPLICATION_JSON)
		.content("{\"login\":\"someLogin\"}"))
		.andExpect(status().isOk())
		.andDo(print());
	}

	@Test
	public void  getCustomer()throws Exception{
		Customer customer = new Customer("jack", "sparrow");
		customer.setId(1);
		mvc.perform(get("/customers/1")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.login").value(customer.getLogin()))
				.andDo(print());
	}

}
