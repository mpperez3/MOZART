//package es.uvigo.ei.sing.chemSmile.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.CacheControl;
//import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//import java.util.Objects;
//import java.util.concurrent.TimeUnit;
//
//@Configuration
//public class WebConfig implements WebMvcConfigurer {
//
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        Objects.requireNonNull(registry);
//
////        // could use either '/**/images/{filename:\w+\.png}' or '/**/images/*.png'
////        registry.addResourceHandler("/**/images/{filename:\\w+\\.png}")
////                .addResourceLocations("classpath:/static/")
////                .setCacheControl(CacheControl.maxAge(1, TimeUnit.DAYS));
////
////        registry.addResourceHandler("/**/images/*.jpg")
////                .addResourceLocations("classpath:/static/")
////                .setCacheControl(CacheControl.maxAge(2, TimeUnit.DAYS));
////
////        registry.addResourceHandler("/**/lib/*.js")
////                .addResourceLocations("classpath:/static/")
////                .setCacheControl(CacheControl.maxAge(3, TimeUnit.DAYS));
//
//
//        registry.addResourceHandler("/static/js/**")
//                .addResourceLocations("/static/js/")
//                .setCacheControl(CacheControl.maxAge(3, TimeUnit.DAYS));
//        registry.addResourceHandler("/static/img/**")
//                .addResourceLocations("/static/img/")
//                .setCacheControl(CacheControl.maxAge(3, TimeUnit.DAYS));
//        registry.addResourceHandler("/static/css/**")
//                .addResourceLocations("/static/css/")
//                .setCacheControl(CacheControl.maxAge(3, TimeUnit.DAYS));
//
//
//    }
//}