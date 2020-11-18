import java.io.*;
import java.net.*;
class eg1client
{
public static void main(String gg[])
{
try
{
int rollNumber=Integer.parseInt(gg[0]);
String name=gg[1];
String gender=gg[2];
String request=rollNumber+","+name+","+gender+"#";
String server=gg[3];
int serverPort=Integer.parseInt(gg[4]);
System.out.println("Establishing connection with"+server+"listening to port"+serverPort);
Socket socket=new Socket(server,serverPort);
OutputStream os=socket.getOutputStream();
OutputStreamWriter osw=new OutputStreamWriter(os);
System.out.println("Sending request : "+request);
osw.write(request);
osw.flush();
InputStream is=socket.getInputStream();
InputStreamReader isr=new InputStreamReader(is);
StringBuffer sb=new StringBuffer();
int x;
while(true)
{
x=isr.read();
if(x==-1) break;
if(x=='#') break;
sb.append((char)x);
}
String responseReceived=sb.toString();
socket.close();
System.out.println("Response received : "+responseReceived);
}catch(Exception ee)
{
System.out.println(ee);
}
}
}