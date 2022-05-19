package mas.s18322.fin_pro.gui.controllers;

import lombok.RequiredArgsConstructor;
import mas.s18322.fin_pro.gui.view.MainWindowView;
import mas.s18322.fin_pro.model.customers.Customer;
import mas.s18322.fin_pro.model.emps.Employee;
import mas.s18322.fin_pro.model.product.Product;
import mas.s18322.fin_pro.service.EmployeeService;
import org.springframework.stereotype.Controller;
import javax.swing.*;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainWindowController {
    private final MainWindowView view;
    private final CustomersListController customersListController;
    private final SalePageController salesWindowController;
    private final TransactionsHistoryController transactionsHistoryController;
    private final EmployeeService employeeService;
    private Employee terminalUser;

    /***
     * Show graphical user interface for predefined employee
     */
    public void showGUI(){
        view.setVisible(true);
        //predefined
        //for the purpose of this gui
        terminalUser = employeeService.getById(0);
        showCustomerList();
    }

    /***
     * Display Customer list page.
     */
    public void showCustomerList(){
        customersListController.showGUI(this);
    }

    /***
     * Display sale page for specified customer.
     * @param customer customer buying products
     */
    public void showSalePage(Customer customer){
        salesWindowController.showGUI(this);
        salesWindowController.setCustomerAndEmployee(customer, terminalUser);
    }

    /***
     * Display sale page for specified customer and update Scrollbar by list of products.
     * @param customer customer buying products
     * @param productList products to be updated
     */
    public void showSalePage(Customer customer, List<Product> productList){
        salesWindowController.showGUI(this);
        salesWindowController.setCustomerAndEmployee(customer, terminalUser);
        salesWindowController.updateByList(productList);
    }

    /***
     * Display transaction history for specified customer
     * @param customer specified customer
     * @param backToSalePage if true 'Transaction history' page navigates user back to 'Sale page'
     *                      else navigates user to 'Customer list page'
     */
    public void showTransactionsHistory(Customer customer, boolean backToSalePage){
        transactionsHistoryController.setBackToSalePage(backToSalePage);
        transactionsHistoryController.showGUI(this);
        transactionsHistoryController.setCustomer(customer);
    }

    /***
     * Open message dialog with specified content
     * @param massage content of the message
     */
    public void massage(String massage){
        view.msgBox(massage);
    }

    /***
     * Display specified view. Revalidate and repaint JFrame
     * @param viewToShow JPanel to display
     */
    public void showView(JPanel viewToShow){
        view.getContentPane().removeAll();
        view.getContentPane().add((viewToShow));
        view.revalidate();
        view.repaint();
    }
}
