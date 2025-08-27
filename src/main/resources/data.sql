INSERT INTO categories(name) VALUES('本');
INSERT INTO categories(name) VALUES('DVD');
INSERT INTO categories(name) VALUES('ゲーム');

INSERT INTO items(category_id, name, price) VALUES(1, 'insiders/ビジュアル博物館 発明', 2069);
INSERT INTO items(category_id, name, price) VALUES(1, 'C++ クラスと継承 完全制覇', 2728);
INSERT INTO items(category_id, name, price) VALUES(1, 'MOS Excel 365 対策テキスト＆問題集 (よくわかるマスター)', 2310);

INSERT INTO items(category_id, name, price) VALUES(2, 'ハリー・ポッター', 13200);
INSERT INTO items(category_id, name, price) VALUES(2, 'マトリックス', 12864);
INSERT INTO items(category_id, name, price) VALUES(2, 'スターウォーズ', 18150);

INSERT INTO items(category_id, name, price) VALUES(3, 'PERSONA 3 RELOAD', 9680);
INSERT INTO items(category_id, name, price) VALUES(3, 'BIOHAZARD RE:4', 4990);
INSERT INTO items(category_id, name, price) VALUES(3, 'ELDEN RING NIGHTREIGN', 5720);

INSERT INTO users (name, email, password) VALUES('本間', 'kiichi.homma@acrowavenet.com', 'himitu');