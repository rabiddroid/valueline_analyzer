package com.cosmicapps.valueline.task;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PdfToImageConverterTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void get() {


        PdfToImageConverter pdfToImageConverter = new PdfToImageConverter.PdfToImageConverterBuilder()
                .cropBoxLowerLeftX(40).cropBoxLowerLeftY(700)
                .cropBoxUpperRightX(250).cropBoxUpperRightY(770)
                .dpi(500)
                .startPage(1)
                .endPage(1)
                .pdfFileName("/Users/jeffrey/Development/valueline_analyzer/src/test/resources/cost_vl.pdf")//TODO:change to relative path from resources
                .build();


        pdfToImageConverter.get();


    }
}