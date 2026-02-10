
// Employee
// ----------
// name
// age
// gender
// salary
// designation
// department
 
 
// * Find the highest salary paid employee -> 
// * Find how many male & female employees working in company (numbers)
// * Total expense for the company department wise
// * Who is the top 5 senior employees in the company
// * Find only the names who all are managers
// * Hike the salary by 20% for everyone except manager
// * Find the total number of employees

import java.util.*;
import java.util.stream.*;
import java.util.function.*;
class Emp{
    String name;
    int age;
    String gender;
    int salary;
    String designation;
    String department;
    public Emp(String name, int age, String gender, int salary,String designation, String department){
        this.name = name;
        this.age=age;
        this.gender=gender;
        this.salary=salary;
        this.designation=designation;
        this.department=department;
    }
    public double getSalary(){
        return this.salary;
    }
    public String getGender(){
        return this.gender;
    }
    public int getAge(){
        return this.age;
    }
    public String getName(){
        return this.name;
    }
    public String getDesignation(){
        return this.designation;
    }
    public String toString(){
        return "(" + name + ","+ age + ","+ gender + ","+ salary + ","+ designation + "," + department + ")";
    }
    public void setSalary(int amount) {
        this.salary += amount;
    }
}

public class StreamAssignment {
    public static void main(String[] args) {
        List<Emp> list = new ArrayList<Emp>();

        list.add(new Emp("Rakesh",22,"Male",22200,"Intern","HR"));
        list.add(new Emp("Sita", 25, "Female", 50000, "Developer", "IT"));
        list.add(new Emp("Amit", 35, "Male", 80000, "Manager", "Sales"));
        list.add(new Emp("Priya", 29, "Female", 60000, "Lead", "IT"));
        list.add(new Emp("John", 45, "Male", 90000, "Manager", "HR"));
        list.add(new Emp("Rahul", 24, "Male", 25000, "Intern", "IT"));
        
        Optional<Emp> highestPaid = list.stream().max(Comparator.comparingDouble(Emp::getSalary));
        System.out.println("1. Highest Paid Employee: ");
        System.out.println(highestPaid); // 1.highest paid employee
        System.out.println();

        // Map<String,Long> m4 = list.stream().collect(Collectors.groupingBy(e->e.gender,Collectors.counting())); // group by is inefficient .. it will do multiple iterations .. in case of partitioningBy only 2 buckets .. yes or no 
        // System.out.println(m4); // 2.no. of males n females

        Map<Boolean, Long> genderCount =
        list.stream()
            .collect(Collectors.partitioningBy(
                    e -> e.getGender().equalsIgnoreCase("Male"),
                    Collectors.counting()
            ));
        System.out.println("2.Male vs Female");
        System.out.println("Male count   : " + genderCount.get(true));
        System.out.println("Female count : " + genderCount.get(false));
        System.out.println();



        Map<String,Integer> m6 = list.stream().collect(Collectors.groupingBy(e->e.department,Collectors.summingInt(e->e.salary))); 
        System.out.println("3.total expense for the company, department wise");
        System.out.println(m6); // 3.total expense for the company, department wise // this is correct
        System.out.println();

        Predicate<Emp> p1 = x -> x.designation.equals("Manager");

        List<String> m5 = list.stream().filter(p1).map(Emp::getName).collect(Collectors.toList()); 
        System.out.println("4.Name of managers : ");
        System.out.println(m5); // 5.print names of managers //correct
        System.out.println();

        
        // 6. Top 5 senior-most employees
        List<Emp> seniorEmployees =list.stream().sorted(Comparator.comparingInt(Emp::getAge).reversed()).limit(5).collect(Collectors.toList());
        System.out.println("5.Top 5 seniormost employees");
        System.out.println(seniorEmployees);
        System.out.println();

                // 6. Hike salary by 20% for everyone except Manager
        list.stream().filter(p1.negate()).forEach(e -> e.setSalary((int)(e.getSalary() * 1.2)));
        System.out.println("6.hiked employees , manager salary is same");
        System.out.println(list);
        System.out.println();

        long  m3 = list.size(); // dont use streams unnecessarily .. if not required dont use it
        System.out.println("7.No. of employees: " + m3); // 7.Total count


    }

    
}
