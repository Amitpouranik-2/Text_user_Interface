import java.io.*;

class MemberManager
{
private static final String DATA_FILE = "member.data";

public static void main (String gg[])
{
if(gg.length == 0)
{
System.out.println("Operation does not specify");
}
String Operation = gg[0];

if(!isOperationValid(Operation))
{
System.out.print("Invalid Operation :"+Operation);
System.out.print("Operations are Add , Update , remove  , get all , get by contact number , get by course ");
return;
}
if(Operation.equalsIgnoreCase("Add"))
{
Add(gg);
}
else if (Operation.equalsIgnoreCase("Update"))
{
Update(gg);
}
else if (Operation.equalsIgnoreCase("Remove"))
{
Remove(gg);
}
else if (Operation.equalsIgnoreCase("getAll"))
{
GetAll(gg);
}
else if (Operation.equalsIgnoreCase("getByCourse"))
{
GetByCourse(gg);
}
else if (Operation.equalsIgnoreCase("getByContactNumber"))
{
GetByContactNumber(gg);
}
}

// ADD -- CONTACT NUMBER , NAME , COURSES , FEE


private static void Add(String []data)
{
if(data.length != 5)
{
System.out.print("Not Provide a Sufficient Data to Add ....");
return;
}

String mobileNumber = data[1];
String name = data[2];
String course =  data[3];
if(!isCourseValid(course))
{
System.out.print(" ...Invalid Course :"+ course);
System.out.print("[C , C++ , Java , Python]");
return;
}

int fee;
try
{
fee = Integer.parseInt(data[4]);
}catch(NumberFormatException numberFormatException)
{
System.out.println("Fee Should be an Integer  Type Value ");
return;
}
try
{
File file = new File(DATA_FILE);
RandomAccessFile randomAccessFile;
randomAccessFile =  new RandomAccessFile(file , "rw");
String fmobileNumber;
while(randomAccessFile.getFilePointer() < randomAccessFile.length())
{
fmobileNumber = randomAccessFile.readLine();
if(fmobileNumber.equalsIgnoreCase(mobileNumber))
{
randomAccessFile.close();
System.out.print("Mobile Number : "+ mobileNumber + " Exists.");
return;
}
randomAccessFile.readLine();
randomAccessFile.readLine();
randomAccessFile.readLine();
}

randomAccessFile.writeBytes(mobileNumber);
randomAccessFile.writeBytes("\n");
randomAccessFile.writeBytes(name);
randomAccessFile.writeBytes("\n");
randomAccessFile.writeBytes(course);
randomAccessFile.writeBytes("\n");
randomAccessFile.writeBytes(String.valueOf(fee));
randomAccessFile.writeBytes("\n");
randomAccessFile.close();

System.out.print("Member Added ........");
} catch(IOException ioexception)
{
System.out.print(ioexception.getMessage());
return;
}
}


// UPDATE RECORDS

private static void Update(String []data)
{
if(data.length != 5)
{
System.out.println(" Wrong Number of Element passed for Updation ......");
System.out.println("Usage : MemberManager Update MobileNumber......");
return;
}
String mobileNumber = data[1];
String name = data[2];
String course = data[3];
if(!isCourseValid(course))
{
System.out.println("Invalid Course :" + course);
System.out.println(" [ C , C++ , Java , Python]");
return;
}
int fee =0;
try
{
fee = Integer.parseInt(data[4]);
}catch (NumberFormatException numberFormatException)
{
System.out.println("Invalid Fee :"+ fee);
return ;
}
try
{
File file = new File(DATA_FILE);
if(file.exists() == false)
{
System.out.println("No file Exists....");
return;
}

RandomAccessFile randomAccessFile;
randomAccessFile = new RandomAccessFile(file , "rw");
if(randomAccessFile.length()== 0)
{
System.out.println("No Member Is Exists");
randomAccessFile.close();
return;
}
boolean found = false;
String fmobileNumber ="";
String fname = "";
String fCourse = "";
int  ffee = 0;
while(randomAccessFile.getFilePointer() < randomAccessFile.length())
{
fmobileNumber = randomAccessFile.readLine();
fname = randomAccessFile.readLine();
fCourse = randomAccessFile.readLine();
ffee = Integer.parseInt(randomAccessFile.readLine());
if(fmobileNumber.equalsIgnoreCase(mobileNumber))
{
found = true;
break;
}
}
if(found == false)
{
System.out.println("Invlaid Mobile Number  : "+ mobileNumber );
randomAccessFile.close();
return;
}
System.out.println("Updation data is :" + mobileNumber);
System.out.println(" Name of Candidate is :"+ fname);

File tmpFile = new File("tmp.tmp");
RandomAccessFile  tmpRandomAccessFile;
tmpRandomAccessFile = new RandomAccessFile(tmpFile , "rw");
tmpRandomAccessFile.setLength(0);
randomAccessFile.seek(0);
while(randomAccessFile.getFilePointer() < randomAccessFile.length())
{
fmobileNumber = randomAccessFile.readLine();
fname = randomAccessFile.readLine();
fCourse = randomAccessFile.readLine();
ffee = Integer.parseInt(randomAccessFile.readLine());
if(fmobileNumber.equalsIgnoreCase(mobileNumber) == false)
{
tmpRandomAccessFile.writeBytes(fmobileNumber + "\n");
tmpRandomAccessFile.writeBytes(fname + "\n");
tmpRandomAccessFile.writeBytes(fCourse + "\n");
tmpRandomAccessFile.writeBytes(ffee +"\n");
}
else
{
tmpRandomAccessFile.writeBytes(mobileNumber + "\n");
tmpRandomAccessFile.writeBytes(name + "\n");
tmpRandomAccessFile.writeBytes( course+"\n");
tmpRandomAccessFile.writeBytes( fee + "\n");
}
}
randomAccessFile.seek(0);
tmpRandomAccessFile.seek(0);
while(randomAccessFile.getFilePointer() < randomAccessFile.length())
{
randomAccessFile.writeBytes(tmpRandomAccessFile. readLine() + "\n");
}
randomAccessFile.setLength(tmpRandomAccessFile.length());
tmpRandomAccessFile.setLength(0);
randomAccessFile.close();
tmpRandomAccessFile.close();
System.out.println("Data Updated.....");
}catch(IOException ioexception)
{
System.out.println(ioexception.getMessage());
return;
}
}


// REMOVE RECORDS


private static void Remove (String []data)
{
if(data.length !=  2)
{
System.out.println("Wrong Number of Element Passed for removal...");
System.out.println("Usage : Member Manager Remove MobileNumber");
return;
}

String mobileNumber = data[1];
int fee;
try
{
File file = new File(DATA_FILE);
if(file.exists()== false)
{
System.out.println("No File Exist");
return;
}
RandomAccessFile randomAccessFile;
randomAccessFile = new RandomAccessFile(file ,"rw");
if(randomAccessFile.length()== 0)
{
randomAccessFile.close();
System.out.println("no member Exists");
return;
}
boolean found = false;
String fmobileNumber = "";
String fname = "";
String fCourse = "";
int  ffee = 0;
while(randomAccessFile.getFilePointer() < randomAccessFile.length())
{

fmobileNumber = randomAccessFile.readLine();
fname = randomAccessFile.readLine();
fCourse = randomAccessFile.readLine();
ffee = Integer.parseInt(randomAccessFile.readLine());

if(fmobileNumber.equalsIgnoreCase(mobileNumber))
{
found = true;
break;
}
}

if(found == false)
{
System.out.println("Invalid Contact Number :"+ mobileNumber);
randomAccessFile.close();
return;
}

System.out.println("Deleting data of :"+mobileNumber);
System.out.println("Name of Candidate is :"+fname);

File tmpFile = new File("tmp.tmp");
RandomAccessFile tmpRandomAccessFile;
tmpRandomAccessFile = new RandomAccessFile(tmpFile  , "rw");
tmpRandomAccessFile.setLength(0);
randomAccessFile.seek(0);

while(randomAccessFile.getFilePointer() < randomAccessFile.length())
{
fmobileNumber = randomAccessFile.readLine();
fname = randomAccessFile.readLine();
fCourse = randomAccessFile.readLine();
ffee = Integer.parseInt(randomAccessFile.readLine());

if(fmobileNumber.equalsIgnoreCase(mobileNumber)== false)
{
tmpRandomAccessFile.writeBytes(fmobileNumber + "\n");
tmpRandomAccessFile.writeBytes(fname + "\n");
tmpRandomAccessFile.writeBytes(fCourse + "\n");
tmpRandomAccessFile.writeBytes(ffee + "\n");
}
}

randomAccessFile.seek(0);
tmpRandomAccessFile.seek(0);
while(tmpRandomAccessFile.getFilePointer() < tmpRandomAccessFile.length())
{
randomAccessFile.writeBytes(tmpRandomAccessFile.readLine() + "\n");
}
randomAccessFile.setLength(tmpRandomAccessFile.length());
tmpRandomAccessFile.setLength(0);
randomAccessFile.close();
tmpRandomAccessFile.close();
System.out.println("Data Deleted ....");
}catch(IOException ioexception)
{
System.out.println(ioexception.getMessage());
}
}


// Get ALL Records OF Members


private static void GetAll(String [] data)
{
try
{
File file = new File(DATA_FILE);
if(file.exists() == false)
{
System.out.print("No file");
return;
}

RandomAccessFile randomAccessFile;
randomAccessFile = new RandomAccessFile(file , "rw");
if(randomAccessFile.length() == 0)
{
randomAccessFile.close();
System.out.print("no Member");
return;
}

String mobileNumber;
String name;
String course;
int fee;
int memberCount =0;
int TotalFee =0;
 
while(randomAccessFile.getFilePointer() < randomAccessFile.length())
{
mobileNumber  = randomAccessFile.readLine();
name = randomAccessFile.readLine();;
course =randomAccessFile.readLine();
fee = Integer.parseInt(randomAccessFile.readLine());
System.out.printf("%s , %s , %s , %d \n " , mobileNumber , name , course , fee);
TotalFee += fee;
memberCount++;
}
randomAccessFile.close();
System.out.println(" total Registration : "+memberCount );
System.out.println("total fees :"+TotalFee);
}catch(IOException ioexception)
{
System.out.println(ioexception.getMessage());
}
}

// GET Records By Course 

private static void GetByCourse(String []data)
{
if(data.length!= 2)
{
System.out.println("Invalid Number of Data");
System.out.println("Usage : java Member Manager getBy Course : C/c++/java/j2ee/python");
return;
}
String course = data[1];
if(isCourseValid(course)== false)
{
System.out.println("Course does not exist :"+course);
return;
}
try
{
File file = new File(DATA_FILE);
if(file.exists()== false)
{
System.out.println("File is not Exist yet .....");
return;
}
RandomAccessFile randomAccessFile;
randomAccessFile = new RandomAccessFile(file , "rw");
if(randomAccessFile.length() == 0)
{
System.out.println("No member is exists");
return;
}

String fMobileNumber = "";
String fName = "";
String fCourse = "";
int ffee = 0;
boolean found = false;

while(randomAccessFile.getFilePointer() < randomAccessFile.length())
{
fMobileNumber = randomAccessFile.readLine();
fName = randomAccessFile.readLine();
fCourse = randomAccessFile.readLine();
ffee = Integer.parseInt(randomAccessFile.readLine());

if(fCourse.equalsIgnoreCase(course))
{
System.out.println("Contact Number :" +fMobileNumber);
System.out.println("Name :" +fName);
System.out.println("Course : "+fCourse);
System.out.println("Fees :"+ffee);
found = true;
break;
}
}
randomAccessFile.close();
if(found == false)
{
System.out.println("No Registration Against Course"+course);
return;
}
}catch(IOException ioexception)
{
System.out.println(ioexception.getMessage());
}
}


// GET Record By Contact Number 

private static void  GetByContactNumber(String []data)
{
String MobileNumber = data[1];
if(data.length!= 2)
{
System.out.println("Invalid Number of data ");
System.out.println("Usage : Java Member Manager Get By Contact Number : *************");
}
try
{
File file = new File(DATA_FILE);
if(file.exists()== false)
{
System.out.println("No File Exists");
return ;
}
RandomAccessFile randomAccessFile;
randomAccessFile = new RandomAccessFile(file , "rw");
if(randomAccessFile.length()==0)
{
System.out.println("No member Exist");
return;
}
String fMobileNumber = "";
String fName = "";
String fCourse = "";
int ffee = 0;
boolean found = false;
while(randomAccessFile.getFilePointer() < randomAccessFile.length())
{
fMobileNumber = randomAccessFile.readLine();
if(fMobileNumber.equalsIgnoreCase(MobileNumber)== true)
{
fName = randomAccessFile.readLine();
fCourse = randomAccessFile.readLine();
ffee = Integer.parseInt(randomAccessFile.readLine());
found = true;
break;
}
randomAccessFile.readLine();
randomAccessFile.readLine();
randomAccessFile.readLine();
}
randomAccessFile.close();
if(found == false)
{
System.out.print("Invalid Contact Number");
return;
}
System.out.println("Name :" + fName);
System.out.println("Course :"+ fCourse);
System.out.println("Fee :"+ ffee);
} catch(IOException ioexception)
{
System.out.println(ioexception.getMessage());
}
}


// HELPER Function 


private static boolean isOperationValid( String Operation)
{
Operation = Operation.trim();
String Operations[] = { "add" , "update " , "remove" , "getAll" , "getByCourse" , "getByContactNumber" };
for(int e=0; e<Operations.length; e++)
{
if(Operations[e].equalsIgnoreCase(Operation)) return true;
}
return false;
}

private static boolean isCourseValid(String course)
{
String courses[] = { "C" , "C++" ,  "java" , "python" };
for(int e=0; e<courses.length; e++)
{
if(courses[e].equalsIgnoreCase(course))return true;
}
return false;
}
}

