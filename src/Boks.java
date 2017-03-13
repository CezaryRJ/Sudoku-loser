public class Boks {
	rute[] data;
	int counter = 0;
	Boks(int size) {
		data = new rute[size];

	}
	void settinn(rute a){
		data[counter] = a;
		counter++;
		
	}

	rute[] getruter() {
		return data;
	}

}
