import java.io.*;
class psp
{
public static void main(String gg[])
{
try
{ 
InputStreamReader isr;
isr=new InputStreamReader(System.in);
BufferedReader br=new BufferedReader(isr);
char m;
System.out.print("Enter a character : ");
m=(char)br.read();
System.out.println(m);
char j;
System.out.print("Enter another character : ");
br.read();
br.read();
j=(char)br.read();
System.out.println(j);
}catch(IOException ioe)
{
System.out.println(ioe);
}
}
}