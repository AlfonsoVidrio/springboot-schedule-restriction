# springboot-schedule-restriction

This project demonstrates how to implement a custom interceptor in Spring Boot to control access to endpoints based on time restrictions. The interceptor checks whether the request is made within a predefined time range and returns a message accordingly. If the request is made outside of the allowed time range, the interceptor will block access and send a JSON response indicating the service is closed.

## Usage

- The application runs a web server on the default port (8080).
- The key endpoint for this demonstration is `/foo`:

  ```plaintext
  http://localhost:8080/foo
  ```
If the request is made between the configured hours (14:00 to 18:00), a success message will be shown.

If the request is made outside of this time range, a message indicating the service is closed will be returned.

## Configuration

In the `application.properties` file, you can configure the opening and closing hours for the service:

```properties
spring.application.name=springboot-schedule
config.calendar.open=14
config.calendar.close=18
```

## In the MvcConfig class, the custom interceptor (CalendarInterceptor) is registered and applied to the /foo endpoint:

```java
@Override
public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(calendarInterceptor).addPathPatterns("/foo");
}
```
## What happens when the request is outside of allowed hours?

If the current time is outside the defined range (`open` and `close`), the `preHandle()` method of the interceptor will block the request, and a JSON response will be sent back to the client with a message indicating the service is closed. The response will look like this:

```json
{
  "message": "Sorry, we are closed, we are open from 14hrs to 18hrs. Thank you for your visit!",
  "date": "Tue Mar 02 15:45:00 UTC 2025"
}
```

## What happens when the request is within the allowed hours?

If the current time is within the defined range (`open` and `close`), the request will proceed to the controller method, and a success message will be included in the response.

```json
{
  "title": "Welcome to the schedule app",
  "date": "Tue Mar 02 15:45:00 UTC 2025",
  "message": "Welcome to the customer service hours, we are open from 14hrs to 18hrs. Thank you for your visit!"
}
```
