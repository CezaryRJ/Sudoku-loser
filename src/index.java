
public class index {
	
		int index = 0;

	synchronized 	void minus() {
			index--;
		}

	synchronized 	void pluss() {
			index++;
		}

	synchronized 	int getindex() {
			return index;
		}

	
}
