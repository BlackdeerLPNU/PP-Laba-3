import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SuperMarket {
    private ArrayList<Product> availableProducts = new ArrayList<>();
    private ArrayList<Product> purchaseHistory = new ArrayList<>();
    private final String pathToAvailableProducts;
    private final String pathToHistory;


    public SuperMarket(final String pathToAvailableProducts, final String pathToHistory) {
        this.availableProducts = FileService.availableProductsReadFile(pathToAvailableProducts);
        this.pathToAvailableProducts = pathToAvailableProducts;
        this.pathToHistory = pathToHistory;
    }

    public ArrayList<Product> getAvailableProducts() {
        return availableProducts;
    }

    public ArrayList<Product> getPurchaseHistory() {
        return purchaseHistory;
    }

    public void addProduct(final Product product) {

        availableProducts.add(product);
    }

    public void sellProduct(final String name, final TypeOfProduct typeOfProduct, final ArrayList<Product> listOfProduct) {
        try {
            this.availableProducts.stream()
                    .filter(product -> name.equals(product.getName()) && typeOfProduct.equals(product.getTypeOfProduct()))
                    .forEach(product -> {
                        this.purchaseHistory.add(product);
                        listOfProduct.add(product);
                        FileService.writeHistoryInFile(pathToHistory, product);
                    });

        } catch (final Exception e) {
            System.out.println(e);
        }
    }

    public void editProduct(final Product product, final double newPrice) {
        this.availableProducts.stream()
                .filter(p -> p.equals(product))
                .forEach(p -> p.setPrice(newPrice));
    }

    public void printProductsInSuperMarket() {
        this.availableProducts.forEach(System.out::println);
    }

    public List<Product> filterAndSortProductsByPrice(final double minPrice, final double maxPrice) {
        return this.availableProducts.stream()
                .filter(product -> product.getPrice() >= minPrice && product.getPrice() <= maxPrice)
                .sorted((product1, product2) -> Double.compare(product1.getPrice(), product2.getPrice()))
                .collect(Collectors.toList());
    }

    public double calculateAveragePrice() {
        return this.availableProducts.stream()
                .mapToDouble(Product::getPrice)
                .average()
                .orElse(0.0);
    }

    public Product popularPrduct() {
        return this.purchaseHistory.stream()
                .collect(Collectors.toMap(
                        product -> product,
                        product -> 1,
                        Integer::sum
                ))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
    }

    public double findMaxDailyIncome(final LocalDate date) {
        return this.purchaseHistory.stream()
                .filter(product -> product.getDate().isEqual(date))
                .collect(Collectors.groupingBy(
                        Product::getDate,
                        Collectors.summingDouble(Product::getPrice)
                ))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getValue)
                .orElse(0.0);
    }

}
