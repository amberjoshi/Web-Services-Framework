import java.util.*;
class employee implements Comparable<employee>
{
private int code;
private String name;
public boolean compareTo(String name)
{
return this.name.toUpperCase().equalsIgnoreCase(name.toUpperCase());
}
public int getcode()
{
return code;
}
public void setcode(int code)
{
this.code=code;
}
public String getname()
{
return name;
}
public void setname(String name)
{
this.name=name;
} 
}
class psp
{
public static void main(String gg[])
{
ArrayList<employee> list=new ArrayList<employee>();
employee e1=new employee();
e1.setcode(1);
e1.setname("Pradeep");
list.add(e1);
employee e2=new employee();
e2.setcode(2);
e2.setname("Mahesh");
list.add(e2);
employee e3=new employee();
e3.setcode(3);
e3.setname("jaggu");
list.add(e3);
Collections.sort(list);
for(employee e: list)
{
System.out.println(e.getcode()+". "+e.getname()+"\n");
}

}
}