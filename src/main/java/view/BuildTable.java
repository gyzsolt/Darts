package view;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcTo;
import javafx.scene.shape.Circle;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * A megjelenítésben szereplő tábla generálására szolgál.
 * 
 * @author Gyulai Zsolt
 *
 */
public class BuildTable {

	private final static int ROTATE_ANGLE_DEGREES = 18;
	private static int ROTATE_ANGLE_DEGREES_OFFSET = ROTATE_ANGLE_DEGREES / 2;
	private static final String[] DARTBOARD_NUMBERS = 
        	   {"1", "18", "4", "13",  "6", "10", "15", "2", "17", "3",
        	    "19", "7", "16", "8",  "11", "14", "9", "12", "5","20"};

    private Path createDartboardField(double degreeStart, double degreeEnd, double innerRadius, double outerRadius, double origoX,double origoY,String id,int part,int i) {
        double angleAlpha = degreeStart * (Math.PI / 180);
        double angleAlphaNext = degreeEnd * (Math.PI / 180);
     

        double pointX1 = origoX + innerRadius * Math.sin(angleAlpha);
        double pointY1 = origoY - innerRadius * Math.cos(angleAlpha);
     
        double pointX2 = origoX + outerRadius * Math.sin(angleAlpha);
        double pointY2 = origoY - outerRadius * Math.cos(angleAlpha);
     
        double pointX3 = origoX + outerRadius * Math.sin(angleAlphaNext);
        double pointY3 = origoY - outerRadius * Math.cos(angleAlphaNext);
     
        double pointX4 = origoX + innerRadius * Math.sin(angleAlphaNext);
        double pointY4 = origoY - innerRadius * Math.cos(angleAlphaNext);
     
        Path path = new Path();
        
 
                path.getElements().add(new MoveTo(pointX4, pointY4)); 
                path.getElements().add(new LineTo(pointX3, pointY3)); 
                
                ArcTo arcto = new ArcTo();
                arcto.setRadiusX(outerRadius);
                arcto.setRadiusY(outerRadius);
                arcto.setX(pointX2);
                arcto.setY(pointY2);
                
                
               path.getElements().add(arcto);

               path.getElements().add(new LineTo(pointX1, pointY1)); 

               path.getElements().add(new MoveTo(pointX4, pointY4));
                
                ArcTo arcto2 = new ArcTo();
                arcto2.setRadiusX(innerRadius);
                arcto2.setRadiusY(innerRadius);
                arcto2.setX(pointX1);
                arcto2.setY(pointY1);
                
                
                path.getElements().add(arcto2);
               
                if( ( part % 2 ==1) && (i % 2 ==0))
                	path.setFill(Color.BURLYWOOD);
                if( ( part % 2 ==1) && (i % 2 ==1))
                	path.setFill(Color.BISQUE);
                
                if( ( part % 2 ==0) && (i % 2 ==0))
                	path.setFill(Color.RED);
                
                if( ( part % 2 ==0) && (i % 2 ==1))
                	path.setFill(Color.ROYALBLUE);
                
               
                path.setId(id);
        return path;
    }
    
    private Circle createCircle(Double x, Double y , Double r,String id){
    	Circle circle = new Circle(x,y,r);
    	
    	circle.setId(id);
    	return circle;
    }
    
    private double TextOffsetX(String text, double fontSize) {
        return (text.length() / 2) + fontSize * 0.5;
    }
 

    private double TextOffsetY(double fontSize) {
        return (fontSize / 2) - fontSize * 0.4;
    }
    /**
     * A darts tábla körül elhelyezkedő számok elhelyezését számolja ki.
     * @param origoX az középpont x koordinátája 
     * @param origoY az középpont y koordinátája
     * @param radius a körnek a sugara melyre helyezni szeretnénk a számokat
     * @return lista vissza adja a pe pozícionált számok listáját
     */
    public List<Text> createTextList(double origoX ,double origoY, double radius){
    	List<Text> lista = new ArrayList<>();
    	
    	
        for (int i = 0; i < DARTBOARD_NUMBERS.length; i++) {
            double degreeStart = (i+1) * ROTATE_ANGLE_DEGREES;
            double angleAlpha = degreeStart * (Math.PI / 180);
             
            double pointX = origoX+ radius * Math.sin(angleAlpha)  - TextOffsetX(DARTBOARD_NUMBERS[i], 20);
            double pointY = origoY - radius * Math.cos(angleAlpha) + TextOffsetY(20);
            Text text = new Text(pointX, pointY, DARTBOARD_NUMBERS[i]);
            text.setFont(Font.font(20));
            lista.add(text);
        }
    	
    	
    	return lista;
    }
    /**
     * Elkészíti a darts tablát megvalósítő elemeket.
     * @param origoX a darts tábla középpontjának X koordinátája 
     * @param origoY a darts tábla középpontjának Y koordinátája	
     * @param radius a darts tábla sugara
     * @return	visszatér egy {@code Group} objektummal ami tartalmazza a táblát
     */
    public Group tableBuild(Double origoX, Double origoY, Double radius){
    	Group tablegroup = new Group();
    	
    	Circle backCircle = createCircle(origoX, origoY, radius+40,"S0");
    	backCircle.setFill(Color.YELLOW);
    	
    	Circle sBull = createCircle(origoX, origoY, radius/10,"S25");
    	sBull.setFill(Color.RED);
    	
    	Circle bBull = createCircle(origoX, origoY, (radius/10)*2,"D25");
    	bBull.setFill(Color.GREEN);
    	
    	tablegroup.getChildren().add(backCircle);
    	tablegroup.getChildren().add(bBull);
    	tablegroup.getChildren().add(sBull);

    	for(int i=0;i<20;i++){
    	tablegroup.getChildren().add(createDartboardField(
                i * ROTATE_ANGLE_DEGREES + ROTATE_ANGLE_DEGREES_OFFSET,
                (i + 1) * ROTATE_ANGLE_DEGREES + ROTATE_ANGLE_DEGREES_OFFSET,
                (radius/10)*2,(radius/10)*5,origoX,origoY,"S"+DARTBOARD_NUMBERS[i],1,i));
    	
    	tablegroup.getChildren().add(createDartboardField(
                i * ROTATE_ANGLE_DEGREES + ROTATE_ANGLE_DEGREES_OFFSET,
                (i + 1) * ROTATE_ANGLE_DEGREES + ROTATE_ANGLE_DEGREES_OFFSET,
                (radius/10)*5,(radius/10)*6,origoX,origoY,"T"+DARTBOARD_NUMBERS[i],2,i));
    	
    	tablegroup.getChildren().add(createDartboardField(
                i * ROTATE_ANGLE_DEGREES + ROTATE_ANGLE_DEGREES_OFFSET,
                (i + 1) * ROTATE_ANGLE_DEGREES + ROTATE_ANGLE_DEGREES_OFFSET,
                (radius/10)*6,(radius/10)*9,origoX,origoY,"S"+DARTBOARD_NUMBERS[i],3,i));
    	
    	tablegroup.getChildren().add(createDartboardField(
                i * ROTATE_ANGLE_DEGREES + ROTATE_ANGLE_DEGREES_OFFSET,
                (i + 1) * ROTATE_ANGLE_DEGREES + ROTATE_ANGLE_DEGREES_OFFSET,
                (radius/10)*9,(radius/10)*10,origoX,origoY,"D"+DARTBOARD_NUMBERS[i],4,i));
    	
    	
    	}
    	
    	return tablegroup;
    }
    
}
