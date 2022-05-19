package mas.s18322.fin_pro.gui.controllers;

import lombok.RequiredArgsConstructor;
import mas.s18322.fin_pro.gui.view.SalePageView;
import mas.s18322.fin_pro.model.customers.Customer;
import mas.s18322.fin_pro.model.emps.Employee;
import mas.s18322.fin_pro.model.product.Product;
import mas.s18322.fin_pro.model.product.Shopping;
import mas.s18322.fin_pro.model.product.Transaction;
import mas.s18322.fin_pro.repository.ShoppRepository;
import mas.s18322.fin_pro.repository.TransactionRepository;
import mas.s18322.fin_pro.service.ProductService;
import org.springframework.stereotype.Controller;
import javax.annotation.PostConstruct;
import javax.swing.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequiredArgsConstructor
public class SalePageController {
    private final SalePageView view;
    private final ProductService productService;
    private Set<Shopping> shopping;
    private Transaction transaction;
    private final TransactionRepository transactionRepository;
    private final ShoppRepository shoppRepository;
    private List<Product> lastProducts;
    private Customer customer;
    private MainWindowController mwc;
    private Employee employee;
    private double bill = 0.00;


    /***
     * Updates products list from the database, displays sales page on main window
     * @param mainWindowController main controller
     */
    public void showGUI(MainWindowController mainWindowController) {
        mwc = mainWindowController;
        mwc.showView(view.getSalesPanel());
        view.getPrice().setText(String.format("%.2f", bill)+ " zł");
        view.getA1TextField().setText("1");
    }

    /***
     * Set customer and employee that are participating in transaction,
     * Updates product list from database
     * @param customer Customer registering transaction
     * @param employee Employee concluding transaction
     */
    public void setCustomerAndEmployee(Customer customer, Employee employee){
        if(customer!=this.customer) {
            updateProductsFromDB();
            mwc.showView(view.getSalesPanel());
            this.customer = customer;
        }
        this.employee = employee;
    }

    /***
     * Specify user input.
     * Update product list by id or product name
     * or update all products.
     */
    private void searchAction(){
        String text = view.getSearchProduct().getText();
        if (text.isEmpty()) {
            updateProductsFromDB();
            mwc.showView(view.getSalesPanel());
        }
        try {
            Long searched = Long.valueOf(text);
            int size = updateProductsFromDB(searched);
            mwc.showView(view.getSalesPanel());
            if(size==0) {
                mwc.massage("No entry found :(");
            }
        }catch (Exception l){
            int size = updateProductsFromDB(text);
            mwc.showView(view.getSalesPanel());
            if(size==0) {
                mwc.massage("No entry found :(");
            }
        }
        view.getSearchProduct().setText("");
    }

    /***
     * Add action listeners to buttons
     */
    @PostConstruct
    private void setListeners(){
        // searching customer using 'enter'
        view.getSearchProduct().addActionListener(e -> {
            searchAction();
        });

        // searching customers using button
        view.getSearchButton().addActionListener(e -> {
            searchAction();
        });

        // get products from old shopping bag button
        view.getViewHistoryButton().addActionListener(e-> {
            mwc.showTransactionsHistory(customer, true);
        });

        // add button
        view.getAddProductButton().addActionListener(e-> {
            if(view.getList2().isSelectionEmpty()){
                mwc.massage("Select a product ;)");
            } else{
                int amount;
                try {
                    amount = Integer.parseInt(view.getA1TextField().getText());
                    if (amount < 1) {
                        view.getA1TextField().setText("1");
                        mwc.massage("Amount cannot be zero or less ;)");
                        return;
                    }
                } catch (NumberFormatException exc) {
                    mwc.massage("I need an Integer ;)");
                    view.getA1TextField().setText("1");
                    return;
                }

                bill += amount * view.getSelected().getPrice();

                if (shopping == null) {
                    shopping = new HashSet<>();
                    transaction = Transaction.builder()
                            .concludes(customer)
                            .registers(employee)
                            .shoppingBags(shopping)
                            .date(LocalDate.now())
                            .build();
                }

                boolean alreadyEx = false;

                for (Shopping s : shopping
                ) {
                    if (s.getBought().getId().equals(view.getSelected().getId())) {
                        s.setAmount(s.getAmount() + amount);
                        alreadyEx = true;
                        break;
                    }
                }
                if (!alreadyEx) {
                    shopping.add(Shopping.builder()
                            .amount(amount).bought(view.getSelected()).
                                    trans(transaction).build());
                }

                view.getPrice().setText(String.format("%.2f", bill) + " zł");
                updateByShoppingList(new ArrayList<>(shopping));
                updateByList(lastProducts);
                view.getA1TextField().setText("1");
            }
        });

        // finalize transaction button
        view.getFinalizeTransactionButton().addActionListener(e -> {
            if(bill==0) {
                mwc.massage("No products!");
                return;
            }
            transactionRepository.save(transaction);
            shoppRepository.saveAll(shopping);
            bill=0;
            shopping.clear();
            shopping = null;
            transaction = null;
            view.getPrice().setText(String.format("%.2f", bill)+ " zł");
            updateByShoppingList(new ArrayList<>());
            mwc.massage("Transaction finalized!");
        });

        // back to customer list
        view.getBackButton().addActionListener(e -> {
            bill=0;
            shopping = null;
            transaction = null;
            updateByShoppingList(new ArrayList<>());
            mwc.showCustomerList();
        });

        // remove shopping from transaction
        view.getRemoveSelectedButton().addActionListener(e -> {
            if(view.getList1().isSelectionEmpty())
               return;
            Shopping red = view.getSelectedRed();
            bill -= red.getAmount()*red.getBought().getPrice();
            view.getPrice().setText(String.format("%.2f",bill) + " zł");
            shopping.removeIf(s -> s.getBought().equals(view.getSelectedRed().getBought()));

            updateByShoppingList(new ArrayList<>(shopping));
            mwc.showView(view.getSalesPanel());
        });

        // subtract one from amount
        view.getMinusButton().addActionListener(e -> {
            int amount;
            try {
                amount = Integer.parseInt(view.getA1TextField().getText());
                if(amount<2) {
                    view.getA1TextField().setText("1");
                    mwc.massage("Amount cannot be zero or less ;)");
                    return;
                }
            }catch (NumberFormatException exc) {
                view.getA1TextField().setText("1");
                return;
            }
            amount -=1;
            view.getA1TextField().setText(String.valueOf(amount));
        });

        // add one to amount
        view.getPlusButton().addActionListener(e -> {
            int amount;
            try {
                amount = Integer.parseInt(view.getA1TextField().getText());
                if(amount<1) {
                    view.getA1TextField().setText("1");
                    return;
                }
            }catch (NumberFormatException exc) {
                view.getA1TextField().setText("1");
                return;
            }

            amount +=1;
            view.getA1TextField().setText(String.valueOf(amount));
        });
    }

    /***
     * Updates all products from the database
     */
    private int updateProductsFromDB(){
        List<Product> products = productService.getAllProducts();
        return updateByList(products);
    }

    /***
     * Updates products from database by product id
     * @param id products to display
     */
    private int updateProductsFromDB(long id){
        List<Product> products = productService.getProductById(id);
        return updateByList(products);
    }

    /***
     * Updates products from database by product name
     * @param name products to display
     */
    private int updateProductsFromDB(String name){
        List<Product> products = productService.getAllProductsByName(name);
        return updateByList(products);
    }

    /***
     * Updates product scrollbar by list of products
     * @param products products to display
     * @return size of updated list
     */
    public int updateByList(List<Product> products){
        DefaultListModel<Product> model = (DefaultListModel<Product>) view.getList2().getModel();
        model.removeAllElements();
        lastProducts = products;
        products.forEach(model :: addElement);
        return products.size();
    }

    /***
     * Updates shopping scrollbar by list of shopping
     * @param shopping products to display
     */
    private void updateByShoppingList(List<Shopping> shopping){
        DefaultListModel<Shopping> model = (DefaultListModel<Shopping>) view.getList1().getModel();
        model.removeAllElements();
        shopping.forEach(model :: addElement);
    }


}
