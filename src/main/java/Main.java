import actions.Database;
import actions.ProductFilter;
import base.Category;
import base.Product;

public class Main{
    public static void main(String[] args) {
        Database db = Database.getInstance();
        db.deleteAllCategories();
        db.deleteAllProducts();

        Category category1 = new Category("1 Category Title", "This is 1 Title category description");
        Category category2 = new Category("2 Category Title", "This is 2 Title category description");
        Category category3 = new Category("3 Category Title", "This is 3 Title category description");
        Category category4 = new Category("4 Category Title", "This is 4 Title category description");
        Category category5 = new Category("5 Category Title", "This is 5 Title category description");


        db.insertCategory(category1);
        db.insertCategory(category2);
        db.insertCategory(category3);
        db.insertCategory(category4);
        db.insertCategory(category5);

       // System.out.println(db.getCategory(category1.getTitle()));
        System.out.println(db.getCategoryList(0, 10));

        System.out.println("\n\n--------Changing the description of thr first element--------\n\n");

        db.updateCategory("description", "This is the new description", "title", category1.getTitle());
        System.out.println(db.getCategoryList(0, 10));

        System.out.println("\n\n--------Deleting the first category--------\n\n");
        db.deleteCategory(category1.getTitle());
        System.out.println(db.getCategoryList(0, 10));

        System.out.println("\n\n--------Deleting all categories and inserting a new one --------");
        db.deleteAllCategories();
        System.out.println(db.getCategoryList(0, 10) + "\n");

        db.insertCategory(category1);
        Product product = new Product("This is the product title", "This is the product description", "productProducer",
                5, 10, category1.getTitle());

        System.out.println("\n\n--------Adding a product--------\n");
        db.insertProduct(product);
        System.out.println(db.getProduct(product.getTitle()));
        System.out.println(db.getProductList(0, 10, new ProductFilter()));

        System.out.println("\n\n--------Changing the price of the product--------\n");
        db.updateProduct("price", "10000", "title", product.getTitle());
        System.out.println(db.getProduct(product.getTitle()));

        db.deleteProduct(product.getTitle());
        System.out.println(db.getProduct(product.getTitle()));

        db.deleteAllCategories();
        db.deleteAllProducts();
    }
}
