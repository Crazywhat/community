CREATE TABLE question (
  id bigint NOT NULL AUTO_INCREMENT primary key,
  title varchar(50),
  description text,
  gmt_create bigint,
  gmt_modified bigint,
  creator bigint,
  comment_count int DEFAULT 0,
  view_count int DEFAULT 0,
  like_count int DEFAULT 0,
  tag varchar(256)
);