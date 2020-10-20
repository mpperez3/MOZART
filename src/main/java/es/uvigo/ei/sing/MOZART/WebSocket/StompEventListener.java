package es.uvigo.ei.sing.MOZART.WebSocket;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.session.web.socket.events.SessionConnectEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
@Log4j2
public class StompEventListener implements ApplicationListener<SessionConnectEvent> {

    @Override
    public void onApplicationEvent(SessionConnectEvent event) {
        log.debug("Connect: " + event.getWebSocketSession().isOpen()
                + ",disconnect:" + event.getWebSocketSession().isOpen()
                + ", event [sessionId: " + event.getWebSocketSession().getId() + ";" + event.getWebSocketSession().getPrincipal().getName() + " ,command=" + event.getWebSocketSession().getUri());

    }


    @EventListener
    public void onSocketConnected(SessionConnectedEvent event) {
        StompHeaderAccessor sha = StompHeaderAccessor.wrap(event.getMessage());
        log.info("[Connected] " + sha.getUser().getName());
    }

    @EventListener
    public void onSocketDisconnected(SessionDisconnectEvent event) {
        StompHeaderAccessor sha = StompHeaderAccessor.wrap(event.getMessage());
        log.info("[Disonnected] " + sha.getUser().getName());
    }

}