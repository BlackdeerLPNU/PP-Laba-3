import java.time.LocalDate;
import java.util.*;
import java.util.Date;
import java.util.stream.Collectors;


public class Order {
    private String customerName;
    private ArrayList<Product> listOfProduct;
    private LocalDate date;

    public Order(final String customerName) {
        this.customerName = customerName;
        this.listOfProduct = new ArrayList<>();
        this.date = LocalDate.now();
    }

    public ArrayList<Product> getListOfProduct() {
        return listOfProduct;
    }

    public void addProdutc(final ArrayList<Product> productsInSuperMarket, final String productName) {
        productsInSuperMarket.stream()
                .filter(product -> product.getName().equals(productName))
                .forEach(this.listOfProduct::add);
    }

    public void generateOrderReceipt() {
        final double totalPrice = this.listOfProduct.stream()
                .mapToDouble(Product::getPrice)
                .sum();

        final String refrigeratorProducts = this.listOfProduct.stream()
                .filter(p -> p.getTypeOfProduct() == TypeOfProduct.MEAT || p.getTypeOfProduct() == TypeOfProduct.FISH)
                .map(Product::getName)
                .collect(Collectors.joining(", "));

        final StringBuilder sb = new StringBuilder();
        sb.append("Order Details:");
        sb.append("\n-----------------");
        sb.append("\n" + customerName + " " + date);

        listOfProduct.stream()
                .map(p -> {
                    final String packageText = (p.getTypeOfProduct() == TypeOfProduct.FRUITS || p.getTypeOfProduct() == TypeOfProduct.VEGETABLES) ? " + package" : "";
                    return p + packageText;
                })
                .forEach(description -> sb.append("\n" + description));

        if (!refrigeratorProducts.isEmpty()) {
            sb.append("\nDo not forget to store these products in the refrigerator: " + refrigeratorProducts);
        }

        final String path = "C:\\VOVA\\JAVA\\laba3\\other\\" + customerName + "_" + date + ".txt";
        sb.append("\nTotal price: " + totalPrice + "\n");

        FileService.generateOrderReceipt(sb.toString(), path);
    }


    public double allExpenses(final LocalDate start, final LocalDate finish) {
        return this.listOfProduct.stream()
                .filter(product -> product.getDate().isAfter(start) && product.getDate().isBefore(finish))
                .mapToDouble(Product::getPrice)
                .sum();
    }

    public Map<Product, Integer> getPurchaseSummary() {
        final Map<Product, Integer> purchaseSummary = this.listOfProduct.stream()
                .collect(Collectors.toMap(
                        product -> product,
                        product -> 1,
                        Integer::sum
                ));

        return purchaseSummary;

    }

}
