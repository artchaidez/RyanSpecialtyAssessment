package apiModals;

public class Address {
    private String streetAddress;
    private String city;
    private String zip;
    private String countryCode;

    // Getter Methods

    public String getStreetAddress() {
        return streetAddress;
    }

    public String getCity() {
        return city;
    }

    public String getZip() {
        return zip;
    }

    public String getCountryCode() {
        return countryCode;
    }

    // Setter Methods

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }
}
