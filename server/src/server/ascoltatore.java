package server;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JTextField;

public class ascoltatore implements ActionListener{

		private JTextField input;
		private JTextField output;
		private boolean connesso = false;
		private Scanner sc;
		private PrintWriter pw;
		private Socket socket;
		
	public ascoltatore(JTextField input, JTextField output) {
		this.input = input;
		this.output = output;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();
		
		if(comando.equals("SEND")) {
			if(connesso) {
				String msg = input.getText();
				pw.println(msg);
				input.setText("");
				
				if(msg.equals("close")) {
					try {
					pw.close();
					sc.close();
					socket.close();
					connesso = false;
					output.setText("Server terminato");
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
			else {
				output.setText("Non connesso");
			}
		}
		else if(comando.equals("CONNECT")) {
			if(connesso) {
				output.setText("Utente già connesso");
			}
			else {
				try {
					int porta = Integer.parseInt(input.getText());
					socket = new Socket("localhost", porta);
					sc = new Scanner(socket.getInputStream());
					pw = new PrintWriter(socket.getOutputStream(), true);
					
					connesso = true;
					
					output.setText("Connesso");
					input.setText("");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		else if(comando.equals("DISCONNECT")) {
			if(connesso) {
				try {
					pw.close();
					sc.close();
					socket.close();
					connesso = false;
					output.setText("Sei uscito");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			else {
				output.setText("Server già chiuso");
			}
		}
	}

}
