import java.awt.Color;

class EmojiTile extends Tile{
  EmojiTile( int size ){
    super( size );
  }

  public void paintEmoji( Color[][] canvas, int offsetX, int offsetY){
    Color avgColor = this.getAverageColor();
    Emoji emoji = null;

    if ( avgColor.getAlpha() > 0 ){
      emoji = EmojiLookupTable.findEmoji(avgColor);

      if (emoji != null){
        emoji.setSize( this.outputSize );

        for ( int y = 0; y < this.outputSize; y++ ) {
          for ( int x = 0; x < this.outputSize; x++ ) {
            canvas[offsetX + x][offsetY + y] = emoji.image[x][y];
          }
        }

        return;
      }

    }


    for ( int y = 0; y < this.outputSize; y++ ) {
      for ( int x = 0; x < this.outputSize; x++ ) {
        canvas[offsetX + x][offsetY + y] = new Color(0,0,0,0);
      }
    }

  }

}
