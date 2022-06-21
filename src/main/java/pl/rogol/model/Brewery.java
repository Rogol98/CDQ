package pl.rogol.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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


}
