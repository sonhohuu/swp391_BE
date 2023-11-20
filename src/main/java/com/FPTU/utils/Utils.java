package com.FPTU.utils;

import javax.servlet.http.HttpServletRequest;

public class Utils {
    public static String getBaseURL(HttpServletRequest request) {
        String scheme = request.getScheme();  // http or https
        String serverName = request.getServerName();  // Server name
        int serverPort = request.getServerPort();  // Server port
        String contextPath = request.getContextPath();  // Context path of the application
        StringBuffer url = new StringBuffer();
        url.append(scheme).append("://").append(serverName);

        // Include the server port if it's not the default (80 for http, 443 for https)
        if ((serverPort != 80) && (serverPort != 443)) {
            url.append(":").append(serverPort);
        }

        url.append(contextPath);

        // Ensure that the URL ends with a trailing slash
        if (url.toString().endsWith("/")) {
            url.append("/");
        }

        return url.toString();
    }
}
