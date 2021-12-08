CREATE
DATABASE IF NOT EXISTS test;

CREATE TABLE IF NOT EXISTS test.business_object_1
(
    id
    int
    AUTO_INCREMENT
    PRIMARY
    KEY,
    title
    VARCHAR
(
    100
) NOT NULL,
    createdAt TIMESTAMP NOT NULL DEFAULT current_timestamp
(
),
    changedAt TIMESTAMP NOT NULL DEFAULT current_timestamp
(
) ON UPDATE current_timestamp
(
)
    ) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS test.business_object_2
(
    id
    int
    AUTO_INCREMENT
    PRIMARY
    KEY,
    nameKey
    VARCHAR
(
    100
) NOT NULL,
    description VARCHAR
(
    100
) DEFAULT NULL,
    createdAt TIMESTAMP NOT NULL DEFAULT current_timestamp
(
),
    changedAt TIMESTAMP NOT NULL DEFAULT current_timestamp
(
) ON UPDATE current_timestamp
(
)
    ) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS test.business_object_1_x_business_object_2
(
    id
    int
    AUTO_INCREMENT
    PRIMARY
    KEY,
    o1_id
    INT
    NOT
    NULL,
    o2_id
    INT
    NOT
    NULL,
    createdAt
    TIMESTAMP
    NOT
    NULL
    DEFAULT
    current_timestamp
(
),
    changedAt TIMESTAMP NOT NULL DEFAULT current_timestamp
(
) ON UPDATE current_timestamp
(
),
    CONSTRAINT `fk_o1`
    FOREIGN KEY
(
    o1_id
) REFERENCES business_object_1
(
    id
)
  ON UPDATE RESTRICT,
    CONSTRAINT `fk_o2`
    FOREIGN KEY
(
    o2_id
) REFERENCES business_object_2
(
    id
)
  ON UPDATE RESTRICT
    ) ENGINE = InnoDB;