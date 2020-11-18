import java.io.*;
import java.net.*;
class eg1server
{
private ServerSocket serverSocket;
private int portNumber;
eg1server(int portNumber) throws Exception
{ 
this.portNumber=portNumber;
serverSocket=new ServerSocket(portNumber);
startListening();
}
public void startListening()
{ 
try
{
InputStream is;
InputStreamReader isr;
OutputStream os;
OutputStreamWriter osw;
StringBuffer sb;
int c1,c2;
int rollNumber;
String name,gender;
String pc1,pc2,pc3;
int x;
Socket socket;
String request,response;
while(true)
{
socket=serverSocket.accept(); // server will go in listening mode
System.out.println("Request arrived");
is=socket.getInputStream();
isr=new InputStreamReader(is);
sb=new StringBuffer();
while(true)
{
x=isr.read();
if(x==-1) break; // this case won't arise in our example
if(x=='#') break;
sb.append((char)x);
}
request=sb.toString();
System.out.println("Request : "+request);
// sample request 101,Sameer,M
c1=request.indexOf(","); // as per sample 3
c2=request.indexOf(",",c1+1); // as per sample 10
pc1=request.substring(0,c1); // as per sample 0,3 101 (end is ignored by substring)
pc2=request.substring(c1+1,c2); // as per sample 4,10 Sameer
pc3=request.substring(c2+1); // as per sample everything from 11 M
rollNumber=Integer.parseInt(pc1);
name=pc2;
gender=pc3;
System.out.println("Data received ");
System.out.println("Roll number : "+rollNumber);
System.out.println("Name : "+name);
System.out.println("Gender : "+gender);
// some code to save data in database or whatever is required by the application
response="Data Saved#";
os=socket.getOutputStream();
osw=new OutputStreamWriter(os);
osw.write(response);
osw.flush();
System.out.println("Response sent : "+response);
socket.close();
}
}
catch(Exception ee)
{
System.out.println(ee);
}
}
public static void main(String gg[])
{ 
int portNumber=Integer.parseInt(gg[0]);
System.out.println("Starting server on port : "+portNumber);
try
{ 
eg1server server=new eg1server(portNumber);
System.out.println("Server is ready and is listening on port : "+portNumber);
}catch(Exception e)
{
System.out.println(e);
}
}
}