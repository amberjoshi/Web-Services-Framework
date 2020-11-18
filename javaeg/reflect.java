import java.lang.reflect.*;
class pspreflect
{
public static void main(String gg[])
{
String className=gg[0];
try{
Class c=Class.forName(className);
Method methods[]=c.getMethods();
for(Method method:methods)
{
System.out.println(method);
}
}catch(Exception e)
{

}
}
}


