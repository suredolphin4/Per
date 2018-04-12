-- 科研成果数量统计

DROP PROCEDURE IF EXISTS Proc_Research_Statistics; 

DELIMITER $$ 
CREATE PROCEDURE Proc_Research_Statistics() 
BEGIN
DECLARE count int(11) DEFAULT 0; 
DECLARE usercode_var VARCHAR(150);
DECLARE stopFlag int(11);

-- 定义游标
DECLARE cursor1 cursor for 
SELECT usercode FROM per.t_user WHERE department IS NOT NULL;

-- 定义结束标记
DECLARE CONTINUE HANDLER FOR NOT FOUND set stopFlag=1;  
SET stopFlag=0; 
-- 打开游标
open cursor1;

-- 清空已有数据
DELETE FROM t_research_statistics;

-- 插入用户记录
INSERT INTO t_research_statistics(usercode, NAME, domain, report_level) SELECT t.usercode, t.name, t.domain s.personcate from t_user t, t_usercate s where t.usercode = s.usercode AND t.department IS NOT NULL; 

REPEAT
    FETCH cursor1 INTO usercode_var;

		--
    SELECT    COUNT(*) INTO  count FROM per.t_lw where commauthorcode = usercode_var or firstauthorcode = usercode_var;
    UPDATE per.t_research_statistics SET t_comm_or_firstauthor = count where usercode = usercode_var;

    SELECT    COUNT(*) INTO  count FROM per.t_lw where commauthorcode = usercode_var or firstauthorcode = usercode_var or secauthorcode = usercode_var or threeauthorcode = usercode_var or fourauthorcode = usercode_var or fiveauthorcode = usercode_var or otherauthorcode like concat('%',usercode_var,'%');
		UPDATE per.t_research_statistics SET t_all = count where usercode = usercode_var;
    
		SELECT    COUNT(*) INTO  count FROM per.t_lw where (commauthorcode = usercode_var or firstauthorcode = usercode_var) AND (pubkind = 'SCI收录' OR pubkind = 'SSCI收录') AND status = '正式发表';
    UPDATE per.t_research_statistics SET t_sci_comm_or_firstauthor = count where usercode = usercode_var;

		SELECT    COUNT(*) INTO  count FROM per.t_lw where (commauthorcode = usercode_var or firstauthorcode = usercode_var or secauthorcode = usercode_var or threeauthorcode = usercode_var or fourauthorcode = usercode_var or fiveauthorcode = usercode_var or otherauthorcode like concat('%',usercode_var,'%')) AND (pubkind = 'SCI收录' OR pubkind = 'SSCI收录') AND status = '正式发表';
		UPDATE per.t_research_statistics SET t_sci_all = count where usercode = usercode_var;

		SELECT    COUNT(*) INTO  count FROM per.t_lw where (commauthorcode = usercode_var or firstauthorcode = usercode_var) AND (pubkind = 'SCI收录' OR pubkind = 'SSCI收录') AND status = '已接收有doi';
    UPDATE per.t_research_statistics SET t_sci_comm_or_firstauthor_wait = count where usercode = usercode_var;

		SELECT    COUNT(*) INTO  count FROM per.t_lw where (commauthorcode = usercode_var or firstauthorcode = usercode_var or secauthorcode = usercode_var or threeauthorcode = usercode_var or fourauthorcode = usercode_var or fiveauthorcode = usercode_var or otherauthorcode like concat('%',usercode_var,'%')) AND (pubkind = 'SCI收录' OR pubkind = 'SSCI收录') AND status = '已接收有doi';
		UPDATE per.t_research_statistics SET t_sci_all_wait = count where usercode = usercode_var;

		SELECT    COUNT(*) INTO  count FROM per.t_lw where (commauthorcode = usercode_var or firstauthorcode = usercode_var) AND pubkind = '国内刊物';
    UPDATE per.t_research_statistics SET t_home_comm_or_firstauthor = count where usercode = usercode_var;

		SELECT    COUNT(*) INTO  count FROM per.t_lw where (commauthorcode = usercode_var or firstauthorcode = usercode_var or secauthorcode = usercode_var or threeauthorcode = usercode_var or fourauthorcode = usercode_var or fiveauthorcode = usercode_var or otherauthorcode like concat('%',usercode_var,'%')) AND pubkind = '国内刊物';
		UPDATE per.t_research_statistics SET t_home_all = count where usercode = usercode_var;
		
		SELECT    COUNT(*) INTO  count FROM per.t_lw where (commauthorcode = usercode_var or firstauthorcode = usercode_var) AND pubkind = 'EI收录';
    UPDATE per.t_research_statistics SET t_ei_comm_or_firstauthor = count where usercode = usercode_var;

		SELECT    COUNT(*) INTO  count FROM per.t_lw where (commauthorcode = usercode_var or firstauthorcode = usercode_var or secauthorcode = usercode_var or threeauthorcode = usercode_var or fourauthorcode = usercode_var or fiveauthorcode = usercode_var or otherauthorcode like concat('%',usercode_var,'%')) AND pubkind = 'EI收录';
		UPDATE per.t_research_statistics SET t_ei_all = count where usercode = usercode_var;

		SELECT    COUNT(*) INTO  count FROM per.t_lw where (commauthorcode = usercode_var or firstauthorcode = usercode_var or secauthorcode = usercode_var or threeauthorcode = usercode_var or fourauthorcode = usercode_var or fiveauthorcode = usercode_var or otherauthorcode like concat('%',usercode_var,'%')) AND pubkind = 'ISTP收录';
		UPDATE per.t_research_statistics SET t_istp_all = count where usercode = usercode_var;

		SELECT    COUNT(*) INTO  count FROM per.t_lw where (commauthorcode = usercode_var or firstauthorcode = usercode_var or secauthorcode = usercode_var or threeauthorcode = usercode_var or fourauthorcode = usercode_var or fiveauthorcode = usercode_var or otherauthorcode like concat('%',usercode_var,'%')) AND pubkind = '其他国外刊物';
		UPDATE per.t_research_statistics SET t_abroad_all = count where usercode = usercode_var;

		-- 著作
		SELECT    COUNT(*) INTO  count FROM t_writing where first_author_code = usercode_var or second_author_code = usercode_var  or third_author_code = usercode_var or fourth_author_code = usercode_var or fifth_author_code = usercode_var or other_author_code like concat('%',usercode_var,'%');
		UPDATE per.t_research_statistics SET writting_all = count where usercode = usercode_var;

		-- 软件
		SELECT    COUNT(*) INTO  count FROM t_rj where firstauthorcode = usercode_var or secauthorcode = usercode_var  or threeauthorcode = usercode_var or fourauthorcode = usercode_var or fiveauthorcode = usercode_var or otherauthorcode like concat('%',usercode_var,'%');
		UPDATE per.t_research_statistics SET software_all = count where usercode = usercode_var;

		-- 专利
		SELECT    COUNT(*) INTO  count FROM t_zl where firstInvectorcode = usercode_var or secondInvectorcode = usercode_var  or thirdInvectorcode = usercode_var or fourthInvectorcode = usercode_var or fifthInvectorcode = usercode_var or otherInvectorcode like concat('%',usercode_var,'%');
		UPDATE per.t_research_statistics SET patent_all = count where usercode = usercode_var;

		-- 奖励
		SELECT    COUNT(*) INTO  count FROM t_award where awardeecode1 = usercode_var or awardeecode2 = usercode_var  or awardeecode3 = usercode_var or awardeecode4 = usercode_var or awardeecode5 = usercode_var or awardeecode6 = usercode_var or awardeecode7 = usercode_var or awardeecode8 = usercode_var or awardeecode9 = usercode_var or awardeecode10 = usercode_var or awardeecodeother like concat('%',usercode_var,'%');
		UPDATE per.t_research_statistics SET award_all = count where usercode = usercode_var;

		-- 报告
		SELECT    COUNT(*) INTO  count FROM t_zx where firstauthorcode = usercode_var or secauthorcode = usercode_var  or threeauthorcode = usercode_var or fourauthorcode = usercode_var or fiveauthorcode = usercode_var or otherauthorcode like concat('%',usercode_var,'%');
		UPDATE per.t_research_statistics SET report_all = count where usercode = usercode_var;

		-- 规划
		SELECT    COUNT(*) INTO  count FROM t_gh where firstauthorcode = usercode_var or secauthorcode = usercode_var  or threeauthorcode = usercode_var or fourauthorcode = usercode_var or fiveauthorcode = usercode_var or otherauthorcode like concat('%',usercode_var,'%');
		UPDATE per.t_research_statistics SET plan_all = count where usercode = usercode_var;

		-- 标准
		SELECT    COUNT(*) INTO  count FROM t_bz where firstauthorcode = usercode_var or secauthorcode = usercode_var  or threeauthorcode = usercode_var or fourauthorcode = usercode_var or fiveauthorcode = usercode_var or otherauthorcode like concat('%',usercode_var,'%');
		UPDATE per.t_research_statistics SET standard_all = count where usercode = usercode_var;

UNTIL stopFlag = 1 END REPEAT; 
CLOSE cursor1;

END $$ 
DELIMITER ;

call Proc_Research_Statistics();

