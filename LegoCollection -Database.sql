--
-- File generated with SQLiteStudio v3.4.17 on Wed Nov 5 16:59:00 2025
--
-- Text encoding used: System
--
PRAGMA foreign_keys = off;
BEGIN TRANSACTION;

-- Table: LegoSet
CREATE TABLE IF NOT EXISTS LegoSet (SetNumber INTEGER PRIMARY KEY AUTOINCREMENT, Name VARCHAR (50), Theme VARCHAR (50), Pieces INT, ReleaseDate DATE, Price DECIMAL (6, 2));
INSERT INTO LegoSet (SetNumber, Name, Theme, Pieces, ReleaseDate, Price) VALUES (1, 'The Colosseum', 'Creator Expert', 9036, '11/27/2020', 549.99);
INSERT INTO LegoSet (SetNumber, Name, Theme, Pieces, ReleaseDate, Price) VALUES (2, 'TypeWriter', 'Ideas', 2079, '7/1/2021', 199.99);
INSERT INTO LegoSet (SetNumber, Name, Theme, Pieces, ReleaseDate, Price) VALUES (3, 'The Globe', 'Ideas', 2585, '2/1/2022', 229.99);
INSERT INTO LegoSet (SetNumber, Name, Theme, Pieces, ReleaseDate, Price) VALUES (4, 'The Lighthouse', 'Ideas', 2065, '9/1/2022', 299.99);
INSERT INTO LegoSet (SetNumber, Name, Theme, Pieces, ReleaseDate, Price) VALUES (5, 'The White House', 'Architecture', 1483, '8/1/2020', 99.99);
INSERT INTO LegoSet (SetNumber, Name, Theme, Pieces, ReleaseDate, Price) VALUES (6, 'Capital Building', 'Architecture', 1032, '9/1/2016', 99.99);
INSERT INTO LegoSet (SetNumber, Name, Theme, Pieces, ReleaseDate, Price) VALUES (7, 'Aston Martin', 'Creator Expert', 1295, '8/1/2022', 149.99);
INSERT INTO LegoSet (SetNumber, Name, Theme, Pieces, ReleaseDate, Price) VALUES (8, 'London Bus', 'Creator Expert', 1686, '8/1/2017', 111.99);
INSERT INTO LegoSet (SetNumber, Name, Theme, Pieces, ReleaseDate, Price) VALUES (9, 'The Louvre', 'Architecture', 695, '7/1/2025', 59.99);
INSERT INTO LegoSet (SetNumber, Name, Theme, Pieces, ReleaseDate, Price) VALUES (10, 'Arc De Triomphe', 'Architecture', 386, '8/1/2017', 39.99);
INSERT INTO LegoSet (SetNumber, Name, Theme, Pieces, ReleaseDate, Price) VALUES (11, 'Buckingham Palace', 'Architecture', 780, '9/1/2016', 49.99);
INSERT INTO LegoSet (SetNumber, Name, Theme, Pieces, ReleaseDate, Price) VALUES (12, 'London Skyline', 'Architecture', 468, '1/1/2017', 39.99);
INSERT INTO LegoSet (SetNumber, Name, Theme, Pieces, ReleaseDate, Price) VALUES (13, 'Great Wall of China', 'Architecture', 5551, '8/1/2017', 49.99);
INSERT INTO LegoSet (SetNumber, Name, Theme, Pieces, ReleaseDate, Price) VALUES (14, 'Trafalgar Square', 'Architecture', 1197, '4/27/2019', 79.99);
INSERT INTO LegoSet (SetNumber, Name, Theme, Pieces, ReleaseDate, Price) VALUES (15, 'Steamboat Willie', 'Ideas', 751, '4/1/2019', 89.99);
INSERT INTO LegoSet (SetNumber, Name, Theme, Pieces, ReleaseDate, Price) VALUES (16, 'Chicago Skyline', 'Architecture', 444, '1/1/2017', 39.99);
INSERT INTO LegoSet (SetNumber, Name, Theme, Pieces, ReleaseDate, Price) VALUES (17, 'San Francisco Skyline', 'Architecture', 565, '1/1/2019', 49.99);
INSERT INTO LegoSet (SetNumber, Name, Theme, Pieces, ReleaseDate, Price) VALUES (18, 'Statue of Liberty', 'Architecture', 1685, '6/1/2018', 119.99);
INSERT INTO LegoSet (SetNumber, Name, Theme, Pieces, ReleaseDate, Price) VALUES (19, 'New York Skyline', 'Architecture', 598, '1/1/2016', 59.99);
INSERT INTO LegoSet (SetNumber, Name, Theme, Pieces, ReleaseDate, Price) VALUES (20, 'Tree House', 'Ideas', 3036, '8/1/2019', 249.99);
INSERT INTO LegoSet (SetNumber, Name, Theme, Pieces, ReleaseDate, Price) VALUES (21, 'Leaning Tower of Pisa', 'Architecture', 345, '6/1/2013', 34.99);

COMMIT TRANSACTION;
PRAGMA foreign_keys = on;
