INSERT IGNORE INTO users(id, email, name, password, reg_date, mod_date )
VALUES  (1,  'test1@email.com', 'Yael Harris1', '$2a$10$RLIdAhk2hytLfwVOshi3Te716UadfBrJIRKgCpMa/GnCIYN0t.V4q',NOW(), NOW()),
        (2,  'test2@email.com', 'Yael Harris2', '$2a$10$RLIdAhk2hytLfwVOshi3Te716UadfBrJIRKgCpMa/GnCIYN0t.V4q',NOW(), NOW()),
        (3,  'test3@email.com', 'Yael Harris3', '$2a$10$RLIdAhk2hytLfwVOshi3Te716UadfBrJIRKgCpMa/GnCIYN0t.V4q',NOW(), NOW()),
        (4,  'test4@email.com', 'Yael Harris4', '$2a$10$RLIdAhk2hytLfwVOshi3Te716UadfBrJIRKgCpMa/GnCIYN0t.V4q',NOW(), NOW()),
        (5,  'test5@email.com', 'Yael Harris5', '$2a$10$RLIdAhk2hytLfwVOshi3Te716UadfBrJIRKgCpMa/GnCIYN0t.V4q',NOW(), NOW()),
        (5,  'test6@email.com', 'Yael Harris6', '$2a$10$RLIdAhk2hytLfwVOshi3Te716UadfBrJIRKgCpMa/GnCIYN0t.V4q',NOW(), NOW()),
        (7,  'test7@email.com', 'Yael Harris7', '$2a$10$RLIdAhk2hytLfwVOshi3Te716UadfBrJIRKgCpMa/GnCIYN0t.V4q',NOW(), NOW()),
        (8,  'test8@email.com', 'Yael Harris8', '$2a$10$RLIdAhk2hytLfwVOshi3Te716UadfBrJIRKgCpMa/GnCIYN0t.V4q',NOW(), NOW()),
        (9,  'test9@email.com', 'Yael Harris9', '$2a$10$RLIdAhk2hytLfwVOshi3Te716UadfBrJIRKgCpMa/GnCIYN0t.V4q',NOW(), NOW()),
        (10, 'test10@email.com', 'Yael Harris10', '$2a$10$RLIdAhk2hytLfwVOshi3Te716UadfBrJIRKgCpMa/GnCIYN0t.V4q', NOW(), NOW()),
        (11, 'test11@email.com', 'Yael Harris11', '$2a$10$RLIdAhk2hytLfwVOshi3Te716UadfBrJIRKgCpMa/GnCIYN0t.V4q', NOW(), NOW()),
        (12, 'test12@email.com', 'Yael Harris12', '$2a$10$RLIdAhk2hytLfwVOshi3Te716UadfBrJIRKgCpMa/GnCIYN0t.V4q', NOW(), NOW()),
        (13, 'test13@email.com', 'Yael Harris13', '$2a$10$RLIdAhk2hytLfwVOshi3Te716UadfBrJIRKgCpMa/GnCIYN0t.V4q', NOW(), NOW()),
        (14, 'test14@email.com', 'Yael Harris14', '$2a$10$RLIdAhk2hytLfwVOshi3Te716UadfBrJIRKgCpMa/GnCIYN0t.V4q', NOW(), NOW()),
        (15, 'test15@email.com', 'Yael Harris15', '$2a$10$RLIdAhk2hytLfwVOshi3Te716UadfBrJIRKgCpMa/GnCIYN0t.V4q', NOW(), NOW()),
        (16, 'test16@email.com', 'Yael Harris16', '$2a$10$RLIdAhk2hytLfwVOshi3Te716UadfBrJIRKgCpMa/GnCIYN0t.V4q', NOW(), NOW()),
        (17, 'test17@email.com', 'Yael Harris17', '$2a$10$RLIdAhk2hytLfwVOshi3Te716UadfBrJIRKgCpMa/GnCIYN0t.V4q', NOW(), NOW()),
        (18, 'test18@email.com', 'Yael Harris18', '$2a$10$RLIdAhk2hytLfwVOshi3Te716UadfBrJIRKgCpMa/GnCIYN0t.V4q', NOW(), NOW()),
        (19, 'test19@email.com', 'Yael Harris19', '$2a$10$RLIdAhk2hytLfwVOshi3Te716UadfBrJIRKgCpMa/GnCIYN0t.V4q', NOW(), NOW()),
        (20, 'test20@email.com', 'Yael Harris20', '$2a$10$RLIdAhk2hytLfwVOshi3Te716UadfBrJIRKgCpMa/GnCIYN0t.V4q', NOW(), NOW()),
        (21, 'test21@email.com', 'Yael Harris21', '$2a$10$RLIdAhk2hytLfwVOshi3Te716UadfBrJIRKgCpMa/GnCIYN0t.V4q', NOW(), NOW()),
        (22, 'test22@email.com', 'Yael Harris22', '$2a$10$RLIdAhk2hytLfwVOshi3Te716UadfBrJIRKgCpMa/GnCIYN0t.V4q', NOW(), NOW());

INSERT IGNORE INTO broadcast(id,user_id, name, reg_date, mod_date )
VALUES  (1, 1, 'DJ_1', NOW(), NOW()),
        (2, 2, 'DJ_2', NOW(), NOW()),
        (3, 3, 'DJ_3', NOW(), NOW()),
        (4, 4, 'DJ_4', NOW(), NOW()),
        (5, 5, 'DJ_5', NOW(), NOW()),
        (6, 6, 'DJ_6', NOW(), NOW()),
        (7, 7, 'DJ_7', NOW(), NOW()),
        (8, 8, 'DJ_8', NOW(), NOW()),
        (9, 9, 'DJ_9', NOW(), NOW()),
        (10, 10, 'DJ_10', NOW(), NOW());

INSERT IGNORE INTO follow (broadcast_id, users_id,broadcast_status, user_status, mod_date, reg_date)
VALUES  (1, 12, 0, 0, NOW(), NOW()),
        (2, 12, 1, 0, NOW(), NOW()),
        (3, 12, 0, 1, NOW(), NOW()),
        (4, 12, 1, 1, NOW(), NOW());


