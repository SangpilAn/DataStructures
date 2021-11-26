package Exam;

import ListClass.ArrayList;

public class ArrayListExam {
    public void exam(){
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(1);

        ArrayList<Integer> copy = arrayList;
        @SuppressWarnings("unchecked")
        ArrayList<Integer> clone = (ArrayList<Integer>) arrayList.clone();

        copy.add(2);
        clone.add(3);

        for (int i = 0; i < arrayList.size(); i++) {
            System.out.println("index "+i+" data = "+arrayList.get(i));
        }

        for (int i = 0; i < copy.size(); i++) {
            System.out.println("index "+i+" data = "+copy.get(i));
        }

        for (int i = 0; i < clone.size(); i++) {
            System.out.println("index "+i+" data = "+clone.get(i));
        }

        System.out.println("arrayList : "+arrayList);
        System.out.println("copy : "+copy);
        System.out.println("clone : "+clone);
    }
}
