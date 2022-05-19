package mas.s18322.fin_pro.gui.view;

import lombok.Data;
import mas.s18322.fin_pro.gui.view.cellRenderer.BlueListCellRenderer;
import mas.s18322.fin_pro.model.product.Product;
import mas.s18322.fin_pro.model.product.Shopping;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import javax.swing.*;

@Component
@Data
public class SalePageView extends JFrame{
    private JPanel salesPanel;
    private JTextField searchProduct;
    private JButton searchButton;
    private JButton addProductButton;
    private JTextField a1TextField;
    private JButton finalizeTransactionButton;
    private JLabel Price;
    private JButton removeSelectedButton;
    private JButton plusButton;
    private JList list1;
    private JButton minusButton;
    private JTextField salePageTextField;
    private JTextField productsTextField;
    private JTextField shoppingBagTextField;
    private JList list2;
    private JButton backButton;
    private JButton viewHistoryButton;
    private BlueListCellRenderer<Product> blr;
    private BlueListCellRenderer<Shopping> red;

    /***
     * Crates two new list cell renderers. Product list and Shopping list.
     * Selects fields to be printed out:
     * Product: id, name, type, price.
     * Shopping: product_name, product_type, amount.
     */
    @PostConstruct
    private void createUIComponents(){
        blr = new BlueListCellRenderer<>(
                product -> "id:" + product.getId() + ", " +  product.getName() + ", " +
                        product.getType().toString().toLowerCase() + ", "
                        + product.getPrice() + " zł");
        list2.setCellRenderer(blr);
        list2.setModel(new DefaultListModel());

        red = new BlueListCellRenderer<>(
                s -> "prod: " + s.getBought().getName() + ", " +
                        s.getBought().getType().toString().toLowerCase() + "; amount: " +
                        s.getAmount() + "; subtotal: " + s.getBought().getPrice()*s.getAmount() + " zł");
        red.setBorderRed();
        list1.setCellRenderer(red);
        list1.setModel(new DefaultListModel());

    }

    /***
     * Returns Product object selected by the user.
     * @return selected Product
     */
    public Product getSelected() {
        return blr.getSelected();
    }

    /***
     * Returns shopping object selected by the user.
     * @return selected Shopping
     */
    public Shopping getSelectedRed() {
        return red.getSelected();
    }

}

