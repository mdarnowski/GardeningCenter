package mas.s18322.fin_pro.service;

import lombok.RequiredArgsConstructor;
import mas.s18322.fin_pro.model.customers.Customer;
import mas.s18322.fin_pro.model.product.Transaction;
import mas.s18322.fin_pro.repository.TransactionRepository;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionsService {
    private final TransactionRepository transactionRepository;

    public List<Transaction> findByCustomer(Customer customer){
        Iterable<Transaction> all = transactionRepository.findTransactionByConcludes(customer);
        List<Transaction> res = new ArrayList<>();
        all.forEach(res::add);
        return res;
    }
}
