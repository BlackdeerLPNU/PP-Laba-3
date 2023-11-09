import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.Collectors;

public class FileService {

/*    public static ArrayList<Product> availableProductsReadFile(String availableProductsPathFile) {
        ArrayList<Product> products = new ArrayList<>();
        final int numberofFieldProduct = 3;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        try (BufferedReader reader = new BufferedReader(new FileReader(availableProductsPathFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");
                if (parts.length == numberofFieldProduct) {
                    String name = parts[0];
                    TypeOfProduct typeOfProduct = TypeOfProduct.valueOf(parts[1]);
                    double price = Double.parseDouble(parts[2]);
                    products.add(new Product(name, typeOfProduct, price));
                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

        return products;
    }*/
    public static ArrayList<Product> availableProductsReadFile(final String availableProductsPathFile) {
        try {
            return Files.lines(Paths.get(availableProductsPathFile))
                    .map(line -> {
                        final String[] parts = line.split(" ");
                        if (parts.length == 3) {
                            final String name = parts[0];
                            final TypeOfProduct typeOfProduct = TypeOfProduct.valueOf(parts[1]);
                            final double price = Double.parseDouble(parts[2]);
                            return new Product(name, typeOfProduct, price);
                        } else {
                            return null;
                        }
                    })
                    .filter(Objects::nonNull)
                    .collect(Collectors.toCollection(ArrayList::new));
        } catch (final IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }


    public static void writeHistoryInFile(final String histiryPath, final Product product) {

        try (final BufferedWriter writter = new BufferedWriter(new FileWriter(histiryPath, true))) {
            writter.write(product + "\n");
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    public static void generateOrderReceipt(final String check, final String receiptPath) {
        try {
            final File file = new File(receiptPath);
            if (!file.exists()) {
                file.createNewFile();
            }

            try (final BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
              writer.write(check);
            }
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

}
