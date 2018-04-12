-- 统计指定用户论文个数
DROP PROCEDURE IF EXISTS proc_award_stat; 

DELIMITER $$ 
CREATE PROCEDURE proc_award_stat(usercode_var varchar(150))
BEGIN
DECLARE count int(11) DEFAULT 0; 
IF TRIM(usercode_var) != '' THEN
		SELECT    COUNT(*) INTO  count FROM t_award where awardeecode1 = usercode_var or awardeecode2 = usercode_var  or awardeecode3 = usercode_var or awardeecode4 = usercode_var or awardeecode5 = usercode_var or awardeecode6 = usercode_var or awardeecode7 = usercode_var or awardeecode8 = usercode_var or awardeecode9 = usercode_var or awardeecode10 = usercode_var or awardeecodeother like concat('%',usercode_var,'%');
		UPDATE per.t_research_statistics SET award_all = count where usercode = usercode_var;
END IF;
END $$ 
DELIMITER ;

-- 统计其他作者论文个数
DROP PROCEDURE IF EXISTS proc_award_othercode; 

DELIMITER $$ 
CREATE PROCEDURE proc_award_othercode(usercode_var TEXT)
BEGIN
	DECLARE remainder TEXT;
	DECLARE delimiter VARCHAR(10) DEFAULT ',';
	DECLARE cur_position INT DEFAULT 1 ;
	DECLARE cur_string VARCHAR(1000);
	DECLARE delimiter_length TINYINT UNSIGNED;

	-- IF TRIM(usercode_var) != '' THEN
			SET remainder = usercode_var;
			SET delimiter_length = CHAR_LENGTH(delimiter);

      WHILE CHAR_LENGTH(remainder) > 0 AND cur_position > 0 DO
        SET cur_position = INSTR(remainder, delimiter);

				IF cur_position = 0 THEN
					SET cur_string = remainder;
				ELSE
					SET cur_string = LEFT(remainder, cur_position - 1);
        END IF;

				IF TRIM(cur_string) != '' THEN
						CALL proc_plan_stat(cur_string);
				END IF;

				SET remainder = SUBSTRING(remainder, cur_position + delimiter_length);
     END WHILE;
	-- END IF;
END $$ 
DELIMITER ;

DROP TRIGGER IF EXISTS tri_award_insert;

DELIMITER $$ 
CREATE TRIGGER tri_award_insert AFTER INSERT ON t_award FOR EACH ROW
BEGIN
			CALL proc_award_stat(NEW.awardeecode1);
			CALL proc_award_stat(NEW.awardeecode2);
			CALL proc_award_stat(NEW.awardeecode3);
			CALL proc_award_stat(NEW.awardeecode4);
			CALL proc_award_stat(NEW.awardeecode5);
			CALL proc_award_stat(NEW.awardeecode6);
			CALL proc_award_stat(NEW.awardeecode7);
			CALL proc_award_stat(NEW.awardeecode8);
			CALL proc_award_stat(NEW.awardeecode9);
			CALL proc_award_stat(NEW.awardeecode10);

			CALL proc_award_othercode(NEW.awardeecodeother);
END $$ 
DELIMITER ;

DROP TRIGGER  IF EXISTS tri_award_update;
DELIMITER $$ 
CREATE TRIGGER tri_award_update AFTER UPDATE ON t_award FOR EACH ROW
BEGIN
			IF OLD.awardeecode1 != NEW.awardeecode1 THEN
				CALL proc_award_stat(OLD.awardeecode1);
				CALL proc_award_stat(NEW.awardeecode1);
			END IF;

			IF OLD.awardeecode2 != NEW.awardeecode2 THEN
				CALL proc_award_stat(OLD.awardeecode2);
				CALL proc_award_stat(NEW.awardeecode2);
			END IF;

			IF OLD.awardeecode3 != NEW.awardeecode3 THEN
				CALL proc_award_stat(OLD.awardeecode3);
				CALL proc_award_stat(NEW.awardeecode3);
			END IF;

			IF OLD.awardeecode4 != NEW.awardeecode4 THEN
				CALL proc_award_stat(OLD.awardeecode4);
				CALL proc_award_stat(NEW.awardeecode4);
			END IF;

			IF OLD.awardeecode5 != NEW.awardeecode5 THEN
				 CALL proc_award_stat(OLD.awardeecode5);
				 CALL proc_award_stat(NEW.awardeecode5);
			END IF;

			IF OLD.awardeecode6 != NEW.awardeecode6 THEN
				CALL proc_award_stat(OLD.awardeecode6);
				CALL proc_award_stat(NEW.awardeecode6);
			END IF;

			IF OLD.awardeecode7 != NEW.awardeecode7 THEN
				CALL proc_award_stat(OLD.awardeecode7);
				CALL proc_award_stat(NEW.awardeecode7);
			END IF;

			IF OLD.awardeecode8 != NEW.awardeecode8 THEN
				CALL proc_award_stat(OLD.awardeecode8);
				CALL proc_award_stat(NEW.awardeecode8);
			END IF;

			IF OLD.awardeecode9 != NEW.awardeecode9 THEN
				CALL proc_award_stat(OLD.awardeecode9);
				CALL proc_award_stat(NEW.awardeecode9);
			END IF;

			IF OLD.awardeecode10 != NEW.awardeecode10 THEN
				 CALL proc_award_stat(OLD.awardeecode10);
				 CALL proc_award_stat(NEW.awardeecode10);
			END IF;

			IF OLD.awardeecodeother != NEW.awardeecodeother THEN
				CALL proc_award_othercode(OLD.awardeecodeother);
				CALL proc_award_othercode(NEW.awardeecodeother);
			END IF;
END $$ 
DELIMITER ;

DROP TRIGGER IF EXISTS tri_award_delete;

DELIMITER $$ 
CREATE TRIGGER tri_award_delete AFTER DELETE ON t_award FOR EACH ROW
BEGIN
			CALL proc_award_stat(OLD.awardeecode1);
			CALL proc_award_stat(OLD.awardeecode2);
			CALL proc_award_stat(OLD.awardeecode3);
			CALL proc_award_stat(OLD.awardeecode4);
			CALL proc_award_stat(OLD.awardeecode5);
			CALL proc_award_stat(OLD.awardeecode6);
			CALL proc_award_stat(OLD.awardeecode7);
			CALL proc_award_stat(OLD.awardeecode8);
			CALL proc_award_stat(OLD.awardeecode9);
			CALL proc_award_stat(OLD.awardeecode10);

			CALL proc_award_othercode(OLD.awardeecodeother);
END $$ 
DELIMITER ;