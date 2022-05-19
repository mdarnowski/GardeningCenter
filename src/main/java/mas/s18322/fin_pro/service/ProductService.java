package mas.s18322.fin_pro.service;

import lombok.RequiredArgsConstructor;
import mas.s18322.fin_pro.model.product.Product;
import mas.s18322.fin_pro.model.product.Transaction;
import mas.s18322.fin_pro.repository.ProductRepository;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;


    public List<Product> getAllProducts(){
        Iterable<Product> all = productRepository.findAll();
        List<Product> res = new ArrayList<>();
        all.forEach(res::add);
        return res;
    }

    public List<Product> getAllProductsByName(String name){
        Iterable<Product> all = productRepository.findAllByNameStartingWith(name);
        List<Product> res = new ArrayList<>();
        all.forEach(res::add);
        return res;
    }

    public List<Product> getProductById(long id){
        List<Product> res = new ArrayList<>();
        Optional<Product> cus = productRepository.findById(id);
        cus.ifPresent(res::add);
        return res;
    }

    public List<Product> getByTransaction(Transaction transaction){
        Iterable<Product> all = new ArrayList<>();
        List<Product> pro = productRepository.findAllByTransaction(transaction.getId());
        all.forEach(pro::add);
        return pro;
    }

}
