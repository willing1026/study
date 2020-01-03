package lec08;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.joining;


public class StreamEx4 {
    public static void main(String[] args) {
        List<Product> products = Arrays.asList(
                new Product(1L, "A", new BigDecimal("100.00")),
                new Product(2L, "B", new BigDecimal("23.00")),
                new Product(3L, "C", new BigDecimal("31.45"))
        );

        BigDecimal zero = new BigDecimal(30);

        System.out.println(
                products.stream()
                        .filter(product -> product.getPrice().compareTo(zero) >= 0)
                        .map(product -> product.toString())
                        .collect(joining("\n"))
        );

        //reduce example - element를 줄여나감
        products.stream()
                .map(p -> p.getPrice())
                .reduce(BigDecimal.ZERO, (p1, p2) -> p1.add(p2));

        System.out.println(
                products.stream()
                        .filter(product -> product.getPrice().compareTo(zero) >= 0)
                        .map(product -> product.getPrice())
//                        .reduce(BigDecimal.ZERO, (p1, p2) -> p1.add(p2))
                        .reduce(BigDecimal.ZERO, BigDecimal::add)
        );

        //개수 구하기
        System.out.println(
                products.stream()
                        .filter(product -> product.getPrice().compareTo(zero) >= 0)
                        .count()
        );

    }
}


class Product {
    private Long id;
    private String name;
    private BigDecimal price;

    public Product(Long id, String name, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}