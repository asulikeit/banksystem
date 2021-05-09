package kr.rootuser.banksystem.bankcore.api;

import kr.rootuser.banksystem.bankcore.CardNumber;
import kr.rootuser.banksystem.bankcore.PinNumber;

public interface Cards {

	CardNumber getCardNumber();

	boolean checkPin(PinNumber pinNumber);

}