package com.alfonsovidrio.springboot.calendar.interceptor.springboot_schedule.interceptors;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CalendarInterceptor implements HandlerInterceptor{

    @Value("${config.calendar.open}")
    private Integer open;
    @Value("${config.calendar.close}")
    private Integer close;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

                Calendar calendar = Calendar.getInstance();
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                System.out.println(hour);

                if(hour >= open && hour < close) {
                    StringBuilder message = new StringBuilder("Welcome to the costumer service hours, we are open from ");
                    message.append(", we serve from ");
                    message.append(open);
                    message.append("hrs to ");
                    message.append(close);
                    message.append("hrs.");     
                    message.append(" Thank you for your visit!");
                    request.setAttribute("message", message.toString());
                    return true;
                }

                ObjectMapper mapper = new ObjectMapper();
                Map<String, Object> data = new HashMap<>();
                StringBuilder message = new StringBuilder("Sorry, we are closed, we are open from ");
                message.append(open);
                message.append("hrs to ");
                message.append(close);
                message.append("hrs.");
                message.append(" Thank you for your visit!");
                data.put("message", message.toString());
                data.put("date", new Date().toString());
                response.setContentType("application/json");
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write(mapper.writeValueAsString(data));

                return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
    }

    
}
