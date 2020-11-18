import java.awt.*;
import javax.swing.*;
class ItemFrame extends JFrame
{
private ItemPanel itemPanel;
private Container container;
ItemFrame()
{
initComponents();
setAppearance();
}
private void initComponents()
{
container=getContentPane();
container.setLayout(null);
itemPanel=new ItemPanel();
itemPanel.setBounds(1,1,682,608);

container.add(itemPanel);
setSize(700,650);
Dimension dimension=Toolkit.getDefaultToolkit().getScreenSize();
setLocation(dimension.width/2-getWidth()/2,dimension.height/2-getHeight()/2);
setVisible(true);
}
private void setAppearance()
{
setTitle("Inventory Management System");
ImageIcon appIcon=new ImageIcon("app.png");
setIconImage(appIcon.getImage());
itemPanel.setBorder(BorderFactory.createLineBorder(new Color(112,112,112)));
}
public static void main(String gg[])
{
ItemFrame itemFrame=new ItemFrame();
}
}
