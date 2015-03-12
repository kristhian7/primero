

import javax.swing.*;
import java.awt.Color;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentListener;
import java.awt.event.ComponentEvent;

public class Entrega2 extends JComponent  {
    
    JFrame shell;
    Image fondo;
    JButton boton1;
    ActionListener pulsar;
    JTextField tComando;
    JTextArea tResultado;
    JScrollPane sPane;

    public Entrega2(JFrame shell){
    this.shell=shell;
    actbackground();
    }
    
    
public void actbackground( ) {
try {
Robot rbt = new Robot( );
Toolkit tk = Toolkit.getDefaultToolkit( );
Dimension dim = tk.getScreenSize( );
fondo = rbt.createScreenCapture(
new Rectangle(0,0,(int)dim.getWidth( ),
(int)dim.getHeight( )));
} catch (Exception ex) {
ex.printStackTrace( );
}
}

public void paintComponent(Graphics g) {
Point pos = this.getLocationOnScreen( );
Point offset = new Point(-pos.x,-pos.y);
g.drawImage(fondo,offset.x,offset.y,null);
repaint();
}


public void grafico(){



tComando = new JTextField();

tComando.setBounds(50,50,250,30);

add(tComando);

		
//botón para ejecutar comando

boton1 = new JButton("Ejecutar");

boton1.setBounds(350,50,150,30);

add(boton1);

boton1.addActionListener(pulsar);


		//área de texto
	
tResultado = new JTextArea();

tResultado.setBounds(50,130,600,370);


tResultado.setBackground( Color.black);		
tResultado.setForeground(Color.GREEN);


		//scroll pane
		
sPane = new JScrollPane(tResultado);

sPane.setBounds(50,120,500,400);

add(sPane);

}


private void acciones(){

    pulsar = new ActionListener(){
 
        public void actionPerformed(ActionEvent e){
            ejecutar();
          
            String borrar ="clear";
        if(tComando.getText().equals(borrar)){
        
            tResultado.setText("");
        }
 
     }
	
    };
}


private void ejecutar(){

Process proc;
InputStream is_in;
String s_aux;	
BufferedReader br;

	
try{

    proc = Runtime.getRuntime().exec(tComando.getText());	
    is_in=proc.getInputStream();
    br=new BufferedReader (new InputStreamReader (is_in));
    s_aux = br.readLine();
    
    while (s_aux!=null){
       
        
        tResultado.setText(tResultado.getText()+s_aux+"\n "); 
        s_aux = br.readLine();

    } 

}

catch(Exception e){
		
	e.getMessage();
}


	
}

public static void main(String[] args) {
    
    JFrame shell = new JFrame();
    
    Entrega2 as = new Entrega2(shell);
    as.acciones();
    as.grafico();
    shell.getContentPane( ).add("Center",as);
    shell.setTitle(System.getProperty("os.name"));
    shell.setVisible(true);
    shell.setSize(650,600);
    shell.setLocationRelativeTo(null);
    shell.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    
    
    
        
    }

    
    
}
