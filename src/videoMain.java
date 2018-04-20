

public class videoMain {
	public static void main(String [] args){
		Timer mainT = new Timer();
		
		mainT.start();
		EmojiLookupTable.buildLookupTable();
		System.out.println("EmojiLookupTable built in " + mainT.stop() + "ms.");

		Images imgArray = new Images();
		
		//If you want to do another series of pictures, just replace the ones in fullOfPictures with your own sequence		
		mainT.start();
		imgArray.addImagesFromFolder("fullOfPictures");
		System.out.println("All images added in " + mainT.stop() + "ms");

		/*
		 * If you want to do another series of pictures, just uncomment this and delete the stuff in outputVideo
		mainT.start();
		Emojify.emojifyFolder(imgArray,"outputVideo",5);
		System.out.println("All images emojified in " + mainT.stop() + "ms");
		*/

		VideoFrame vidFrame = new VideoFrame("Original Gif");
		vidFrame.display();
		vidFrame.repeatedImages(imgArray,10);
		

		Images imgArrayEmoji = new Images();
		VideoFrame vidFrameDef = new VideoFrame("Emojified Gif");
		imgArrayEmoji.addImagesFromFolder("outputVideo");
		vidFrameDef.display();
		vidFrameDef.repeatedImages(imgArrayEmoji,10);
	}
}
