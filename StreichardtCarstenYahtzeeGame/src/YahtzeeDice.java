public class YahtzeeDice {
	
	protected Die[] d = new Die[5];
	
	public YahtzeeDice() {
		for(int i = 0; i < d.length; ++i) {
			d[i] = new Die();
		}
	}	
	public YahtzeeDice(int sides) {
		for(int i = 0; i < d.length; ++i) {
			d[i] = new Die(sides);
		}
	}

	public int[] roll() {
		int[] j = new int[d.length];
		for(int i = 0; i < d.length; ++i) {
			j[i] = d[i].roll();
		}
		return j; 
	}
	
	public int[] roll(boolean[] b) {
		int[] j = new int[d.length];
		for(int i = 0; i < d.length; ++i) {
			if(!b[i]) j[i] = d[i].roll();
			else j[i] =  d[i].getCurrentValue();
		}
		return j; 
	}
	
	public Die[] getDice() {
		return d;
	}
	
	public int[] getDiceValues() {
		int[] r = new int[d.length];
		for(int i = 0; i < d.length; ++i) {
			r[i] = d[i].getCurrentValue();
		}
		return r;
	}
	
	public String toString() {
		String s = "[";
		for(int i = 0; i < d.length; ++i) {
			if(i == d.length - 1) s += d[i].getCurrentValue();
			else s += d[i].getCurrentValue() + ", ";
		}
		return s + "]";
	}
}
