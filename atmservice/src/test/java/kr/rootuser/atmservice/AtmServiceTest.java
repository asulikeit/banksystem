package kr.rootuser.atmservice;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.assertj.core.util.Arrays;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AtmServiceTest {
	
	@Autowired
	private MockMvc mvc;

	/**
	 * 1) insert a card (4 or more characters)
	 * 2) enter pin number (the last 4 digits	for card number - to keep simple)
	 * 3) list accounts
	 * 4) get an account
	 * @throws Exception 
	 */
	@Test
	public void testNormalFlow() throws Exception {
		String cardNumber = "CARD12345";
		String pinNumber = "2345";
		
//		1) insert a card
		MvcResult cardResult = mvc.perform(
				MockMvcRequestBuilders.get("/cards/" + cardNumber)).
				andExpect(status().isOk()).andReturn();
		String tranId = cardResult.getResponse().getContentAsString();
		
//		2) enter pin
		String jsonBody = "{\"pinNumber\": \"" + pinNumber + "\", \"transactionId\": \"" + tranId + "\"}"; 
		
		MvcResult pinResult = mvc.perform(
				MockMvcRequestBuilders.post("/cards/" + cardNumber).
				content(jsonBody).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
		boolean isVerify = Boolean.valueOf(pinResult.getResponse().getContentAsString());
		assertThat(isVerify).isTrue();
		
//		3) list accounts
		MvcResult listResult = mvc.perform(
				MockMvcRequestBuilders.get("/cards/" + cardNumber + "/accounts")
				.header("X-TRANID", tranId)).
				andExpect(status().isOk()).andReturn();
		String accs = listResult.getResponse().getContentAsString().replace("[", "").replace("]", "");
		List<Object> accList = Arrays.asList(accs.split(","));
		assertThat(accList.size()).isEqualTo(3);
		
//		4) get an account
		MvcResult accResult = mvc.perform(
				MockMvcRequestBuilders.get("/cards/" + cardNumber + "/accounts/BALANCE")
				.header("X-TRANID", tranId)).
				andExpect(status().isOk()).andReturn();
		int account = Integer.parseInt(accResult.getResponse().getContentAsString());
		assertThat(account).isGreaterThanOrEqualTo(0);
	}
}
