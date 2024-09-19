-- test 库中数据
INSERT INTO test.money
    (id, name, money, is_deleted, create_at, update_at)
VALUES (1, 'test', 1000, 0, '2024-09-14 13:34:40', '2024-09-14 13:34:40');

-- story 库中数据
INSERT INTO story.money
    (id, name, money, is_deleted, create_at, update_at)
VALUES (1, 'dataInStory1', 1000, 0, '2024-09-14 13:35:21', '2024-09-14 13:35:21'),
       (2, 'dataInStory2', 2000, 0, '2024-09-14 13:35:35', '2024-09-14 13:35:35');