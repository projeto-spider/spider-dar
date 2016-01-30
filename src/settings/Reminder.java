package settings;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JOptionPane;
import model.Acessar;
import view.ViewLogin;
import view.ViewPrincipal;

/**
 *
 * @author Bleno Vale
 */
public class Reminder {

    private List<Acessar> useAcsess = new ArrayList<>();
    private final Facade facade = Facade.getInstance();
    private final ViewPrincipal viewPrincipal;
    private final Timer timer = new Timer();

    public Reminder(ViewPrincipal viewPrincipal) {
        int id = Integer.parseInt(KeepData.getData("Usuario.id"));
        useAcsess = facade.initializeJpaUsuario().findAcessoByUsuario(id);
        this.viewPrincipal = viewPrincipal;
    }

    public void reload() {
        timer.schedule(new TimerTask() {

            @Override
            public void run() {
                int id = Integer.parseInt(KeepData.getData("Usuario.id"));
                List<Acessar> accessList = facade.initializeJpaUsuario().findAcessoByUsuario(id);

//                System.out.println(">> Usuario: " + KeepData.getData("Usuario.nome") + " logado!!");
                
                if (!useAcsess.equals(accessList)) {
                    JOptionPane.showMessageDialog(null, "Houveram modificações em sua conta."
                            + "\n\nO programa irá reiniciar.");
                    new ViewLogin().setVisible(true);
                    viewPrincipal.dispose();
                    killTimer();
                }
            }
        }, 0, 5 * 1000);
    }

    public void killTimer() {
        timer.cancel();
    }

 //    @Override
    //    public void run() {
    //        while (flag) {
    //            int id = Integer.parseInt(KeepData.getData("Usuario.id"));
    //            List<Acessar> accessList = facade.initializeJpaUsuario().findAcessoByUsuario(id);
    //
    //            System.out.println(">> Usuario: " + KeepData.getData("Usuario.nome") + " logado!!");
    //
    //            if (!useAcsess.equals(accessList)) {
    //                flag = false;
    //                JOptionPane.showMessageDialog(null, "Houve modificações em sua conta."
    //                        + "\nO programa irá reiniciar.");
    //                new ViewLogin().setVisible(true);
    //                viewPrincipal.dispose();
    //            }
    //            
    //            try {
    //                Thread.sleep(5000);
    //            } catch (InterruptedException ex) {
    //                Logger.getLogger(Reminder.class.getName()).log(Level.SEVERE, null, ex);
    //            }
    //        }
    //    }
}
