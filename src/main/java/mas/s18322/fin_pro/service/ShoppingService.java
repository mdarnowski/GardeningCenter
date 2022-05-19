package mas.s18322.fin_pro.service;

import lombok.RequiredArgsConstructor;
import mas.s18322.fin_pro.model.product.Shopping;
import mas.s18322.fin_pro.model.product.Transaction;
import mas.s18322.fin_pro.repository.ShoppRepository;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ShoppingService {
    private final ShoppRepository shoppRepository;

    public List<Shopping> getShoppingByTransaction(Transaction transaction){
        Iterable<Shopping> all = shoppRepository.findAllByTransId(transaction.getId());
        List<Shopping> res = new ArrayList<>();
        all.forEach(res::add);
        return res;
    }
}
