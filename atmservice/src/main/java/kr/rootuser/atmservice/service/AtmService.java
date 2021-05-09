package kr.rootuser.atmservice.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import kr.rootuser.atmservice.ServiceRequest;
import kr.rootuser.banksystem.bankcore.CardNumber;
import kr.rootuser.banksystem.bankcore.Constants;
import kr.rootuser.banksystem.bankcore.PinNumber;
import kr.rootuser.banksystem.bankcore.api.Bank;
import kr.rootuser.banksystem.bankcore.api.impl.BearBank;

@RestController
public class AtmService {

	private Bank bank = new BearBank();

	@GetMapping("/cards/{cardNumber}")
	public ResponseEntity<String> insertCard(@PathVariable String cardNumber) {
		CardNumber cardNum = new CardNumber(cardNumber);
		String transactionId = bank.insertCard(cardNum);
		return ResponseEntity.ok(transactionId);
	}

	@PostMapping("/cards/{cardNumber}")
	public ResponseEntity<String> enterPin(@PathVariable String cardNumber, @RequestBody ServiceRequest request) {
		PinNumber pinNumber = new PinNumber(request.getPinNumber());
		boolean result = bank.enterPin(request.getTransactionId(), pinNumber);
		return ResponseEntity.ok(Boolean.toString(result));
	}

	@PostMapping("/cards")
	public ResponseEntity<String> checkPin(@RequestBody ServiceRequest request) {
		CardNumber cardNum = new CardNumber(request.getCardNumber());
		PinNumber pinNum = new PinNumber(request.getPinNumber());
		boolean result = bank.checkPin(cardNum, pinNum);
		return ResponseEntity.ok(Boolean.toString(result));
	}

	@GetMapping("/cards/{cardNumber}/accounts")
	public ResponseEntity<List<String>> listAccounts(@RequestHeader(name = "X-TRANID", required = true) String tranId) {
		return ResponseEntity.ok(bank.listAccounts(tranId));
	}

	@GetMapping("/cards/{cardNumber}/accounts/{account}")
	public ResponseEntity<Integer> getAccount(@RequestHeader(name = "X-TRANID", required = true) String tranId,
			@PathVariable String cardNumber, @PathVariable String account) {
		return ResponseEntity.ok(bank.getAccount(tranId, Constants.Accounts.valueOf(account)));
	}

}
