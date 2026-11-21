DROP DATABASE IF EXISTS cis2232_tennis_tracker;
CREATE DATABASE cis2232_tennis_tracker;
USE cis2232_tennis_tracker;

CREATE TABLE tennis_match (
    id INT NOT NULL AUTO_INCREMENT,

    player1FirstName VARCHAR(100) NOT NULL,
    player1LastName VARCHAR(100) NOT NULL,
    player1DateOfBirth VARCHAR(10) NOT NULL,

    player2FirstName VARCHAR(100) NOT NULL,
    player2LastName VARCHAR(100) NOT NULL,
    player2DateOfBirth VARCHAR(10) NOT NULL,

    matchDate VARCHAR(10) NOT NULL,
    createdDateTime VARCHAR(100) NOT NULL,
    winnerName VARCHAR(201) NOT NULL,

    tournamentName VARCHAR(150) NOT NULL,
    courtSurface VARCHAR(20) NOT NULL,

    setsPlayed INT NOT NULL,
    player1SetsWon INT NOT NULL,
    player2SetsWon INT NOT NULL,

    player1Aces INT NOT NULL,
    player2Aces INT NOT NULL,

    player1DoubleFaults INT NOT NULL,
    player2DoubleFaults INT NOT NULL,

    player1FirstServe INT NOT NULL,
    player2FirstServe INT NOT NULL,

    player1BreakPointsWon INT NOT NULL,
    player2BreakPointsWon INT NOT NULL,

    player1TotalPointsWon INT NOT NULL,
    player2TotalPointsWon INT NOT NULL,

    PRIMARY KEY (id)
);

INSERT INTO tennis_match (
    player1FirstName, player1LastName, player1DateOfBirth,
    player2FirstName, player2LastName, player2DateOfBirth,
    matchDate, createdDateTime, winnerName,
    tournamentName, courtSurface,
    setsPlayed, player1SetsWon, player2SetsWon,
    player1Aces, player2Aces,
    player1DoubleFaults, player2DoubleFaults,
    player1FirstServe, player2FirstServe,
    player1BreakPointsWon, player2BreakPointsWon,
    player1TotalPointsWon, player2TotalPointsWon
)
VALUES
('Alice', 'Smith', '2001-04-12',
 'Bob', 'Jones', '2000-08-03',
 '2026-09-01', '2026-09-01 19:45:12', 'Alice Smith',
 'Fall Open', 'Hard',
 3, 3, 0, 4, 2, 1, 3, 68, 55, 5, 3, 42, 35),

('Bob', 'Jones', '2000-08-03',
 'Carlos', 'Garcia', '2002-03-21',
 '2026-09-03', '2026-09-03 20:10:05', 'Bob Jones',
 'Fall Open', 'Clay',
 4, 3, 1, 5, 3, 2, 4, 72, 61, 6, 4, 58, 49),

('Carlos', 'Garcia', '2002-03-21',
 'Dana', 'White', '2001-11-17',
 '2026-09-05', '2026-09-05 18:30:40', 'Carlos Garcia',
 'Fall Open', 'Grass',
 5, 3, 2, 7, 4, 3, 2, 75, 66, 8, 5, 71, 65),

('Dana', 'White', '2001-11-17',
 'Alice', 'Smith', '2001-04-12',
 '2026-09-08', '2026-09-08 19:02:33', 'Dana White',
 'Fall Open', 'Hard',
 4, 3, 1, 6, 3, 2, 3, 70, 64, 7, 4, 63, 52),

('Alice', 'Smith', '2001-04-12',
 'Carlos', 'Garcia', '2002-03-21',
 '2026-09-10', '2026-09-10 21:15:00', 'Carlos Garcia',
 'Fall Open', 'Clay',
 4, 1, 3, 3, 8, 4, 2, 59, 78, 3, 9, 48, 66),

('Bob', 'Jones', '2000-08-03',
 'Dana', 'White', '2001-11-17',
 '2026-09-12', '2026-09-12 17:50:21', 'Bob Jones',
 'Fall Open', 'Grass',
 3, 3, 0, 9, 2, 1, 3, 81, 58, 7, 2, 55, 31),

('Carlos', 'Garcia', '2002-03-21',
 'Bob', 'Jones', '2000-08-03',
 '2026-09-15', '2026-09-15 20:40:18', 'Bob Jones',
 'Fall Open', 'Hard',
 5, 2, 3, 4, 7, 3, 2, 65, 73, 5, 8, 68, 72),

('Alice', 'Smith', '2001-04-12',
 'Dana', 'White', '2001-11-17',
 '2026-09-17', '2026-09-17 19:25:47', 'Dana White',
 'Fall Open', 'Clay',
 4, 1, 3, 2, 6, 3, 1, 62, 76, 4, 7, 46, 61);
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

