package es.uvigo.ei.sing.MOZART.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class SpringConfiguration {


    @Value("${threadPool.maxThreads}")
    private int maxThreads;

    @Value("${threadPool.maxPoolSize}")
    private int maxPoolSize;

    @Bean
    public ThreadPoolTaskExecutor restExecutorBean() {
        // Limit the Executor to 3 threads (API limitations)
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(maxThreads);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setQueueCapacity(500);

        executor.setThreadNamePrefix("Chemical workers");
        executor.initialize();

        return executor;
    }


}
