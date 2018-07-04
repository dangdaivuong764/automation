package support.steps;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

import net.coobird.thumbnailator.Thumbnails;

public class ImageComparison {

    private BufferedImage imgOut = null;
    private int pixelPerBlockX;
    private int pixelPerBlockY;
    private double threshold;

    /**
     * compare each pixel in per block in threshold
     * 
     * @param pixelPerBlockX
     * @param pixelPerBlockY
     * @param threshold
     */
    public ImageComparison(int pixelPerBlockX, int pixelPerBlockY, double threshold) {
        this.pixelPerBlockX = pixelPerBlockX;
        this.pixelPerBlockY = pixelPerBlockY;
        this.threshold = threshold;
    }

    /**
     * path image is String
     * 
     * @param path1
     * @param path2
     * @param pathOut
     * @return
     * @throws IOException
     */
    public boolean fuzzyEqual(String path1, String path2, String pathOut) throws IOException {
        return fuzzyEqual(ImageIO.read(new File(path1)), ImageIO.read(new File(path2)), pathOut);
    }

    /**
     * path image is File
     * 
     * @param file1
     * @param file2
     * @param pathOut
     * @return
     * @throws IOException
     */
    public boolean fuzzyEqual(File file1, File file2, String pathOut) throws IOException {
        return fuzzyEqual(ImageIO.read(file1), ImageIO.read(file2), pathOut);
    }

    /**
     * path image is Image
     * 
     * @param img1
     * @param img2
     * @param pathOut
     * @return
     * @throws IOException
     */
    public boolean fuzzyEqual(Image img1, Image img2, String pathOut) throws IOException {
        return fuzzyEqual(imageToBufferedImage(img1), imageToBufferedImage(img2), pathOut);
    }

    /**
     * find image different
     * 
     * @param img1
     * @param img2
     * @param pathOut
     * @return
     * @throws IOException
     */
    public boolean fuzzyEqual(BufferedImage img1, BufferedImage img2, String pathOut) throws IOException {
        boolean fuzzyEqual = true;
        img2 = adaptImageSize(img1, img2);

        imgOut = imageToBufferedImage(img2);
        Graphics2D outImgGraphics = imgOut.createGraphics();
        outImgGraphics.setColor(Color.RED);

        int subImageHeight;
        int subImageWidth;

        int blocksx = (int) Math.ceil((float) img1.getWidth() / (float) pixelPerBlockX);
        int blocksy = (int) Math.ceil((float) img1.getHeight() / (float) pixelPerBlockY);

        for (int y = 0; y < blocksy; y++) {
            for (int x = 0; x < blocksx; x++) {
                subImageWidth = calcPixSpan(pixelPerBlockX, x, img1.getWidth());
                subImageHeight = calcPixSpan(pixelPerBlockY, y, img1.getHeight());

                HashMap<String, Integer> avgRgb1 = getAverageRgb(img1.getSubimage(x * pixelPerBlockX, y
                        * pixelPerBlockY, subImageWidth, subImageHeight));
                HashMap<String, Integer> avgRgb2 = getAverageRgb(img2.getSubimage(x * pixelPerBlockX, y
                        * pixelPerBlockY, subImageWidth, subImageHeight));

                if (calculateRgbDiff(avgRgb1, avgRgb2) > threshold) {
                    outImgGraphics.drawRect(x * pixelPerBlockX, y * pixelPerBlockY, pixelPerBlockX - 1,
                            pixelPerBlockY - 1);
                    fuzzyEqual = false;
                }
            }
        }
        if (pathOut != null && !pathOut.isEmpty())
            saveImage(imgOut, pathOut);
        return fuzzyEqual;
    }

    /**
     * check pixel overlap threshold or not
     * 
     * @param pixelPerBlock
     * @param n
     * @param overallSpan
     * @return
     */
    private int calcPixSpan(int pixelPerBlock, int n, int overallSpan) {
        if (pixelPerBlock * (n + 1) > overallSpan)
            return overallSpan % pixelPerBlock;
        else
            return pixelPerBlock;
    }

    /**
     * scale size image to match each other
     * 
     * @param img1
     * @param img2
     * @return
     * @throws IOException
     */
    private BufferedImage adaptImageSize(BufferedImage img1, BufferedImage img2) throws IOException {
        int scalePixelWidth;
        int scalePixelHeight;

        if (((float) img2.getWidth() / (float) img1.getWidth()) < ((float) img2.getHeight() / (float) img1.getHeight())) {
            scalePixelWidth = img1.getWidth();
            scalePixelHeight = (int) (img2.getHeight() * Math.ceil((float) img1.getWidth() / (float) img2.getWidth()));

        } else {
            scalePixelHeight = img1.getHeight();
            scalePixelWidth = (int) (img2.getWidth() * Math.ceil((float) img1.getHeight() / (float) img2.getHeight()));

        }

        return Thumbnails.of(img2).size(scalePixelWidth, scalePixelHeight).asBufferedImage();
    }

    /**
     * calculate Rgb
     * 
     * @param avgRgb1
     * @param avgRgb2
     * @return
     */
    private double calculateRgbDiff(HashMap<String, Integer> avgRgb1, HashMap<String, Integer> avgRgb2) {
        double maxDifference = 255 * 3;
        double difference = Math.abs(avgRgb1.get("r") - avgRgb2.get("r"))
                + Math.abs(avgRgb1.get("g") - avgRgb2.get("g")) + Math.abs(avgRgb1.get("b") - avgRgb2.get("b"));

        return difference / maxDifference;
    }

    private HashMap<String, Integer> getAverageRgb(BufferedImage img) {
        Raster currentRaster = img.getData();
        HashMap<String, Integer> averageRgb = new HashMap<String, Integer>();
        averageRgb.put("r", 0);
        averageRgb.put("g", 0);
        averageRgb.put("b", 0);

        for (int y = 0; y < img.getHeight(); y++) {
            for (int x = 0; x < img.getWidth(); x++) {
                averageRgb.put("r", averageRgb.get("r") + currentRaster.getSample(x, y, 0));
                averageRgb.put("g", averageRgb.get("g") + currentRaster.getSample(x, y, 1));
                averageRgb.put("b", averageRgb.get("b") + currentRaster.getSample(x, y, 2));

            }
        }

        averageRgb.put("r", averageRgb.get("r") / (img.getHeight() * img.getWidth()));
        averageRgb.put("g", averageRgb.get("g") / (img.getHeight() * img.getWidth()));
        averageRgb.put("b", averageRgb.get("b") / (img.getHeight() * img.getWidth()));

        return averageRgb;
    }

    /**
     * covert image to BufferedImage
     * 
     * @param img
     * @return
     */
    private BufferedImage imageToBufferedImage(Image img) {
        BufferedImage bi = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = bi.createGraphics();
        g2.drawImage(img, null, null);
        return bi;
    }

    /**
     * save image different
     * 
     * @param img
     * @param filename
     * @throws IOException
     */
    private void saveImage(Image img, String filename) throws IOException {
        BufferedImage bi = imageToBufferedImage(img);
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(filename);
        }
        catch (java.io.FileNotFoundException io) {
            System.out.println("File Not Found");
        }
        ImageIO.write(bi, "png", out);

    }

}
