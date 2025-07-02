use javaee;
CREATE TABLE IF NOT EXISTS `user`
(
    uid          INT PRIMARY KEY,
    phoneNumber  VARCHAR(32)  NOT NULL,
    userName     VARCHAR(64)  NOT NULL,
    userPassword VARCHAR(128) NOT NULL,
    sex          VARCHAR(16)  NOT NULL DEFAULT 'unknown',
    birthday     DATE         NOT NULL DEFAULT '2024-08-31',
    permission   INT          NOT NULL DEFAULT 0
);

CREATE TABLE IF NOT EXISTS articles
(
    id          INT PRIMARY KEY,
    title       VARCHAR(255) NOT NULL,
    description TEXT         NOT NULL,
    image       VARCHAR(255) NOT NULL,
    content     TEXT         NOT NULL
);

CREATE TABLE IF NOT EXISTS blog
(
    blogId     INT PRIMARY KEY,
    content    TEXT         NOT NULL,
    createTime DATETIME     NOT NULL,
    updateTime DATETIME     NOT NULL,
    uid        INT          NOT NULL,
    title      VARCHAR(255) NOT NULL,
    FOREIGN KEY (uid) REFERENCES user (uid)
);

CREATE TABLE IF NOT EXISTS bloodPressureLog
(
    bplId          INT PRIMARY KEY,
    uid            INT         NOT NULL,
    SBP            DOUBLE      NOT NULL,
    DBP            DOUBLE      NOT NULL,
    recordTime     DATETIME    NOT NULL,
    riskLevel      VARCHAR(32) NOT NULL,
    classification VARCHAR(32) NOT NULL,
    FOREIGN KEY (uid) REFERENCES user (uid)
);

CREATE TABLE IF NOT EXISTS drug
(
    drugId    INT PRIMARY KEY,
    uid       INT          NOT NULL,
    drugName  VARCHAR(128) NOT NULL,
    frequency VARCHAR(32)  NOT NULL,
    unit      VARCHAR(32)  NOT NULL,
    dosage    DOUBLE       NOT NULL,
    isActive  TINYINT(1)   NOT NULL,
    FOREIGN KEY (uid) REFERENCES user (uid)
);

CREATE TABLE IF NOT EXISTS drugAlert
(
    alertId   INT PRIMARY KEY,
    uid       INT      NOT NULL,
    drugId    INT      NOT NULL,
    alertTime DATETIME NOT NULL,
    eatTime   DATETIME NOT NULL,
    FOREIGN KEY (uid) REFERENCES user (uid),
    FOREIGN KEY (drugId) REFERENCES drug (drugId)
);

CREATE TABLE IF NOT EXISTS heartRate
(
    hrlId      INT PRIMARY KEY,
    uid        INT      NOT NULL,
    heartRate  DOUBLE   NOT NULL,
    recordTime DATETIME NOT NULL,
    FOREIGN KEY (uid) REFERENCES user (uid)
);

CREATE TABLE IF NOT EXISTS userIntro
(
    uid   INT PRIMARY KEY,
    intro TEXT NOT NULL,
    FOREIGN KEY (uid) REFERENCES user (uid)
);