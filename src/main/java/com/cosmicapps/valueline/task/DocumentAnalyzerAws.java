package com.cosmicapps.valueline.task;

import com.cosmicapps.valueline.aws.textract.AnalyzedDocument;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.auth.credentials.internal.ProfileCredentialsUtils;
import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.textract.TextractClient;
import software.amazon.awssdk.services.textract.model.AnalyzeDocumentRequest;
import software.amazon.awssdk.services.textract.model.AnalyzeDocumentResponse;
import software.amazon.awssdk.services.textract.model.Document;
import software.amazon.awssdk.services.textract.model.FeatureType;

@Slf4j
@NoArgsConstructor
@AllArgsConstructor
public class DocumentAnalyzerAws {

  private Region region = Region.US_WEST_1;

  public AnalyzedDocument analyze(byte[] data) {

    log.trace("reading image data");
    SdkBytes sourceBytes = SdkBytes.fromByteArray(data);

    // Get the input Document object as bytes
    Document myDoc = Document.builder()
        .bytes(sourceBytes)
        .build();

    List<FeatureType> featureTypes = new ArrayList<>();
    featureTypes.add(FeatureType.TABLES);

    AnalyzeDocumentRequest analyzeDocumentRequest = AnalyzeDocumentRequest.builder()
        .featureTypes(featureTypes)
        .document(myDoc)
        .build();

    log.info("analyzing image data...");
    TextractClient textractClient = TextractClient.builder()
        .credentialsProvider(ProfileCredentialsProvider.create("personal"))
        .region(region)
        .build();

    AnalyzeDocumentResponse analyzeDocument;

    try {

      analyzeDocument = textractClient.analyzeDocument(analyzeDocumentRequest);
    } finally {
      log.trace("closing aws client...");
      textractClient.close();
    }

    return new AnalyzedDocument(analyzeDocument.blocks());
  }
}
