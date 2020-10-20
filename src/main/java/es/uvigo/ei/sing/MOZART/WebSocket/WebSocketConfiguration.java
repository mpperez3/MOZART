package es.uvigo.ei.sing.MOZART.WebSocket;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketTransportRegistration;
import org.springframework.web.socket.server.standard.ServletServerContainerFactoryBean;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import javax.servlet.http.HttpSession;
import javax.websocket.ContainerProvider;
import java.util.Map;

@Configuration
@EnableWebSocketMessageBroker
@Log4j2
//https://medium.com/@yairharel/websockets-spring-boot-application-cd33c8e90c0a
public class WebSocketConfiguration extends AbstractWebSocketMessageBrokerConfigurer {


    double maxMessageMB = 0.1;

    public void configureWebSocketTransport(WebSocketTransportRegistration registration) {
        // TODO: 08/07/2019 ver por qué la configuracion no funciona
        registration.setMessageSizeLimit((int) (maxMessageMB * 1024 * 1024));
        registration.setSendBufferSizeLimit((int) (maxMessageMB * 1024 * 1024));
        registration.setSendTimeLimit(20000);
    }


    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/socket")
                .setAllowedOrigins("*").setHandshakeHandler(new DefaultHandshakeHandler() {

            public boolean beforeHandshake(
                    ServerHttpRequest request,
                    ServerHttpResponse response,
                    WebSocketHandler wsHandler,
                    Map attributes) {
                System.out.println("new Register user = " + request);
                if (request instanceof ServletServerHttpRequest) {
                    ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
                    HttpSession session = servletRequest.getServletRequest().getSession();
                    System.out.println("session = " + session);
                    attributes.put("sessionId", session.getId());
                }
                return true;
            }
        })
                .withSockJS().
                setStreamBytesLimit((int) (maxMessageMB * 1024 * 1024))
                .setHttpMessageCacheSize(1000)
                .setDisconnectDelay(30 * 1000)
        ;
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
//        https://www.adictosaltrabajo.com/2017/11/06/websockets-escalables-con-spring-y-rabbitmq/
        registry.setApplicationDestinationPrefixes("/app", "/project", "/guidelines")
                .enableSimpleBroker("/collaborativeCkEditor", "/collaborativeAtomicInput", "/fireEvent");
//        registry.setApplicationDestinationPrefixes("/project").enableSimpleBroker("/addSubscription");


//        setRelayHost("localhost")
//                .setRelayPort(61613)
//                .setClientLogin("guest")
//                .setClientPasscode("guest");
    }


    @Bean
    public ServletServerContainerFactoryBean createServletServerContainerFactoryBean() {
        ServletServerContainerFactoryBean container = new ServletServerContainerFactoryBean();
        container.setMaxTextMessageBufferSize((int) (maxMessageMB * 1024 * 1024));
        container.setMaxBinaryMessageBufferSize((int) (maxMessageMB * 1024 * 1024));
        log.info("Websocket factory returned");
        ContainerProvider.getWebSocketContainer().setDefaultMaxTextMessageBufferSize((int) (maxMessageMB * 1024 * 1024));
        return container;
    }




}