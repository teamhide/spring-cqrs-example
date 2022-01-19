package com.example.springcqrsexample.common.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.EventType;
import org.hibernate.internal.SessionFactoryImpl;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManagerFactory;

@Component
@RequiredArgsConstructor
@Slf4j
public class HibernateListener {
    private final EntityManagerFactory entityManagerFactory;
    private final CustomUpdateEventListener customUpdateEventListener;
    private final CustomPostInsterEventListener customPostInsterEventListener;

    @PostConstruct
    private void init() {
        log.info("Initializing HibernateListener");
        SessionFactoryImpl sessionFactory = entityManagerFactory.unwrap(SessionFactoryImpl.class);
        EventListenerRegistry registry = sessionFactory.getServiceRegistry().getService(EventListenerRegistry.class);
        registry.getEventListenerGroup(EventType.PRE_UPDATE).appendListener(customUpdateEventListener);
        registry.getEventListenerGroup(EventType.POST_INSERT).appendListener(customPostInsterEventListener);
    }
}
