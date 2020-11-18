class cmnplace
{
private int num;
private boolean b=false;
synchronized public void setNum(int n)
{
if(b==true)
{ 
try
{
wait();
}catch(Exception e)
{
}
}
num=n;
System.out.println("Produced : "+num);
b=true;
notify();
}
synchronized public int getNum()
{ 
if(b==false)
{ 
try
{
wait();
}catch(Exception e)
{
}
}
System.out.println("Consumed : "+num);
b=false;
notify();
return num;
}
} 
class Producer extends Thread
{
private cmnplace c;
Producer(cmnplace k)
{ 
c=k;
start();
}
public void run()
{
for(int e=201;e<=250;e++)
{ 
c.setNum(e);
}
}
}
class Consumer extends Thread
{
private cmnplace c;
Consumer(cmnplace k)
{ 
c=k;
start();
}
public void run()
{ 
int n;
for(int x=1;x<=50;x++)
{
n=c.getNum();
}
}
}
class example63psp
{
public static void main(String gg[])
{
cmnplace c=new cmnplace();
Producer pp=new Producer(c);
Consumer cc=new Consumer(c);
}
}