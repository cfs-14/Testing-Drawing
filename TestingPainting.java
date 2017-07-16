import javax.swing.SwingUtilities;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.Font;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.Shape;
import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.awt.BasicStroke;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.lang.Thread;

//(!) add a timer to erase the string and paint a rectangle. 

class TestingPainting
{
   final int WIN_W = 700;
   final int WIN_H = 700;

   
   class MyPanel extends JPanel
   {
      private void paintStuff(Graphics g)
      {
         Graphics2D temp = (Graphics2D) g;
         
         BasicStroke bstr = new BasicStroke(2, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 0.1F);          
         
         FontRenderContext frc = temp.getFontRenderContext();         
         Font f = new Font("Javanese Text", Font.PLAIN, 100);
         Shape outline = new TextLayout("Software\nEngineer!", f, frc).getOutline(null);
         temp.translate(50, 200);
         
         
         temp.setColor(java.awt.Color.green);
         temp.fill(outline);
         
         temp.setStroke(bstr);//.createStrokedShape(new Rectangle2D.Float(10,10,20,20)));
         temp.setColor(new Color(0,100,0));        
         temp.draw(outline);
         

         
//          temp.drawString("Yes, I drew something!! And I will be the best !!!!", WIN_W/2, WIN_H/2);
         

         
         try
         {
            Thread.sleep(3000);
            repaint(WIN_W/2, WIN_H/2, 20, 20);
            temp.setColor(java.awt.Color.blue);
            temp.fillRoundRect(50, 100, 300, 100, 1, 1);
            temp.setColor(java.awt.Color.green);
            temp.drawRoundRect(50, 100, 300, 100, 1, 1);
         }
         catch(InterruptedException e)
         {
            ;
         }         
         
//          temp.clearRect(WIN_W/2, WIN_H/2, 
         
      }
      
      @Override
      public void paintComponent(Graphics g)
      {
      
         super.paintComponent(g);
         
         paintStuff(g);
      }
   
   }   
   
   //I create a class so I can define the constructor which will initialize all the contents. 
   
   class MyWindow extends JFrame
   {
      public MyWindow()
      {
         initGUI();
      }
      
      void initGUI()
      {
         add(new MyPanel());
         
         setTitle("My Prog");
         
         setSize(WIN_W, WIN_H);
         
         setLocationRelativeTo(null); //center
         
         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         
         setVisible(true);
         
      
      }
   }
   
   public static void main(String[] args)
   {
      //we have to create a "thread" and run it.
//       GraphicsEnvironment G = GraphicsEnvironment.getLocalGraphicsEnvironment();
//       
//       String[] fonts = G.getAvailableFontFamilyNames();
//       
//       for(int i = 0; i < fonts.length; i++)
//          System.out.println(fonts[i]);
      EventQueue.invokeLater(new Runnable()
      {  
         public void run()
         {                
            MyWindow prog = new TestingPainting().new MyWindow();
            //is the below method non-static so it can't be called w/in its class?
//             prog.setVisible(true);
         }
      });
            
   }
   
}