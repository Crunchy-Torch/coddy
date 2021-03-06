package org.crunchytorch.coddy.user.data.in;

public class UpdateUser {

    private String login;
    /**
     * a String type is not security safe. If you dumb the java memory, you will see clearly the password
     */
    private char[] password;

    private String firstName;
    private String lastName;
    private String email;

    public UpdateUser(){
        // this blank constructor is used by json serialization
    }

    public UpdateUser(String login, char[] password){
        this.login = login;
        this.password = password;
    }

    public UpdateUser(String login, char[] password, String email){
        this(login, password);
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public char[] getPassword() {
        return password;
    }

    public void setPassword(char[] password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
