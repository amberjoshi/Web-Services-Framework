import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
interface CrossButtonListener
{
public void windowClosing(WindowEvent e);
} 
class CrossButtonHandler extends WindowAdapter
{
private CrossButtonListener target;
CrossButtonHandler(CrossButtonListener t)
{ 
target=t;
}
public void windowClosing(WindowEvent ev)
{ 
target.windowClosing(ev);
}
} 
class AdditionFrame extends Frame implements CrossButtonListener,ActionListener
{
private TextField firstNum,secondNum;
private Button addButton;
private Label firstLabel,secondLabel;
private Label resultLabel;
AdditionFrame()
{
setTitle("Addition Module");
firstLabel=new Label("First Number");
firstNum=new TextField(10);
secondLabel=new Label("Second Label");
secondNum=new TextField(10);
addButton=new Button("Add");
addButton.addActionListener(this);
resultLabel=new Label(" ");
Panel p1=new Panel();
p1.setLayout(new GridLayout(2,2));
p1.add(firstLabel);
p1.add(firstNum);
p1.add(secondLabel);
p1.add(secondNum);
Panel p2=new Panel();
p2.setLayout(new GridLayout(2,1));
p2.add(resultLabel);
p2.add(addButton);
setLayout(new BorderLayout());
add(p1,BorderLayout.CENTER);
add(p2,BorderLayout.SOUTH);
CrossButtonHandler ch;
ch=new CrossButtonHandler(this);
addWindowListener(ch);
setLocation(10,10);
setSize(400,150);
setVisible(true);
}
public void actionPerformed(ActionEvent ev)
{ 
// Integer.parseInt to convert a string to int
// String.valueOf to convert int to string
int num1,num2;
try
{
num1=Integer.parseInt(firstNum.getText());
num2=Integer.parseInt(secondNum.getText());
int total=num1+num2;
resultLabel.setText("Result : "+String.valueOf(total));
}catch(NumberFormatException nfe)
{
resultLabel.setText("Please provied 2 numbers");
}
}
public void windowClosing(WindowEvent ev)
{
setVisible(false);
}
}
class SubstractionFrame extends Frame implements CrossButtonListener,ActionListener
{
private TextField firstNum,secondNum;
private Button substractButton;
private Label firstLabel,secondLabel;
private Label resultLabel;
SubstractionFrame()
{
setTitle("Substraction Module");
firstLabel=new Label("First Number");
firstNum=new TextField(10);
secondLabel=new Label("Second Label");
secondNum=new TextField(10);
substractButton=new Button("Substract");
substractButton.addActionListener(this);
resultLabel=new Label(" ");
Panel p1=new Panel();
p1.setLayout(new GridLayout(2,2));
p1.add(firstLabel);
p1.add(firstNum);
p1.add(secondLabel);
p1.add(secondNum);
Panel p2=new Panel();
p2.setLayout(new GridLayout(2,1));
p2.add(resultLabel);
p2.add(substractButton);
setLayout(new BorderLayout());
add(p1,BorderLayout.CENTER);
add(p2,BorderLayout.SOUTH);
CrossButtonHandler ch;
ch=new CrossButtonHandler(this);
addWindowListener(ch);
setLocation(10,10);
setSize(400,150);
setVisible(true);
}
public void actionPerformed(ActionEvent ev)
{ 
int num1,num2;
try
{
num1=Integer.parseInt(firstNum.getText());
num2=Integer.parseInt(secondNum.getText());
int difference=num1-num2;
resultLabel.setText("Result : "+String.valueOf(difference));
}catch(NumberFormatException nfe)
{
resultLabel.setText("Please provied 2 numbers");
}
}
public void windowClosing(WindowEvent ev)
{
setVisible(false);
}
} 
class ImagePanel extends Panel
{
private Image img;
ImagePanel()
{
img=Toolkit.getDefaultToolkit().getImage("java.gif");
MediaTracker mt=new MediaTracker(this);
mt.addImage(img,0);
}
public void update(Graphics g)
{
paint(g);
}
public void paint(Graphics g)
{ 
if(img!=null)
{
g.drawImage(img,0,0,this);
}
}
} 
class AboutBox extends Frame implements CrossButtonListener
{
private ImagePanel ip;
private Label topMessageLabel,bottomMessageLabel;
AboutBox()
{
setTitle("About Us");
ip=new ImagePanel();
topMessageLabel=new Label("Thinking Machines");
bottomMessageLabel=new Label("Menu Example");
setLayout(new BorderLayout());
add(topMessageLabel,BorderLayout.NORTH);
add(ip,BorderLayout.CENTER);
add(bottomMessageLabel,BorderLayout.SOUTH);
addWindowListener(new CrossButtonHandler(this));
setLocation(10,10);
setSize(300,300);
setVisible(true);
}
public void windowClosing(WindowEvent ev)
{
setVisible(false);
}
} 
class MainMenu extends Frame implements ActionListener,CrossButtonListener
{
private MenuBar mb;
private Menu m1,m2,m3;
private MenuItem addMenuItem,substractMenuItem,exitMenuItem,aboutMenuItem;
MainMenu()
{ 
addMenuItem=new MenuItem("Add");
addMenuItem.addActionListener(this);
substractMenuItem=new MenuItem("substract");
substractMenuItem.addActionListener(this);
exitMenuItem=new MenuItem("Exit");
exitMenuItem.addActionListener(this);
aboutMenuItem=new MenuItem("About Us");
aboutMenuItem.addActionListener(this);
m1=new Menu("Options");
m2=new Menu("Math");
m3=new Menu("Help");
m3.add(aboutMenuItem);
m2.add(addMenuItem);
m2.add(substractMenuItem);
m1.add(m2);
m1.add(exitMenuItem);
mb=new MenuBar();
mb.add(m1);
mb.add(m3);
setMenuBar(mb);
addWindowListener(new CrossButtonHandler(this));
setLocation(10,10);
setSize(500,500);
setVisible(true);
}
public void windowClosing(WindowEvent ev)
{
System.exit(0);
}
public void actionPerformed(ActionEvent ev)
{ 
if(ev.getSource()==addMenuItem)
{
AdditionFrame af=new AdditionFrame();
} 
if(ev.getSource()==substractMenuItem)
{
SubstractionFrame sf=new SubstractionFrame();
} 
if(ev.getSource()==exitMenuItem)
{
System.exit(0);
} 
if(ev.getSource()==aboutMenuItem)
{
AboutBox ab=new AboutBox();
}
}
} 
class example35psp
{
public static void main(String g[])
{
MainMenu m;
m=new MainMenu();
}
}