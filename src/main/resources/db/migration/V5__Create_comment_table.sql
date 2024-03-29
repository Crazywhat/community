CREATE TABLE comment
(
    id bigint AUTO_INCREMENT PRIMARY KEY,
    parent_id bigint NOT NULL,
    type int NOT NULL,
    commentator bigint NOT NULL,
    gmt_create bigint NOT NULL,
    gmt_modified bigint NOT NULL,
    like_count bigint DEFAULT 0,
    content varchar(1024) NOT NULL
);