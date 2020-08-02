package subscription.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import subscription.entity.Customer;
import subscription.entity.Subscription;
import subscription.service.SubscriptionService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

import static subscription.util.Constant.SESSION_KEY_EMAIL;

@Controller
public class SubscriptionController {

    @Autowired
    private SubscriptionService subscriptionService;

    @RequestMapping("/index")
    public String index(HttpServletRequest request, @ModelAttribute Customer customer) {
        return "index";
    }

    @PostMapping("/login")
    public String login(HttpServletRequest request, @Valid @ModelAttribute Customer customer) {

        Map<String, String> customerCache = (Map<String, String>)request.getServletContext().getAttribute("customerCache");

        if (customerCache.containsKey(customer.getEmail())) {
            request.getSession().setAttribute(SESSION_KEY_EMAIL, customer.getEmail());
            return "redirect:/subscription";
        } else {
            request.setAttribute("error", "User Name Not Exists");
        }

        return "index";
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {

        request.getSession().removeAttribute(SESSION_KEY_EMAIL);

        return "redirect:/index";
    }

    @RequestMapping("/subscription")
    public String list(HttpServletRequest request) {

        String customerId = getCustomerId(request);

        int id = Integer.valueOf(customerId);
        List<Subscription> subscribed = subscriptionService.getSubscriptionByCustomerId(id);
        Map<String, String> serviceCache = (Map<String, String>)request.getServletContext().getAttribute("serviceCache");
        Map<String, String> unsubscribed = subscriptionService.getUnsubscribedService(serviceCache, subscribed);

        request.setAttribute("subscribedServices", subscribed);
        request.setAttribute("unsubscribedServices", unsubscribed);

        return "subscription_list";
    }

    @PostMapping("/subscription/{id}")
    public String unsubscribe(HttpServletRequest request, @PathVariable("id") String subscriptionId) {

        String customerId = getCustomerId(request);

        if (customerId == null) {
            return "redirect:/index";
        }

        subscriptionService.removeSubscription(Integer.valueOf(subscriptionId));

        return "redirect:/subscription";
    }

    @PostMapping("/subscription")
    public String subscribe(HttpServletRequest request, Subscription entity) {

        String customerId = getCustomerId(request);

        int id = Integer.valueOf(customerId);
        entity.setCustomerId(id);
        subscriptionService.subscribeNewService(entity);

        return "redirect:/subscription";
    }

    private String getCustomerId(HttpServletRequest request) {
        String email = (String)request.getSession().getAttribute(SESSION_KEY_EMAIL);
        Map<String, String> customerCache = (Map<String, String>)request.getServletContext().getAttribute("customerCache");
        String customerId = customerCache.get(email);

        if (customerId == null) {
            return "redirect:/index";
        }

        return customerId;
    }
}
