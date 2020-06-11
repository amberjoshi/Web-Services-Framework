# Web-Services-Framework
I have designed some custom annotations in java which will help user to -
1) Easily divert a request to specified method with just 1 line.
2) Forward the request to another .jsp file or service method with 1 line.
3) Restrict running of a method until programmer authenticates the user.
4) Easy way to get parameter from request without doing request.getParameter.

$[Know about the tools](#Tools)

$[Get to know installation process](#Installation)

# Tools -
## 1) Path Annotation

### Syntax- @Path("/urlpattern")

It can be applied to both classes and methods.When you want a "localhost::8080/sitename/math/getSum"to go to a Class named Maths and method name sum or anything then you need to apply this custom annotation
like-

@Path("/math")

public class Maths

{

@Path("/getSum")

public void sum()

{


}

}

## 2) Forward Annotation 

### Syntax-@Forward("/something.jsp")

When it is required for a reques to be forwarded to another service or a jsp file this annotaion comes in 
action as with one line a request can directly be forwarded to specified web page

like- 

public class Hello

{

@Forward("/math/sum")

public void good()

{

}

This request will be forwarded to another service.

note- Provide query string if service requires parameters

## 3)Secured Annotation

### Syntax- @Sercured("nameOfClass")

You need to implement a interface named AuthenticationScenarios in your class
and write two methods Boolean authenticate(HttpServletRequest,HttpSession,ServletContext) 
and void onAuthenticationFailed()
With there two methods defined by you the method with the secured annotation will
either be called on scenarios where authentication was successfull or will not be
called in scenarios where authentication failed.
like-

class gg

{

@Secured("LoginAuthenticator")

public void login()

{

System.out.println("Successfully logged in now i can do my work");

}

}

class LoginAuthenticator implements AuthenticationScenarios

{

public Boolean authenticate(HttpServletRequest req,HttpSession session,ServletContext context)

{

//you will choose what goes here

return false;

}

public void onAuthenticationFailed()

{

//you will choose what goes here

}

}

## 4)RequestData annotation
### Syntax- @RequestData("requestParameterName")
This annotation can be applied on parameter of a function
like-

class hello

{

public void sam(@RequestData("num1") int num1,@RequestData("num2") int num2)

{

//your code

}

}

Here Query String will be checked and parameter named as num1 and num2 will be extracted and

will be passed to sam while calling it.

Float String and Integer are Supported

# Installation

1) Download the service.jar file provided.

2) Paste the jar file in your lib folder of specified project.

3) Open the web.xml file and add this new servlet
```html
<servlet-name>TMService"</servlet-name>
<servlet-class>com.thinking.machines.servlets.TMService</servlet-class>
</servlet>
<servlet-mapping>
<servlet-name>TMService</servlet-name>
<url-pattern>/service/*</url-pattern>
</servlet-mapping>
```
note- You can change the url pattern to /* if you want to call this servlet every time.

4) Import pacakage

import com.thinking.machines.servlets.*;

5) Compile your java file while including the lib folder in your classpath 

6) Put a url to test it and Enjoy this wonderful tool !

