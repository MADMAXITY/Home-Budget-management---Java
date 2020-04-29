import java.util.*;
import java.lang.*;
import java.io.File;
import java.io.FileWriter;


class People
{
    int plans[] = {1500, 22000, 5500, 18500, 11000};
    String name;
    int salary;
    int saving_option;
    int flag = 0;

    boolean check(int opt)
    {
        if (opt> 5 || opt < 1)
        {   System.out.println("Incorrect Saving Plans Options");
            return(false); }
        else{return true;}
    }

    void get_details(int i)
    {
        
        i = i + 1;
        Scanner sc = new Scanner(System.in);
        while(flag==0)
        {
            System.out.println("Saving Options : 1) 1500. 2) 22000. 3) 5500. 4) 18500. 5) 11000 ");
            System.out.println("Enter Name, Salary and Saving Option Number For House Member "+i+" : ");
            name = sc.next();
            salary = sc.nextInt();
            saving_option = sc.nextInt();
            if(check(saving_option)==true)
            flag=1;



        }
        

    }

}


class Home 
{
    int electricy_bill;
    int telephone_bill;
    int grocery_bill;
    int others;
    
    void get_details_for_home()
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Give Following Details For Your Monthly House Expenses");
        System.out.print("Electricity Bill : ");
        electricy_bill = sc.nextInt();
        System.out.print("Telephone Bill : ");
        telephone_bill = sc.nextInt();
        System.out.print("Grocery Bill : ");
        grocery_bill = sc.nextInt();
        System.out.print("Others : ");
        others = sc.nextInt();
        System.out.print("\n");




    }



}


class suggestions_and_finalize extends Home
{   
    People members[];
    Home home;
    int n;

    float salaries[];
    int saving_option[];
    String names[];

    float total_money = 0;
    int bill = 0;

    float contributions[];
    int plans[] = {1500, 22000, 5500, 18500, 11000};

    suggestions_and_finalize(People[] p,Home h,int nums)
    {   
        members = p;
        home = h;
        n = nums;

    }

    void analyze()
    {
        salaries = new float[n];
        saving_option = new int[n];
        names = new String[n];
        contributions = new float[n];
        for(int j=0;j<n;j++)
        {
            
            salaries[j] = members[j].salary;

            float temp =  salaries[j] * 25/100;
            total_money+=temp;
            contributions[j] = salaries[j] * 25/100;
            saving_option[j] = members[j].saving_option;
            names[j] = members[j].name;

        }
        bill += (home.electricy_bill + home.grocery_bill + home.telephone_bill + home.others);
       
        if(bill <= total_money)
        {   
            float left = (total_money - bill)/n;
            for(int var=0;var<n;var++)
            {
                contributions[var] += left;

            }

            System.out.println("\n\nThe Budget is well under control");
            System.out.println("Total Spending : "+bill+"\nTotal Money Contributed By each family member : "+bill/n+"\n");
            System.out.println("The below chart shows How everyone contributed 25% of their income and not 25% of the total bill\nThe rest of the money contributed by everyone will go in savings fund.");

            for(int line=0;line<2;line++){System.out.print("--------------------------------------------------------------------------------\n");}
            System.out.println("Name      Salary      SavingsPLan     Contributed     Saved");
            for(int r=0;r<n;r++)
            {
                float saved = salaries[r] - contributions[r];
                System.out.println(names[r]+"     "+salaries[r]+"     "+plans[saving_option[r]]+"        "+contributions[r]+"        "+saved);
            }
        }

        else

        {   
            
            for(int line=0;line<2;line++){System.out.print("--------------------------------------------------------------------------------\n");}
            System.out.println("Total Spending : "+bill+"\nTotal Money Contributed By each family member : "+bill/n+"\n");
            System.out.print("The Budget is not under control\nSuggestions:\n");
            System.out.print("1. Every Family member must increase their percent in the contribution\n");
            System.out.print("2. Lower the usage of Electricity\n");
            System.out.print("3. Lower the usage of Telephone\n");
            float max = -1;
            int ind=0;
            for(int r = 0;r<n;r++)
            {
                if(salaries[r]>max)
                {   max = salaries[r];
                    ind = r;
                }
            }    
            System.out.print("4. "+names[ind]+" Is the richest person in the family so he should make more contribution\n");
            for(int line=0;line<2;line++){System.out.print("--------------------------------------------------------------------------------\n");}
            System.out.println("Name      Salary      SavingsPLan     Contributed     Saved");
            for(int r=0;r<n;r++)
            {
                float saved = salaries[r] - contributions[r];
                System.out.println(names[r]+"     "+salaries[r]+"     "+plans[saving_option[r]]+"        "+contributions[r]+"        "+saved);
            }
            


        }

        
        
        


    }

    class textgenerator
    {
        public void textgenerate()
        {
            try
            {
                FileWriter file = new FileWriter("Home_budget_management.txt");
                String a1 = "Total Spending : "+bill+"\nTotal Money Contributed By each family member : "+bill/n+"\n";
                String a2 = "\n--------------------------------------------------------------------------------\n";
                String a3 = "\nThe below chart shows How everyone contributed 25% of their income and not 25% of the total bill\nThe rest of the money contributed by everyone will go in savings fund.";
                file.write(a1);
                file.write(a3);
                String a4 = "\nName      Salary      SavingsPLan     Contributed     Saved";
                for(int line=0;line<2;line++){file.write(a2);}
                file.write(a4);
                for(int r=0;r<n;r++)
                {
                    float saved = salaries[r] - contributions[r];
                    String a5 = "\n"+names[r]+"     "+salaries[r]+"     "+plans[saving_option[r]]+"        "+contributions[r]+"        "+saved;
                    file.write(a5);
                }
                file.close();
                System.out.print("\n"+a2+"A file named 'Home_budget_management.txt' has been created with this data\n"+a2);
                



            }
            catch(Exception e)
            {
                 System.out.print("Exception Caught\n"+e);
            }
        }
    }
    
        

    
}



abstract class information_gathering
{
    public abstract void get_det_people(People obj,int i);
    public abstract void get_det_home(Home home);
    
}



public class Home_Budget_management extends information_gathering
{

    public void get_det_home(Home home)
    {
        home.get_details_for_home();
    }

    public void get_det_people(People obj,int i)
    { 
        obj.get_details(i);
    }


    public static void main(String []nouse)
    {

        Home_Budget_management home_manage = new Home_Budget_management();
        Home home = new Home();
        home_manage.get_det_home(home);

        Scanner sc = new Scanner(System.in);
        System.out.print("The Number of earning Members in the family : ");
        int num_of_members = sc.nextInt();
        System.out.println();

        People people[] = new People[num_of_members];

        for(int ii=0;ii<num_of_members;ii++)
        {
            people[ii] = new People();
            home_manage.get_det_people(people[ii], ii);
        }

        suggestions_and_finalize object = new suggestions_and_finalize(people, home,num_of_members);
        object.analyze();

        suggestions_and_finalize.textgenerator text = object.new textgenerator();
        text.textgenerate();
        
        

        

    }



}