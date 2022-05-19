package mas.s18322.fin_pro;

import mas.s18322.fin_pro.gui.controllers.MainWindowController;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import javax.swing.*;

@SpringBootApplication
public class FinProApplication {
    /***
     * Builder for SpringApplication and ApplicationContext instances.
     * Invokes Main window controller.
     */
    public static void main(String[] args) {
        ConfigurableApplicationContext ctx;
        ctx = new SpringApplicationBuilder(FinProApplication.class).headless(false).run(args);
        SwingUtilities.invokeLater(() -> {
            ctx.getBean(MainWindowController.class).showGUI();
        });
    }
}
