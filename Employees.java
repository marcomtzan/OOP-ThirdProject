/**   
   @author       Marco Martinez
   @fileName     Employees.java
   @version      1.0
   @description  This program will construct and manipulate Employees objects.
   
   Classes
      EmployeeRecord
      Employee
      Hourly
      Salary
      Piece
      Employees
      AppDriver
   
   Associations
      EmployeeRecord(1) --- includes --- (1) Employee
      Hourly(1) --- inherits --- (1) Employee
      Salary(1) --- inherits --- (1) Employee
      Piece(1) --- inherits --- (1) Employee
      Employees(1) --- contains --- (m) Employee
      AppDriver(1) --- uses --- (1) Employees
   
   Employees Class Attributes
      INSTANCE VARIABLES
      (-) EmployeeRecord[] emps
      (-) int index
      (-) int currentIndex
   
      CLASS CONSTRUCTORS
      (+) Employees()
      (+) Employees(Employee newEmployee)
      (+) Employees(Employee[] newEmployee, int newIndex)
      (+) Employees(Employees newEmployees)
      
      CHANGE STATE SERVICES
      (+) void add(Employee e)
      (+) void add(Employee[] newEmployee, int newIndex)
      (+) void add(Employees newEmployees)
      (+) void delete(String lastName)
      (+) void delete(int eID)
      (+) void sort()
      (-) void qSort(int start, int finish)
      
      READ STATE SERVICES
      (+) int getLength()
      (+) Employee[] getEmp()
      (+) Employee getEmp(int indexPoint)
      (+) EmployeeRecord search(int eID)
      (+) EmployeeRecord search(String lastName)
      (-) EmployeeRecord biSearch(int key, int low, int high)
      (-) EmployeeRecord biSearch(String key, int low, int high)
      (+) int getCurrentIndex()
      (+) EmployeeRecord iterate()
      (+) boolean hasNext()
      (+) void resetIterator()
      (+) String toString()
   
   @date         10/11/2018

   Program Change Log 
   ==========================
   Name     Date     Description
   Marco    10/11    Create baseline for Employees.
   Marco    10/28    Add finishing touches to Employees.
   Marco    11/12    Adjust for inheritance model.
   Marco    12/8     Adjust per feedback. Removed multiple add and constructor methods. Removed "determineEmployee" method.
 */
 
public class Employees
{
   // CONSTANT DEFINITIONS
   public static final int EMPMAX = 100;
   
   // INSTANCE VARIABLE INITIALIZATIONS
   private Employee emps[] = new Employee[EMPMAX];
   private int index = 0;
   private int currentIndex = 0;
   
   // CLASS CONSTRUCTORS
   // (+) Employees() 				
   public Employees(){}
  
   // (+) Employees(Employee e)
   public Employees(Employee e)
   {
      this.emps[this.index] = e;
      (this.emps[this.index].get()).employeeNumber = this.index;
      this.index++;
   }
   
   // (+) Employees(Employee[] newEmployee, int newIndex)
   public Employees(Employee[] newEmployee, int newIndex)
   { 
      if (newIndex < EMPMAX)
      {
         for (int i = 0; i < newIndex; i++)
         {
            this.emps[this.index] = newEmployee[i];
            (this.emps[this.index].get()).employeeNumber = this.index;
            this.index++;
         }
      }
   }
   
   // (+) Employees(Employees newEmployees)
   public Employees(Employees newEmployees)
   {
      if (newEmployees.getLength() < EMPMAX)
      {
         for(int i = 0; i < newEmployees.getLength(); i++)
         {
            this.emps[this.index] = newEmployees.getEmp(i);
            (this.emps[this.index].get()).employeeNumber = this.index;
            this.index++;
         }
      }

   }
   
   // CHANGE STATE SERVICES
   // (+) void add(Employee e)
   public void add(Employee e)
   {  
      this.emps[this.index] = e;
      (this.emps[this.index].get()).employeeNumber = this.index;
      this.index++;
   }
   
   // (+) void add(Employee[] newEmployee, int newIndex)
   public void add(Employee[] newEmployee, int newIndex)
   {
      if (this.index + newIndex < EMPMAX)
      {
         for (int i = 0; i < newIndex; i++)
         {
            this.emps[this.index] = newEmployee[i];
            (this.emps[this.index].get()).employeeNumber = this.index;
            this.index++;
         }
      }
   }
      
   // (+) void add(Employees newEmployees)
   public void add(Employees newEmployees)
   {  
      if (this.index + newEmployees.getLength() < EMPMAX)
      {
         for(int i =  0; i < newEmployees.getLength(); i++)
         {
            this.emps[this.index] = newEmployees.getEmp(i);
            (this.emps[this.index].get()).employeeNumber = this.index;
            this.index++;
         }
      }
   }
   
   // (+) void delete(int eID)
   public void delete(int eID)
   {
      if ((search(eID).lastName) != null)
      {
         this.emps[search(eID).employeeNumber] = this.emps[index-1];
         this.emps[index-1] = new Hourly();
         this.index--;
      }
      else return;
   }
   
   // (+) void delete(String lastName)
   public void delete(String lastName)
   {
      if ((search(lastName).lastName) != null)
      {
         this.emps[(search(lastName)).employeeNumber] = this.emps[index-1];
         this.emps[index-1] = new Hourly();
         this.index--;
      }
      else return;
   }
   
   // (+) void sort()
   public void sort()
   {
      if (this.index > 0) qSort(0, this.index-1);
   }
   
   // (-)  void qSort(int start, int finish)
   private void qSort(int start, int finish)
   {
      int i = start;
      int j = finish;
      String pivot = (this.emps[start + (finish - start) / 2].get()).lastName;

      while (i <= j) 
      {
         while ((this.emps[i].get()).lastName.compareToIgnoreCase(pivot) < 0) 
         {
            i++;
         }

         while ((this.emps[j].get()).lastName.compareToIgnoreCase(pivot) > 0) 
         {
            j--;
         }

         if (i <= j) 
         {
            Employee temp = this.emps[i];
            this.emps[i] = this.emps[j];
            this.emps[j] = temp;
            i++;
            j--;
         }
      }
    
        if (start < j) {
            qSort(start, j);
        }
        if (i < finish) {
            qSort(i, finish);
        }
        
        for(int n = 0; n < this.index; n++) (this.emps[n].get()).employeeNumber = n;
   }
   
   // READ STATE SERVICES
   // (+) int getLength()
   public int getLength()
   {
      return this.index;
   }
   
   // (+) Employee[] getEmp()
   public Employee[] getEmp()
   {
      return this.emps;
   }
   
   // (+) Employee getEmp(int indexPoint)
   public Employee getEmp(int indexPoint)
   {
      return this.emps[indexPoint];
   }
   
   // (+) EmployeeRecord search(int eID)
   public EmployeeRecord search(int eID)
   {
      return biSearch(eID, 0, this.index-1);
   }
   
   // (+) EmployeeRecord search(String lastName)
   public EmployeeRecord search(String lastName)
   {
      return biSearch(lastName, 0, this.index-1);
   }
   
   // (-)  EmployeeRecord biSearch(int key, int low, int high)
   private EmployeeRecord biSearch(int key, int low, int high)
   {       
      while(high >= low) 
      {
         int middle = (low + high) / 2;
         if ((this.emps[middle].get()).employeeNumber == key)
         {
            return this.emps[middle].get();
         }
         if ((this.emps[middle].get()).employeeNumber > key) 
         {
            return biSearch(key, low, middle-1);
         }
         if ((this.emps[middle].get()).employeeNumber < key) 
         {
            return biSearch(key, middle+1, high);
         }   
      }   
      return new EmployeeRecord();
   }
      
   // (-)  EmployeeRecord biSearch(String key, int low, int high)
   private EmployeeRecord biSearch(String key, int low, int high)
   {       
      while(high >= low) 
      {
         int middle = (low + high) / 2;
         if ((this.emps[middle].get()).lastName.compareToIgnoreCase(key) == 0)
         {
            return this.emps[middle].get();
         }
         if ((this.emps[middle].get()).lastName.compareToIgnoreCase(key) > 0) 
         {
            return biSearch(key, low, middle-1);
         }
         if ((this.emps[middle].get()).lastName.compareToIgnoreCase(key) < 0) 
         {
            return biSearch(key, middle+1, high);
         }   
      }   
      return new EmployeeRecord();
   }
   
   // (+) int getCurrentIndex()
   public int getCurrentIndex()
   {
      return this.currentIndex;
   }
   
   // (+) EmployeeRecord iterate()
   public EmployeeRecord iterate()
   {
      EmployeeRecord temp = new EmployeeRecord(this.emps[this.currentIndex].get());
      this.currentIndex++;
      return temp;
   }
   
   // (+) void resetIterator()
   public void resetIterator()
   {
      this.currentIndex = 0;
   }
   
   // (+) boolean hasNext()
   public boolean hasNext()
   {
      if (this.currentIndex < this.index)
      {
         return true;
      }
      else
      {
         return false;
      }
   }
   
   // (+) String toString()
   public String toString()
   {
      String str = "" ;
      
      for(int i = 0; i < this.index; i++) str += "\n" + this.emps[i].toString();
      
      return str;
   }
}