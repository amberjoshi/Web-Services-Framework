import java.io.*;
class addItem
{
public static void main(String gg[])
{
int code;
int vcode;
String name=gg[0];
String vname;
try{
File file=new File("ItemData");
RandomAccessFile raf=new RandomAccessFile(file,"rw");
if(raf.length()>0)
{
raf.readLine();
while(raf.getFilePointer()<raf.length())
{
raf.readLine();
if(raf.readLine().equals(name)==true)
{
System.out.println("Item already exists.");
System.exit(0);
}
}
}
raf.seek(0);
if(raf.getFilePointer()==raf.length())
{
code=1;
raf.writeBytes(String.format("%10d",code));
raf.writeBytes("\n");
}
else
{
code=Integer.parseInt((raf.readLine()).trim());
code=code+1;
raf.seek(0);
raf.writeBytes(String.format("%10d",code));
raf.writeBytes("\n");
while(raf.getFilePointer()<raf.length())
{
raf.readLine();
}
}
raf.writeBytes(String.valueOf(code));
raf.writeBytes("\n");
raf.writeBytes(name);
raf.writeBytes("\n");
raf.seek(0);
raf.readLine();
while(raf.getFilePointer()<raf.length())
{
vcode=Integer.parseInt(raf.readLine());
vname=raf.readLine();
System.out.println("Code:"+vcode);
System.out.println("Item:"+vname);
}
raf.close();
}catch(Exception exception)
{
}
}
}