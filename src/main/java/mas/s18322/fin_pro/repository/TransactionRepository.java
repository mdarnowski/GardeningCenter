package mas.s18322.fin_pro.repository;
import mas.s18322.fin_pro.model.customers.Customer;
import mas.s18322.fin_pro.model.product.Transaction;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface TransactionRepository extends CrudRepository<Transaction, Long> {
    /***
     * Selects transactions of specified customer
     * @param customer customer with transactions
     * @return list of transactions concluded by a customer
     */
    @Query("select t from Transaction t join fetch t.registers where t.concludes = :customer")
    List<Transaction> findTransactionByConcludes(@Param("customer") Customer customer);
}