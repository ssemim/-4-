import javax.swing.JPanel;

import 객체모음.Student;
import 메소드모음.EquipmentItem;

public class Test {
	public static void main(String[] args) {
		Student s = new Student("dd", 0);
		EquipmentItem c = new EquipmentItem();
		int[] iarr = c.itemNos(s);
		String[] arr = c.selectItemIamgeName(iarr);
		for (int i : iarr) {
			System.out.println(i);
		}
		for (String string : arr) {
			System.out.println(string);
		}
		EquipmentItem e = new EquipmentItem();
		JPanel pnl = e.equipmentItem(e.selectItemIamgeName(e.itemNos(s)));
	}
}

