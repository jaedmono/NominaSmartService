package com.smartservice.nomina.config;

import com.smartservice.nomina.SmartServiceNominaApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.util.StringUtils;

import static java.lang.System.getProperty;

public class SpringProfileInitializer extends SpringBootServletInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    private static final String PROFILE_PROPERTY = "tomcat.env";
    private static final String DEFAULT_PROFILE = "default";

    private static final Logger LOGGER = LoggerFactory.getLogger(SmartServiceNominaApplication.class);

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        ConfigurableEnvironment environment = applicationContext.getEnvironment();
        environment.setActiveProfiles(getActiveProfile());
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        application.profiles(getActiveProfile());
        return application.sources(SmartServiceNominaApplication.class);
    }

    public static String getActiveProfile(){
        String readProperty = getProperty(PROFILE_PROPERTY);
        if(StringUtils.isEmpty(readProperty)){
            return DEFAULT_PROFILE;
        }else{
            return readProperty;
        }
    }
}
