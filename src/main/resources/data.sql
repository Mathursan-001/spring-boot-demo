CREATE TABLE score (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    userName VARCHAR(255) NOT NULL,
    enteredNumber INT NOT NULL,
    generatedNumber INT NOT NULL,
    attemptDateTime TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

insert into score(userName,enteredNumber,generatedNumber) values('USER',1,2)