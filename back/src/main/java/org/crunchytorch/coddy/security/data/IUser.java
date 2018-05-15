package org.crunchytorch.coddy.security.data;

import java.util.List;

public interface IUser {

    String getLogin();

    String getFirstName();

    String getLastName();

    List<String> getPermissions();
}
