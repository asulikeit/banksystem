package kr.rootuser.banksystem.bankcore.api;

import java.util.List;

import kr.rootuser.banksystem.bankcore.CardNumber;
import kr.rootuser.banksystem.bankcore.Constants;
import kr.rootuser.banksystem.bankcore.PinNumber;

public interface Bank {

	String insertCard(CardNumber cardNumber);

	boolean enterPin(String transactId, PinNumber pinNumber);

	boolean checkPin(CardNumber cardNumber, PinNumber pinNumber);

	List<String> listAccounts(String transactId);

	int getAccount(String transactId, Constants.Accounts account);

}