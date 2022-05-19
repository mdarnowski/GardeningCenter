package mas.s18322.fin_pro.gui.controllers;

import lombok.RequiredArgsConstructor;
import mas.s18322.fin_pro.gui.view.CustomerListView;
import mas.s18322.fin_pro.model.customers.Customer;
import mas.s18322.fin_pro.service.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import javax.swing.*;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class CustomersListController {
    private final CustomerListView view;
    private MainWindowController mwc;
    private final CustomerService customerService;

    /***
     * Updates customer list from the database, displays customer list view on main window
     * @param mainWindowController main controller
     */
    public void showGUI(MainWindowController mainWindowController){
        this.mwc = mainWindowController;
        updateCustomersFromDB();
        mainWindowController.showView(view.getCustomerPanel());
    }

    /***
     * Checks user input. Updates the database.
     */
    private void searchAction(){
        String text = view.getSearchCustomerByIdTextField().getText();

        if (text.isEmpty()) {
            updateCustomersFromDB();
            mwc.showView(view.getCustomerPanel());
        }

        try {
            long searched = Long.parseLong(text);
            int size = updateCustomersFromDB(searched);
            mwc.showView(view.getCustomerPanel());
            if(size==0) {
                mwc.massage("No entry found :(");
            }
        }catch (Exception e){
            String searched = StringUtils.capitalize(text);

            int size = updateCustomersFromDB(searched);
            mwc.showView(view.getCustomerPanel());
            if(size==0) {
                mwc.massage("No entry found :(");
            }
        }
        view.getSearchCustomerByIdTextField().setText("");
    }

    /***
     * Add action listeners to buttons
     */
    @PostConstruct
    public void setListeners(){
        view.getSearchCustomerByIdTextField().addActionListener(e-> {
            searchAction();
        });

        view.getSearchButton().addActionListener(e -> {
            searchAction();
        });

        view.getAddTransactionButton().addActionListener(e-> {
            if(view.getCustomersList().isSelectionEmpty()) {
                mwc.massage("Select a customer");
            }else {
                mwc.showSalePage(view.getSelected());
            }
        });

        view.getTransactionHistoryButton().addActionListener(e-> {
            if(!view.getCustomersList().isSelectionEmpty()) {
                mwc.showTransactionsHistory(view.getSelected(), false);
            }else {
                mwc.massage("Select a customer");
            }
        });
    }

    /***
     * Updates all customers from the database
     * @return number of customers in the database
     */
    public int updateCustomersFromDB(){
        List<Customer> customers = customerService.getAllCustomers();
        DefaultListModel<Customer> model = (DefaultListModel<Customer>) view.getCustomersList().getModel();
        model.removeAllElements();
        customers.forEach(model :: addElement);
        return customers.size();
    }

    /***
     * Updates customers from the database by id
     * @param id products id to display
     * @return number of customers in the database with specified id
     */
    private int updateCustomersFromDB(long id){
        List<Customer> customers = customerService.getListById(id);
        DefaultListModel<Customer> model = (DefaultListModel<Customer>) view.getCustomersList().getModel();
        model.removeAllElements();
        customers.forEach(model :: addElement);
        return customers.size();
    }

    /***
     * Updates customers from the database by surname
     * @param surname products to display
     * @return number of customers in the database with surname starting with 'surname'
     */
    private int updateCustomersFromDB(String surname){
        List<Customer> customers = customerService.getAllCustomersBySurname(surname);
        DefaultListModel<Customer> model = (DefaultListModel<Customer>) view.getCustomersList().getModel();
        model.removeAllElements();
        customers.forEach(model :: addElement);
        return customers.size();
    }
}
