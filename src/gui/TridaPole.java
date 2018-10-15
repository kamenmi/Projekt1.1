package gui;

import java.util.ArrayList;
import java.util.List;

public class TridaPole{
    List<Integer> cislaPole = new ArrayList<>();
    MainFrame_Pravidelny_N x = new MainFrame_Pravidelny_N();
    MainFrame_Pravidelny_N y = new MainFrame_Pravidelny_N();

       public TridaPole(){
           //poleStartovnichHodnot = new ArrayList<>();
       }

    public List<Integer> getListPocatek() {
        return cislaPole;
    }

    public void setListPocatek(List<Integer> cislaPole) {
        this.cislaPole = cislaPole;
    }
    public void addPoint(int x, int y) {
        cislaPole.add(x,y);
    }
}
