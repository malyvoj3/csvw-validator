package com.malyvoj3.csvwvalidator.utils;

import com.malyvoj3.csvwvalidator.domain.ContentType;
import com.malyvoj3.csvwvalidator.domain.FileResponse;
import com.malyvoj3.csvwvalidator.domain.Link;
import lombok.NonNull;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

// TODO predelat na beanu
@UtilityClass
@Slf4j
public class FileUtils {

    public boolean isUtf8(ByteBuffer byteBuffer) {
        CharsetDecoder decoder =
                StandardCharsets.UTF_8.newDecoder();
        try {
            decoder.decode(byteBuffer);
        } catch (CharacterCodingException ex) {
            return false;
        }
        return true;
    }

    public FileResponse downloadTabularFile(String stringUrl) {
        FileResponse fileResponse;
        if (isFileUrl(stringUrl)) {
            fileResponse = getLocalFile(stringUrl);
        } else {
            fileResponse = downloadRemoteFile(stringUrl);
        }
        return fileResponse;
    }

    public FileResponse downloadMetadataFile(String stringUrl) {
        FileResponse fileResponse;
        if (isFileUrl(stringUrl)) {
            fileResponse = getLocalMetadataFile(stringUrl);
        } else {
            fileResponse = downloadRemoteMetadaFile(stringUrl);
        }
        return fileResponse;
    }

    private static FileResponse getLocalFile(@NonNull String stringUrl) {
        FileResponse fileResponse = null;
        String normalizedUrl = UriUtils.normalizeUri(stringUrl);
        log.info("Not downloading file - it is local file {}.", normalizedUrl);
        fileResponse = new FileResponse();
        fileResponse.setFilePath(normalizedUrl);
        fileResponse.setUrl(normalizedUrl);
        fileResponse.setRemoteFile(false);
        return fileResponse;
    }

    private static FileResponse getLocalMetadataFile(@NonNull String stringUrl) {
        FileResponse fileResponse = null;
        String normalizedUrl = UriUtils.normalizeUri(stringUrl);
        URL url;
        try {
            url = new URL(normalizedUrl);
            log.info("Opening local file {}.", normalizedUrl);
            byte[] byteArray = IOUtils.toByteArray(url);
            fileResponse = new FileResponse();
            fileResponse.setContent(byteArray);
            fileResponse.setUrl(normalizedUrl);
            fileResponse.setRemoteFile(false);
        } catch (IOException e) {
            log.error("Cannot open local file with url {}.", stringUrl);
        }
        return fileResponse;
    }

    private FileResponse downloadRemoteMetadaFile(@NonNull String stringUrl) {
        String normalizedUrl = UriUtils.normalizeUri(stringUrl);
        URL url;
        FileResponse fileResponse = null;
        HttpURLConnection connection = null;
        try {
            url = new URL(normalizedUrl);
            connection = (HttpURLConnection) url.openConnection();
            log.info("Downloading file {}.", normalizedUrl);
            connection.setInstanceFollowRedirects(true);
            connection.setRequestMethod("GET");
            int responseCode = connection.getResponseCode();
            log.info("{} responded with response code {}.", normalizedUrl, responseCode);
            if (responseCode == HttpURLConnection.HTTP_MOVED_PERM
                    || responseCode == HttpURLConnection.HTTP_MOVED_TEMP) {
                connection.disconnect();
                normalizedUrl = UriUtils.normalizeUri(connection.getHeaderField("Location"));
                url = new URL(normalizedUrl);
                connection = (HttpURLConnection) url.openConnection();
                responseCode = connection.getResponseCode();
            }
            if (responseCode == HttpURLConnection.HTTP_OK) {
                try (InputStream inputStream = connection.getInputStream()) {
                    fileResponse = new FileResponse();
                    fileResponse.setResponseCode(responseCode);
                    fileResponse.setUrl(normalizedUrl);
                    fileResponse.setContent(IOUtils.toByteArray(inputStream));
                    fileResponse.setContentType(createContentType(connection.getContentType()));
                    fileResponse.setLink(createLink(connection.getHeaderFields().get("Link"), normalizedUrl));
                }
            }
        } catch (Exception ex) {
            log.error("Cannot download file with url {}.", stringUrl);
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
        return fileResponse;
    }

    private FileResponse downloadRemoteFile(@NonNull String stringUrl) {
        String normalizedUrl = UriUtils.normalizeUri(stringUrl);
        URL url;
        FileResponse fileResponse = null;
        HttpURLConnection connection = null;
        try {
            url = new URL(normalizedUrl);
            connection = (HttpURLConnection) url.openConnection();
            log.info("Downloading file {}.", normalizedUrl);
            connection.setInstanceFollowRedirects(true);
            connection.setRequestMethod("GET");
            int responseCode = connection.getResponseCode();
            log.info("{} responded with response code {}.", normalizedUrl, responseCode);
            if (responseCode == HttpURLConnection.HTTP_MOVED_PERM
                    || responseCode == HttpURLConnection.HTTP_MOVED_TEMP) {
                connection.disconnect();
                normalizedUrl = UriUtils.normalizeUri(connection.getHeaderField("Location"));
                url = new URL(normalizedUrl);
                connection = (HttpURLConnection) url.openConnection();
                responseCode = connection.getResponseCode();
            }
            if (responseCode == HttpURLConnection.HTTP_OK) {
                fileResponse = new FileResponse();
                fileResponse.setResponseCode(responseCode);
                fileResponse.setUrl(normalizedUrl);
                fileResponse.setContentType(createContentType(connection.getContentType()));
                fileResponse.setLink(createLink(connection.getHeaderFields().get("Link"), normalizedUrl));
                File tmpFile = File.createTempFile("tmp", null, new File("tmp"));
                try (ReadableByteChannel readerChannel = Channels.newChannel(connection.getInputStream());
                     WritableByteChannel writerChannel = Channels.newChannel(new FileOutputStream(tmpFile))) {
                    fileResponse.setFilePath(UriUtils.normalizeUri(tmpFile.toURI().toString()));
                    ByteBuffer buffer = ByteBuffer.allocate(10000);
                    while (readerChannel.read(buffer) != -1) {
                        buffer.flip();
                        writerChannel.write(buffer);
                        buffer.clear();
                    }
                }
            }
        } catch (Exception ex) {
            log.error("Cannot download file with url {}.", stringUrl);
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
        return fileResponse;
    }

    private boolean isFileUrl(String url) {
        return url != null && url.startsWith("file:/");
    }

    private ContentType createContentType(String contentTypeHeader) {
        ContentType contentType = new ContentType();
        contentType.setType(getHeaderValue(contentTypeHeader));
        contentType.setCharset(getHeaderParameter(contentTypeHeader, "charset"));
        contentType.setHeader(getHeaderParameter(contentTypeHeader, "header"));
        return contentType;
    }

    private Link createLink(List<String> linkHeaders, String fileUrl) {
        Link link = null;
        String linkHeader = null;
        if (linkHeaders != null && linkHeaders.size() > 0) {
            linkHeader = linkHeaders.get(linkHeaders.size() - 1);
            Link tmp = new Link();
            String url = Optional.ofNullable(getHeaderValue(linkHeader))
                    .map(FileUtils::removeLinkBrackets)
                    .orElse(null);
            tmp.setLink(UriUtils.resolveUri(fileUrl, url));
            tmp.setRel(getHeaderParameter(linkHeader, "rel"));
            tmp.setType(getHeaderParameter(linkHeader, "type"));
            if ("describedby".equals(tmp.getRel())) {
                link = tmp;
            }
        }
        return link;
    }

    private String removeLinkBrackets(String link) {
        String result = link;
        if (result != null) {
            if (result.startsWith("<")) {
                result = result.substring(1);
            }
            if (result.endsWith(">")) {
                result = result.substring(0, result.length() - 1);
            }
        }
        return result;
    }

    private String getHeaderValue(String header) {
        String value = null;
        if (StringUtils.isNotEmpty(header)) {
            int index = StringUtils.indexOf(header, ";");
            if (index > 0) {
                value = header.substring(0, index);
            } else {
                value = header;
            }
        }
        return value;
    }

    private String getHeaderParameter(String header, @NonNull String parameterName) {
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
