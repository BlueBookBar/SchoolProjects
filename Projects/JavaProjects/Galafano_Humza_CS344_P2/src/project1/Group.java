package project1;




public class Group {//Object Class holds the type of groups

	//Holds Id numbers
	public int Orange=0;
	public int Orange2=0;
	public int Blue=0;


	//Holds String
	public String OrangeS=null;
	public String Orange2S=null;
	public String BlueS=null;


	//constructors
	public Group(){
	}


	//Constructor for Integers
	public Group(int orange, int orange2, int blue) {
		Orange = orange;
		Orange2= orange2;
		Blue= blue;
	}


	//Constructor for Strings
	public Group(String orange, String orange2, String blue) {
		OrangeS = orange;
		Orange2S= orange2;
		BlueS= blue;
	}

	//Add orange id
	//will return false if id is already assigned
	public boolean addOrange(int temp){
		if(Orange == 0){
			Orange = temp;
			return true;
		}
		else if(Orange2 == 0){
			Orange2 = temp;
			return true;
		}
		else {
			return false;
		}
	}

	//add blue id
	public boolean addBlue(int temp){
		Blue = temp;
		return true;
	}


	//Add orange id
	//will return false if id is already assigned
	public boolean addOrangeS(String temp){
		if(OrangeS == null){
			OrangeS = temp;
			return true;
		}
		else if(Orange2S == null){
			Orange2S = temp;
			return true;
		}
		else {
			return false;
		}
	}

	//add blue id
	public boolean addBlueS(String temp){
		BlueS = temp;
		return true;
	}

}
