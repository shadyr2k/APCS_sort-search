import java.awt.Color;
import java.util.Arrays;
import java.util.Scanner;
public class MosaicCreator extends Mosaic {
	public static void main(String[] mosaiclmao) {
		Scanner s = new Scanner(System.in);
		System.out.print("rows? "); final int rows = s.nextInt();
		System.out.print("columns? "); final int columns = s.nextInt();
		open(rows, columns, 40, 40);
		int onRow = 0;
		Color[][] mosaicPanel = new Color[columns][rows];
		String rowInput = s.nextLine();
		while(onRow <= rows-1) {
			System.out.print("row #" + (onRow+1) + " "); rowInput = s.nextLine();
			Color[] printMosaic = parseLine(rowInput, columns);
			for(int i = 0; i < columns; ++i) {
				setColor(onRow, i, printMosaic[i]);
				mosaicPanel[i][onRow] = printMosaic[i];
			}
			++onRow;
		}
		while(true) {
			delay(1000);
			for(int i = 0; i < rows; ++i) {
				for(int k = 0; k < columns; ++k) {
					setColor(i, k, replaceColor(mosaicPanel[k][i]));
					mosaicPanel[k][i] = replaceColor(mosaicPanel[k][i]);
				}
			}
		}
	}
	private static Color[] parseLine(String s, int limit) {
		int rCount = 0;
		Color[] r = new Color[limit];
		String parsedString = s.length() > limit ? s.replaceAll("(?<=.{"+limit+"}).+", "") : s.length() < limit ? String.format("%-"+limit+"s", s) : s;
		for(String i : parsedString.split("")) {	
			r[rCount] = toColor(i);
			++rCount;
		}
		return r;
	}
	private static Color toColor(String c) {
		switch(c) {
		case "r": return Color.RED;  
		case "g": return Color.GREEN; 
		case "b": return Color.BLUE; 
		case "y": return Color.YELLOW; 
		case "c": return Color.CYAN; 
		case "m": return Color.MAGENTA; 
		case "w": return Color.WHITE; 
		default: return Color.BLACK; 
		}
	}
	public static Color replaceColor(Color c) {
		Color[] referenceArray = {Color.RED, Color.CYAN, Color.YELLOW, Color.WHITE, Color.GREEN, Color.BLUE, Color.MAGENTA, Color.BLACK};
		if(c.equals(Color.BLACK)) return Color.RED;
		return referenceArray[Arrays.asList(referenceArray).indexOf(c) + 1];
	}
}
