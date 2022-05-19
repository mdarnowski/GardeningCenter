package mas.s18322.fin_pro.repository;
import mas.s18322.fin_pro.model.product.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Long> {

    /***
     * Select products with name starting with input parameter
     * @param name the starting characters of name
     * @return list of products with name starting with 'name'
     */
    List<Product> findAllByNameStartingWith(String name);

    /***
     * Select products within specified transaction
     * @param transaction_id id of transaction
     * @return list of products inside specified transaction
     */
    @Query("select shop.bought from Shopping shop where shop.trans.id = :transaction_id")
    List<Product> findAllByTransaction(@Param("transaction_id") long transaction_id);
}
