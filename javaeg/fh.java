import java.io.*;
class assignment
{
private String name;
private int code;
private int lastcode;
public int y=0;
public void addItem()
{
try
{
File f;
f=new File("itemData");
RandomAccessFile raf=new RandomAccessFile(f,"rw");
raf.seek(raf.length());
while(y==0)
{
code=lastcode+1;
BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
System.out.println("1.Add name ");
System.out.println("2.Exit");
System.out.print("Enter your choice: ");
String ch=br.readLine();
if(ch.equals(String.valueOf(1))==true)
{
System.out.println("Enter the name: ");
String name=br.readLine();
raf.writeBytes(String.valueOf(code));
raf.writeBytes(".");
raf.writeBytes(name);
raf.writeBytes("\n");
}
else
{
break;
}
lastcode=code;
}
raf.close();
}catch(Exception e)
{
}
}
}
class psp
{
public static void main(String gg[])
{
assignment c=new assignment();
c.addItem();
}
}