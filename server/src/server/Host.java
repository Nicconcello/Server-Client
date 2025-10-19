package server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket; 
import java.net.Socket; 
import java.util.Scanner; 
import javax.swing.JFrame;
import javax.swing.SwingUtilities; 

public class Host {

    private static int vol = 50;
    private static int volMax = 100;
    private static int volMin = 0;

    public static void main(String[] args) throws IOException {
        boolean connesso = false;
        ServerFrame finestra = new ServerFrame();
        ServerSocket ss = new ServerSocket(53550);

        while (true) {
        	SwingUtilities.invokeLater(() -> finestra.testo.setText("Il Server sta attendendo la connessione....."));
            Socket s = ss.accept();
            Scanner sc = new Scanner(s.getInputStream());
            PrintWriter pw = new PrintWriter(s.getOutputStream(), true);
            connesso = true;
            SwingUtilities.invokeLater(() -> finestra.testo.setText("Utente connesso"));
            
            while (connesso) {
                if (!sc.hasNextLine()) {
                    connesso = false;
                    s.close();
                    break;
                }

                String cv = sc.nextLine().trim();

                if (cv.equals("volume+")) {
                    if (vol < volMax) {
                    	vol ++;
                    	SwingUtilities.invokeLater(() -> finestra.testo.setText("Volume" + vol));
                    }
                    
                } else if (cv.equals("volume-")) {
                    if (vol > volMin) {
                    	vol --;
                    	SwingUtilities.invokeLater(() -> finestra.testo.setText("Volume" + vol));
                    }
                    
                } else if (cv.equals("volume")) {
                	SwingUtilities.invokeLater(() -> finestra.testo.setText("Volume" + vol));
                }

                System.out.println("Comando ricevuto: " + cv);
                System.out.println("Volume corrente: " + vol);

                // Aggiorna GUI
                SwingUtilities.invokeLater(() -> finestra.testo.setText("Volume corrente: " + vol));
            }

            sc.close();
        }
    }
}
