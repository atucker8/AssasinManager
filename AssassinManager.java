import java.util.*;

public class AssassinManager {
   private AssassinNode front;
   private AssassinNode graveyard;
   private AssassinNode current;
   private int sizeK=0;
   private int sizeG=0;
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

    public void printKillRing(){
        current=front;
        if(sizeK==1){
            System.out.print(front+" is stalking "+front);
        }
        for(int i=0;i<sizeK;i++){
            System.out.print("    "+current.name);
            current=current.next;
         System.out.println(" is stalking "+current.name);
        }
    }

    public void printGraveyard(){
        current=graveyard;
        for(int i=0;i<sizeG;i++){
            System.out.print("    "+current.name);
            current=current.next;
            System.out.println(" was killed by "+current.killer);
        }
       
    }

    public boolean killRingContains(String name){
        boolean yn=false;
        current=front;
        for(int i=0;i<sizeK;i++){
            if(current.name.toLowerCase().equals(name.toLowerCase())){
                yn=true;
            }
            current=current.next;
        }
        return yn;
    }
    
    public boolean graveyardContains(String name){
        boolean yn=false;
        current=graveyard;
        for(int i=0;i<sizeG;i++){
            if(current.name.toLowerCase().equals(name.toLowerCase())){
                yn=true;
            }
        }
        return yn;
    }

    public boolean gameOver(){
        return (sizeK==1);
    }

    public String winner(){
        if(sizeK==1){
            return front.name;
        }
        return null;
    }

    public void kill(String name){
        if((gameOver()==true&&killRingContains(name)==false)||(gameOver()==true)){
            throw new IllegalStateException();
        }
        if(killRingContains(name)==false){
            throw new IllegalArgumentException();
        }
        AssassinNode temp=new AssassinNode(name);
        temp.next=graveyard;
        graveyard=temp;
        current=front;
        for(int i=0;i<sizeK-1;i++){
            if(current.next.name.toLowerCase().equals(name.toLowerCase())){
                current.next.killer=current.name;
                sizeK--;
                sizeG++;
                current.next=current.next.next;
            }
            else{
                current=current.next;
            }
            

    }
}
}
