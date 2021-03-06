package org.crunchytorch.coddy.user.runner;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.crunchytorch.coddy.security.data.Permission;
import org.crunchytorch.coddy.user.data.in.UpdateUser;
import org.crunchytorch.coddy.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AdminCreationOnStartup implements ApplicationRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdminCreationOnStartup.class);

    @Autowired
    UserService service;

    @Value("${org.crunchytorch.coddy.user.admin.login:}")
    private String adminUsername;

    @Value("${org.crunchytorch.coddy.user.admin.password:}")
    private char[] adminPassword;

    @Value("${org.crunchytorch.coddy.user.admin.email:}")
    private String adminEmail;

    @Override
    public void run(ApplicationArguments args) {
        this.createAdmin();
    }

    private void createAdmin() {
        if (StringUtils.isEmpty(adminUsername) && ArrayUtils.isEmpty(adminPassword) && StringUtils.isEmpty(adminEmail)) {
            return;
        }

        LOGGER.info("create admin user if exists");
        if (!service.exists(adminUsername)) {
            List<String> permissions = new ArrayList<>();
            permissions.add(Permission.USER);
            permissions.add(Permission.ADMIN);
            service.create(new UpdateUser(adminUsername, adminPassword, adminEmail), permissions);
        }
    }
}
