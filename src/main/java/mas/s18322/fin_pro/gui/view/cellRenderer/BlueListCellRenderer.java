package mas.s18322.fin_pro.gui.view.cellRenderer;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class BlueListCellRenderer<Class> extends JLabel implements ListCellRenderer<Class> {
    private Border border;
    private Class selected;
    private boolean selecting;

    Color customSelection = new Color(175, 240, 234);
    Color borderColor = new Color(157, 223, 241);

    Color   firstCell = new Color(238, 239, 245);
    Color   secondCell = new Color(231, 231, 245);

    /***
     * Return object corresponding to the specified Class
     * @return selected object
     */
    public Class getSelected(){
        return selected;
    }
    private final IStringFun<Class> iStringFun;

    /***
     * Class constructor specifying the method of printing out the list
     * @param iStringFun specified method of printing out the list
     */
    public BlueListCellRenderer(IStringFun<Class> iStringFun){
        this.iStringFun = iStringFun;
        setOpaque(true);
        border = BorderFactory.createLineBorder(borderColor,3, true);
        selecting = true;
    }

    /***
     * Set border of the list to the color red and selection color to light-yellow
     */
    public void setBorderRed()
    {
        border = BorderFactory.createLineBorder(Color.red,1);
        customSelection = new Color(227, 225, 159, 255);
    }

    /***
     * Disable selecting list
     */
    public void setSelectingFalse()
    {
        this.selecting = false;
    }

    /***
     * Returns a component that has been configured to display the specified value
     * @param list JList to paint
     * @param value value returned by list.getModel().getElementAt(index)
     * @param index cells index
     * @param isSelected true if the specified cell was selected
     * @param cellHasFocus true if the specified cell has the focus
     * @return a component whose paint() method will render the specified value
     */
    @Override
    public java.awt.Component getListCellRendererComponent(JList<? extends Class> list, Class value,
                                                           int index, boolean isSelected, boolean cellHasFocus) {
        if (index % 2 == 0) setBackground(firstCell);
        else setBackground(secondCell);
        setText(iStringFun.get(value));
        selected = value;
        if(selecting) {
            if (isSelected) {
                setForeground(list.getSelectionForeground());
                setBackground(customSelection);
            } else {
                setForeground(list.getForeground());
            }
            setFont(list.getFont());
            setEnabled(list.isEnabled());

            if (isSelected && cellHasFocus)
                setBorder(border);
            else
                setBorder(null);
        }
        return this;
    }
}