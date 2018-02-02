package com.bbva.intranet.utilities;

/**
 * Created by XMZ0860 on 22/06/2016.
 */
public abstract class QueryUtility {

    public static String buildLikeConditional(String fieldName, String value) {
        String conditional;
        conditional = value != null ? String.format(" UPPER(%s) LIKE UPPER('%%%s%%') ", fieldName, value) : "";

        return conditional;
    }

    public static String buildEqualIntegerConditional(String fieldName, Integer value) {
        String conditional;
        conditional = value != null ? String.format(" %s = %d ", fieldName, value) : "";

        return conditional;
    }

    public static String buildEqualDoubleConditional(String fieldName, Double value) {
        String conditional;
        conditional = value != null ? String.format(" %s = %d ", fieldName, value) : "";

        return conditional;
    }

    public static String buildEqualDateConditional(String fieldName, String value, String format) {
        String conditional;

        conditional = value != null ? String.format("  DATE_FORMAT(%s, '%%e/%%m/%%Y') = DATE_FORMAT(%s, '%%e/%%m/%%Y') ", fieldName, value) : "";

        return conditional;
    }

    public static String addConjunction(String fieldConditional, String conjunction) {
        String queryPartial;
        queryPartial = !fieldConditional.isEmpty() ? String.format("%s%s", fieldConditional, conjunction) : "";
        return queryPartial;
    }

    public static String checkQueryCorrectlyEnds(String query, String conjunction) {
        if (query.endsWith(conjunction)) {
            int querySize = query.length();
            query = query.substring(0, (querySize - conjunction.length()));
        }
        return query;
    }

}
