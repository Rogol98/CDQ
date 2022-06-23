package pl.rogol.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Brewery {

    private String id;
    private String address;
    private String categories;
    private String city;
    private String country;
    private String hours;
    private String keys;
    private double latitude;
    private double longitude;
    private String menus;
    private String name;
    private String postalCode;
    private String province;
    private String twitter;
    private String websites;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Brewery brewery = (Brewery) o;
        return Double.compare(brewery.latitude, latitude) == 0 && Double.compare(brewery.longitude, longitude) == 0 &&
                Objects.equals(address, brewery.address) &&
                Objects.equals(categories, brewery.categories) && Objects.equals(city, brewery.city) &&
                Objects.equals(country, brewery.country) && Objects.equals(hours, brewery.hours) &&
                Objects.equals(keys, brewery.keys) && Objects.equals(menus, brewery.menus) &&
                Objects.equals(name, brewery.name) && Objects.equals(postalCode, brewery.postalCode) &&
                Objects.equals(province, brewery.province) && Objects.equals(twitter, brewery.twitter) &&
                Objects.equals(websites, brewery.websites);
    }

    @Override
    public int hashCode() {
        return Objects.hash(address, categories, city, country, hours, keys, latitude, longitude, menus, name, postalCode, province, twitter, websites);
    }

    @Override
    public String toString() {
        return "Brewery{" +
                "id='" + id + '\'' +
                ", address='" + address + '\'' +
                ", categories='" + categories + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", hours='" + hours + '\'' +
                ", keys='" + keys + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", menus='" + menus + '\'' +
                ", name='" + name + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", province='" + province + '\'' +
                ", twitter='" + twitter + '\'' +
                ", websites='" + websites + '\'' +
                '}';
    }
}
