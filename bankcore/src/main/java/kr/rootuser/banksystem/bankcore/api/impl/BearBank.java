package kr.rootuser.banksystem.bankcore.api.impl;

import java.util.HashMap;
import java.util.List;

import kr.rootuser.banksystem.bankcore.CardNumber;
import kr.rootuser.banksystem.bankcore.Constants;
import kr.rootuser.banksystem.bankcore.PinNumber;
import kr.rootuser.banksystem.bankcore.Transactions;
import kr.rootuser.banksystem.bankcore.api.Accounts;
import kr.rootuser.banksystem.bankcore.api.Bank;
import kr.rootuser.banksystem.bankcore.api.Cards;

public class BearBank implements Bank {
	private HashMap<String, Transactions> tranList = new HashMap<String, Transactions>();

	@Override
	public String insertCard(CardNumber cardNumber) {
		Cards card = new BearCards(cardNumber);
		Transactions transaction = new Transactions(card);
		String transactId = transaction.getTractionsId();
		tranList.put(transactId, transaction);
		return transactId;
	}

	@Override
	public boolean enterPin(String transactId, PinNumber pinNumber) {
		Transactions tran = tranList.get(transactId);
		Cards card = tran.getBearCards();
		if (card.checkPin(pinNumber)) {
			tran.verifyPin();
//			tranList.replace(transactId, tran);
			return true;
		}
		return false;
	}

	@Override
	public boolean checkPin(CardNumber cardNumber, PinNumber pinNumber) {
		Cards card = new BearCards(cardNumber);
		return card.checkPin(pinNumber);
	}

	@Override
	public List<String> listAccounts(String transactId) {
		Transactions tran = tranList.get(transactId);
		if (tran.isVerified()) {
			CardNumber cardNum = tran.getBearCards().getCardNumber();
			Accounts account = new BearAccounts(cardNum);
			return account.list();
		}
		return null;
	}

	@Override
	public int getAccount(String transactId, Constants.Accounts account) {
		Transactions tran = tranList.get(transactId);
		if (tran.isVerified()) {
			CardNumber cardNum = tran.getBearCards().getCardNumber();
			Accounts bearAccount = new BearAccounts(cardNum);
			return bearAccount.get(account);
		}
		return -1;
	}

}
