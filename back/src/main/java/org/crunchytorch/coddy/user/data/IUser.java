package org.crunchytorch.coddy.user.data;

import java.util.List;

public interface IUser {

    String getLogin();

    String getFirstName();

    String getLastName();

    List<String> getPermissions();
}
