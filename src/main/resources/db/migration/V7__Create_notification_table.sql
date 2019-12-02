CREATE TABLE notification
(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    sender_name VARCHAR(50) NOT NULL,
    sender_id BIGINT NOT NULL,
    receiver_id BIGINT NOT NULL,
    type VARCHAR(50) NOT NULL,
    question_title VARCHAR(50) NOT NULL,
    question_id BIGINT NOT NULL,
    status int NOT NULL,
    gmt_create BIGINT NOT NULL
);