package subscription.cache;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.core.io.ClassPathResource;

import java.io.*;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static subscription.util.Constant.CUSTOMER_CACHE_FILE_PATH;
import static subscription.util.Constant.SERVICE_CACHE_FILE_PATH;

public class CacheManager {

    public static Map<String, String> loadCustomer() {
        return generateCache(CUSTOMER_CACHE_FILE_PATH);
    }

    public static Map<String, String> loadService() {
        return generateCache(SERVICE_CACHE_FILE_PATH);
    }

    private static Map<String, String> generateCache(String filePath) {

        ClassPathResource classPathResource = new ClassPathResource(filePath);
        Iterable<CSVRecord> records = null;
        Map<String, String> cacheMap = new HashMap<>();
        try (InputStream inputStream = classPathResource.getInputStream();
             Reader in = new InputStreamReader(inputStream, "UTF-8");) {

            records = CSVFormat.DEFAULT.parse(in);

            for (CSVRecord record : records) {
                String columnOne = record.get(0);
                String columnTwo = record.get(1);
                cacheMap.put(columnOne, columnTwo);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return Collections.unmodifiableMap(cacheMap);
    }
}
