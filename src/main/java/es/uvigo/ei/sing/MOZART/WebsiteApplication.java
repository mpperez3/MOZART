package es.uvigo.ei.sing.MOZART;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.CacheControl;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.TimeUnit;


@SpringBootApplication
//        (exclude = {DataSourceAutoConfiguration.class })

//@EnableCaching
@Log4j2
@EnableAsync
//@EnableConfigurationProperties(DataSourceProperties.class)
//@EnableJpaRepositories(repositoryBaseClass = CustomRepositoryImpl.class)
public class WebsiteApplication {


    public static void main(String[] args) {
        SpringApplication.run(WebsiteApplication.class, args);


//        System.out.println("Desktop.isDesktopSupported() = " + Desktop.isDesktopSupported());
//        System.out.println("Desktop.isDesktopSupported() = " + Desktop.getDesktop().isSupported(Desktop.Action.BROWSE));

//        String url = "http://" + "localhost" + ":" + "8080";
//        Browse(url);

//        if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
//            environment.getProperty("spring.cloud.client.ip-address");
//            environment.getProperty("spring.cloud.client.hostname");
//
//
//            String url = "http://" + environment.getProperty("spring.cloud.client.ip-address") + ":" + environment.getProperty("local.server.port");
//
//            try {
//                Desktop.getDesktop().browse(new URI(url));
//            } catch (IOException e) {
//                e.printStackTrace();
//            } catch (URISyntaxException e) {
//                e.printStackTrace();
//            }
    }

    public static void Browse(String url) {
        if (Desktop.isDesktopSupported()) {
            Desktop desktop = Desktop.getDesktop();
            try {
                desktop.browse(new URI(url));
            } catch (IOException | URISyntaxException e) {
                e.printStackTrace();
            }
        } else {
            Runtime runtime = Runtime.getRuntime();
            try {
                runtime.exec("rundll32 url.dll,FileProtocolHandler " + url);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Bean
    public WebMvcConfigurerAdapter webConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addResourceHandlers(ResourceHandlerRegistry registry) {
                registry.addResourceHandler("static/js/**")
                        .addResourceLocations("/static/js/")
                        .setCacheControl(CacheControl.maxAge(3, TimeUnit.DAYS));
                registry.addResourceHandler("static/img/**")
                        .addResourceLocations("/static/img/")
                        .setCacheControl(CacheControl.maxAge(3, TimeUnit.DAYS));
                registry.addResourceHandler("static/css/**")
                        .addResourceLocations("/static/css/")
                        .setCacheControl(CacheControl.maxAge(3, TimeUnit.DAYS));
            }
        };
    }


//    public static void main(String args[]) throws Exception {
////        System.setProperty(PlatformUtilities.PROPERTY_RAPIDMINER_HOME, Paths.get("").toAbsolutePath().toString());
////        RapidMinerGUI.registerStartupListener(new ToolbarGUIStartupListener());
////        RapidMinerGUI.main(args);
//
//        System.setProperty(PlatformUtilities.PROPERTY_RAPIDMINER_HOME, Paths.get("").toAbsolutePath().toString());
//        RapidMiner.setExecutionMode(RapidMiner.ExecutionMode.COMMAND_LINE);
//        RapidMiner.init();
//    }



}

