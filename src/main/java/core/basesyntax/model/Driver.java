package core.basesyntax.model;

import java.util.Objects;

public class Driver {
    private Long id;
    private String name;
    private String licenseNumber;
    private String login;
    private String password;

    public Driver(String name, String licenseNumber, String login, String password) {
        this.name = name;
        this.licenseNumber = licenseNumber;
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public Driver setLogin(String login) {
        this.login = login;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public Driver setPassword(String password) {
        this.password = password;
        return this;
    }

    public Long getId() {
        return id;
    }

    public Driver setId(long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public Driver setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Driver)) return false;
        Driver driver = (Driver) o;
        return Objects.equals(id, driver.id) && Objects.equals(name, driver.name)
                && Objects.equals(licenseNumber, driver.licenseNumber)
                && Objects.equals(login, driver.login)
                && Objects.equals(password, driver.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, licenseNumber, login, password);
    }

    @Override
    public String toString() {
        return "Driver{" + "id=" + id
                + ", name='" + name + '\''
                + ", licenseNumber='" + licenseNumber + '\''
                + ", login='" + login + '}';
    }
}
