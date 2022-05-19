package mas.s18322.fin_pro.repository;
import mas.s18322.fin_pro.model.product.Shopping;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface ShoppRepository extends CrudRepository<Shopping, Long> {
    /***
     * Selects all shopping with products details within specified transaction
     * @param transaction_id specified transaction
     * @return list of all shopping bags from specified transaction
     */
    @Query("select shop from Shopping shop left join fetch shop.bought Product where shop.trans.id = :transaction_id")
    List<Shopping> findAllByTransId(@Param("transaction_id") long transaction_id);
}
