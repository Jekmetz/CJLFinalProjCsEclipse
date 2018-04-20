import java.awt.Color;

class Tile {
  public Color[][] pixels;
  public int inputSize;
  public int outputSize;
  public int margin;

  Tile( int size ){
    this.inputSize = size;
    this.outputSize = size*2;
    this.pixels = new Color[size][size];
  }

  public Color getAverageColor(){
    int total = 0;
    int r = 0;
    int g = 0;
    int b = 0;
    int a = 0;

    for ( int y = 0; y < pixels.length; y++ ) {
      for ( int x = 0; x < pixels[y].length; x++ ) {

        if ( pixels[y][x] != null ){
          total++;
          r = r + pixels[y][x].getRed();
          g = g + pixels[y][x].getGreen();
          b = b + pixels[y][x].getBlue();
          a = a + pixels[y][x].getAlpha();
        }

      }
    }

    r = r/total;
    g = g/total;
    b = b/total;
    a = a/total;

    return new Color( r, g, b, a );
  }

  public void setPixel( int x, int y, Color color ){
    pixels[y][x] = color;
  }

  public void paintTest( Color[][] canvas, int offsetX, int offsetY){
    Color avgColor = this.getAverageColor();

    for ( int y = 0; y < this.outputSize; y++ ) {
      for ( int x = 0; x < this.outputSize; x++ ) {
        canvas[offsetX + x][offsetY + y] = avgColor;
      }
    }
  }
}
