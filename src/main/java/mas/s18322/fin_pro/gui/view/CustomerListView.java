package mas.s18322.fin_pro.gui.view;

import lombok.Data;
import mas.s18322.fin_pro.gui.view.cellRenderer.BlueListCellRenderer;
import mas.s18322.fin_pro.model.customers.Customer;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import javax.swing.*;

@Component
@Data
public class CustomerListView {
    private JPanel customerPanel;
    private JList customersList;
    private JTextField searchCustomerByIdTextField;
    private JButton searchButton;
    private JButton addTransactionButton;
    private JButton transactionHistoryButton;
    private JTextField customerListTextField;
    private Customer selected;
    private BlueListCellRenderer<Customer> blr;

    /***
     * Crates a new list cell renderer. Selects fields to be printed out: id, name, surname.
     */
    @PostConstruct
    private void createUIComponents(){
        blr = new BlueListCellRenderer<>(
                c -> "id:" + c.getId() + " " + c.getName() + " " + c.getSurname());
        customersList.setCellRenderer(blr);
        customersList.setModel(new DefaultListModel());
    }

    /***
     * Returns Customer object selected by the user.
     * @return selected Customer
     */
    public Customer getSelected() {
        return blr.getSelected();
    }
}
