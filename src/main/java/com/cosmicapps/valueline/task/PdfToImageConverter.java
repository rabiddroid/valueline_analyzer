package com.cosmicapps.valueline.task;

import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.tools.imageio.ImageIOUtil;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;

@Builder
@Slf4j
public class PdfToImageConverter{

    private String pdfFileName;
    @Builder.Default
    private String password = "";
    private float cropBoxLowerLeftX;
    private float cropBoxLowerLeftY;
    private float cropBoxUpperRightX;
    private float cropBoxUpperRightY;
    @Builder.Default
    private int endPage = 2147483647;
    private boolean subsampling;
    @Builder.Default
    private int startPage = 1;
    @Builder.Default
    private int dpi = 96;
    private boolean showTime;
    @Builder.Default
    private String imageFormat = "jpg";
    @Builder.Default
    private float quality = -1.0F;

    private static void changeCropBox(PDDocument document, float a, float b, float c, float d) {
        Iterator var5 = document.getPages().iterator();

        while(var5.hasNext()) {
            PDPage page = (PDPage)var5.next();
            System.out.println("resizing page");
            PDRectangle rectangle = new PDRectangle();
            rectangle.setLowerLeftX(a);
            rectangle.setLowerLeftY(b);
            rectangle.setUpperRightX(c);
            rectangle.setUpperRightY(d);
            page.setCropBox(rectangle);
        }

    }


    public ByteArrayOutputStream get() {
        ByteArrayOutputStream output = null;

        //convert to jpg data
        try(PDDocument document = PDDocument.load(new File(pdfFileName), password)) {

            String  outputPrefix = pdfFileName.substring(0, pdfFileName.lastIndexOf(46));

            PDAcroForm acroForm = document.getDocumentCatalog().getAcroForm();
            if (acroForm != null && acroForm.getNeedAppearances()) {
                acroForm.refreshAppearances();
            }

            ImageType imageType = ImageType.BINARY;
            changeCropBox(document, cropBoxLowerLeftX, cropBoxLowerLeftY, cropBoxUpperRightX, cropBoxUpperRightY);
            if (quality < 0.0F) {
                quality = "png".equals(imageFormat) ? 0.0F : 1.0F;
            }

            long startTime = System.nanoTime();
            //boolean success = true;
            endPage = Math.min(endPage, document.getNumberOfPages());
            PDFRenderer renderer = new PDFRenderer(document);
            renderer.setSubsamplingAllowed(subsampling);

            for(int i = startPage - 1; i < endPage; ++i) {
                BufferedImage image = renderer.renderImageWithDPI(i, (float)dpi, imageType);
                output = new ByteArrayOutputStream();
                ImageIOUtil.writeImage(image, imageFormat, output, dpi);
                //String fileName = outputPrefix + "_"+(i + 1) + "." + imageFormat;
                //success &= ImageIOUtil.writeImage(image, fileName, dpi, quality);
            }

            long endTime = System.nanoTime();
            long duration = endTime - startTime;
            int count = 1 + endPage - startPage;
            if (showTime) {
                log.info("Rendered %d page%s in %dms%n", count, count == 1 ? "" : "s", duration / 1000000L);
            }

        } catch (IOException e) {
           log.info("conversion failed.",e);
        }


        return output;
    }
}
