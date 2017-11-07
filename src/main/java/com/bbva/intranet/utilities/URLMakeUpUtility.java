package com.bbva.intranet.utilities;

public abstract class URLMakeUpUtility {

    public static String changeDomain(String url, String domain) {
        String newUrl = "";
        if (url.startsWith("http")) {
            // If the URL starts with "http", it will the finish of the Domain
            int index = url.indexOf("/") + 2;
            String uri = url.substring(url.indexOf("/", index), url.length());

            newUrl = domain + uri;
        } else {
            // If it isn't any protocol, the domain will just added
            newUrl = domain + url;
        }

        return newUrl;
    }

    public static String changePortForJunction(String url, String junction) {
        int index = url.indexOf("/") + 2;

        String baseUrl = url.substring(0, url.indexOf(":", index));
        String uri = url.substring(url.indexOf("/", index));

        return String.format("%s/%s%s", baseUrl, junction, uri);
    }

    public static String addParams(String url, String params) {
        if (url != null && !url.equals("")) {
            if (url.contains("?")) {
                url = String.format("%s&%s", url, params);
            } else {
                url = String.format("%s?%s", url, params);
            }
        }
        return url;
    }

}
