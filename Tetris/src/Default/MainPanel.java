package Default;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class MainPanel {
	
     public static void main(String[] args) {

         JFrame window = new JFrame("Tetris");
         window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         window.setResizable(false);
                 
         GamePanel gp = new GamePanel(); 
         window.add(gp); 

         window.pack(); 
         window.setLocationRelativeTo(null); 
         window.setVisible(true);
         
         gp.launchGame();

     }
 }