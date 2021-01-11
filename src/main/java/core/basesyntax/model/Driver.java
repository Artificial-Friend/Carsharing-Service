package core.basesyntax.model;

public class Driver {
    private static long id;
    private String name;
    private String licenseNumber;

    public Driver(long id, String name, String licenseNumber) {
        id++;
        this.name = name;
        this.licenseNumber = licenseNumber;
    }

    public long getId() {
        return id;
    }

    public Driver setId(long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Driver setName(String name) {
        this.name = name;
        return this;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public Driver setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
        return this;
    }
}
