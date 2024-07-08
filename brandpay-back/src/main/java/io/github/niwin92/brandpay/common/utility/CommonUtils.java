package io.github.niwin92.brandpay.common.utility;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class CommonUtils {

    public static String relayURL(String url,  Map<String,String> map){
        return url + "?" + mapToQueryString(map);
    }

    public static String mapToQueryString(Map<String,String> map) {
        List<NameValuePair> nameValuePairs = map.entrySet().stream()
                .map(entry -> new BasicNameValuePair(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
        return URLEncodedUtils.format(nameValuePairs, StandardCharsets.UTF_8);
    }
}

