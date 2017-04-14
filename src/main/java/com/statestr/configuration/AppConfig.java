package com.statestr.configuration;

import com.statestr.filter.FastJsonLazyFIilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by ruantianbo on 2017/4/12.
 */
@Configuration
public class AppConfig {
    @Bean(name="fastJsonLazyFIilter")
    public FastJsonLazyFIilter initFastJsonLazyFIilter(){
        return new FastJsonLazyFIilter();
    }
}
