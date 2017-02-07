package imageProcessing;
import java.awt.image.*;

//-------------------------------------------
//Author: Séamus Ó Ceanainn
//-------------------------------------------

public class ImageList { 
    ImageNode curr; // Keeps track of current image for undo / redo
    
    public ImageList(BufferedImage image) { // Constructor 
       curr = new ImageNode(image);
    }

    public void add(BufferedImage image) { 
    	curr.next = new ImageNode(image, curr);
    	curr = curr.next;
    }
    
    public boolean hasNext(){ // Check if redo is possible, outputs error message in MainPanel.java if next is null
    	return curr.next!=null;
    }
    
    public boolean hasPrev(){ // Check if undo is possible, outputs error message in MainPanel.java if prev is null
    	return curr.prev!=null;
    }
    
    public BufferedImage undo(){ // Go to previous image
    	curr = curr.prev;
    	return curr.image;
    }
    
    public BufferedImage redo(){ // Go to next image
    	curr = curr.next;
    	return curr.image;
    }

    private class ImageNode { 	// List nodes

        BufferedImage image;
        ImageNode next;
        ImageNode prev;

        // Constructors
        private ImageNode(BufferedImage image) { 
           this.image = image;
        }
        private ImageNode(BufferedImage image, ImageNode prev) { 
            this.image = image;
            this.prev = prev;
        }
    }
}