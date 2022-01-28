package com.cosmicapps.valueline.valuation.projection;


import com.cosmicapps.valueline.aws.textract.AnalyzedDocument;
import lombok.Data;

import java.util.List;

@Data
public class Projections {

    private Double earningsPerShare;
    private Double dividendsDeclaredPerShare;
    private Double AvgAnnualPERatioPerShare;

    public static class ProjectionsBuilder{

        private AnalyzedDocument analyzedDocument;

        public ProjectionsBuilder withAnalyzedDocument(AnalyzedDocument analyzedDocument){
            this.analyzedDocument = analyzedDocument;
            return this;
        }

        public Projections build(){


            //assuming only 1 table block in analyzed document
            List<List<String>> table = analyzedDocument.getTableValues(analyzedDocument.getTables().get(0));


            Projections projections = new Projections();
            ProjectionsValueExtractor projectionsValueExtractor = new ProjectionsValueExtractor(table);
            projections.setEarningsPerShare(projectionsValueExtractor.get("earnings"));
            projections.setDividendsDeclaredPerShare(projectionsValueExtractor.get("div'ds decl'd"));
            projections.setAvgAnnualPERatioPerShare(projectionsValueExtractor.get("avg ann'l p/e"));


            return projections;


        }


    }

}
