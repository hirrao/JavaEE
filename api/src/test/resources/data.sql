-- 正常测试用户1
INSERT INTO user (id, uid, email, user_name, salt_password, salt, sex, birthday, permission)
values (1, 2, 'test@test.com', 'test01', 'test0', 'test1', 0, '2004-01-02', 1);
--
/*
INSERT INTO user (id, uid, email, user_name, salt_password, salt, sex, birthday, permission)
values (1, 1, 'test@test.com', 'test', 'test', 'test', 0, '2004-01-02', 1);
--
INSERT INTO user (id, uid, email, user_name, salt_password, salt, sex, birthday, permission)
values (1, 1, 'test@test.com', 'test', 'test', 'test', 0, '2004-01-02', 1);
--
INSERT INTO user (id, uid, email, user_name, salt_password, salt, sex, birthday, permission)
values (1, 1, 'test@test.com', 'test', 'test', 'test', 0, '2004-01-02', 1);
*/