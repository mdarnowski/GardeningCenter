package mas.s18322.fin_pro.gui.view.cellRenderer;

public interface IStringFun<Class>  {
    /***
     * This method selects content to be displayed from selected Class,
     * it should be called inside BlueListCellRender
     * @param value Class
     * @return specified content to be displayed
     */
    String get(Class value);
}
