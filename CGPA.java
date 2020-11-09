import javax.swing.JOptionPane;

public class CGPA {

    public static void main(String[] args) {
        System.out.println("WARNING : STARTING THE PROGRAM \nAdvanced CGPA CALCULATOR\n");
        CGPACalc cgpa = new CGPACalc();
        int input = JOptionPane.showConfirmDialog(null, "Would you like to start the program?");
        if(input == 0) {
            cgpa.addSubject();
            Calculate calc = new Calculate(cgpa);
            int yes = JOptionPane.showConfirmDialog(null, "Display your result?");
            if(yes == 0) {
                //display all subject start
                Display dsply = new Display();
                dsply.display(cgpa);
                //display all subject end
                JOptionPane.showMessageDialog(null, "Student's CGPA information\nTotal Subject taken : " + calc.totalSubject + "\nYour Credit Hour : " + calc.totalCreditAchieve + "/" + calc.totalCreditHour + "\nCGPA : " +calc.totalPointer);
                System.out.println("\nStudent's CGPA information\nTotal Subject taken : " + calc.totalSubject + "\nTotal Credit Hour taken : " + calc.totalCreditHour + "\nYour Credit Hour : " + calc.totalCreditAchieve + "/" + calc.totalCreditHour + "\nCGPA : " +calc.totalPointer);
		JOptionPane.showMessageDialog(null, "Task complete! ,Exit program!");
            } else {
                JOptionPane.showMessageDialog(null, "INFO : result will not displayed");
            }
        }
        //final should execute normally
        System.exit(0);
    }
}

class Display{
    public void display(CGPACalc obj){
        System.out.println("\nRESULT OBTAINED");
        for(int i = 0; i < obj.course.length; i++){
            System.out.println((i+1) + ". " + obj.course[i].subjectName + "  -  Grade : " + obj.course[i].grade + "  -  Credit Hour : " + obj.course[i].creditHour);
        }
        System.out.println("-------------------------");
    }
}

abstract class Calculator
{
    //wrapper class
    Integer totalSubject = new Integer("0");
    Double totalCreditHour = new Double("0.00");
}
class CGPACalc extends Calculator
{
    Subject[] course = new Subject[1]; //atribute - a collection of subject were stored here
    //end of attribute
    
    CGPACalc(){ //methods - constructor
        System.out.println("INFO : CGPA - operate.");
    }
    private Subject[] increaseArray(Subject [] sub) {
        int newLength = sub.length+1;
        Subject[] course = new Subject[newLength];
        course = FillArray(sub, course);
        return course;
    }
    private Subject[] FillArray(Subject[] origin, Subject[] duplicate)
    {
        for(int i = 0; i < origin.length; i ++)
        {
            duplicate[i] = origin[i];
        }
        return duplicate;
    }
    public void addSubject(){
        int reply = JOptionPane.showConfirmDialog(null, "Would you like to start input a subject? ", "CGPA calculator", JOptionPane.YES_NO_OPTION);
        if(reply == 0){
            JOptionPane.showMessageDialog(null, "Please enter a several information after this message dialog.");
            while(reply == 0) {
                Integer hour = new Integer(JOptionPane.showInputDialog("Enter a credit hours of the Subject"));
                totalCreditHour += hour;
                String subjectName = new String(JOptionPane.showInputDialog("Enter the name of subject(course)"));
                JOptionPane.showMessageDialog(null, "Please enter number provided based on your grade achieved");
                String grade = JOptionPane.showInputDialog("1 = A/A+\n2 = A-\n3 = B+\n4 = B\n5 = B-\n6 = C+\n7 = C\n8 = C-\n9 = D+\nD = D\nN = E\nM = E-\nF = F");
                course[totalSubject] = new Subject(hour, subjectName, grade.charAt(0));
                totalSubject++;
                System.out.println("Info : Length for first :" + course.length);
                reply = JOptionPane.showConfirmDialog(null, "Would you like to enter much more subject? currently subject added is " + totalSubject , "CGPA calculator", JOptionPane.YES_NO_OPTION);
                if(reply == 0){
                    course = increaseArray(course); //increase 1 length.
                    System.out.println("Info : Length for second :" + course.length + "\nFirst value : " + course[totalSubject-1].subjectName);
                } //else exit this methods
            }
        } else {
            JOptionPane.showMessageDialog(null, "Thank you for using our services! Terminate now!");
            System.exit(0);
        }
    }
}

class Calculate extends Calculator
{
    //attribute
    Double totalPointer = new Double("0.00");
    Double totalCreditAchieve = new Double("0.00");
    //totalPointer
    
    //procedure
    Calculate(CGPACalc objectCGPACalc) {
        totalSubject = objectCGPACalc.totalSubject;
        totalCreditHour = objectCGPACalc.totalCreditHour;
        cgpa(objectCGPACalc);
    }
    private void cgpa(CGPACalc CGPA){
        for(int i = 0; i < totalSubject; i++)
        {
            totalCreditAchieve += returnPoint((CGPA.course[i].grade), (CGPA.course[i].creditHour));
        }
        totalPointer = totalCreditAchieve/totalCreditHour;
    }
    private double returnPoint(char Grade, double credit)
    {
        double point;
        switch(Grade){
                case '1': //grade A/A+
                    point = 4.0*credit;
                    break;
                case '2': //grade A-
                    point = 3.67*credit;
                    break;
                case '3': //grade B+
                    point = 3.33*credit;
                    break;
                case '4': //grade B
                    point = 3.0*credit;
                    break;
                case '5': //grade B-
                    point = 2.67*credit;
                    break;
                case '6': //grade C+
                    point = 2.33*credit;
                    break;
                case '7': //grade C
                    point = 2.0*credit;
                    break;
                case '8': //grade C-
                    point = 1.67*credit;
                    break;
                case '9': //grade D+
                    point = 1.33*credit;
                    break;
                case 'D': //grade D
                    point = 1.00*credit;
                    break;
                case 'N': //grade E
                    point = 0.67*credit;
                    break;
                case 'M': //grade E-
                    point = 0.33*credit;
                    break;
                case 'F': //grade F
                    point = 0.0*credit;
                    break; 
                default: {
                    point = 0;
                    JOptionPane.showMessageDialog(null, "Something wrong occur while input a grade. You are advices to repeat the program. ");
                    System.exit(0);
                }
            }
        return point;
    }
}

class Subject
{
    Integer creditHour = new Integer("0");
    String subjectName = new String("");
    char grade;
    Subject(int hour, String name, char gred){
        creditHour = hour;
        subjectName = name;
        grade = gred;
    }
}
