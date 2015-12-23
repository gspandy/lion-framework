/**
 * 
 */
package com.newtouch.lion.common.sql;

/**
 * @author wanglijun
 *
 */
import java.util.Date;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;

import com.newtouch.lion.common.Assert;

public class HqlUtils {
	private static final Logger log = LoggerFactory.getLogger(HqlUtils.class);
	
	public static final String LIKE_CHAR = "%";

	public static String generateCountHql(String originalHql,
			String distinctName) {
		String loweredOriginalHql = originalHql.toLowerCase();
		int beginPos = loweredOriginalHql.indexOf("from");
		if (beginPos == -1) {
			throw new IllegalArgumentException("not a valid hql string");
		}

		int endPos = loweredOriginalHql.lastIndexOf("order by");
		if (endPos == -1) {
			endPos = loweredOriginalHql.length();
		}
		String countField = null;
		if (distinctName != null)
			countField = distinctName;
		else {
			countField = "*";
		}
		StringBuffer sb=new StringBuffer("select count( ");
		sb.append(countField);
		sb.append(" )");
		sb.append(originalHql.substring(beginPos, endPos).replaceAll("join fetch ", "join "));
		return sb.toString();
	}

	public static String generateHql(String queryFields,
			String fromJoinSubClause, String[] whereBodies, String orderField,
			String orderDirection, Map<String, ?> params) {
		Assert.notNull(fromJoinSubClause);
		StringBuffer sb = new StringBuffer();
		if (queryFields != null) {
			sb.append(queryFields).append(" ");
		}
		sb.append(fromJoinSubClause);
		boolean  includeWhere=sb.indexOf(" where ")>0?true:false;
		sb.append(" ").append(generateHqlWhereClause(whereBodies, params,includeWhere));
		sb.append(" ").append(generateHqlOrderClause(orderField, orderDirection));
		String finalHql = sb.toString();
		log.debug("HQL: " + finalHql);

		return finalHql;
	}

	static String generateHqlOrderClause(Order[] orders) {
		if (orders == null) {
			return "";
		}
		boolean isFirst = true;
		StringBuffer stringBuffer = new StringBuffer();
		for (Order order : orders) {
			if (order != null) {
				if (isFirst) {
					stringBuffer.append(" order by ");
					isFirst = false;
				} else {
					stringBuffer.append(", ");
				}
				stringBuffer.append(order.toString());
			}
		}

		return stringBuffer.toString();
	}

	private static String generateHqlOrderClause(String orderField,
			String orderDirection) {
		if (StringUtils.isBlank(orderField)) {
			return "";
		}
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append(" order by ");
		stringBuffer.append(orderField).append(" ");
		if (orderDirection != null) {
			stringBuffer.append(orderDirection);
		}

		return stringBuffer.toString();
	}

	private static String generateHqlWhereClause(String[] whereBodies,
			Map<String, ?> params,boolean includeWhere) {
		StringBuffer sb = new StringBuffer();
		String andOp = " and ";
		if ((whereBodies != null) && (whereBodies.length > 0)) {
			for (String whereBody : whereBodies) {
				String paramName = getWhereBodyParamName(whereBody);
				if (paramName != null) {
					if ((params == null) || (!(params.containsKey(paramName)))
							|| (params.get(paramName) == null)
							|| (params.get(paramName).toString().length() <= 0)) {
						continue;
					}
					sb.append(andOp).append("(").append(whereBody).append(")");
				} else {
					sb.append(andOp).append("(").append(whereBody).append(")");
				}
			}
			if (sb.length() > 0&&!includeWhere) {
				sb.replace(0, andOp.length(), " where ");
			}
		}
		return sb.toString();
	}

	private static String getWhereBodyParamName(String original) {
		if (!(original.contains(":"))) {
			return null;
		}
		String[] oris = original.split("[:()]");
		if (oris.length == 1) {
			return null;
		}
		return oris[(oris.length - 1)].trim();
	}

	public static String fullILike(Object ori) {
		if (ori == null) {
			return null;
		}
		return "%" + ori.toString().toLowerCase() + "%";
	}

	public static Date parseEndDate(Date ori) {
		if (ori == null) {
			return null;
		}

		return DateUtils.addDays(ori, 1);
	}

	public static Date parseEndWeekDate(Date ori) {
		if (ori == null) {
			return null;
		}

		return DateUtils.addWeeks(ori, 1);
	}
}