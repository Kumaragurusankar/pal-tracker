package io.pivotal.pal.tracker;


import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public class CustomizedBuildInfoBean implements InfoContributor{



    @Override
    public void contribute(Info.Builder builder) {
        builder.withDetail("build", "appName:MandeepSodhi");
    }


}
