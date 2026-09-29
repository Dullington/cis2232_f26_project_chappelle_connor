# For hccis.ca version of the database
# DROP DATABASE IF EXISTS bjmac_squash_skills_w26;
# CREATE DATABASE bjmac_squash_skills_w26;
# use bjmac_squash_skills_w26;

#For localhost
DROP DATABASE IF EXISTS cis2232_squash_scorer;
CREATE DATABASE cis2232_squash_scorer;
use cis2232_squash_scorer;

-- ------------------------------------------------------------------------------
-- Note the table below to hold data associated with your project.  Expect one
-- table with 7-9 fields.
-- ------------------------------------------------------------------------------

CREATE TABLE squash_match (
                              id                  INT             NOT NULL AUTO_INCREMENT,
                              matchDate           VARCHAR(10)    NOT NULL,
                              createdDateTime     VARCHAR(100)   NOT NULL,
                              player1Name         VARCHAR(100)    NOT NULL,
                              player2Name         VARCHAR(100)    NOT NULL,
                              player1Game1Score   TINYINT UNSIGNED NOT NULL DEFAULT 0,
                              player2Game1Score   TINYINT UNSIGNED NOT NULL DEFAULT 0,
                              player1Game2Score   TINYINT UNSIGNED NOT NULL DEFAULT 0,
                              player2Game2Score   TINYINT UNSIGNED NOT NULL DEFAULT 0,
                              player1Game3Score   TINYINT UNSIGNED NOT NULL DEFAULT 0,
                              player2Game3Score   TINYINT UNSIGNED NOT NULL DEFAULT 0,
                              player1Game4Score   TINYINT UNSIGNED NOT NULL DEFAULT 0,
                              player2Game4Score   TINYINT UNSIGNED NOT NULL DEFAULT 0,
                              player1Game5Score   TINYINT UNSIGNED NOT NULL DEFAULT 0,
                              player2Game5Score   TINYINT UNSIGNED NOT NULL DEFAULT 0,
                              winnerName          VARCHAR(100)    NOT NULL,
                              PRIMARY KEY (id)
);

INSERT INTO squash_match
(matchDate, createdDateTime, player1Name, player2Name,
 player1Game1Score, player2Game1Score,
 player1Game2Score, player2Game2Score,
 player1Game3Score, player2Game3Score,
 player1Game4Score, player2Game4Score,
 player1Game5Score, player2Game5Score,
 winnerName)
VALUES
-- 3-0 win
('2026-09-01', '2026-09-01 19:45:12', 'Alice',  'Bob',    11, 5,  11, 7,  11, 9,  0, 0,   0, 0,   'Alice'),
-- 3-1 win
('2026-09-03', '2026-09-03 20:10:05', 'Bob',    'Carlos', 11, 8,  9, 11,  11, 6,  11, 4,  0, 0,   'Bob'),
-- 3-2 win (deciding fifth game)
('2026-09-05', '2026-09-05 18:30:40', 'Carlos', 'Dana',   11, 9,  8, 11,  11, 7,  6, 11,  11, 8, 'Carlos'),
-- 3-1 win with extended games (win by two)
('2026-09-08', '2026-09-08 19:02:33', 'Dana',   'Alice',  12, 10, 11, 13, 11, 9,  14, 12, 0, 0,  'Dana'),
-- 3-1 win for player 2
('2026-09-10', '2026-09-10 21:15:00', 'Alice',  'Carlos', 5, 11,  11, 8,  11, 13, 9, 11,  0, 0,  'Carlos'),
-- 3-0 shutout-style win
('2026-09-12', '2026-09-12 17:50:21', 'Bob',    'Dana',   11, 3,  11, 6,  11, 8,  0, 0,   0, 0,   'Bob'),
-- 3-2 with extended fifth game
('2026-09-15', '2026-09-15 20:40:18', 'Carlos', 'Bob',    9, 11,  11, 7,  8, 11,  11, 9,  11, 13, 'Bob'),
-- 3-1 win for player 2
('2026-09-17', '2026-09-17 19:25:47', 'Alice',  'Dana',   4, 11,  6, 11,  11, 9,  3, 11,  0, 0,  'Dana');

# ALTER TABLE SkillsAssessmentSquashTechnical
#     ADD PRIMARY KEY (id);
# ALTER TABLE SkillsAssessmentSquashTechnical
#     MODIFY id int(4) NOT NULL AUTO_INCREMENT COMMENT 'This is the primary key',
#     AUTO_INCREMENT = 1;


# CREATE TABLE CodeType (codeTypeId int(3) COMMENT 'This is the primary key for code types',
#                        englishDescription varchar(100) NOT NULL COMMENT 'English description',
#                        frenchDescription varchar(100) DEFAULT NULL COMMENT 'French description',
#                        createdDateTime datetime DEFAULT NULL,
#                        createdUserId varchar(20) DEFAULT NULL,
#                        updatedDateTime datetime DEFAULT NULL,
#                        updatedUserId varchar(20) DEFAULT NULL
# ) COMMENT 'This tables holds the code types that are available for the application';
#
# ALTER TABLE CodeType
#     ADD PRIMARY KEY (CodeTypeId);
#
# INSERT INTO CodeType (CodeTypeId, englishDescription, frenchDescription, createdDateTime, createdUserId, updatedDateTime, updatedUserId) VALUES
#     (1, 'User Types', 'User Types FR', sysdate(), '', CURRENT_TIMESTAMP, '');
# INSERT INTO CodeType (CodeTypeId, englishDescription, frenchDescription, createdDateTime, createdUserId, updatedDateTime, updatedUserId) VALUES
#     (2, 'Squash Technical Types', 'Squash Technical Types FR', sysdate(), '', CURRENT_TIMESTAMP, '');
#
#
#
# CREATE TABLE CodeValue (
#                            codeTypeId int(3) NOT NULL COMMENT 'see code_type table',
#                            codeValueSequence int(3) NOT NULL,
#                            englishDescription varchar(100) NOT NULL COMMENT 'English description',
#                            englishDescriptionShort varchar(20) NOT NULL COMMENT 'English abbreviation for description',
#                            frenchDescription varchar(100) DEFAULT NULL COMMENT 'French description',
#                            frenchDescriptionShort varchar(20) DEFAULT NULL COMMENT 'French abbreviation for description',
#                            sortOrder int(3) DEFAULT NULL COMMENT 'Sort order if applicable',
#                            createdDateTime datetime DEFAULT NULL,
#                            createdUserId varchar(20) DEFAULT NULL,
#                            updatedDateTime datetime DEFAULT NULL,
#                            updatedUserId varchar(20) DEFAULT NULL
# ) COMMENT='This will hold code values for the application.';
#
# ALTER TABLE CodeValue
#     ADD PRIMARY KEY (CodeTypeId, codeValueSequence);
#
# INSERT INTO CodeValue (codeTypeId, codeValueSequence, englishDescription, englishDescriptionShort, frenchDescription, frenchDescriptionShort, createdDateTime, createdUserId, updatedDateTime, updatedUserId) VALUES
#     (1, 1, 'General', 'General', 'GeneralFR', 'GeneralFR', '2015-10-25 18:44:37', 'admin', '2015-10-25 18:44:37', 'admin');
# INSERT INTO CodeValue (codeTypeId, codeValueSequence, englishDescription, englishDescriptionShort, frenchDescription, frenchDescriptionShort, createdDateTime, createdUserId, updatedDateTime, updatedUserId) VALUES
#     (1, 2, 'Admin', 'Admin', 'Admin', 'Admin', '2015-10-25 18:44:37', 'admin', '2015-10-25 18:44:37', 'admin');
# INSERT INTO CodeValue (codeTypeId, codeValueSequence, englishDescription, englishDescriptionShort, frenchDescription, frenchDescriptionShort, createdDateTime, createdUserId, updatedDateTime, updatedUserId) VALUES
#     (2, 1, 'Forehand Drives', 'FH Drives', 'Forehand DrivesFR', 'FH DrivesFR', '2024-09-13 18:44:37', 'admin', '2024-09-13 18:44:37', 'admin');
# INSERT INTO CodeValue (codeTypeId, codeValueSequence, englishDescription, englishDescriptionShort, frenchDescription, frenchDescriptionShort, createdDateTime, createdUserId, updatedDateTime, updatedUserId) VALUES
#     (2, 2, 'Backhand Drives', 'BH Drives', 'Backhand DrivesFR', 'BH DrivesFR', '2024-09-13 18:44:37', 'admin', '2024-09-13 18:44:37', 'admin');
#

