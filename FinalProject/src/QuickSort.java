import java.util.ArrayList;

public class QuickSort {
	private ArrayList<WebPage> lst;
	
	public QuickSort(){
		this.lst = new ArrayList<WebPage>();
    }
	
	public void add(WebPage webpage){
		lst.add(webpage);
		System.out.println("Done");
    }
	
	//quick sort
	public void sort(){
		quickSort(0, lst.size()-1);
	}
	
	
	private void quickSort(int leftbound, int rightbound){
		//implement quickSort algorithm
		WebPage pivot = this.lst.get(rightbound);
		for (int i = leftbound; i < rightbound; i++) {
			if (this.lst.get(i).score > pivot.score) {
				this.swap(i, rightbound);
				pivot = this.lst.get(rightbound);
			}
		}
		if (rightbound> leftbound) {
			this.quickSort(leftbound, rightbound - 1);
		} else {
			System.out.println("Done!");
		}
	}
	
	
	private void swap(int aIndex, int bIndex){
		WebPage temp = lst.get(aIndex);
		lst.set(aIndex, lst.get(bIndex));
		lst.set(bIndex, temp);
	}
	
	public ArrayList<WebPage> getPages(){
		return this.lst;
	}
	
}