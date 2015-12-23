/**
 * 
 */
package com.newtouch.lion.common.sql;

/**
 * @author wanglijun
 * 
 */
public class SqlUtils {
	/***
	 * 
	 * @param originalSql
	 * @param distinctName
	 * @return
	 * @throw {@link IllegalArgumentException}
	 */
	public static String parseHqlCount(String originalSql, String distinctName) {
		String newSql = originalSql.toLowerCase();
		int beginPos = newSql.indexOf(" from ");
		if (beginPos == -1) {
			throw new IllegalArgumentException("not a valid sql string");
		}
		String countField = null;
		if (distinctName != null)
			countField = "distinct " + distinctName;
		else {
			countField = "*";
		}
		newSql = "select count(" + countField + ")"
				+ originalSql.substring(beginPos);

		return newSql;
	}
}
