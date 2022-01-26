# Spring Boot CQRS Example

Implemented CQRS architecture through spring boot.


For reference, in this example, multi-module is not used. In production, it will be common to configure multimodules. However, it is not applied because it is just an example.

# Architecture overview

![cqrs](./cqrs.png)

- Command used PostgreSQL and query used Redis.
- Changes are synchronized through events via SQS and maintain final consistency.
- The event uses a zero payload form containing only id values and the consumer uses rest api call to get the latest data. This eliminates concerns about order guarantees.

## Why used Zero payload?

To eliminate concerns about ordering. In general, it is very difficult to guarantee the order of events. However, in the case of zero payload, only the id value is included and the rest api is called with that value, so the latest data can always be guaranteed.

Of course, this incurs additional network costs. But let's think about it. If multiple tables need to be affected by one event, more and more data will be included in the payload as the service grows. Therefore, it is better to distribute and delegate responsibility by letting each consumer take the necessary data through a simple id value.