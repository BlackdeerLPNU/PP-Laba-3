import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Product {
    private String name;
    private TypeOfProduct typeOfProduct;
    private double price;
    private LocalDate date;

    public Product(final String name, final TypeOfProduct typeOfProduct, final double price) {
        this.date = LocalDate.now();
        this.name = name;
        this.typeOfProduct = typeOfProduct;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public TypeOfProduct getTypeOfProduct() {
        return typeOfProduct;
    }

    public void setTypeOfProduct(final TypeOfProduct typeOfProduct) {
        this.typeOfProduct = typeOfProduct;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(final double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return   name +" " + typeOfProduct +" " + price +" " + date;
    }

}
