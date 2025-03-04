##Java Spring Boot application for handling cash withdrawal from an ATM

Below have been focused while developing this application:

ATM with various denominations: We have assume the ATM supports a few denominations (like 100, 500, 1000).
Minimum number of banknotes: We ensure that the ATM dispenses the minimum number of banknotes.
Flexibility to handle any denomination (multiple of 10).
Parallel withdrawals: We have utilized asynchronous processing to handle parallel withdrawal requests.


Local Url
----------
http://localhost:8080/citiatm/withdraw?amount=500 (Post Method)


To Test ATMService class, we have created the test cases that validate different scenarios such as:

A withdrawal request that is a multiple of 10.
A withdrawal request that is not a multiple of 10.
A withdrawal request that exceeds the available funds.
A withdrawal request where the ATM cannot dispense the exact amount due to limitations in denominations.
A successful withdrawal with exact notes dispensed.