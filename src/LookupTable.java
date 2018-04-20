import java.awt.Color;
import java.awt.image.BufferedImage;

class LookupTable{

  public static Color standardColor = new Color( 255, 255, 255, 255 );


  public static Color getBufferedAverageColor( BufferedImage img ){
    Color color= null;
    int width = img.getWidth();
    int height = img.getHeight();
    int total = 0;
    int r = 0;
    int g = 0;
    int b = 0;
    int a = 1;

    for ( int y = 0; y < height; y++ ) {
      for ( int x = 0; x < width; x++ ) {

        color = new Color(img.getRGB(x, y), true);

        if (color.getAlpha() > 0){
          total++;
          r = r + color.getRed();
          g = g + color.getGreen();
          b = b + color.getBlue();
          a = a + color.getAlpha();
        }else{
          r = r + 255;
          g = g + 255;
          b = b + 255;
          a = a + 255;
          total++;
        }

      }
    }

    r = r/total;
    g = g/total;
    b = b/total;
    a = a/total;

    return new Color( r, g, b, a );
  }

  public static double calculateColorDistance( Color color1, Color color2 ){

    return Math.sqrt(
      Math.pow( color1.getRed() - color2.getRed() , 2 ) +
      Math.pow( color1.getGreen() - color2.getGreen() , 2 ) +
      Math.pow( color1.getBlue() - color2.getBlue() , 2 ) +
      Math.pow( color1.getAlpha() - color2.getAlpha() , 2 )
    );
  }

  public static double calculateStandardColorDistance( Color color ){
    return LookupTable.calculateColorDistance( LookupTable.standardColor, color );
  }
}
