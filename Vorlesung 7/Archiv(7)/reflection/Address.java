package reflection;

public class Address
{
    public String street;
    private int    number;
    private int    zip;
    private String city;

    Address(String street, int number, int zip, String city)
    {
        super();
        this.street = street;
        this.number = number;
        this.zip = zip;
        this.city = city;
    }

    @Override
    public String toString() {
        return "Address{" +
                "street='" + street + '\'' +
                ", number=" + number +
                ", zip=" + zip +
                ", city='" + city + '\'' +
                '}';
    }
}