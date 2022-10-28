import java.util.*;

public class AssassinManager {
   private AssassinNode front;
   private AssassinNode graveyard;
   private AssassinNode current;
   private int sizeK=0;
//Contructor where kill ring is initialized from the list of names
public AssassinManager(List<String> names){
    front=new AssassinNode(names.get(0));
    graveyard= new AssassinNode(null);
    if(names.size()==0){
        throw new IllegalArgumentException();
    }
    current=front;
        for(int i=1;i<names.size();i++){
            current.next=new AssassinNode(names.get(i));
            sizeK++;
            current=current.next;
        }
        current=front;
    }
//prints the kill ring
    public void printKillRing(){
        current=front;
        if(sizeK==1){
            System.out.print(front+" is stalking "+front);
        }
        while(current.next!=null){
            System.out.print("    "+current.name);
            current=current.next;
         System.out.println(" is stalking "+current.name);
        }
    }
//prints the graveyard of people that were killed
    public void printGraveyard(){
        current=graveyard;
        while(current.next!=null){
            System.out.println("    "+current.name+" was killed by "+current.killer);
            current=current.next;
        }
       
    }
//checks if the kill ring still has a specific names and returns true if that person is there
    public boolean killRingContains(String name){
        boolean yn=false;
        current=front;
        while(current.next!=null){
            if(current.name.toLowerCase().equals(name.toLowerCase())){
                yn=true;
            }
            current=current.next;
        }
        return yn;
    }
//checks if the graveyard has a name and returns true if that person is there
    public boolean graveyardContains(String name){
        boolean yn=false;
        current=graveyard;
        while(current.next!=null){
            if(current.name.toLowerCase().equals(name.toLowerCase())){
                yn=true;
            }
        }
        return yn;
    }
//returns true of the game is over and false if there is more than one player
    public boolean gameOver(){
        return (front.next==null);
    }

    public String winner(){
        if(gameOver()==true){
            return front.name;
        }
        return null;
    }
//kills the person and assigns their killer to whoever that person was stalking 
    public void kill(String name){
        if((gameOver()==true&&killRingContains(name)==false)||(gameOver()==true)){
            throw new IllegalStateException();
        }
        if(killRingContains(name)==false){
            throw new IllegalArgumentException();
        }
        AssassinNode temp;
        current=front;
        while(current.next!=null){
            if(current.next.name.toLowerCase().equals(name.toLowerCase())){
                current.next.killer=current.name;
                temp=current.next;
                current.next=current.next.next;
                temp.next=graveyard;
                graveyard=temp;
            }
            else{
                current=current.next;
            }
            

    }
}
}
