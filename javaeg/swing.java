import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.filechooser.*;
import java.io.*;
class ItemPanel extends JPanel implements DocumentListener,ListSelectionListener,ActionListener
{
private int mode; 
private static final int VIEW_MODE=1;
private static final int ADD_MODE=2;
private static final int EDIT_MODE=3;
private static final int DELETE_MODE=4;
private static final int EXPORT_TO_PDF_MODE=5;
private JLabel moduleTitle;
private JLabel searchCaptionLabel;
private JTextField searchTextField;
private JButton clearSearchTextFieldButton;
private JLabel searchErrorLabel;
private JTable table;
private JScrollPane jsp;
private ItemModel itemModel;
private ItemDetailsPanel itemDetailsPanel;
ItemPanel()
{
itemModel=new ItemModel();
initComponents();
setAppearance();
addListeners();
setViewMode();
itemDetailsPanel.setViewMode();
}
private void initComponents()
{
moduleTitle=new JLabel("Items");
searchCaptionLabel=new JLabel("Search");
searchTextField=new JTextField();
clearSearchTextFieldButton=new JButton(new ImageIcon("clearIcon.png"));
searchErrorLabel=new JLabel("");
table=new JTable(itemModel);
jsp=new JScrollPane(table,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
itemDetailsPanel=new ItemDetailsPanel();
setLayout(null);
int lm=5;
moduleTitle.setBounds(lm+5,5,400,50);
searchCaptionLabel.setBounds(lm+5,60,60,30);
searchTextField.setBounds(lm+65,60,400,30);
clearSearchTextFieldButton.setBounds(lm+467,60,30,30);
searchErrorLabel.setBounds(lm+405,40,75,20);
jsp.setBounds(lm+5,95,663,250);
itemDetailsPanel.setBounds(lm+5,350,663,250);
add(moduleTitle);
add(searchCaptionLabel);
add(searchTextField);
add(clearSearchTextFieldButton);
add(searchErrorLabel);
add(jsp);
add(itemDetailsPanel);
}
private void setAppearance()
{
Font moduleTitleFont=new Font("Verdana",Font.BOLD,20);
moduleTitle.setFont(moduleTitleFont);
Font font=new Font("Verdana",Font.PLAIN,16);
Font searchErrorFont=new Font("Verdana",Font.BOLD,10);
searchCaptionLabel.setFont(font);
searchTextField.setFont(font);
searchErrorLabel.setFont(searchErrorFont);
searchErrorLabel.setForeground(new Color(111,0,0));
table.setRowHeight(30);
table.setFont(font);
Font tableTitleFont=new Font("Verdana",Font.BOLD,16);
table.getTableHeader().setFont(tableTitleFont);
table.getColumnModel().getColumn(0).setPreferredWidth(100);
table.getColumnModel().getColumn(1).setPreferredWidth(560);
table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
table.getTableHeader().setResizingAllowed(false);
table.getTableHeader().setReorderingAllowed(false);
}
private void addListeners()
{
searchTextField.getDocument().addDocumentListener(this);
table.getSelectionModel().addListSelectionListener(this);
clearSearchTextFieldButton.addActionListener(this);
}
private void setAddMode()
{
mode=ADD_MODE;
searchTextField.setEnabled(false);
clearSearchTextFieldButton.setEnabled(false);
table.setEnabled(false);
}
private void setDeleteMode()
{
mode=DELETE_MODE;
searchTextField.setEnabled(false);
clearSearchTextFieldButton.setEnabled(false);
table.setEnabled(false);
}
private void setExportToPDFMode()
{
mode=EXPORT_TO_PDF_MODE;
searchTextField.setEnabled(false);
clearSearchTextFieldButton.setEnabled(false);
table.setEnabled(false);
}
private void setEditMode()
{
mode=EDIT_MODE;
searchTextField.setEnabled(false);
clearSearchTextFieldButton.setEnabled(false);
table.setEnabled(false);
}
private void setViewMode()
{
mode=VIEW_MODE;
if(itemModel.getRowCount()==0)
{
searchTextField.setText("");
searchErrorLabel.setText("");
searchTextField.setEnabled(false);
clearSearchTextFieldButton.setEnabled(false);
table.setEnabled(false);
}
else
{
searchTextField.setEnabled(true);
clearSearchTextFieldButton.setEnabled(true);
table.setEnabled(true);
}
}
private void search()
{
searchErrorLabel.setText("");
String searchWhat=searchTextField.getText().trim();
if(searchWhat.length()==0) return;
int index=itemModel.search(searchWhat);
if(index==-1)
{
searchErrorLabel.setText("Not found");
return;
}
table.setRowSelectionInterval(index,index);
table.scrollRectToVisible(new Rectangle(table.getCellRect(index,0,false)));
}
public void insertUpdate(DocumentEvent ev)
{
search();
}
public void removeUpdate(DocumentEvent ev)
{
search();
}
public void changedUpdate(DocumentEvent ev)
{
search();
}
public void actionPerformed(ActionEvent ev)
{
searchTextField.setText("");
searchErrorLabel.setText("");
searchTextField.requestFocus();
}
public void valueChanged(ListSelectionEvent ev)
{
int selectedIndex=table.getSelectedRow();
if(selectedIndex==-1) 
{
itemDetailsPanel.setItem(null);
return;
}
try
{
ItemDTO selectedItem=itemModel.getItemAt(selectedIndex);
itemDetailsPanel.setItem(selectedItem);
}catch(ModelException modelException)
{
itemDetailsPanel.setItem(null);
}
}

// inner class begins
class ItemDetailsPanel extends JPanel implements ActionListener
{
private ItemDTO item;
private JLabel  nameCaptionLabel;
private JTextField nameTextField;
private JLabel nameLabel;
private JButton clearNameTextFieldButton;
private JLabel priceCaptionLabel;
private JTextField priceTextField;
private JButton clearPriceTextFieldButton;
private JLabel priceLabel;
private JLabel categoryCaptionLabel;
private JLabel categoryLabel;
private ButtonGroup categoryGroup;
private JRadioButton finishedGoodsRadioButton;
private JRadioButton rawMaterialRadioButton;
private JRadioButton consumableRadioButton;
private JButton addButton;
private JButton editButton;
private JButton deleteButton;
private JButton cancelButton;
private JButton exportToPDFButton;
ItemDetailsPanel()
{
initComponents();
setAppearance();
addListeners();
}
private void initComponents()
{
nameCaptionLabel=new JLabel("Item : ");
nameTextField=new JTextField();
nameLabel=new JLabel("");
clearNameTextFieldButton=new JButton("x");
priceCaptionLabel=new JLabel("Price : ");
priceTextField=new JTextField();
priceLabel=new JLabel("");
categoryCaptionLabel=new JLabel("Category : ");
categoryGroup=new ButtonGroup();
finishedGoodsRadioButton=new JRadioButton("Finished Goods");
rawMaterialRadioButton=new JRadioButton("Raw Material");
consumableRadioButton=new JRadioButton("Consumable");
categoryGroup.add(finishedGoodsRadioButton);
categoryGroup.add(rawMaterialRadioButton);
categoryGroup.add(consumableRadioButton);
categoryLabel=new JLabel("");
clearPriceTextFieldButton=new JButton("x");
addButton=new JButton("A");
editButton=new JButton("E");
deleteButton=new JButton("D");
cancelButton=new JButton("C");
exportToPDFButton=new JButton("P");
setLayout(null);
int lm,tm;
lm=5;
tm=5;
nameCaptionLabel.setBounds(lm+5,tm+10,60,30);
nameTextField.setBounds(lm+5+60+5,tm+10,400,30);
nameLabel.setBounds(lm+5+60+5,tm+10,400,30);
clearNameTextFieldButton.setBounds(lm+5+60+5+400+2,tm+10,30,30);

priceCaptionLabel.setBounds(lm+5,tm+10+30+10,60,30);
priceTextField.setBounds(lm+5+60+5,tm+10+30+10,150,30);
priceLabel.setBounds(lm+5+60+5,tm+10+30+10,150,30);
clearPriceTextFieldButton.setBounds(lm+5+60+5+150+2,tm+10+30+10,30,30);

categoryCaptionLabel.setBounds(lm+5,tm+10+30+10+30+10,100,30);
finishedGoodsRadioButton.setBounds(lm+5+100+5,tm+10+30+10+30+10,150,30);
rawMaterialRadioButton.setBounds(lm+5+100+5+150+5,tm+10+30+10+30+10,140,30);
consumableRadioButton.setBounds(lm+5+100+5+150+5+140+5,tm+10+30+10+30+10,150,30);
categoryLabel.setBounds(lm+5+100+5,tm+10+30+10+30+10,350,30);

JPanel p1=new JPanel();
p1.setLayout(null);
p1.setBorder(BorderFactory.createLineBorder(Color.gray));
p1.setBounds(663/2-310/2,tm+10+30+10+30+10+30+10,310,70);
p1.setLayout(null);
addButton.setBounds(10,10,50,50);
editButton.setBounds(70,10,50,50);
deleteButton.setBounds(130,10,50,50);
cancelButton.setBounds(190,10,50,50);
exportToPDFButton.setBounds(250,10,50,50);
p1.add(addButton);
p1.add(editButton);
p1.add(deleteButton);
p1.add(cancelButton);
p1.add(exportToPDFButton);
add(nameCaptionLabel);
add(nameTextField);
add(clearNameTextFieldButton);
add(nameLabel);
add(priceCaptionLabel);
add(priceTextField);
add(clearPriceTextFieldButton);
add(priceLabel);

add(categoryCaptionLabel);
add(finishedGoodsRadioButton);
add(rawMaterialRadioButton);
add(consumableRadioButton);
add(categoryLabel);
add(p1);
}
private void setAppearance()
{
setBorder(BorderFactory.createLineBorder(Color.gray));
Font font=new Font("Verdana",Font.PLAIN,16);
nameCaptionLabel.setFont(font);
nameLabel.setFont(font);
nameTextField.setFont(font);
priceCaptionLabel.setFont(font);
priceLabel.setFont(font);
priceTextField.setFont(font);
categoryCaptionLabel.setFont(font);
finishedGoodsRadioButton.setFont(font);
rawMaterialRadioButton.setFont(font);
consumableRadioButton.setFont(font);
categoryLabel.setFont(font);
}
private void addListeners()
{
addButton.addActionListener(this);
editButton.addActionListener(this);
cancelButton.addActionListener(this);
deleteButton.addActionListener(this);
exportToPDFButton.addActionListener(this);
}
public void actionPerformed(ActionEvent ev)
{
if(ev.getSource()==addButton)
{
if(mode==VIEW_MODE)
{
this.setAddMode();
ItemPanel.this.setAddMode();
}
else
{
String name=nameTextField.getText().trim();
if(name.length()==0)
{
JOptionPane.showMessageDialog(this,"Name required");
nameTextField.requestFocus();
return;
}
String price=priceTextField.getText().trim();
if(price.length()==0)
{
JOptionPane.showMessageDialog(this,"Price required");
priceTextField.requestFocus();
return;
}
try
{
Integer.parseInt(price);
}catch(NumberFormatException numberFormatException)
{
JOptionPane.showMessageDialog(this,"Price should be numeric");
priceTextField.requestFocus();
return;
}
if(finishedGoodsRadioButton.isSelected()==false && rawMaterialRadioButton.isSelected()==false && consumableRadioButton.isSelected()==false)
{
JOptionPane.showMessageDialog(this,"Category required");
return;
}
ItemDTO item=new ItemDTO();
item.setName(name);
item.setPrice(Integer.parseInt(price));
if(finishedGoodsRadioButton.isSelected()) item.setCategory("F");
if(rawMaterialRadioButton.isSelected()) item.setCategory("R");
if(consumableRadioButton.isSelected()) item.setCategory("C");
try
{
itemModel.add(item);
}catch(ModelException modelException)
{
JOptionPane.showMessageDialog(this,modelException.getMessage());
return;
}
int index=itemModel.search(name);
table.setRowSelectionInterval(index,index);
table.scrollRectToVisible(new Rectangle(table.getCellRect(index,0,false)));
this.setViewMode();
ItemPanel.this.setViewMode();
}
}
if(ev.getSource()==editButton)
{
if(mode==VIEW_MODE)
{
int selectedRowIndex=table.getSelectedRow();
if(selectedRowIndex<0)
{
JOptionPane.showMessageDialog(this,"Select an item to edit");
return;
}
this.setEditMode();
ItemPanel.this.setEditMode();
}
else
{
String name=nameTextField.getText().trim();
if(name.length()==0)
{
JOptionPane.showMessageDialog(this,"Name required");
nameTextField.requestFocus();
return;
}
String price=priceTextField.getText().trim();
if(price.length()==0)
{
JOptionPane.showMessageDialog(this,"Price required");
priceTextField.requestFocus();
return;
}
try
{
Integer.parseInt(price);
}catch(NumberFormatException numberFormatException)
{
JOptionPane.showMessageDialog(this,"Price should be numeric");
priceTextField.requestFocus();
return;
}
if(finishedGoodsRadioButton.isSelected()==false && rawMaterialRadioButton.isSelected()==false && consumableRadioButton.isSelected()==false)
{
JOptionPane.showMessageDialog(this,"Category required");
return;
}
ItemDTO item=new ItemDTO();
item.setCode(this.item.getCode());
item.setName(name);
item.setPrice(Integer.parseInt(price));
if(finishedGoodsRadioButton.isSelected()) item.setCategory("F");
if(rawMaterialRadioButton.isSelected()) item.setCategory("R");
if(consumableRadioButton.isSelected()) item.setCategory("C");
try
{
itemModel.update(item);
}catch(ModelException modelException)
{
JOptionPane.showMessageDialog(this,modelException.getMessage());
return;
}
int index=itemModel.search(name);
table.setRowSelectionInterval(index,index);
table.scrollRectToVisible(new Rectangle(table.getCellRect(index,0,false)));
this.setViewMode();
ItemPanel.this.setViewMode();
}
}
if(ev.getSource()==deleteButton)
{
int selectedIndex=table.getSelectedRow();
if(selectedIndex<0)
{
JOptionPane.showMessageDialog(this,"Select an item to delete");
return;
}
this.setDeleteMode();
ItemPanel.this.setDeleteMode();
int selectedOption;
selectedOption=JOptionPane.showConfirmDialog(this,"Delete : "+this.item.getName(),"Delete Confirmation",JOptionPane.YES_NO_OPTION);
if(selectedOption==JOptionPane.YES_OPTION)
{
try
{
String name=this.item.getName();
itemModel.delete(this.item.getCode());
this.setItem(null);
JOptionPane.showMessageDialog(this,"Item : "+name+" deleted");
}catch(ModelException modelException)
{
JOptionPane.showMessageDialog(this,modelException.getMessage());
}
}
this.setViewMode();
ItemPanel.this.setViewMode();
}
if(ev.getSource()==cancelButton)
{
this.setViewMode();
ItemPanel.this.setViewMode();
}
if(ev.getSource()==exportToPDFButton)
{
this.setExportToPDFMode();
ItemPanel.this.setExportToPDFMode();
JFileChooser jfc=new JFileChooser();
jfc.setAcceptAllFileFilterUsed(false);
FileNameExtensionFilter fileFilter=new FileNameExtensionFilter("PDF Files","pdf");
jfc.addChoosableFileFilter(fileFilter);
jfc.setCurrentDirectory(new File("."));
int selectedOption=jfc.showSaveDialog(ItemPanel.this);
if(selectedOption==JFileChooser.APPROVE_OPTION)
{
File file=jfc.getSelectedFile();
String fullPath=file.getAbsolutePath();
File parent=new File(file.getParent());
if(parent.exists()==false)
{
JOptionPane.showMessageDialog(this,"Invalid path : "+fullPath);
this.setViewMode();
ItemPanel.this.setViewMode();
return;
}
if(parent.isDirectory()==false)
{
JOptionPane.showMessageDialog(this,parent.getAbsolutePath()+" is not a folder");
this.setViewMode();
ItemPanel.this.setViewMode();
return;
}
if(fullPath.endsWith(".pdf")==false)
{
if(fullPath.endsWith(".")) fullPath+="pdf";
else fullPath+=".pdf";
}
// code to export data to pdf starts here
try
{
com.itextpdf.text.Document document=new com.itextpdf.text.Document();
com.itextpdf.text.pdf.PdfWriter pdfWriter=com.itextpdf.text.pdf.PdfWriter.getInstance(document,new FileOutputStream(fullPath));
document.open();
com.itextpdf.text.Paragraph p=new com.itextpdf.text.Paragraph();
int k=itemModel.getRowCount();
int pageNumber=0;
int pageSize=40;
boolean newPage=true;
com.itextpdf.text.Image logo;
com.itextpdf.text.pdf.PdfPTable table;
com.itextpdf.text.Paragraph paragraph;
com.itextpdf.text.Font firmNameFont=new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.TIMES_ROMAN,16,com.itextpdf.text.Font.BOLD);
com.itextpdf.text.Font titleFont=new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.TIMES_ROMAN,14,com.itextpdf.text.Font.BOLD);
com.itextpdf.text.Font columnTitleFont=new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.TIMES_ROMAN,12,com.itextpdf.text.Font.BOLD);
com.itextpdf.text.Font dataFont=new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.TIMES_ROMAN,12,com.itextpdf.text.Font.NORMAL);
String firmName="ABC Retailers";
ItemDTO item;
int x=0;
while(x<k)
{
if(newPage)
{
//logo=com.itextpdf.text.Image.getInstance("logo.png");
//logo.setAbsolutePosition(100.0f,50.0f);
//document.add(logo);
paragraph=new com.itextpdf.text.Paragraph(firmName,firmNameFont);
paragraph.setAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
document.add(paragraph);

paragraph=new com.itextpdf.text.Paragraph("Items",titleFont);
paragraph.setAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
document.add(paragraph);
// add page number
// create table
// add columns/titles
pageNumber++;
newPage=false;
}
try
{
item=itemModel.getItemAt(x);
// add row to table with data from item and serial number as(x+1)
}catch(ModelException modelException)
{
// do nothing
}
// create and add row to table
x++;
if(x%pageSize==0 || x==k)
{
// create footer
if(x!=k)
{
document.newPage();
newPage=true;
}
}
}





document.close();
JOptionPane.showMessageDialog(this,fullPath+"  created");
}catch(Throwable t)
{
System.out.println(t.getMessage());
JOptionPane.showMessageDialog(this,"Cannot create pdf, contact administrator");
}
// code to export data to pdf ends here
}
this.setViewMode();
ItemPanel.this.setViewMode();
}
}
private void setAddMode()
{
mode=ADD_MODE;
nameTextField.setText("");
priceTextField.setText("");
finishedGoodsRadioButton.setSelected(false);
rawMaterialRadioButton.setSelected(false);
consumableRadioButton.setSelected(false);
nameLabel.setVisible(false);
priceLabel.setVisible(false);
categoryLabel.setVisible(false);
nameTextField.setVisible(true);
clearNameTextFieldButton.setVisible(true);
priceTextField.setVisible(true);
clearPriceTextFieldButton.setVisible(true);
finishedGoodsRadioButton.setVisible(true);
rawMaterialRadioButton.setVisible(true);
consumableRadioButton.setVisible(true);
addButton.setText("S");
editButton.setEnabled(false);
cancelButton.setEnabled(true);
deleteButton.setEnabled(false);
exportToPDFButton.setEnabled(false);
}

private void setDeleteMode()
{
mode=DELETE_MODE;
addButton.setEnabled(false);
editButton.setEnabled(false);
cancelButton.setEnabled(false);
deleteButton.setEnabled(false);
exportToPDFButton.setEnabled(false);
}

private void setExportToPDFMode()
{
mode=EXPORT_TO_PDF_MODE;
addButton.setEnabled(false);
editButton.setEnabled(false);
cancelButton.setEnabled(false);
deleteButton.setEnabled(false);
exportToPDFButton.setEnabled(false);
}


private void setEditMode()
{
mode=EDIT_MODE;
nameTextField.setText(item.getName());
priceTextField.setText(String.valueOf(item.getPrice()));
finishedGoodsRadioButton.setSelected(false);
rawMaterialRadioButton.setSelected(false);
consumableRadioButton.setSelected(false);
if(item.getCategory().equals("F")) finishedGoodsRadioButton.setSelected(true);
if(item.getCategory().equals("C")) consumableRadioButton.setSelected(true);
if(item.getCategory().equals("R")) rawMaterialRadioButton.setSelected(true);
nameLabel.setVisible(false);
priceLabel.setVisible(false);
categoryLabel.setVisible(false);
nameTextField.setVisible(true);
clearNameTextFieldButton.setVisible(true);
priceTextField.setVisible(true);
clearPriceTextFieldButton.setVisible(true);
finishedGoodsRadioButton.setVisible(true);
rawMaterialRadioButton.setVisible(true);
consumableRadioButton.setVisible(true);
editButton.setText("U");
addButton.setEnabled(false);
cancelButton.setEnabled(true);
deleteButton.setEnabled(false);
exportToPDFButton.setEnabled(false);
}
private void setViewMode()
{
mode=VIEW_MODE;
nameTextField.setVisible(false);
clearNameTextFieldButton.setVisible(false);
nameLabel.setVisible(true);
priceTextField.setVisible(false);
clearPriceTextFieldButton.setVisible(false);
priceLabel.setVisible(true);
finishedGoodsRadioButton.setVisible(false);
rawMaterialRadioButton.setVisible(false);
consumableRadioButton.setVisible(false);

categoryLabel.setVisible(true);
cancelButton.setEnabled(false);
addButton.setText("A");
editButton.setText("E");
addButton.setEnabled(true);
if(itemModel.getRowCount()==0)
{
editButton.setEnabled(false);
deleteButton.setEnabled(false);
exportToPDFButton.setEnabled(false);
}
else
{
editButton.setEnabled(true);
deleteButton.setEnabled(true);
exportToPDFButton.setEnabled(true);
}

}
public void setItem(ItemDTO item)
{
this.item=item;
if(this.item==null)
{
nameLabel.setText("");
priceLabel.setText("");
categoryLabel.setText("");
}
else
{
nameLabel.setText(this.item.getName());
priceLabel.setText(String.valueOf(this.item.getPrice()));
String category=item.getCategory();
if(category.equals("F"))
{
categoryLabel.setText("Finished Goods");
}
if(category.equals("R"))
{
categoryLabel.setText("Raw Material");
}
if(category.equals("C"))
{
categoryLabel.setText("Consumable");
}
}
}
}// inner class ends
}