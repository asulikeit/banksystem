# banksystem

## atmservice - API (controlle)
## bankcore - business logic

you can develop API with bankcore interface.

## Environment
    Java 1.8
    Maven
    Git

## How To
    git clone https://github.com/asulikeit/banksystem.git
    cd banksystem/atmservice
    mvn clean install
    cd target
    java -jar atmservice-0.0.1-SNAPSHOT.jar
  
## API Test
### GET /cards/{cardNumber}
insert a card & get transaction id
### POST /cards/{cardNumber} & BODY {"pinNumber": "$pinNumber", "transactionId": "$transactionId"}
enter pin number
### POST /cards & BODY {"pinNumber": "$pinNumber", "cardNumber": "$cardNumber"}
just check card and pin number
### GET /cards/{cardNumber}/accounts & HEADER {"X-TRANID": $transactionId}
list accounts after verify pin
### GET /cards/{cardNumber}/accounts/{account} & HEADER {"X-TRANID": $transactionId}
get an account (balance, deposit, withdraw)


### Test code: https://github.com/asulikeit/banksystem/blob/main/atmservice/src/test/java/kr/rootuser/atmservice/AtmServiceTest.java
