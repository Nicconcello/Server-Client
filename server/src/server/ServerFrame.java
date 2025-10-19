package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import javax.swing.*;
import java.awt.*;

public class ServerFrame extends JFrame {

	public JTextField testo;
	private ServerSocket serverSocket;

	public ServerFrame() {
		super("SERVER");
	    setSize(400, 120);
	    setLocationRelativeTo(null);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	    testo = new JTextField();
	    testo.setEditable(false);
	    add(testo, BorderLayout.CENTER);
	    
	    setVisible(true);


	}
}