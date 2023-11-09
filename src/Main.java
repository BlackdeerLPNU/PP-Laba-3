import java.security.Provider;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(final String[] args) {
        final String pathToAvailableProducts = "C:\\VOVA\\JAVA\\laba3\\other\\availableProducts.txt";
        final String pathToHistory = "C:\\VOVA\\JAVA\\laba3\\other\\history.txt";

        final SuperMarket rukavychka = new SuperMarket(pathToAvailableProducts, pathToHistory);


        final Order vova_order = new Order("Vova");
        rukavychka.sellProduct("Veal", TypeOfProduct.MEAT, vova_order.getListOfProduct());
        rukavychka.sellProduct("Veal", TypeOfProduct.MEAT, vova_order.getListOfProduct());
        rukavychka.sellProduct("Chicken", TypeOfProduct.MEAT, vova_order.getListOfProduct());
        rukavychka.sellProduct("Chicken", TypeOfProduct.MEAT, vova_order.getListOfProduct());
        rukavychka.sellProduct("Mackerel", TypeOfProduct.FISH, vova_order.getListOfProduct());
        rukavychka.sellProduct("Mackerel", TypeOfProduct.FISH, vova_order.getListOfProduct());
        rukavychka.sellProduct("Mackerel", TypeOfProduct.FISH, vova_order.getListOfProduct());
        rukavychka.sellProduct("Oranges", TypeOfProduct.FRUITS, vova_order.getListOfProduct());
        rukavychka.sellProduct("Coffee", TypeOfProduct.DRINKS, vova_order.getListOfProduct());
        rukavychka.sellProduct("Tomatoes", TypeOfProduct.VEGETABLES, vova_order.getListOfProduct());
        rukavychka.sellProduct("Tomatoes", TypeOfProduct.VEGETABLES, vova_order.getListOfProduct());


        final Order dmytro_order = new Order("Dmytro");
        rukavychka.sellProduct("Veal", TypeOfProduct.MEAT, dmytro_order.getListOfProduct());
        rukavychka.sellProduct("Mackerel", TypeOfProduct.FISH, dmytro_order.getListOfProduct());
        rukavychka.sellProduct("Mackerel", TypeOfProduct.FISH, dmytro_order.getListOfProduct());
        rukavychka.sellProduct("Mackerel", TypeOfProduct.FISH, dmytro_order.getListOfProduct());
        rukavychka.sellProduct("Oranges", TypeOfProduct.FRUITS, dmytro_order.getListOfProduct());
        rukavychka.sellProduct("Tomatoes", TypeOfProduct.VEGETABLES, dmytro_order.getListOfProduct());

        vova_order.generateOrderReceipt();
        System.out.println("General expenses : " + vova_order.allExpenses(LocalDate.of(2023, 11, 1)
                , LocalDate.of(2023, 11, 10)));
        final Map<Product, Integer> purchaseSummary = vova_order.getPurchaseSummary();
        purchaseSummary.entrySet().stream()
                .forEach(entry -> System.out.println(entry.getKey() + " " + entry.getValue()));

        System.out.println("----------------------------");
        rukavychka.printProductsInSuperMarket();
        System.out.println("----------------------------");

        final List<Product> filteredList = rukavychka.filterAndSortProductsByPrice(4, 20);
        System.out.println("Sorted");
        filteredList.forEach(System.out::println);

        final double averagePrice = rukavychka.calculateAveragePrice();
        System.out.println("Average price : " + averagePrice);

        System.out.println("The most popular product - " + rukavychka.popularPrduct());
        System.out.println( rukavychka.findMaxDailyIncome(LocalDate.of(2023, 11, 8)));


    }

}
