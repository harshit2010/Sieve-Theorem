
import java.awt.*;
import java.awt.event.*;

public class SimpleMenuExample extends Frame implements ActionListener
{
  Menu changecolor,changefont,changesize;
  public static String colorval="Magenta";
  public static String fontval="Calibri";
  public static int fontsize=11;
  public SimpleMenuExample()
  {			
    MenuBar mb = new MenuBar();		        
    setMenuBar(mb);				

    changecolor = new Menu("Change Color");		
	changefont = new Menu("Change Font");	
	changesize	=new Menu("Change FontSize");	
	
    mb.add(changecolor);				
    mb.add(changefont);
	mb.add(changesize);

    changecolor.addActionListener(this);		
    changefont.addActionListener(this);
	changesize.addActionListener(this);
	
	
    changecolor.add(new MenuItem("RED"));
    changecolor.add(new MenuItem("BLUE"));
    changecolor.add(new MenuItem("CYAN"));
    
    changefont.add(new MenuItem("Arial"));
    changefont.add(new MenuItem("Times New Roman"));
    changefont.add(new MenuItem("Comic Sans MS"));
	
	changesize.add(new MenuItem("Small"));
	changesize.add(new MenuItem("Medium"));
	changesize.add(new MenuItem("Large"));
	
    
    setSize(320, 200);
    setVisible(true);
  }
  public void actionPerformed(ActionEvent e)
  {
    if((e.getActionCommand()).equals("RED")){
			colorval=new String();
			colorval+="RED";
			//System.out.println(colorval+"FCOL");
		}
	else if((e.getActionCommand()).equals("BLUE")){
			colorval=new String();
			colorval+="BLUE";
			//System.out.println(colorval+"FCOL");
		}
	else if((e.getActionCommand()).equals("CYAN")){
			colorval=new String();
			colorval+="CYAN";
			//System.out.println(colorval+"FCOL");
		}
		
		
	if((e.getActionCommand()).equals("Arial")){
			fontval=new String();
			fontval+="Arial";
			//System.out.println(fontval+"Ftype");
		}
	else if((e.getActionCommand()).equals("Times New Roman")){
			fontval=new String();
			fontval+="Times New Roman";
			//System.out.println(fontval+"Ftype");
		}
	else if((e.getActionCommand()).equals("Comic Sans MS")){
			fontval=new String();
			fontval+="Comic Sans MS";
			//System.out.println(fontval+"Ftype");
		}
		
		
	if((e.getActionCommand()).equals("Small")){
			
			fontsize=9;
			//System.out.println(fontsize+"FS");
		}
	else if((e.getActionCommand()).equals("Medium")){
			
			fontsize=11;
			//System.out.println(fontsize+"FS");
		}
	else if((e.getActionCommand()).equals("Large")){
			
			fontsize=13;
			//System.out.println(fontsize+"FS");
		}
	
	new Make_array();
  }
}