package quiz.posMachine;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import jdk.nashorn.internal.runtime.arrays.ArrayLikeIterator;

import java.io.IOException;
import java.io.Reader;
import java.util.*;
import java.util.stream.Collectors;

public class PosMachine {

    private static final String separator = "------------------------------------------------------------";
    private final String line = System.lineSeparator();
    private final ObjectMapper objectMapper = new ObjectMapper();
    private Product[] products;

    // Note: much blank space in test each line can be output by this way
    // String productLine = String.format(
    //                "%-32s%-11s%s",
    //                " ",
    //                " ",
    //                " ");

    public void readDataSource(Reader reader) throws IOException {
        // TODO: please implement the following method to pass the test
        // <--start

//        JsonFactory jsonFactory = new JsonFactory();
//        MappingIterator<Product[]> mappingIterator = objectMapper.readValues(jsonFactory.createParser(reader), Product[].class);
//        if (mappingIterator.hasNext()) {
//            products = Arrays.asList(mappingIterator.next());
//        }
        this.products = objectMapper.readValue(reader, Product[].class);
        // --end-->
    }

    public String printReceipt(String barcodeContent) throws IOException {
        // TODO: please implement the following method to pass the test
        // <--start
        if (products == null) {
            throw new IllegalStateException();
        }
        StringBuilder result = new StringBuilder();
        Integer totalPrice = 0;

        result.append("Receipts" + line + separator + line);

        if (barcodeContent == null || barcodeContent.equals("[]")) {
            result.append(separator + line + "Price: 0" + line);
            return result.toString();
        }

        //string 转json
        String[] ids = objectMapper.readValue(barcodeContent, String[].class);

        Map<String, Integer> idToCount = new LinkedHashMap<>();
        for (String id : ids) {
            Integer count = idToCount.getOrDefault(id, 0) + 1;
            idToCount.put(id, count);
        }
//stream
        for (String id : idToCount.keySet()) {
            for (Product product : products) {
                if (id.equals(product.getId())) {
                    Integer count = idToCount.get(id);
                    result.append(String.format("%-32s%-11s%s%s", product.getName(), product.getPrice().toString(), count.toString(), line));
                    totalPrice += product.getPrice() * count;
                }
            }
        }
        result.append(String.format(separator + line + "Price: %d" + line, totalPrice));
        return result.toString();
        // --end-->
    }
}

class Product {

    private String id;
    private String name;
    private Integer price;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }

        Product other = (Product) obj;

        return Objects.equals(id, other.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}