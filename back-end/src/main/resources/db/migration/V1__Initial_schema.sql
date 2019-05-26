CREATE TABLE IF NOT EXISTS user (
      id INT NOT NULL AUTO_INCREMENT,
      username VARCHAR(32),
      password VARCHAR(512),

      CONSTRAINT pk_t_user PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS tag (
      id INT NOT NULL AUTO_INCREMENT,
      name VARCHAR(32),

      CONSTRAINT pk_t_tag PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS question (
       id INT NOT NULL AUTO_INCREMENT,
       author INT,
       title VARCHAR(32),
       text VARCHAR(128),
       creation_date VARCHAR(32),

       CONSTRAINT pk_t_question PRIMARY KEY (id),
       CONSTRAINT fk_t_question FOREIGN KEY (author) REFERENCES user(id)
);

CREATE TABLE IF NOT EXISTS question_tag (
        id INT NOT NULL AUTO_INCREMENT,
        question_id INT,
        tag_id INT,

        CONSTRAINT pk_t_question_tag PRIMARY KEY (id),
        CONSTRAINT fk_t_question_tag FOREIGN KEY (question_id) REFERENCES question(id),
        CONSTRAINT fk_t_question_tag2 FOREIGN KEY (tag_id) REFERENCES tag(id)
);

CREATE TABLE IF NOT EXISTS answer (
        id INT NOT NULL AUTO_INCREMENT,
        question_id INT,
        author_id INT,
        text VARCHAR(128),
        creation_date VARCHAR(32),

        CONSTRAINT pk_t_answer PRIMARY KEY (id),
        CONSTRAINT fk_t_answer FOREIGN KEY (question_id) REFERENCES question(id),
        CONSTRAINT fk_t_answer2 FOREIGN KEY (author_id) REFERENCES user(id)
);
-- uh8uh8 --



