/*Vending machine accepting denominations of only 1,5 and 10 INR.
Implementing Moore machine
First terminate the amchine temporarily, to shut it down
Displayable menu from where the customer can place order
Payble amount to be paid through 1, 5 and 10 INR only
*/

import java.util.*;
/**
 *
 * @author Aviral
 */
class State_Obj{
    String states;
    public void convert(String st){
        states=st;
    }
}
public class vending_machine {
    List<String> states_seque=new ArrayList(); // storing state sequences
    String[] items_sets=new String[4];         // storing choosed items
    int[] price=new int[4];                  // price of items  
    String items;
    int posn;
    int pay_del=0; 
    Scanner in=new Scanner(System.in);
    State_Obj state_objs=new State_Obj();       // storing next states
    String state_assigned;
    vending_machine(String states){
        state_objs.states=states;               
        states_seque.add(state_objs.states);       
        init();
    }
    void init(){                            
        items_sets[0]="X";
        items_sets[1]="Y";
        items_sets[2]="Z";
        items_sets[3]="W";
        price[0]=40;
        price[1]=30;
        price[2]=30;
        price[3]=20;
    }
    void initialising(String inputs){
        pay_del=0;
        states_seque.add("Initialising");                    // state 1 
        if(inputs.equals("S")){                          // Machine Starts
                if(state_objs.states.equals("Terminated")){
                    System.out.println("Initialising......");
                    state_assigned="Start";
                    start();
                }                        
        }
        else if(inputs.equals("q")){                     // temporarily terminating the machine
            if(state_objs.states.equals("Terminated")){
                state_assigned="Terminated";
                state_objs.convert(state_assigned);
                terminated();
            }
            else{
                state_assigned="Terminating";
                state_objs.convert(state_assigned);
                terminating();
                
            }
        }
        else if(inputs.equals("B")){                     // Option to select other items
            if(state_objs.states.equals("Dispose")){
                state_assigned="Start";
                states_seque.add("Start");
            }
                
        }
        
        state_objs.convert(state_assigned);
    }
    void start(){
        states_seque.add("Start");                         // Selected state starts
        int i;
        menu();
        items=in.next();
        for(i=0;i<items_sets.length;i++){
            if(items.equals(items_sets[i])){                
                pay_del=price[i];
                posn=i;
                break;
            }
        }
        if(pay_del>0)
            state_assigned="Selected";
        state_objs.convert(state_assigned);
        selection();
    }
    void menu(){                                                                // Menu 
        System.out.println("Select an items from the following");
        System.out.println("XXX Ham                 INR 40      X");
        System.out.println("Yum Zesty Chicken       INR 30      Y");
        System.out.println("Zinger                  INR 30      Z");
        System.out.println("Hot Wings               INR 20      W");      
    }
    void selection(){
                                                                                           
        if(state_objs.states.equals("Selected")){                         
            states_seque.add("Selected");                                
            System.out.println("Your selection : "+items);
            state_assigned="Incomplete";
            state_objs.convert(state_assigned);
            incompleted();
        }
        else{
            state_assigned="Invalid";                                     
            state_objs.convert(state_assigned);
            invalid();
        }
        
    }
    void invalid(){
        states_seque.add("Invalid");                                      
        System.out.println("Select a valid state");
        state_assigned="Start";                                            // Looping back to selection states
        state_objs.convert(state_assigned);
        start();
    }
    void incompleted(){      
        int tempo;   
        if(state_objs.states.equals("Incomplete"))                        
        {
            states_seque.add("Incomplete");
            while(pay_del>0){
                System.out.println("Payment amount is "+pay_del+" \nPlease enter the denomination");
                tempo=in.nextInt();
                if((tempo==1)||(tempo==5)||(tempo==10)){
                    if(isAllowed(tempo)){
                        state_assigned="Incomplete";
                        states_seque.add("Incomplete");
                        pay_del=pay_del-tempo;
                    }                        
                }
                else{
                    state_assigned="Incorrect";                        
                    incorrect();
                }
                state_objs.convert(state_assigned);
            }
            if(pay_del==0){
                state_assigned="Complete";                             
            }
            state_objs.convert(state_assigned);
            complete();
        }
        
    }
    boolean isAllowed(int bal){                                
        if(bal<=pay_del)
            return true;                                            
    return false;
    }
    void incorrect(){
        states_seque.add("Incorrect");                                
        System.out.println("Invalid denomination\nEnter 1,5 and 10 coins only");
    }
    void complete(){
        states_seque.add("Complete");                                  
        state_assigned="Dispose";
        state_objs.convert(state_assigned);
        disposal();                                                 
    }
    void disposal(){
        states_seque.add("Dispose");                                   
        System.out.print("Your hot and crisp chicken is ready\n");
        switch(posn){
            case 0:
                System.out.println("XXX Ham");
                break;
            case 1:
                System.out.println("Yum zesty chicken");
                break;
            case 2:
                System.out.println("Zinger");
                break;
            case 3:
                System.out.println("Hot Wings");
                break;
        }
        System.out.print("Press B for menu\nPress q to terminate the machine\n");            
        String inputs=in.next();
        if(inputs.equals("B")){
            state_assigned="Start";
            state_objs.convert(state_assigned);                           
            start();
        }
        else if(inputs.equals("q")){
            state_assigned="Terminating";
            state_objs.convert(state_assigned);                           
            terminating();
        }
    }
    void terminating(){
        
        System.out.println("Terminating...");        
        states_seque.add("Terminating");                               
        state_assigned="Terminated";
        state_objs.convert(state_assigned);
        terminated();
        
    }
    void terminated(){
        states_seque.add("Terminated");
        System.out.println("The sequence of states is "+states_seque);
        state_objs.states="Terminated";                                            
        System.out.println("Termination mode on.\nPress S to start the machine");
    }
    //Driver method
    public static void main(String args[]){
        vending_machine v=new vending_machine("Terminated");
        String inputs;
        Scanner in=new Scanner(System.in);
        System.out.println("Press S to start the machine");
        System.out.println("Press q to terminate the machine");
        while(true){
            System.out.println("Press p to shut down the machine");
            inputs=in.next();
            if(inputs.equals("p"))                                   
                System.exit(0);
            else
                v.initialising(inputs);
        }
    }
}