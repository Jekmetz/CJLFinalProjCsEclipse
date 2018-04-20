import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

class Emoji{
  private BufferedImage baseImage;
  public int size = 0;
  public Color[][] image = new Color[0][0];
  public Color avgColor;


  Emoji( String filepath ){
    this.baseImage = ImageUtils.loadBufferedImage(filepath);
    this.avgColor = LookupTable.getBufferedAverageColor(this.baseImage);
  }

  Emoji( BufferedImage baseImage ){
    this.baseImage = baseImage;
    this.avgColor = LookupTable.getBufferedAverageColor(this.baseImage);
  }

  public void setSize( int size ){
    BufferedImage buffImage;
    Color[][] resizedImage;

    if (size != this.size){
      this.size = size;

      //A dirty HACK
      buffImage = ImageUtils.convertToBufferedFromImage(
        this.baseImage.getScaledInstance( this.size, this.size, Image.SCALE_SMOOTH )
      );
      this.image = ImageUtils.convertTo2DFromBuffered( buffImage );
    }
  }
}
