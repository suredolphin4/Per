/**
 * 
 */
package com.performance.pagemodel;
/**
 * 审批
 * @author peng
 *
 */

public enum Examine {
	
	EXAMINE_SAVE{
		@Override
		public String getExamine() {
			// TODO Auto-generated method stub
			return "已保存";
		}
		
	},
	EXAMINE_SUBMIT{
		@Override
		public String getExamine() {
			// TODO Auto-generated method stub
			return "已提交";
		}
	},
	EXAMINE_COMPLETE{
		@Override
		public String getExamine() {
			// TODO Auto-generated method stub
			return "审核通过";
		}
	},
	EXAMINE_REJECT{
		@Override
		public String getExamine() {
			// TODO Auto-generated method stub
			return "已退回";
		}
		
	};
	
	public  abstract String getExamine();
	
	
	

}
