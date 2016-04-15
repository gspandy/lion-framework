package com.newtouch.lion.query;

import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by li.yu on 2015/8/9.
 */
public class QueryUtils {

    public static final String COUNT_QUERY_STRING = "select count(%s) from %s x";

    /**
     * Returns the query string for the given class name.
     *
     * @param template
     * @param entityName
     * @return
     */
    public static String getQueryString(String template, String entityName) {

        Assert.hasText(entityName, "Entity name must not be null or empty!");

        return String.format(template, entityName);
    }

    public static String getCountQueryString(String entityName) {

        String countQuery = String.format(COUNT_QUERY_STRING, "x", "%s");
        return getQueryString(countQuery, entityName);
    }

    public static String genCountQueryString(String queryString) {
        return "select count(*) " + removeSelect(queryString);
    }

    /**
     * <pre>
     * 去除JPQL语句前的select部分，用来生成查询总记录条数的HQL语句。
     *
     * <strong>程序范例：</strong>
     * String queryCountString = "select count(*) " + QueryUtils.removeSelect(queryString);
     *
     * </pre>
     * @param queryString
     * @return
     */
    public static String removeSelect(String queryString) {
        Assert.notNull(queryString);
        queryString = removeFetch(queryString);
        int beginPos = queryString.toLowerCase().indexOf("from");
        Assert.isTrue(beginPos != -1, " the jpql : " + queryString + " must has a keyword 'from'");
        return queryString.substring(beginPos);
    }

    /**
     * <pre>
     * 去除HQL语句后的order by部分
     *
     * <strong>程序范例：</strong>
     * queryCountString = HqlUtils.removeOrders(queryCountString);
     *
     * </pre>
     * @param queryString
     * @return
     */
    public static String removeOrders(String queryString) {
        Pattern pattern = Pattern.compile("order\\s*by[\\w|\\W|\\s|\\S]*", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(queryString);
        StringBuffer stringBuffer = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(stringBuffer, "");
        }
        matcher.appendTail(stringBuffer);
        return stringBuffer.toString();
    }

    /**
     * <pre>
     * 去除JPQL语句内的fetch部分
     *
     * <strong>程序范例：</strong>
     * queryString = removeFetch(queryString);
     *
     * </pre>
     * @param queryString
     * @return
     */
    public static String removeFetch(String queryString) {
        Assert.notNull(queryString);
        return StringUtils.delete(queryString, " fetch");
    }
}
