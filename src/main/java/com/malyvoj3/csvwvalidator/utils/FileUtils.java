package com.malyvoj3.csvwvalidator.utils;

import lombok.NonNull;
import lombok.experimental.UtilityClass;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

@UtilityClass
public class FileUtils {

    public static FileResponse downloadFile(String stringUrl) {
        FileResponse fileResponse;
        String normalizedUrl = UriUtils.normalizeUri(stringUrl);
        URL url;
        try {
            url = new URL(normalizedUrl);
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException("Invalid URL.");
        }
        HttpURLConnection connection = null;
        try {
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            try (InputStream inputStream = connection.getInputStream()) {
                fileResponse = new FileResponse();
                fileResponse.setResponseCode(connection.getResponseCode());
                if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    fileResponse.setContent(IOUtils.toByteArray(inputStream));
                    fileResponse.setContentType(createContentType(connection.getContentType()));
                    fileResponse.setLink(createLink(connection.getHeaderField("Link")));
                }
            }
        } catch (ProtocolException e) {
            throw new IllegalArgumentException("Invalid request method.");
        } catch (IOException e) {
            throw new IllegalStateException("Connection exception.");
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
        return fileResponse;
    }

    private static ContentType createContentType(String contentTypeHeader) {
        ContentType contentType = new ContentType();
        contentType.setType(getHeaderValue(contentTypeHeader));
        contentType.setCharset(getHeaderParameter(contentTypeHeader, "charset"));
        contentType.setHeader(getHeaderParameter(contentTypeHeader, "header"));
        return contentType;
    }

    private static Link createLink(String linkHeader) {
        Link link = new Link();
        link.setLink(getHeaderValue(linkHeader));
        link.setRel(getHeaderParameter(linkHeader, "rel"));
        link.setType(getHeaderParameter(linkHeader, "type"));
        return link;
    }

    private static String getHeaderValue(String header) {
        String value = null;
        if (StringUtils.isNotEmpty(header)) {
            int index = StringUtils.indexOf(header, ";");
            if (index > 0) {
                value = header.substring(0, index);
            }
        }
        return value;
    }

    private static String getHeaderParameter(String header, @NonNull String parameterName) {
        String value = null;
        if (StringUtils.isNotEmpty(header)) {
            int index = StringUtils.indexOfIgnoreCase(header, parameterName + "=");
            if (index > 0) {
                int startingIndex = index + parameterName.length() + 1;
                StringBuilder stringBuilder = new StringBuilder();
                for (int i = startingIndex; i < header.length(); i++) {
                    if (header.charAt(i) == ';') {
                        break;
                    }
                    stringBuilder.append(header.charAt(i));
                }
                value = stringBuilder.toString();
            }
        }
        return value;
    }

}
