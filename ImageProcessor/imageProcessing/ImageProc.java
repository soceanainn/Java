package imageProcessing;
//********************************************************************
//  ImageProc.java       Author: Joyce
//
//  Image Processing application for csc 1052, Spring 2010.
//  Full name of file holding image should be in command line parameter
//********************************************************************

import javax.swing.JFrame;

public class ImageProc
{
   public static void main (String[] args)
   {
      JFrame frame = new JFrame ("Image Processing");
      frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);

      MainPanel panel = new MainPanel(); 

      frame.getContentPane().add(panel);
      frame.pack();
      // Make JFrame open in full screen
      frame.setExtendedState(JFrame.MAXIMIZED_BOTH);      
      frame.setVisible(true);
   }
}
