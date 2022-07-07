
import actions.ProductFilter;
import actions.Database;
import base.Category;
import base.Product;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class Tests {
    static Database db;

    @BeforeAll
    static void init() {
        db = Database.getInstance("testDatabase.db");
    }

    @Test
    void test1() {
        Category category = new Category("Title", "Description");
        db.insertCategory(category);
        System.out.println(db.getCategory(category.getTitle()));
        assertEquals(("{\"title\" : \"Title\",\"description\" : \"Description\"}"+"\n"), db.getCategory(category.getTitle()).toString());
    }

    @Test
    void test2() {
        db.insertCategory(new Category("Animals", "some animals"));
        db.insertProduct(new Product("dog", "this is a dog", ":)", 3, 10, "Animal"));
        assertEquals("dog", db.getProduct("dog").getTitle());
    }


    @Test
    void test3() {
        db.insertCategory(new Category("Delete", "description"));
        db.deleteCategory("Delete");
        assertNull(db.getCategory("Delete"));
    }


    @Test
    void test4() {
        db.insertProduct(new Product("cat", "this is a cat", ":)", 100, 10, "Animal"));
        db.updateProduct("price", "30", "title", "cat");
        assertEquals(30, db.getProduct("cat").getPrice());
    }

    @Test
    void test5() {
        db.insertCategory(new Category("Clothes", "This is clothes"));
        for (int i = 1; i < 8; i++) {
            db.insertProduct(new Product("product" + i, "test product", "producer", i * 0.5 + i, i * i, "Clothes"));
        }
        db.getProductList(0, 10, new ProductFilter()).forEach(System.out::println);

        ProductFilter filter = new ProductFilter();
        filter.setFromPrice(4.0);
        filter.setToPrice(10.0);
        filter.setFromQuantity(10);
        filter.setToQuantity(35);
        filter.setQuery("product");

        String expected[] = {"product4", "product5"};

        List<Product> productList = db.getProductList(0, 10, filter);
        for (int i = 0; i < expected.length; i++)
            assertEquals(expected[i], productList.get(i).getTitle());
    }

    @Test
    void test6() {
        db.insertProduct(new Product("ToDelete0", "test product", "producer", 10, 10, "No"));
        db.insertProduct(new Product("ToDelete1", "test product", "producer", 10, 10, "No"));
        db.deleteProduct("ToDelete0");
        db.deleteProduct("ToDelete1");
        assertNull(db.getProduct("ToDelete0"));
        assertNull(db.getProduct("ToDelete1"));
    }

    @AfterAll
    static void test7() {
        db.deleteAllCategories();
        assertEquals("[]", db.getCategoryList(0, 10).toString());
        db.deleteAllProducts();
        assertEquals("[]", db.getProductList(0, 10, new ProductFilter()).toString());
    }

}
