package org.crunchytorch.coddy.user.runner;

import org.crunchytorch.coddy.application.utils.AppUtils;
import org.crunchytorch.coddy.user.data.in.UpdateUser;
import org.crunchytorch.coddy.user.data.security.Permission;
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

    @Value("${" + AppUtils.CONF_ADMIN_LOGIN + ":admin}")
    private String adminUsername;

    @Value("${" + AppUtils.CONF_ADMIN_PASSWORD + ":admin}")
    private char[] adminPassword;

    @Value("${" + AppUtils.CONF_ADMIN_EMAIL + ":admin.coddy@crunchy-torch.org}")
    private String adminEmail;

    @Value("${" + AppUtils.CONF_BOT_LOGIN + ":coddyBot}")
    private String botUsername;

    @Value("${" + AppUtils.CONF_BOT_PASSWORD + ":coddyBot}")
    private char[] botPassword;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<String> permissions = new ArrayList<String>() {{
            add(Permission.PERSO_ACCOUNT);
            add(Permission.PERSO_SNIPPET);
            add(Permission.ADMIN);
        }};
        LOGGER.info("create admin user if exists");
        if (!service.isExist(adminUsername)) {
            service.create(new UpdateUser(adminUsername, adminPassword, adminEmail), permissions);
        }
        LOGGER.info("create bot user is exists");
        if (!service.isExist(botUsername)) {
            service.create(new UpdateUser(botUsername, botPassword), permissions);
        }
    }
}
