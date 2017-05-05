import java.awt.*;
import java.applet.*;
import java.awt.event.*;

public class Make_array extends Applet implements ActionListener,Runnable,KeyListener,ItemListener
{
	TextField a1,a2;
	Label a,b;
	Button b1;
	Thread th;
	Choice TimeSelect;
	AudioClip audioClip;
	
	static String colorval;
	static String fontval;
	static int fontsize;
	
	
	int val;						// no of elements in array
	int []arr;						// for Storing Array values
	int indexi=50,indexj=115;		// starting indexes of array
	int val2insert=0;				// value to be inserted in array
	int cnt=0;						//used for counting number of primes found
	int check1=0;					
	int checkval=0;					
	String S;						// used for printing final String containing ALL PRIMES
	int start=0;					//for checking start of a current thread if 0 no thread in queue
	int finalj;
	int sleeptime=1000;				//waiting time for every transition
	int temp=0;
	int indexval;					//for printing updates
	int j,i;						//index for 2D array
	int widthofwindow=1200;
	int numusedformod=40;			//for Changing rows as needed for finding real value to be highlighted
	
	public Make_array()
	{
		colorval=SimpleMenuExample.colorval;
		fontval=SimpleMenuExample.fontval;
		fontsize=SimpleMenuExample.fontsize;
		//System.out.println("VALUE of COLOR  "+colorval);
		//System.out.println("VALUE of COLOR  "+fontval);
		//System.out.println("VALUE of COLOR  "+fontsize);
	}
	
	public void init()
	{
		new SimpleMenuExample();
		a=new Label("Enter number");
		a1=new TextField();
		a2=new TextField("Please enter a number Greater than 0 and less than 350");
		b1=new Button("Start Finding Primes");
		b=new Label("Change Transition Time");

		TimeSelect = new Choice();
		TimeSelect.add("250");
		TimeSelect.add("500");
		TimeSelect.add("1000");
		TimeSelect.add("1100");
		TimeSelect.add("1200");
		
		
		setLayout(null);
		a.setBounds(405,20,100,30);	
		a1.setBounds(540,20,50,30);	
		a2.setBounds(650,20,350,30);	
		b1.setBounds(520,80,150,30);
		TimeSelect.setBounds(750,80,50,150);
		b.setBounds(850,80,180,20);	

		
		add(a);
		add(a1);
		add(a2);
		add(b1);
		add(TimeSelect);
		add(b);

		b1.addActionListener(this);
		a2.setEditable(false);
		b1.addKeyListener(this);
		a1.addKeyListener(this);
		TimeSelect.addItemListener(this);

	}
	
	
	// function for initializing array with 0 except at indexes 0 and 1
	
	
	public void initialize(){
		if(val>=1){
			arr=new int[val];
			arr[0]=1;
			arr[1]=1;
			for(int i=2;i<val;i++){
				arr[i]=0;
			}
		}
		
		
	}
	
	
	// function for finding primes in the used array
	
	
	public void countPrimes()
	{
		for(i=0;i<val;i++){
			if(arr[i]==0)
				cnt+=1;
		}
		repaint(0,finalj+50,1500,500);
		try{
			Thread.sleep(sleeptime);
		}
		catch(InterruptedException ie){}		
	}
	
	
	// main thread Execution
	
	
	synchronized public void run(){
		val2insert=0;						// variable used for just Drawing some selected things in applet
		finalj=0;							// Variable used for finding final position to display the position of Writing Final String i.e No of Primes....
		initialize();						// Constructor for initializing array elements
		
		
		repaint(0,0,1300,1500);				// For Drawing Array with Initial values as 0 and 1 with no primes yet found
		try{
			Thread.sleep(1000);				// time is not same as sleeptime since it then refreshes page so fast that array gets not correctly displayed
		}
		catch(InterruptedException ie){}		
		
		
		check1=1;
		val2insert=1;
		int subout=0;
		
		
		if(val>1){
			for(i=2;i<val;i++){		
				if(arr[i]==0){
					audioClip = getAudioClip(getCodeBase(), i+".wav");
					checkval=i;
					repaint(60,finalj+50,1000,100);		// for writing value of current number whose multiples are checked
					try{
						Thread.sleep(sleeptime);
					}
					catch(InterruptedException ie){}
					
					
					
					audioClip.play();					// audio file to speak the current number whose multiples are being checked
					try{
						Thread.sleep(4000);
					}
					catch(InterruptedException ie){}		
					
					
					
					indexj=115;							// starting index from top from where array has been started Drawing
					
					checkval=0;							// marking whether value has been checked or not
					
					int sub=0;							// temporary variable used for checking line changes in array
					int k;								// variable used to store value of remainder by selected number(40) to mod from
					int m=0;				// for checking whether j/40 has changed or not
					
					
					for(j=i*2;j<val;j+=i){							// loop used to traverse for checking all multiples of selected number
						k=j%numusedformod;
						
						if(arr[j]==0){								// goes inside only if arrays element value has value as 0
							arr[j]=1;		
							indexi=20+(30*(k+1));					// position of arrays element from x-direction
							
							while(j/numusedformod!=m){				//loops until correct row is found	
								indexj+=50;
								m+=1;									
							}	
							temp=1;
							
							//checking for updated x and y values
							// printing value of current number which is checked
							repaint(260,finalj+50,1000,100);
							try{
								Thread.sleep(sleeptime);
							}
							catch(InterruptedException ie){}			
							temp=0;
							
							repaint(indexi,indexj,30,30);	// for highlighting that array element which is found
							try{
								Thread.sleep(sleeptime);
							}
							catch(InterruptedException ie){}
							}
					}
				}
			}
			start=0;	
			countPrimes();			// counting number of primes found in total
			val2insert=0;			// variable used for marking thread End
		}
	}
	
	
	synchronized public void actionPerformed(ActionEvent ae){					// Action Listener for Button Click
	if((ae.getActionCommand()).equals("Start Finding Primes")){
			sleeptime=Integer.parseInt(TimeSelect.getSelectedItem());
			val=Integer.parseInt(a1.getText())+1;
			th = new Thread(this);
			th.start();
		}
	}
	
	
	public void keyPressed(KeyEvent ke){										//Key Listener for Key pressed
		if(ke.getKeyCode()==KeyEvent.VK_ENTER){
			sleeptime=Integer.parseInt(TimeSelect.getSelectedItem());
			val=Integer.parseInt(a1.getText())+1;
			th = new Thread(this);
			th.start();
			//System.out.println("Nhi chal raha");
		}
	}
	
	
	public void itemStateChanged(ItemEvent abc){								//changes value of sleeptime as soon as one is selected
		sleeptime=Integer.parseInt(TimeSelect.getSelectedItem());
	}
	
	public void paint(Graphics g){												// MAIN PRINTING FUNCTION """"PAINT""""
		Font f=new Font(fontval+"", Font.PLAIN,fontsize);
		g.setFont(f);	
		if(start==0 && val2insert==0){											// Initial Putting backgroung color and all stuff
			start=1;
			setBackground(new Color(179,0,119));
		}
		
		g.setColor(Color.WHITE);
		if(temp==1){							// works only when a number is found as mutiple of number being checked
			g.drawString("Multiple of "+i+" is "+j+" at index "+j+"."+"    jsut checking for indexes "+indexi+"."+" and "+indexj,260,finalj+100);
			return;								// returns back to run function
		}
		
		
		if(checkval!=0){					
			g.drawString("Checking For Value :"+checkval+"",60,finalj+100);
			indexval=finalj;
		}
		else{
			if(val2insert==0){
				int sub=0;
				indexj=115;
				for(int i=0;i<val;i++){
					if(indexi>=widthofwindow){
						sub+=(i-sub);
						indexi=50;
						indexj+=50;
					}
					else{
						indexi=20+(30*(i-sub+1));
					}
					
					if(i==0 || i==1){
						g.setColor(Color.ORANGE);
						g.drawRect(indexi,indexj,30,30);
						g.setColor(Color.WHITE);
						g.drawString(i+"",indexi+10,indexj+45);
						if(colorval.equals("RED")){
							g.setColor(Color.RED);
						}
						else if(colorval.equals("BLUE")){
							g.setColor(Color.BLUE);
						}
						else if(colorval.equals("CYAN")){
							g.setColor(Color.CYAN);
						}
						else{
							g.setColor(Color.MAGENTA);
						}
						g.fillRect(indexi+2,indexj+2,27,27);
						g.setColor(Color.BLACK);
						g.drawString(arr[i]+"",indexi+10,indexj+20);
						
					}
					else{
						g.setColor(Color.ORANGE);
						g.drawRect(indexi,indexj,30,30);
						g.setColor(Color.WHITE);
						g.drawString(i+"",indexi+10,indexj+45);
						g.drawString(arr[i]+"",indexi+10,indexj+20);
						
					}
					
				}
				finalj=indexj;
			}
			else{
				g.setColor(Color.ORANGE);
				g.drawRect(indexi,indexj,30,30);
				if(colorval.equals("RED")){
					g.setColor(Color.RED);
				}
				else if(colorval.equals("BLUE")){
					g.setColor(Color.BLUE);
				}
				else if(colorval.equals("CYAN")){
					g.setColor(Color.CYAN);
				}
				else{
					g.setColor(Color.MAGENTA);
				}
				
				g.fillRect(indexi+2,indexj+2,27,27);
				g.setColor(Color.BLACK);
				g.drawString(val2insert+"",indexi+10,indexj+20);
			}
		}
		if(cnt!=0){
			g.setColor(Color.WHITE);
			g.drawString("Number of Primes are "+cnt,60,finalj+100);
			S=new String(" ");
			for(int i=0;i<val;i++){
				if(arr[i]==0){
					S+=i+", ";
				}
			}
			
			g.drawString("Primes encountered are "+S.substring(0,S.length()-2),260,finalj+100);
			cnt=0;
		}
		else if(val2insert==1 && start==0){
			g.setColor(Color.WHITE);
			g.drawString("NO Primes encountered. ",60,finalj+100);
		}
	}
	
	public void keyReleased(KeyEvent k1){
	
	}
	public void keyTyped(KeyEvent k1){
	
	}
}
/*
<applet code="Make_array" width=1500 height=640>
</applet>
*/