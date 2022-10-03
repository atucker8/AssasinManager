import java.util.*;

public class AssassinManager {
   private AssassinNode front;
   private AssassinNode graveyard;
   private AssassinNode current;
   private int size=0;
public AssassinManager(List<String> names){
    front=new AssassinNode(names.get(0));
    graveyard= new AssassinNode(null);
    current=front;
    if(names.size()==0){
        throw new IllegalArgumentException();
    }
        for(int i=1;i<=names.size();i++){
            front.next=new AssassinNode(names.get(i));
            size++;
        }
    }

    public void printKillRing(){
        if(size==1){
            System.out.print(front+" is stalking "+front);
        }
        for(int i=0;i<size;i++){
            System.out.print(current);
            current=current.next;
         System.out.println(" is stalking "+current);
        }
    }

    public void printGraveyard(){
       
    }


}
}