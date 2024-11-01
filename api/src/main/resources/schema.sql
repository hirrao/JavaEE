CREATE TABLE IF NOT EXISTS user
(
    uid          INTEGER PRIMARY KEY NOT NULL,
    phoneNumber  TEXT                NOT NULL,
    userName     TEXT                NOT NULL,
    userPassword TEXT                NOT NULL,
    sex          TEXT                NOT NULL DEFAULT 'unknown',
    birthday     TEXT                NOT NULL DEFAULT '2024-08-31',
    permission   INTEGER             NOT NULL DEFAULT 0
);

CREATE TABLE IF NOT EXISTS articles
(
    id          INTEGER PRIMARY KEY NOT NULL,
    title       TEXT                NOT NULL,
    description TEXT                NOT NULL,
    image       TEXT                NOT NULL,
    content     TEXT                NOT NULL
);

CREATE TABLE IF NOT EXISTS blog
(
    blogId     INTEGER PRIMARY KEY NOT NULL,
    content    TEXT                NOT NULL,
    createTime TEXT                NOT NULL,
    updateTime TEXT                NOT NULL,
    uid        INTEGER             NOT NULL,
    title      TEXT                NOT NULL,
    FOREIGN KEY (uid) REFERENCES user (uid)
);

CREATE TABLE IF NOT EXISTS bloodPressureLog
(
    bplId          INTEGER PRIMARY KEY NOT NULL,
    uid            INTEGER             NOT NULL,
    SBP            REAL                NOT NULL,
    DBP            REAL                NOT NULL,
    recordTime     TEXT                NOT NULL,
    riskLevel      TEXT                NOT NULL,
    classification TEXT                NOT NULL,
    FOREIGN KEY (uid) REFERENCES user (uid)
);

CREATE TABLE IF NOT EXISTS drug
(
    drugId    INTEGER PRIMARY KEY NOT NULL,
    uid       INTEGER             NOT NULL,
    drugName  TEXT                NOT NULL,
    frequency TEXT                NOT NULL,
    unit      TEXT                NOT NULL,
    dosage    REAL                NOT NULL,
    isActive  INTEGER             NOT NULL,
    FOREIGN KEY (uid) REFERENCES user (uid)
);

CREATE TABLE IF NOT EXISTS drugAlert
(
    alertId   INTEGER PRIMARY KEY NOT NULL,
    uid       INTEGER             NOT NULL,
    drugId    INTEGER             NOT NULL,
    alertTime TEXT                NOT NULL,
    eatTime   TEXT                NOT NULL,
    FOREIGN KEY (uid) REFERENCES user (uid),
    FOREIGN KEY (drugId) REFERENCES drug (drugId)
);

CREATE TABLE IF NOT EXISTS heartRate
(
    hrlId      INTEGER PRIMARY KEY NOT NULL,
    uid        INTEGER             NOT NULL,
    heartRate  REAL                NOT NULL,
    recordTime TEXT                NOT NULL,
    FOREIGN KEY (uid) REFERENCES user (uid)
);

CREATE TABLE IF NOT EXISTS userIntro
(
    uid   INTEGER PRIMARY KEY NOT NULL,
    intro TEXT                NOT NULL
);