# For hccis.ca version of the database
# DROP DATABASE IF EXISTS bjmac_squash_skills_w26;
# CREATE DATABASE bjmac_squash_skills_w26;
# use bjmac_squash_skills_w26;

#For localhost
DROP DATABASE IF EXISTS cis2232_squash_skills;
CREATE DATABASE cis2232_squash_skills;
use cis2232_squash_skills;

-- ------------------------------------------------------------------------------
-- Note the table below to hold data associated with your project.  Expect one
-- table with 7-9 fields.
-- ------------------------------------------------------------------------------

CREATE TABLE SkillsAssessmentSquashTechnical
(
    id                int(5),
    assessmentDate    varchar(10) NOT NULL COMMENT 'yyyy-MM-dd',
    createdDateTime   varchar(20) NOT NULL COMMENT 'yyyy-MM-dd hh:mm:ss',
    athleteName       varchar(50) NOT NULL COMMENT 'Athletes name',
    assessorName      varchar(50) NOT NULL COMMENT 'Athletes name',
    forehandDrives    int(5) COMMENT 'Number of forehand drives',
    backhandDrives    int(5) COMMENT 'Number of backhand drives',
    forehandVolleyMax int(5) COMMENT 'Max number of forehand volleys',
    forehandVolleySum int(5) COMMENT 'Sum of forehand volleys',
    backhandVolleyMax int(5) COMMENT 'Max number of backhand volleys',
    backhandVolleySum int(5) COMMENT 'Sum of backhand volleys',
    technicalScore    int(5) COMMENT 'Score calculated at submission'
) COMMENT 'This table holds technical skills assessment details';

ALTER TABLE SkillsAssessmentSquashTechnical
    ADD PRIMARY KEY (id);
ALTER TABLE SkillsAssessmentSquashTechnical
    MODIFY id int(4) NOT NULL AUTO_INCREMENT COMMENT 'This is the primary key',
    AUTO_INCREMENT = 1;

-- insert into SkillsAssessmentSquashTechnical values(0, '2022-08-22',	'2022-08-20 11:35:15','Maria Smith','BJ MacLean',		11,	5,	14,	78,	6,	59,0);
-- insert into SkillsAssessmentSquashTechnical values(0, '2022-08-11',	'2022-08-11 11:35:15','Rhonda Jones','BJ MacLean',		5,	7,	4,	36,	5,	38,0);
-- insert into SkillsAssessmentSquashTechnical values(0, '2022-08-07', '2022-08-07 11:35:15','Chad Collins','BJ MacLean',		8,	8,	4,	37,	5,	42,0);
-- insert into SkillsAssessmentSquashTechnical values(0, '2022-08-07', '2022-08-07 11:35:15','Rhonda Jones','BJ MacLean',		12,	8,	9,	53,	4,	42,0);
-- insert into SkillsAssessmentSquashTechnical values(0, '2022-08-07', '2022-08-05 11:35:15','Chad Collins','BJ MacLean',		8,	10,	7,	52,	3,	26,0);
-- insert into SkillsAssessmentSquashTechnical values(0, '2022-08-08',	'2022-08-08 11:35:15','Rhonda Jones','BJ MacLean',		10,	8,	8,	61,	6,	57,0);
-- insert into SkillsAssessmentSquashTechnical values(0, '2022-08-10',	'2022-08-10 11:35:15','Chad Collins','BJ MacLean',		17,	14,	8,	70,	13,	84,0);
-- insert into SkillsAssessmentSquashTechnical values(0, '2022-08-08',	'2022-08-08 11:35:15','Maria Smith','BJ MacLean',		17,	18,	12,	77,	8,	63,0);
-- insert into SkillsAssessmentSquashTechnical values(0, '2022-08-22',	'2022-08-20 11:35:15','Chad Collins','BJ MacLean',		14,	11,	10,	86,	16,	87,0);

INSERT INTO SkillsAssessmentSquashTechnical (id, assessmentDate, createdDateTime, athleteName, assessorName,
                                             forehandDrives, backhandDrives, forehandVolleyMax, forehandVolleySum,
                                             backhandVolleyMax, backhandVolleySum, technicalScore)
VALUES (1, '2022-08-22', '2023-11-07 01:38:48', 'Maria Smith', 'BJ MacLean', 11, 5, 14, 78, 6, 59, 1085),
       (2, '2022-08-11', '2023-11-07 01:38:52', 'Rhonda Jones', 'BJ MacLean', 5, 7, 4, 36, 5, 38, 622),
       (3, '2022-08-07', '2023-11-07 01:38:56', 'Chad Collins', 'BJ MacLean', 8, 8, 4, 37, 5, 42, 707),
       (4, '2022-08-07', '2023-11-07 01:38:59', 'Rhonda Jones', 'Xavier Fudge', 12, 8, 9, 53, 4, 42, 879),
       (5, '2022-08-07', '2023-11-07 01:39:01', 'Chad Collins', 'Brady Smith', 8, 10, 7, 52, 3, 26, 740),
       (6, '2022-08-08', '2023-11-07 01:39:04', 'Rhonda Jones', 'BJ MacLean', 10, 8, 8, 61, 6, 57, 972),
       (7, '2022-08-10', '2023-11-07 01:39:07', 'Chad Collins', 'BJ MacLean', 17, 14, 8, 70, 13, 84, 1403),
       (8, '2022-08-08', '2023-11-07 01:39:10', 'Maria Smith', 'BJ MacLean', 17, 18, 12, 77, 8, 63, 1385),
       (9, '2022-08-22', '2023-11-07 01:38:44', 'Chad Collins', 'BJ MacLean', 14, 11, 10, 86, 16, 87, 1448);



CREATE TABLE CodeType (codeTypeId int(3) COMMENT 'This is the primary key for code types',
                       englishDescription varchar(100) NOT NULL COMMENT 'English description',
                       frenchDescription varchar(100) DEFAULT NULL COMMENT 'French description',
                       createdDateTime datetime DEFAULT NULL,
                       createdUserId varchar(20) DEFAULT NULL,
                       updatedDateTime datetime DEFAULT NULL,
                       updatedUserId varchar(20) DEFAULT NULL
) COMMENT 'This tables holds the code types that are available for the application';

ALTER TABLE CodeType
    ADD PRIMARY KEY (CodeTypeId);

INSERT INTO CodeType (CodeTypeId, englishDescription, frenchDescription, createdDateTime, createdUserId, updatedDateTime, updatedUserId) VALUES
    (1, 'User Types', 'User Types FR', sysdate(), '', CURRENT_TIMESTAMP, '');
INSERT INTO CodeType (CodeTypeId, englishDescription, frenchDescription, createdDateTime, createdUserId, updatedDateTime, updatedUserId) VALUES
    (2, 'Squash Technical Types', 'Squash Technical Types FR', sysdate(), '', CURRENT_TIMESTAMP, '');



CREATE TABLE CodeValue (
                           codeTypeId int(3) NOT NULL COMMENT 'see code_type table',
                           codeValueSequence int(3) NOT NULL,
                           englishDescription varchar(100) NOT NULL COMMENT 'English description',
                           englishDescriptionShort varchar(20) NOT NULL COMMENT 'English abbreviation for description',
                           frenchDescription varchar(100) DEFAULT NULL COMMENT 'French description',
                           frenchDescriptionShort varchar(20) DEFAULT NULL COMMENT 'French abbreviation for description',
                           sortOrder int(3) DEFAULT NULL COMMENT 'Sort order if applicable',
                           createdDateTime datetime DEFAULT NULL,
                           createdUserId varchar(20) DEFAULT NULL,
                           updatedDateTime datetime DEFAULT NULL,
                           updatedUserId varchar(20) DEFAULT NULL
) COMMENT='This will hold code values for the application.';

ALTER TABLE CodeValue
    ADD PRIMARY KEY (CodeTypeId, codeValueSequence);

INSERT INTO CodeValue (codeTypeId, codeValueSequence, englishDescription, englishDescriptionShort, frenchDescription, frenchDescriptionShort, createdDateTime, createdUserId, updatedDateTime, updatedUserId) VALUES
    (1, 1, 'General', 'General', 'GeneralFR', 'GeneralFR', '2015-10-25 18:44:37', 'admin', '2015-10-25 18:44:37', 'admin');
INSERT INTO CodeValue (codeTypeId, codeValueSequence, englishDescription, englishDescriptionShort, frenchDescription, frenchDescriptionShort, createdDateTime, createdUserId, updatedDateTime, updatedUserId) VALUES
    (1, 2, 'Admin', 'Admin', 'Admin', 'Admin', '2015-10-25 18:44:37', 'admin', '2015-10-25 18:44:37', 'admin');
INSERT INTO CodeValue (codeTypeId, codeValueSequence, englishDescription, englishDescriptionShort, frenchDescription, frenchDescriptionShort, createdDateTime, createdUserId, updatedDateTime, updatedUserId) VALUES
    (2, 1, 'Forehand Drives', 'FH Drives', 'Forehand DrivesFR', 'FH DrivesFR', '2024-09-13 18:44:37', 'admin', '2024-09-13 18:44:37', 'admin');
INSERT INTO CodeValue (codeTypeId, codeValueSequence, englishDescription, englishDescriptionShort, frenchDescription, frenchDescriptionShort, createdDateTime, createdUserId, updatedDateTime, updatedUserId) VALUES
    (2, 2, 'Backhand Drives', 'BH Drives', 'Backhand DrivesFR', 'BH DrivesFR', '2024-09-13 18:44:37', 'admin', '2024-09-13 18:44:37', 'admin');


