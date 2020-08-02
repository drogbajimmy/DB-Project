package subscription.config;

import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.stereotype.Component;
import subscription.cache.CacheManager;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.util.Map;

@Component
public class CustomizeCacheInitializer implements ServletContextInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {

        Map<String, String> customerCache = CacheManager.loadCustomer();
        Map<String, String> serviceCache = CacheManager.loadService();

        servletContext.setAttribute("customerCache", customerCache);
        servletContext.setAttribute("serviceCache", serviceCache);
    }
}
