import java.awt.Color;
import java.util.TreeMap;
import java.util.Map;
import java.util.ArrayList;
import java.io.File;
import java.awt.image.BufferedImage;
import java.io.IOException;

class EmojiLookupTable extends LookupTable{

  public static Map<String, ArrayList<Emoji>> table = new TreeMap<String, ArrayList<Emoji>>();

  public static void buildLookupTable(){

    File emojiDir = null;
    try{
      emojiDir = new File("emoji").getCanonicalFile();
    }catch(IOException e){
      System.out.println(e);
    }
    
    File[] imageFiles = emojiDir.listFiles();
    BufferedImage buffImg = null;
    Color buffImgAvg = null;
    int dist = 0;


    for (int i = 0; i < imageFiles.length; i++) {

      buffImg = ImageUtils.loadBufferedImage( imageFiles[i].getAbsolutePath() );
      buffImgAvg = LookupTable.getBufferedAverageColor( buffImg );

      dist = (int) Math.round( LookupTable.calculateStandardColorDistance( buffImgAvg ) );

      if (!table.containsKey( Integer.toString(dist) ) ){
        table.put( Integer.toString(dist), new ArrayList<Emoji>() );
      }

      table.get( Integer.toString(dist) ).add( new Emoji( imageFiles[i].getAbsolutePath() ) );
    }
  }

  public static Emoji findEmoji( Color color ){
    int standardDist = (int) Math.round( LookupTable.calculateStandardColorDistance( color ) );

    String key = null;
    int offset = 0;
    int layerDepth = 0;

    double dist = 0;

    String closestKey = null;
    int closestIndex = 0;
    double closestDist = 255;


    while ( ( closestDist > 10 ) && (layerDepth < 200) ){
      if ( table.containsKey( Integer.toString( standardDist + offset ) ) ){

        key = Integer.toString( standardDist + offset );

        for (int i = 0; i < table.get( key ).size(); i++) {

          dist = LookupTable.calculateColorDistance( table.get(key).get(i).avgColor, color );

          if ( dist < closestDist ){
            closestDist = dist;
            closestIndex = i;
            closestKey = key;
          }

          layerDepth++;
        }


      }

      if (offset > 0){
        offset = -( Math.abs(offset) + 1 );
      } else {
        offset = Math.abs(offset) + 1;
      }
    }

    if ( closestKey != null){
      return table.get( closestKey ).get( closestIndex );
    }

    return null;
  }

}
