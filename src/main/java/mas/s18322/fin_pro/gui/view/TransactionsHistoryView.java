package mas.s18322.fin_pro.gui.view;

import javax.swing.*;
import lombok.Data;
import mas.s18322.fin_pro.gui.view.cellRenderer.BlueListCellRenderer;
import mas.s18322.fin_pro.model.product.Shopping;
import mas.s18322.fin_pro.model.product.Transaction;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;

@Component
@Data
public class TransactionsHistoryView {
    private JList transactionsList;
    private JPanel panel1;
    private JButton addProductsToSaleButton;
    private JButton backButton;
    private JTextField transactionHistoryTextField;
    private JList shoppingList;
    private JButton viewShoppingBagButton;
    private JLabel customerInfo;
    private BlueListCellRenderer<Transaction> blr;
    private BlueListCellRenderer<Shopping> blrShop;

    /***
     * Crates two new list cell renderers. Transaction list and Shopping list.
     * Selects fields to be printed out:
     * Transaction: id, employee_id, date.
     * Shopping: product_name, product_type, amount.
     */
    @PostConstruct
    private void createUIComponents(){
        blr = new BlueListCellRenderer<>(
                transaction -> "empName: " + transaction.getRegisters().getName() + ", empSur: " +
                        transaction.getRegisters().getSurname() + ", date: " + transaction.getDate());
        transactionsList.setCellRenderer(blr);
        transactionsList.setModel(new DefaultListModel());

        blrShop = new BlueListCellRenderer<>(
                s -> "prod: " + s.getBought().getName() + ", " +
                        s.getBought().getType().toString().toLowerCase() + "; amount: " +
                        s.getAmount() + "; subtotal: " + s.getBought().getPrice()*s.getAmount() + " zł");
        shoppingList.setCellRenderer(blrShop);
        shoppingList.setModel(new DefaultListModel());
        blrShop.setSelectingFalse();
    }

    /***
     * Returns Transaction object selected by the user.
     * @return selected Transaction
     */
    public Transaction getSelected() {
        return blr.getSelected();
    }
}
