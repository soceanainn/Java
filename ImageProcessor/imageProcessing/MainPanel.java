package imageProcessing;

//********************************************************************
//  MainPanel.java       Authors: Joyce, Séamus Ó Ceanainn
// Original Program:	http://www.csc.villanova.edu/~joyce/csc2053/spring17/MainPanel.java
//  Represents the panel in the Image Processing application.
//  Uses the border layout manager with
//    north  = title
//    west   = menu
//    center = current image
//    east   = not used
//    south  = not used
//********************************************************************

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;

public class MainPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	JButton loadBtn, saveBtn, undoBtn, redoBtn, fliphBtn, flipvBtn, brighterBtn, darkerBtn, edgesRedBtn, edgesGrnBtn, edgesBlueBtn, edgesBtn; // Buttons
	BufferedImage currImage;// Image displayed on screen
	JLabel imageLabel; // Title displayed above image
	ImageList imageList; // Linked list of images for undo / redo
	String origFnExt; //file name and extension
	String origFn; //file name
	String origExt; //file extension

	public MainPanel() {
		// general panel set up
		setLayout(new BorderLayout());
		setBackground(Color.lightGray);

		// title
		JLabel title = new JLabel("Image Processing Application", JLabel.CENTER);

		// left side menu
		JPanel menu = new JPanel();
		menu.setLayout(new GridLayout(6, 2)); // make sure this is correct!!!!!!

		// load button
		loadBtn = new JButton("Load Picture");
		loadBtn.addActionListener(new loadOrigListener());
		menu.add(loadBtn);

		// save button
		saveBtn = new JButton("Save Current");
		saveBtn.addActionListener(new saveCurrListener());
		menu.add(saveBtn);

		// Undo button
		undoBtn = new JButton("Undo");
		undoBtn.addActionListener(new opListener());
		menu.add(undoBtn);
		
		// Redo button
		redoBtn = new JButton("Redo");
		redoBtn.addActionListener(new opListener());
		menu.add(redoBtn);

		// flip horizontally button
		fliphBtn = new JButton("Flip Horizontally");
		fliphBtn.addActionListener(new opListener());
		menu.add(fliphBtn);

		// flip vertically button
		flipvBtn = new JButton("Flip Vertically");
		flipvBtn.addActionListener(new opListener());
		menu.add(flipvBtn);

		// brighter button
		brighterBtn = new JButton("Brighter");
		brighterBtn.addActionListener(new opListener());
		menu.add(brighterBtn);

		// Darker button
		darkerBtn = new JButton("Darker");
		darkerBtn.addActionListener(new opListener());
		menu.add(darkerBtn);

		// edges red button
		edgesRedBtn = new JButton("Edges Red");
		edgesRedBtn.addActionListener(new opListener());
		menu.add(edgesRedBtn);

		// edges green button
		edgesGrnBtn = new JButton("Edges Green");
		edgesGrnBtn.addActionListener(new opListener());
		menu.add(edgesGrnBtn);

		// edges blue button
		edgesBlueBtn = new JButton("Edges Blue");
		edgesBlueBtn.addActionListener(new opListener());
		menu.add(edgesBlueBtn);

		// edges all button
		edgesBtn = new JButton("Edges All");
		edgesBtn.addActionListener(new opListener());
		menu.add(edgesBtn);

		// image
		// StretchIcon taken from
		// http://www.camick.com/java/source/StretchIcon.java
		StretchIcon image = new StretchIcon(origFnExt);
		imageLabel = new JLabel(image);

		// populate panel
		add(title, BorderLayout.NORTH);
		add(menu, BorderLayout.WEST);
		add(imageLabel, BorderLayout.CENTER);
	}

	private class saveCurrListener implements ActionListener {
		// --------------------------------------------------------------
		// Saves the current image in a new file.
		// Gets filename as input from user
		// --------------------------------------------------------------
		public void actionPerformed(ActionEvent event) {
			String fn = JOptionPane.showInputDialog("Please enter a save name, including file extension:");
			origFnExt = fn;
			if (fn.indexOf('.')==-1){ // If no '.' then can't have proper file extension
				JOptionPane.showMessageDialog(null, "Error with file name, have you entered in the file extension?");
			} else {
				origFn = fn.substring(0, fn.indexOf('.'));
				origExt = fn.substring(fn.indexOf('.') + 1);
				try {
					File outputfile = new File(origFnExt);
					ImageIO.write(currImage, origExt, outputfile);
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null,"Could not save file ...");
				}
				JOptionPane.showMessageDialog(null, "File saved!"); // Success
			}
		}
	}

	private class loadOrigListener implements ActionListener {
		// --------------------------------------------------------------
		// Loads an image into display.
		// Gets filename as input form user
		// --------------------------------------------------------------
		public void actionPerformed(ActionEvent event) {
			String fn = JOptionPane.showInputDialog("Please enter a picture file name, including file extension:");
			origFnExt = fn;
			if (fn.indexOf('.')==-1){ // If no '.' then can't have proper file extension
				JOptionPane.showMessageDialog(null, "Error with file name, have you entered in the file extension?");
			} else {
				origFn = fn.substring(0, fn.indexOf('.'));
				origExt = fn.substring(fn.indexOf('.') + 1);
	
				// load Image
				try {
					currImage = ImageIO.read(new File(origFnExt));
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null,"could not load file");
				}
				imageList = new ImageList (currImage); // Create new ImageList for undo / redo
				StretchIcon imageIc = new StretchIcon(currImage);
				imageLabel.setIcon(imageIc);
			}
		}
	}

	private class opListener implements ActionListener {
		// --------------------------------------------------------------
		// Makes a change to the current image,
		// as indicated by the operation.
		//
		// Might not work for some image formats.
		// --------------------------------------------------------------
		public void actionPerformed(ActionEvent event) {
			
			// Undo operation
			if (event.getSource() == undoBtn)
				if (imageList.hasPrev())
					currImage = imageList.undo();
				else JOptionPane.showMessageDialog(null,"Cannot perform undo operation");
			
			// Redo operation
			else if (event.getSource() == redoBtn)
				if (imageList.hasNext())
					currImage = imageList.redo();
				else JOptionPane.showMessageDialog(null,"Cannot perform redo operation");
			
			// Flip Horizontally
			else if (event.getSource() == fliphBtn){ 
				currImage = Transform.flipHorizontal(currImage);
				imageList.add(currImage);
			}
			
			// Flip Vertically
			else if (event.getSource() == flipvBtn){ 
				currImage = Transform.flipVertical(currImage);
				imageList.add(currImage);
			}
			
			// Brighten
			else if (event.getSource() == brighterBtn){ 
				currImage = Transform.brighten(currImage);
				imageList.add(currImage);
			}
			
			// Darken
			else if (event.getSource() == darkerBtn){ 
				currImage = Transform.darken(currImage);
				imageList.add(currImage);
			}
			
			// Edges Red
			else if (event.getSource() == edgesRedBtn){ 
				currImage = Transform.edgesRed(currImage);
				imageList.add(currImage);
			}
			
			// Edges Green
			else if (event.getSource() == edgesGrnBtn){ 
				currImage = Transform.edgesGreen(currImage);
				imageList.add(currImage);
			}
			
			// Edges Blue
			else if (event.getSource() == edgesBlueBtn){ 
				currImage = Transform.edgesBlue(currImage);
				imageList.add(currImage);
			}
			
			// Edges All
			else if (event.getSource() == edgesBtn){ 
				currImage = Transform.edgesAll(currImage);
				imageList.add(currImage);
			}
			
			StretchIcon imageIc = new StretchIcon(currImage);
			imageLabel.setIcon(imageIc);
		}
	}
}