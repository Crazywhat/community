CREATE TABLE notification
(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    sender_name VARCHAR(50) NOT NULL,
    sender_id BIGINT NOT NULL,
    receiver_id BIGINT NOT NULL,
    type int NOT NULL,
    question_tile VARCHAR(50) NOT NULL,
    question_id BIGINT NOT NULL,
    status int NOT NULL
);