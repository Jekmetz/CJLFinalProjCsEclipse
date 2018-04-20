import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.IIOException;
import javax.imageio.ImageIO;

class Emojify{

  private static Timer emojiTimer = new Timer();

  public static EmojiTile[][] toMosaic( int tileSize, Color[][] pixels ){
    int outputX = (int) Math.ceil( pixels[0].length / (double) tileSize );
    int outputY = (int) Math.ceil( pixels.length / (double) tileSize );
    int tileX = 0;
    int tileY = 0;
    EmojiTile[][] tiles = new EmojiTile[outputY][outputX];

    for ( int y = 0; y < tiles.length; y++ ) {
      for ( int x = 0; x < tiles[y].length; x++ ) {
        tiles[y][x] = new EmojiTile( tileSize );
      }
    }

    for ( int y = 0; y < pixels.length; y++ ) {
      for ( int x = 0; x < pixels[y].length; x++ ) {
        tileX = (int) Math.floor( x / (double) tileSize );
        tileY = (int) Math.floor( y / (double) tileSize );

        tiles[tileY][tileX].setPixel(
          x - (tileX * tileSize),
          y - (tileY * tileSize),
          pixels[y][x]
        );
      }
    }

    return tiles;
  }

  public static Color[][] prepareMosaicCanvas( EmojiTile[][] tiles ){
    return new Color[ tiles.length * tiles[0][0].outputSize ][ tiles[0].length * tiles[0][0].outputSize ];
  }

  public static Color[][] mosaicTest( int tileSize, Color[][] pixels ){
    EmojiTile[][] tiles = Emojify.toMosaic( tileSize, pixels );
    Color[][] canvas = Emojify.prepareMosaicCanvas( tiles );

    for ( int y = 0; y < tiles.length; y++ ) {
      for ( int x = 0; x < tiles[y].length; x++ ) {

        tiles[y][x].paintTest( canvas, y * tiles[0][0].outputSize, x * tiles[0][0].outputSize);
      }
    }

    return canvas;
  }

  public static Color[][] emojify( int tileSize, Color[][] pixels ){
    EmojiTile[][] tiles = Emojify.toMosaic( tileSize, pixels );
    Color[][] canvas = Emojify.prepareMosaicCanvas( tiles );

    for ( int y = 0; y < tiles.length; y++ ) {
      for ( int x = 0; x < tiles[y].length; x++ ) {

        tiles[y][x].paintEmoji( canvas, y * tiles[0][0].outputSize, x * tiles[0][0].outputSize);
      }
    }

    return canvas;
  }

  public static void emojifyFolder(Images imgs, String folderName, int tileSize) {
		/*
		 *Function that Creates a folder with a bunch of static-y images in it.
		 */
	
		for (int i = 0; i < imgs.getLength(); i++) {
			emojiTimer.start();

			Color[][] img = Emojify.emojify(tileSize,ImageUtils.cloneArray(ImageUtils.convertTo2DFromBuffered(imgs.getImage(i))));

			BufferedImage bi = ImageUtils.convertToBufferedFrom2D(img);

			try {
				File filepath = null;
				if(i < 10){
					filepath = new File(".\\" + folderName + "\\output" + "0" + Integer.toString(i) + ".png").getCanonicalFile();
				}else{
					filepath = new File(".\\" + folderName + "\\output" + Integer.toString(i) + ".png").getCanonicalFile();
				}

				if (!new File(".\\" + folderName).getCanonicalFile().exists()) {
					new File(".\\" + folderName).getCanonicalFile().mkdir();
				}

				ImageIO.write(bi, "png", filepath);
			} catch (IIOException e) {
				System.out.println("Damn it all");
			} catch (IOException e) {
				System.out.println("Damn it all again");
			}

			System.out.printf("Image %d out of %d succesfully emojified in %d ms\n",i,imgs.getLength(),emojiTimer.stop());
		}
	}
}
