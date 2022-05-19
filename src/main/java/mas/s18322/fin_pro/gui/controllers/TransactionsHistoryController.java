package mas.s18322.fin_pro.gui.controllers;

import lombok.RequiredArgsConstructor;
import mas.s18322.fin_pro.gui.view.TransactionsHistoryView;
import mas.s18322.fin_pro.model.customers.Customer;
import mas.s18322.fin_pro.model.product.Shopping;
import mas.s18322.fin_pro.model.product.Transaction;
import mas.s18322.fin_pro.service.ProductService;
import mas.s18322.fin_pro.service.ShoppingService;
import mas.s18322.fin_pro.service.TransactionsService;
import org.springframework.stereotype.Controller;
import javax.annotation.PostConstruct;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class TransactionsHistoryController {
    private final TransactionsHistoryView view;
    private final TransactionsService transactionsService;
    private final ShoppingService shoppingService;
    private final ProductService productService;
    private boolean backToSalePage;
    private MainWindowController mwc;
    private Customer customer;
    private Transaction lastView;
    private List<Transaction> transactionsList;

    public void setBackToSalePage(boolean backToSalePage){
        this.backToSalePage = backToSalePage;
    }
    /***
     * Updates products list from database, displays sales page on main window
     * @param mainWindowController main controller
     */
    public void showGUI(MainWindowController mainWindowController){
        mwc = mainWindowController;
        mwc.showView(view.getPanel1());
        lastView = null;
    }

    /***
     * Update transactions of specified customer from database
     * @param customer specified customer
     * @return size of the list
     */
    private int updateTransactionsFromDB(Customer customer){
        List<Transaction> transactions = transactionsService.findByCustomer(customer);
        transactionsList = transactions;
        return updateTransactionsByList(transactions);
    }

    /***
     * Update all shopping connected to specified transaction
     * @param transaction specified transaction
     */
    private void updateShoppingFromDB(Transaction transaction){
        List<Shopping> shopping = shoppingService.getShoppingByTransaction(transaction);
        updateShoppingByList(shopping);
    }

    /***
     * Update JList with 'Transaction' objects by list of transactions
     * @param transactions list of transactions
     */
    private int updateTransactionsByList(List<Transaction> transactions){
        DefaultListModel<Transaction> model = (DefaultListModel<Transaction>) view.getTransactionsList().getModel();
        model.removeAllElements();
        transactions.forEach(model :: addElement);
        return transactions.size();
    }

    /***
     * Update JList with 'Shopping' objects by list of shopping
     * @param shopping list of transactions
     */
    private void updateShoppingByList(List<Shopping> shopping){
        DefaultListModel<Shopping> model = (DefaultListModel<Shopping>) view.getShoppingList().getModel();
        model.removeAllElements();
        shopping.forEach(model :: addElement);
    }

    /***
     * Sets customer to displays his transactions,
     * checks if there are transactions connected to this customer
     * @param customer specified customer
     */
    public void setCustomer(Customer customer){
        this.customer = customer;
        if(updateTransactionsFromDB(customer)==0){
            if(backToSalePage)
                mwc.showSalePage(customer);
            else
                mwc.showCustomerList();
            mwc.massage("No transactions for this customer");
        }
        updateShoppingByList(new ArrayList<>());
        view.getCustomerInfo().setText(customer.toString());
    }

    /***
     * Add action listeners to buttons
     */
    @PostConstruct
    private void setListeners(){
        // back button
        view.getBackButton().addActionListener((e -> {
            if(backToSalePage)
                mwc.showSalePage(customer);
            else
                mwc.showCustomerList();
        }));

        // add products to the sale page
        view.getAddProductsToSaleButton().addActionListener(e-> {
            if (lastView == null){
                mwc.massage("View shopping bag first");
            }else {
                mwc.showSalePage(customer, productService.getByTransaction(lastView));
            }
        });

        // view transaction
        view.getViewShoppingBagButton().addActionListener(e-> {
            if(view.getTransactionsList().isSelectionEmpty()){
                mwc.massage("Select transaction");
            }else {
                lastView = view.getSelected();
                updateShoppingFromDB(lastView);
                mwc.showView(view.getPanel1());
                updateTransactionsByList(transactionsList);
            }
        });
    }
}
